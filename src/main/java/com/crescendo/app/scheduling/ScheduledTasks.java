package com.crescendo.app.scheduling;

import com.crescendo.app.filldatabase.FillLocalDB;
import com.crescendo.app.smartschoolsync.SmartschoolSync;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.app.models.Statistic;
import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.crescendo.sql.app.repositories.StatisticRepository;
import com.crescendo.sql.utils.Schoolyear;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Scheduling van de volledige sync
 * @author Groep5
 */
@Component
public class ScheduledTasks {

    @Autowired
    private FillLocalDB fillLocalDB;
    @Autowired
    private SmartschoolSync smartschoolSync;
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private InformatService informatService;
    @Autowired
    private CheckboxRepository checkboxRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private LocalDateTime now;

    /**
     * Deze methode bevat de cronjob om elke nacht opnieuw uitgevoerd te worden.<br>
     * Deze methode spreekt alle onderliggen methodes aan om de volledige sync uit te voeren<br>
     * Deze methode gaat ook verschillende statistieken naar de databank pushen
     */
    //@Scheduled(cron="0 1 1 * * *", zone="Europe/Brussels")
    public void fullSync() {
        if (checkboxRepository.findOne(1).isCheckboxState()) {
            now = LocalDateTime.now();

            logger.info("De sync start");
            long startTime = System.nanoTime();
            try {
                fillLocalDB.fillDb();
                smartschoolSync.ssSync();
            } catch (IOException e) {
                e.getStackTrace();
                logger.error("Error bij het uitvoeren van de sync");
            } catch (MessagingException e) {
                logger.error("Error bij het verzenden van een mail tijdens de sync");
            }
            logger.info("De sync is voltooid");
            Statistic statistic = statisticRepository.findOne(1);
            statistic.setAantal(informatService.getTotaalAantalServerErrors());
            statisticRepository.save(statistic);
            informatService.setTotaalAantalServerErrors(0L);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; //milliseconds
            Statistic statisticDuration = statisticRepository.findOne(3);
            statisticDuration.setAantal(duration);
            statisticRepository.save(statisticDuration);
            Statistic statisticLastSync = statisticRepository.findOne(4);
            statisticLastSync.setError(String.valueOf(now));
            statisticRepository.save(statisticLastSync);
        }
    }

}
