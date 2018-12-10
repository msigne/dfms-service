package com.ia.dfms.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "external")
@Data
@Component
public class ExternalProperties {
    private Map<String, Object> amqp;
    private List<String> processors;
    
    public String getQueueName() {
        return amqp.get("queue").toString();
    }
}
