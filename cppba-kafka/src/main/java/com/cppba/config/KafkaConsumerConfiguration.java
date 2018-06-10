package com.cppba.config;

import com.cppba.listener.ConsumerMessageListener;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.Properties;

/**
 * @author winfed
 * @create 2018-06-09 14:30
 */
@Configuration
public class KafkaConsumerConfiguration extends KafkaConfiguration {

    public Properties getKafkaConsumerProperties() {
        //设置sasl文件的路径
        configureSasl();

        Properties props = new Properties();
        //设置接入点，请通过控制台获取对应Topic的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        //设置SSL根证书的路径，请记得将XXX修改为自己的路径
        //与sasl路径类似，该文件也不能被打包到jar中
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getSslTruststoreLocation());
        //根证书store的密码，保持不变
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
        //接入协议，目前支持使用SASL_SSL协议接入
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        //SASL鉴权方式，保持不变
        props.put(SaslConfigs.SASL_MECHANISM, "ONS");
        //当前消费实例所属的消费组，请在控制台申请之后填写
        //属于同一个组的消费实例，会负载消费消息
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        //两次poll之间的最大允许间隔
        //请不要改得太大，服务器会掐掉空闲连接，不要超过30000
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 25000);
        //消息的反序列化方式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }

    @Bean(initMethod = "doStart")
    public KafkaMessageListenerContainer getKafkaMessageListenerContainer(){
        return new KafkaMessageListenerContainer(getKafkaConsumerFactory(), getContainerProperties());
    }

    private ConsumerFactory getKafkaConsumerFactory(){
        return new DefaultKafkaConsumerFactory(getKafkaConsumerProperties());
    }

    private ContainerProperties getContainerProperties(){
        ContainerProperties containerProperties = new ContainerProperties(kafkaProperties.getTopic());
        containerProperties.setMessageListener(new ConsumerMessageListener());
        return containerProperties;
    }

}
