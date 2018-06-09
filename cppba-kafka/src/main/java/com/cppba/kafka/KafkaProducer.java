package com.cppba.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private KafkaTemplate kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message){
        kafkaTemplate.sendDefault(message);
    }
}
