package com.jhonny.reactive.demoreactivomitocode.service;

import com.jhonny.reactive.demoreactivomitocode.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.jhonny.reactive.demoreactivomitocode.repository.PersonaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaRepository {

    private static final Logger log = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Override
    public Mono<Persona> registrar(Persona persona) {
        log.info("SAVE");
        //Este error sale porque se imprimi sin hacer alguna validacion de la persona
        log.info(String.format("Se ha guardado el registro %s", persona.toString()));

        return Mono.just(persona);
    }

    @Override
    public Mono<Persona> modificar(Persona persona) {
        log.info("UPDATE");

        return Mono.just(persona)
                .map(per -> {
                    per.setNombre(persona.getNombre() + " Modificado");
                    log.info(String.format("Se ha actualizado el registry %s", per.toString()));
                    return per;
                });
    }

    @Override
    public Flux<Persona> listar() {
        log.info("LISTAR");
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(Persona.builder()
                .idPersona(1)
                .nombre("Jhonny").build());
        personas1.add(Persona.builder()
                .idPersona(2)
                .nombre("Juan").build());

        return Flux.fromIterable(personas1);
    }

    @Override
    public Mono<Persona> listarPorId(Integer id) {
        return Mono.just(
                Persona.builder()
                        .idPersona(id)
                        .nombre("Jhonny Sierra")
                        .build())
                .doOnNext(persona -> log.info(String.format("LISTAR POR ID: %d", persona.getIdPersona())));
    }

    @Override
    public Mono<Void> eliminar(Integer id) {
        log.info("ELIMINAR");
        log.info(String.format("Se ha eliminado el registro %d", id));
        return Mono.empty();
    }
}
