package com.duoc.inscripciones.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.duoc.inscripciones.rabbitmq.config.Constants.*;

@Service
public class ProductorService {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(QUEUE, message);
    }


}
