package com.cppba.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class ConsumerMessageListener implements AcknowledgingMessageListener<String,String> {

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        System.out.println(String.format("Key:%s value:%s", consumerRecord.key(), consumerRecord.value()));
        // 最后 调用acknowledgment的ack方法，提交offset
        acknowledgment.acknowledge();
    }
}
