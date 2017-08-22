package com.cppba.controller;

import com.cppba.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @Autowired
    private StartService startService;

    @RequestMapping("/start")
    public String start() {
        Integer count = 10;
        for (Integer i = 1; i <= count; i++) {
            startService.taskSayHi(i);
            startService.taskSayHello(i);
        }
        return "success";
    }
}
