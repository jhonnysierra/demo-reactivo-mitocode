package com.jhonny.reactive.demoreactivomitocode.repository;

import com.jhonny.reactive.demoreactivomitocode.model.Persona;
import com.jhonny.reactive.demoreactivomitocode.model.PersonaDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonaRepository {
    Mono<Persona> registrar(Persona persona);
    Mono<Persona> modificar(Persona persona);
    Flux<Persona> listar();
    Mono<Persona> listarPorId(Integer id);
    Mono<Void> eliminar(Integer id);
}
