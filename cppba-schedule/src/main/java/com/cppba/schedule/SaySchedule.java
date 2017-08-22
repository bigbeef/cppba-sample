package com.cppba.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SaySchedule {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0/2 * * * * ?")
    public void sayHi() {
        System.out.println("hi!    time is :" + df.format(new Date()));
    }

    @Scheduled(cron = "1/2 * * * * ?")
    public void sayHello() {
        System.out.println("hello! time is :" + df.format(new Date()));
    }
}
