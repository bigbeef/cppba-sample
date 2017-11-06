package com.cppba.rabbitmq;

import com.cppba.bean.MessagePayload;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author winfed
 * @create 2017-11-06 11:04
 */
@Component
public class RabbitMqProvider {
    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMqProvider(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }

    public void send(String key, String message) {
        MessagePayload messagePlayLoad = new MessagePayload();
        messagePlayLoad.setId(UUID.randomUUID().toString());
        messagePlayLoad.setMessage(message);
        amqpTemplate.convertAndSend(key, messagePlayLoad);
    }
}
