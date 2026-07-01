package com.duoc.inscripciones.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.inscripciones.dto.InscripcionResponse;
import com.duoc.inscripciones.model.Inscripcion;
import com.duoc.inscripciones.service.InscripcionService;

import tools.jackson.databind.ObjectMapper;

import static com.duoc.inscripciones.rabbitmq.config.Constants.*;

@Service
public class ConsumidorService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InscripcionService inscripcionService;

    @RabbitListener(queues = QUEUE)
    public void receiveMessage(String message) {
        Inscripcion inscripcion = objectMapper.readValue(message, Inscripcion.class);
        InscripcionResponse creado = inscripcionService.registrarInscripcion(inscripcion);
        System.out.println("Se ha guardado en la base de datos la inscripcion con datos:\n" + creado);
    }

}
