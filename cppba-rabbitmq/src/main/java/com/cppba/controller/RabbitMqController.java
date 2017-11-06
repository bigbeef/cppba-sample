package com.cppba.controller;

import com.cppba.rabbitmq.RabbitMqProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winfed
 * @create 2017-11-06 11:24
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private RabbitMqProvider amqpProvider;

    @RequestMapping("/send")
    public String send(String key, String message) {
        amqpProvider.send(key, message);
        return "success";
    }
}
