package com.ia.dfms.configuration;

import org.axonframework.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AxonConfiguration {
    
    @Bean
    SpringAMQPMessageSource springAMQPMessageSource(AMQPMessageConverter amqpMessageConverter) {
        return new SpringAMQPMessageSource(amqpMessageConverter) {
            @Override
            @RabbitListener(queues = "${external.amqp.queue}")
            public void onMessage(Message message, Channel channel) {
                log.info("Received message: {}", message);
                log.info("Channel {}", channel);
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    @Scope(value = "prototype")
    public AMQPMessageConverter amqpMessageConverter(Serializer serializer) {
        return new DefaultAMQPMessageConverter(serializer);
    }

}
