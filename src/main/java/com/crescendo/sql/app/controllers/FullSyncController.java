package com.crescendo.sql.app.controllers;

import com.crescendo.app.scheduling.ScheduledTasks;
import com.crescendo.sql.app.models.Statistic;
import com.crescendo.sql.app.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** FullSync restcontroller
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
public class FullSyncController {

    @Autowired
    private ScheduledTasks scheduledTasks;
    @Autowired
    private StatisticRepository statisticRepository;

    /**
     * Api endpoint die de full sync zal uitvoeren en de duration ineens meegeeft
     * @return de duration van de fullsync methode + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/fullsync")
    public ResponseEntity<Long> doFullSync() {
        scheduledTasks.fullSync();
        Statistic statistic = statisticRepository.findOne(3);
        Long duration = statistic.getAantal();
        return new ResponseEntity<Long>(duration,HttpStatus.OK);
    }
}
