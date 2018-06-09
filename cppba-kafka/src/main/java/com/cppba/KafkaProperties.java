package com.cppba;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String bootstrapServers;

    private String sslTruststoreLocation;

    private String javaSecurityAuthLoginConfig;

    private String groupId;

    private String topic;
}
