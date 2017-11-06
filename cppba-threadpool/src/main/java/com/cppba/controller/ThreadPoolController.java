package com.cppba.controller;

import com.cppba.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winfed
 * @create 2017-11-06 15:20
 */
@RestController
@RequestMapping("/thread/pool")
public class ThreadPoolController {

    private final ThreadPoolService threadPoolService;

    @Autowired
    public ThreadPoolController(ThreadPoolService threadPoolService) {
        this.threadPoolService = threadPoolService;
    }

    @RequestMapping("/do/test")
    public String doTest(){
        return threadPoolService.doTest();
    }
}
