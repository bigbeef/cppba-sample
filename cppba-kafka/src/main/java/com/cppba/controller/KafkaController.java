package com.cppba.controller;

import com.cppba.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {


    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/send")
    public String send(String message) {
        kafkaProducer.send(message);
        return "success";
    }
}
