package com.example.simplespringboot.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedule {
    /*
         1 => second
         2 => minute
         3 => hour
         4 => day
         5 => mouth
         6 => year
     */
    @Scheduled(cron = "0 * * * * *")
    public void testEveryMinute(){
       log.info("testEveryMinute");
    }

    // EveryDay at 00:00
    @Scheduled(cron = "0 0 0 * * *")
    public void testEveryMidNight(){
        log.info("testEveryMidNight");
    }

    // EveryDay at 09:00
    @Scheduled(cron = "0 0 9 * * *")
    public void testEverydayNineAM(){
        log.info("testEverydayNineAM");
    }

    // EveryDay at 10:50
    @Scheduled(cron = "0 50 10 * * *")
    public void testEverydayTenAM(){
        log.info("testEverydayTenAM");
    }
}
