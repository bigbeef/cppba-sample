package com.cppba.rabbitmq;

import com.cppba.bean.MessagePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author winfed
 * @create 2017-11-06 11:04
 */
@Component
@Slf4j
public class RabbitMqConsumer {

    @RabbitListener(queues = "say", containerFactory = "rabbitListenerContainerFactory")
    public void onMessageSay1(@Payload MessagePayload messagePayload) {
        try{
            log.warn("消费者:{},接收到消息:{},{}","say1",messagePayload.getId(),messagePayload.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "say" , containerFactory = "rabbitListenerContainerFactory")
    public void onMessageSay2(@Payload MessagePayload messagePlayLoad) {
        try {
            log.warn("消费者:{},接收到消息:{},{}", "say1", messagePlayLoad.getId(), messagePlayLoad.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "print" , containerFactory = "rabbitListenerContainerFactory")
    public void onMessagePrint(@Payload MessagePayload messagePlayLoad) {
        try{
            log.warn("消费者:{},接收到消息:{},{}","say1",messagePlayLoad.getId(), messagePlayLoad.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
