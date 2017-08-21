package com.cppba.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StartService {

    @Async
    public void taskSayHi(Integer i) {
        System.out.println("No." + i + ",hi!");
    }

    @Async
    public void taskSayHello(Integer i) {
        System.out.println("No." + i + ",hello!");
    }
}
