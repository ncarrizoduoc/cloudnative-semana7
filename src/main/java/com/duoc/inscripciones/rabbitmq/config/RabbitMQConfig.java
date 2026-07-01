package com.duoc.inscripciones.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.duoc.inscripciones.rabbitmq.config.Constants.*;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myQueue(){
        return new Queue(QUEUE, true);
    }

}
