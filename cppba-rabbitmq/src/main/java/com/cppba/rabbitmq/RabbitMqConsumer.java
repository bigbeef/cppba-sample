package com.cppba.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author winfed
 * @create 2017-11-06 11:04
 */
@Component
@Slf4j
public class RabbitMqConsumer {

    @RabbitListener(queues = "say")
    public void onMessageSay1(Message message) {
        try{
            log.warn("消费者:{},接收到消息:{}","say1",message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "say")
    public void onMessageSay2(Message message) {
        try{
            log.warn("消费者:{},接收到消息:{}","say2",message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "print")
    public void onMessagePrint(Message message) {
        try{
            log.warn("消费者:{},接收到消息:{}","print",message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
