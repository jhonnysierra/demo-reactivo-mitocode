package com.jhonny.reactive.demoreactivomitocode.controller;

import com.jhonny.reactive.demoreactivomitocode.model.PersonaDTO;
import com.jhonny.reactive.demoreactivomitocode.util.ConverterPersona;
import lombok.RequiredArgsConstructor;
import com.jhonny.reactive.demoreactivomitocode.model.Persona;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import com.jhonny.reactive.demoreactivomitocode.repository.PersonaRepository;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class PersonaHandler {

    private final PersonaRepository personaRepository;
    private final ConverterPersona converterPersona;

    public Mono<ServerResponse> listenPOSTRegistrarPersona(ServerRequest serverRequest) {

        return ServerResponse
                .created(URI.create("/api/mitocode/registrar/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaRepository.registrar(Persona.builder()
                        .idPersona(1)
                        .nombre("Jhonny Sierra")
                        .build()), Persona.class);
    }

    public Mono<ServerResponse> listenPATCHModificarPersona(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(PersonaDTO.class)
                .map(converterPersona::convertirPersonaDtoAPersona)
                .flatMap(persona -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(personaRepository.modificar(persona), Persona.class));
    }

    public Mono<ServerResponse> listenGETistarPersonas(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaRepository.listar(), Persona.class);
    }

    public Mono<ServerResponse> listenGETListarPersonaPorId(ServerRequest serverRequest) {
        Integer id = Integer.parseInt(serverRequest.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaRepository.listarPorId(id), Persona.class);
    }

    public Mono<ServerResponse> listenDELETEEliminarPersona(ServerRequest serverRequest) {
        Integer id = Integer.parseInt(serverRequest.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaRepository.eliminar(id), Persona.class);
    }
}