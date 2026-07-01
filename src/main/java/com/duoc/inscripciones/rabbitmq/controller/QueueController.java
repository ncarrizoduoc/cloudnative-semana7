package com.duoc.inscripciones.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.inscripciones.dto.InscripcionRequest;
import com.duoc.inscripciones.mapper.InscripcionRequestMapper;
import com.duoc.inscripciones.mapper.InscripcionResponseMapper;
import com.duoc.inscripciones.model.Inscripcion;
import com.duoc.inscripciones.rabbitmq.service.ProductorService;

import jakarta.validation.Valid;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private ProductorService productorService;

    @Autowired
    private InscripcionRequestMapper requestMapper;

    @Autowired
    private InscripcionResponseMapper responseMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/inscripcion/new")
    public String enviarMensajeInscripcion(@Valid @RequestBody InscripcionRequest request){
        Inscripcion inscripcion = requestMapper.toInscripcion(request);
        String mensaje = objectMapper.writeValueAsString(inscripcion);
        productorService.sendMessage(mensaje);
        return "Mensaje enviado: " + mensaje;

    }

}
