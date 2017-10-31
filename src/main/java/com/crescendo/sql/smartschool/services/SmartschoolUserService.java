package com.crescendo.sql.smartschool.services;

import com.crescendo.smartschool.models.*;
import com.crescendo.smartschool.services.SmartschoolService;
import com.crescendo.sql.app.models.Statistic;
import com.crescendo.sql.app.repositories.StatisticRepository;
import com.crescendo.sql.informat.repositories.SmartschoolStudentRepository;
import com.crescendo.sql.smartschool.models.Users;

import com.crescendo.sql.smartschool.repositories.UserRepository;
import com.crescendo.sql.utils.Suffix;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/** SmartschoolUserService
 * @author Groep 5
 */
@Service
public class SmartschoolUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmartschoolService smartschoolService;

    @Autowired
    private SmartschoolStudentRepository smartschoolStudentRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    private Suffix suffix = new Suffix();
    private String SUFFIX = suffix.getSUFFIX();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<String> groupIdList = new ArrayList<>();

    public SmartschoolUserService() throws IOException {
    }


    /*
    *
    * ONDERSTAANDE METHODE WORDT GEBRUIKT TIJDENS DEVELOPMENT OM DE ECHTE SITUATE NA TE BOOTSEN
    * SPREEKT ENKEL SMARTSCHOOL OMGEVING VAN GROEP 5 AAN
    *
     */
    /**
     * METHODE WORDT GEBRUIKT TIJDENS DEVELOPMENT OM DE ECHTE SITUATE NA TE BOOTSEN
     * SPREEKT ENKEL SMARTSCHOOL OMGEVING VAN GROEP 5 AAN
     * @return Een lijst van alle users dat in smartschool zitten van groep 5
     * @throws IOException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public List<User> getAlleUsersGroep5() throws IOException, ConfigurationException {
        List<User> allUsers = new ArrayList<>();
        allUsers.clear();
        AccountList accountPerGroup = smartschoolService.getAllAccounts("001", "1");
        try{
            for (Account account : accountPerGroup.getAccount()){
                if (account.getInternnummer().contains(SUFFIX))
                allUsers.add(smartschoolService.getUserDetailsById(account.getInternnummer()));
                Long aantal = (long) allUsers.size();
                Statistic statistic = statisticRepository.findOne(2);
                statistic.setAantal(aantal);
                statisticRepository.save(statistic);
            }
        } catch (NullPointerException ex){
            logger.info("Geen studenten in groep 2017-2018_GR55");
        }
        return  allUsers;
    }

    /**
     * Deze methode zal all user uit smartschool ophalen, actief en niet actief.<br>
     * Uit all groepen
     * @return Een lijst van users die in smartschool zitten
     * @throws IOException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     *
     */
    public List<User> getAllUsers () throws IOException, ConfigurationException {
        List<User> allUsers = new ArrayList<>();
        allUsers.clear();
        List<String> codes = getGroupIdList();

        for (String s: codes){
            try{
                AccountList accountPerGroup = smartschoolService.getAllAccounts(s,"0");
                for (Account account : accountPerGroup.getAccount()){
                    allUsers.add(smartschoolService.getUserDetailsById(account.getInternnummer()));
                }}
            catch(Exception e){
                System.out.println("Er zitten in de groep met code " + s + " geen gebruikers");
            }
        }

        return allUsers;
    }


    /**
     * Methode die een lijst zal aanmaken van alle codes van iedere groep in smartschool
     * @return Lijst van strings met all groepcodes
     * @throws RemoteException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    private List<String> getGroupIdList() throws RemoteException, ConfigurationException {

        GroupList allGroupAndClasses = smartschoolService.getAllGroupsAndClasses();

        for (Group group : allGroupAndClasses.getGroup()) {
            if(!group.getCode().equals("")) {
                groupIdList.add(group.getCode());
            } else {
                logger.info("group or class: " + group.getName() + " has empty code");
            }
        }
        return groupIdList;
    }

    /**
     * Methode om tabel in db te vullen met alle user uit smartschool.<br>
     * Maakt gebruik van {@link #getAllUsers()}
     * @throws IOException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    @Transactional
    public void databaseFillSmartschoolUsers() throws IOException, ConfigurationException {
        userRepository.deleteAll();
        List<User> accountList = getAlleUsersGroep5();
        Users users;

        for(User user : accountList){
            users = new Users(user.getVoornaam(),user.getNaam(), user.getGebruikersnaam(), user.getGeslacht(), user.getGeboortedatum(), user.getEmailadres(), user.getMobielnummer(), user.getTelefoonnummer(), user.getStamboeknummer(),
                    user.getStraat(), user.getHuisnummer(), user.getLand(), user.getWoonplaats().toLowerCase(), user.getStatus(), user.getInternnummer());
            userRepository.save(users);
        }
    }

    /**
     * Methode die de syncronisatie op databank niveau zal uitvoeren<br>
     * Controleert 2 tabellen in de databank: tabel 1 die alle smartschool users bevat en tabel 2 die alle studenten bevat die smartschool nodig hebben<br>
     * Update de status die een user in informat nodig heeft.
     * @throws IOException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     */
    @Transactional
    public void accountSync() throws IOException {
        List<Users> existingAccounts = userRepository.findAll();
        Users userUpdate;

        for(Users user: existingAccounts){

            if(smartschoolStudentRepository.exists(user.getInternnummer().replace(SUFFIX, " "))){
                smartschoolStudentRepository.delete(user.getInternnummer().replace(SUFFIX, " "));
                //MOEST HIJ IN SMARTSCHOOL ZITTEN MAAR OP INACTIEF, ZET DAN OP ACTIEF
                userUpdate = userRepository.findByInternnummer(user.getInternnummer());
                userUpdate.setStatus("actief");
                userRepository.save(userUpdate);
            }
            else
            {
                user.setStatus("inactief");
                userRepository.save(user);
            }
        }
    }
}
