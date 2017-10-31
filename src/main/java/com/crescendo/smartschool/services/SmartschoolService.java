package com.crescendo.smartschool.services;

import com.crescendo.smartschool.services.Webservices.V3.V3Port;
import com.crescendo.smartschool.services.Webservices.V3.V3PortProxy;
import com.crescendo.smartschool.models.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.crescendo.smartschool.services.utils.Converting.base64ToJson;


/** smartschool api aanroepen<br>
 * Sommige jsons zijn buggy omdat ze teruggegeven worden als een array van objecten,<br>
 * Als er echter 0 of 1 object aanwezig is in de response kon deze niet volgens jackson gemapped worden, daarom moesten we enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY) gebruiken
 * @author Groep 5
 */
@Service
@SuppressWarnings("Duplicates")
public class SmartschoolService {


    private PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");
    private final String ACCESCODE = conf.getProperty("SSKEY").toString();
    private V3Port proxy = new V3PortProxy();
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    private static final int MAKE_ARRAY_CHAR_AT = 11;
    private String input = "";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public SmartschoolService() throws IOException, ConfigurationException {
    }


    /**
     * Met deze methode kan u voornaam, naam, gebruikersnaam,<br>
     * intern nummer en het klasnummer van de officiële klas ophalen van gebruikers in een bepaalde groep<br>
     * Converteert de base64 response naar json<br>
     * map de json op een account object
     * @param code unieke groep of klascode
     * @param recursive subgroepen ophalen? 1 is ja, 0 is nee.
     * @return mapped acountList objecten
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public AccountList getAllAccounts(String code, String recursive) throws IOException, ConfigurationException {
        input = proxy.getAllAccounts(ACCESCODE, code, recursive).toString();

        AccountList list = new AccountList();
        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);;

        String jsonData = base64ToJson(input);
        String testIsJsonArray = "[";
        Boolean arrayTest = jsonData.contains(testIsJsonArray);
        String removeBegin;
        StringBuilder makeArray;
        String removeEnd;

        if (arrayTest) {
            removeBegin = jsonData.replace("{\"accounts\":", "");
            removeEnd = removeBegin.substring(0, removeBegin.length() - 1);
        } else{
            if (jsonData.equals("{\"accounts\":\"\"}")) {
                return list;
            } else {
                removeBegin = jsonData.replace("{\"accounts\":", "");
                makeArray = new StringBuilder(removeBegin);
                int voorlaatsteChar = makeArray.length() - 2;
                makeArray = makeArray.insert(voorlaatsteChar, "]");
                makeArray = makeArray.insert(MAKE_ARRAY_CHAR_AT, "[");
                removeEnd = makeArray.substring(0, makeArray.length() - 1);
            }
        }

        list = mapper.readValue(removeEnd, AccountList.class);

        return list;
    }

    public Account getOneAccount(String code, String recursive) throws RemoteException, ConfigurationException {


        input = proxy.getAllAccounts(ACCESCODE, code, recursive).toString();
        String jsonData = base64ToJson(input);
        String removeBegin = jsonData.replace("{\"accounts\":{\"account\":", "");
        String removeEnd = removeBegin.substring(0, removeBegin.length() - 1);

        Account account = new Account();
        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);;

        try {
            account = mapper.readValue(removeEnd, Account.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return account;
    }

    /**
     *  Deze methode vraagt alle groepen en klassen van het Smartschoolplatform op.  <br>
     *  Converteert the base64 list naar Json <br>
     *  Mapped de json op groep opbjecten
     * @return Returns mapped Grouplist
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public GroupList getAllGroupsAndClasses() throws RemoteException, ConfigurationException {

        String input = proxy.getAllGroupsAndClasses(ACCESCODE).toString();
        String jsonData = base64ToJson(input);
        String removeBegin = jsonData.replace("{\"groups\":{\"group\":{\"untis\":\"\",\"instituteNumber\":\"\",\"code\":\"\",\"visible\":\"1\",\"children\":", "");
        String removeEnd = removeBegin.replace(",\"name\":\"Iedereen\",\"adminNumber\":\"\",\"coAccountLabel\":\"\",\"type\":\"G\",\"desc\":\"Iedereen\",\"isOfficial\":\"0\"}}}", "");

        GroupList groeplist = new GroupList();

        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            groeplist = mapper.readValue(removeEnd, GroupList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groeplist;

    }

    /**
     * Met deze methode kan u alle profielvelden (behalve wachtwoorden en profielfoto) en de groepslidmaatschappen ophalen van een specifieke gebruiker (hoofd- en co-accounts <br>
     * Mapped de json string op een user object
     * @param id unieke identifier van gebruiker (intern nummer)
     * @return Returns mapped User object
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public User getUserDetailsById(String id) throws RemoteException, ConfigurationException {
        String jsonData = proxy.getUserDetailsByNumber(ACCESCODE, id).toString();

        User user = new User();

        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);;
        try {
            user = mapper.readValue(jsonData, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;

    }

    /**
     * Deze methode vraagt alle groepen en klassen van het Smartschoolplatform op  <br>
     * knip het begin en einde van de json <br>
     * mapped de json op classlist objeten
     * @return Returns mapped ClassObject
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public ClassLists getClassList() throws RemoteException, ConfigurationException {

        String jsonData = proxy.getClassListJson(ACCESCODE).toString();
        String jsonDataGroup = "{\"classlist\":" + jsonData + " }";

        ClassLists list = new ClassLists();

        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);;
        try {
            list = mapper.readValue(jsonDataGroup, ClassLists.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Deze methode vraagt alle vakken van het Smartschoolplatform op <br>
     * Converteert de base64 list naar Json <br>
     * Mapped de json op groep opbjects
     * @return Returns mapped Courses
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public CourseList getCourses() throws RemoteException, ConfigurationException {

        String input = proxy.getCourses(ACCESCODE).toString();
        String jsonData = base64ToJson(input);
        String removeBegin = jsonData.replace("{\"courses\":", "");
        String removeEnd = removeBegin.substring(0, removeBegin.length() - 1);


        CourseList list = new CourseList();

        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);;
        try {
            list = mapper.readValue(removeEnd, CourseList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Met deze methode kan de status van een leerling gewijzigd worden
     * @param userId Uniek veld gebruiker
     * @param accountStatus mogelijkheden:
     *  - 'actief', 'active' of 'enabled' om het account op actief te zetten
     *  - 'inactief', 'inactive' of 'disabled' om het account uit te schakelen
     *  - 'administrative' of 'administratief' om van de account een administratieve account te maken.
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void setAccountStatus(String userId, String accountStatus) throws RemoteException, ConfigurationException {

        proxy.setAccountStatus(ACCESCODE, userId, accountStatus);
    }

    /**
     * Voegt een gebruiker toe of wijzigt een bestaande gebruiker.<br>
     * Als de saveUser methode wordt aangeroepen dan wordt het ingesteld uniek veld (userIdentifier) gecontroleerd.<br>
     * Indien er nog geen account bestaat met het ingesteld uniek veld dan zal er een nieuwe account worden toegevoegd.<br>
     * Bestaat er echter wel reeds een account met het ingesteld uniek veld dan worden de gegevens van deze account geüpdatet met de meegeleverde gegevens.
     * @param internnumber Intern nummer
     * @param username Gebruikersnaam
     * @param passwd1 Wachtwoord hoofdaccount
     * @param passwd2 Co-account wachtwoord (optioneel)
     * @param passwd3 Tweede Co-account wachtwoord (optioneel)
     * @param name Voornaam
     * @param surname Achternaam
     * @param extranames Extra voornamen (optioneel)
     * @param initials Initialen (optioneel)
     * @param sex Geslacht (optioneel)
     * @param birthday Geboortedatum (optioneel)
     * @param birthplace Geboorteplaats (optioneel)
     * @param birthcountry Geboorteland (optioneel)
     * @param address Adres (optioneel vorm straatnaam spatie huisnummer spatie bus patie busnummer)
     * @param postalcode Postcode (optioneel)
     * @param location Stad/Gemeente (optioneel)
     * @param country Land (optioneel)
     * @param email E-mailadres (optioneel)
     * @param mobilephone Mobiel nummer (optioneel)
     * @param homephone Telefoonnummer (optioneel)
     * @param fax Fax (optioneel)
     * @param prn TRijksregisternummer (optioneel)
     * @param stamboeknummer stamboeknummer (optioneel)
     * @param basisrol basisrol:  'leerling', 'leerkracht', 'directie' of 'andere'
     * @param untis Untisveld (optioneel)
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */

    public void saveUser(String internnumber, String username, String passwd1, String passwd2, String passwd3, String name, String surname, String extranames, String initials, String sex, String birthday, String birthplace, String birthcountry, String address, String postalcode, String location, String country, String email, String mobilephone, String homephone, String fax, String prn, String stamboeknummer, String basisrol, String untis) throws RemoteException, ConfigurationException {
        int passwordTeller = 0;
        String patternDigit = "^(?=.*[0-9])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$";
        String patternLower = "^(?=.*[a-z])$";
        String patterUpper = "^(?=.*[A-Z])$";
        String patternSpecial = "^(?=.*[@#$%^&+=_])$";

        proxy.saveUser(ACCESCODE, internnumber, username, passwd1, passwd2, passwd3, name, surname, extranames, initials, sex, birthday, birthplace, birthcountry, address, postalcode, location, country, email, mobilephone, homephone, fax, prn, stamboeknummer, basisrol, untis);
    }

    /**
     * Deze methode verwijdert de opgegeven gebruiker uit Smartschool.
     * @param userIdentifier uniek veld gebruiker (inter nummer)
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void delUser(String userIdentifier) throws RemoteException, ConfigurationException {


        System.out.println(now());
        proxy.delUser(ACCESCODE, userIdentifier, now());

    }

    /**
     * Met deze methode verplaatst u de gebruiker naar de (nieuwe) officiële klas. Een leerling kan maar aan één officiële klas gekoppeld worden.
     * @param userIdentifier Uniek veld gebruiker
     * @param classAndOrGroepCode lijst van unieke klas of groepscode
     * @param keepOld : ('1' = behoud bestaande lidmaatschappen en voeg nieuwe lidmaatschappen toe, '0' = verwijder oude lidmaatschappen en maak enkel enkel de nieuwe lidmaatschappen)
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void saveUserToClassesAndGroups(String userIdentifier, String classAndOrGroepCode, int keepOld) throws RemoteException, ConfigurationException {
        proxy.saveUserToClassesAndGroups(ACCESCODE, userIdentifier, classAndOrGroepCode, keepOld);
    }

    /** Met deze methode is het mogelijk een gebruiker uit een specifieke groep te verwijderen
     * @param userIdentifier uniek veld gebruiker
     * @param groepCode Tde unieke groep of klascode
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void removeUserFromGroup(String userIdentifier, String groepCode) throws RemoteException, ConfigurationException {
        proxy.removeUserFromGroup(ACCESCODE, userIdentifier, groepCode, now());
    }

    /**
     * Met deze methode is het mogelijk om bepaalde klassen toe te voegen aan een bepaald vak.
     * @param coursename Tde vaknaam
     * @param coursedesc de vakomschrijving
     * @param groupIds lijst met unieke klas of groepcode
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     *
     */
    public void addCourseStudents(String coursename, String coursedesc, String groupIds) throws RemoteException, ConfigurationException {
        proxy.addCourseStudents(ACCESCODE, coursename, coursedesc, groupIds);
    }

    /**
     * Met deze methode is het mogelijk om een vakken aan te maken in Smartschool
     * @param coursename de vaknaam
     * @param coursedesc de vakomschrijving
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void addCourse(String coursename, String coursedesc) throws RemoteException, ConfigurationException {
        proxy.addCourse(ACCESCODE, coursename, coursedesc);
    }

    /**
     * Met deze methode is het mogelijk om een vakleerkracht en co-leerkrachten toe te voegen aan een bepaald vak
     * @param coursename de vaknaam
     * @param coursedesc de vakomschrijving
     * @param internnummer de unieke interne nummer
     * @param userlist lijst met unieke veld gebruiker
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void addCourseTeacher(String coursename, String coursedesc, String internnummer, String userlist) throws RemoteException, ConfigurationException {
        proxy.addCourseTeacher(ACCESCODE, coursename, coursedesc, internnummer, userlist);
    }

    /**
     * Via deze methode kan u het internnummer van een bepaalde gebruiker vervangen.
     * @param oldInnum oud internummer
     * @param newInnum nieuw internummer
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void replaceInnummer(String oldInnum, String newInnum) throws RemoteException, ConfigurationException {
        proxy.replaceInum(ACCESCODE, oldInnum, newInnum);
    }

    /**
     * Met deze methode is het enerzijds mogelijk om klassen en officiële klassen aan te maken of te wijzigen in Smartschool. <br>
     * Het is evenwel niet mogelijk om de klasnaam van bestaande klassen en officiële klassen te wijzigen
     * @param name klasnaam
     * @param desc klasomschrijving
     * @param code unieke id van klas of group
     * @param parent unieke klas of groepcode van parentgroep (leeg of 0 voor hoogste niveau)
     * @param untis koppelingsveld voor agenda
     * @param instituteNumber Instellingsnummer (optioneel)
     * @param adminNumber Nummer van de administratieve groep (optioneel)
     * @param schoolYearDate Dit past het instellingsnummer en nummer van de administratieve groep aan voor het doorgegeven schooljaar
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void saveClass(String name, String desc, String code, String parent, String untis, String instituteNumber, String adminNumber, String schoolYearDate) throws RemoteException, ConfigurationException {
        proxy.saveClass(ACCESCODE, name, desc, code, parent, untis, instituteNumber, adminNumber, schoolYearDate);
    }

    /**
     * Met deze methode is het enerzijds mogelijk om groepen aan te maken of te wijzigen in Smartschool.<br>
     * Het is evenwel niet mogelijk om de groepsnaam van bestaande groepen te wijzigen<br>
     * Indien er nog geen groep voorkomt met de aangeleverde identificatie string ($name) zal Smartschool een nieuwe groep aanmaken.<br>
     * Bestaat er echter wel reeds een groep met de identificatie string dan zullen de bestaande parameters in Smartschool worden aangepast.
     * @param name groepsnaam
     * @param desc groepsomschrijving
     * @param code unieke id van klas of group
     * @param parent unieke klas of groepcode van parentgroep (leeg of 0 voor hoogste niveau)
     * @param untis koppelingsveld voor agenda
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void saveGroup(String name, String desc, String code, String parent, String untis) throws RemoteException, ConfigurationException {
        proxy.saveGroup(ACCESCODE,name,desc,code,parent,untis);
    }

    /**
     * Via deze methode update u een lijst van klassen.
     * @param serializedList een list van alle klassen
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void saveClassList(String serializedList) throws RemoteException, ConfigurationException {
        proxy.saveClassList(ACCESCODE,serializedList);
    }

    /**
     * Met deze methode kan de leerling worden uitgeschreven.
     * @param userIdentifier Uniek veld gebruiker
     * @throws RemoteException exception zal worden gethrowed als de eigenlijke call naar smartschool via proxy failt)
     * @throws ConfigurationException Wordt gethrowed als de config file niet geladen kan worden
     */
    public void unRegisterStudent(String userIdentifier) throws RemoteException, ConfigurationException {
        proxy.unregisterStudent(ACCESCODE,userIdentifier,now());
    }

    public String returnJsonErrorCodes() throws RemoteException, ConfigurationException {
        String error = proxy.returnJsonErrorCodes();
        return error;
    }

    /**
     * functie om de datum van vandaag te krijgen
     * @return Datum van vandaag
     *
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }


}



