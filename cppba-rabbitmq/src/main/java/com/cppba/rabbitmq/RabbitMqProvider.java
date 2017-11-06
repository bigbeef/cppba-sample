package com.cppba.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        amqpAdmin.declareQueue(new Queue("print"));
        amqpAdmin.declareQueue(new Queue("say"));
    }

    public void send(String key, String message) {
        amqpTemplate.convertAndSend(key, message);
    }
}
