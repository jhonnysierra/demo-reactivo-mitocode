package com.jhonny.reactive.demoreactivomitocode.pruebascurso;

import com.jhonny.reactive.demoreactivomitocode.model.Persona;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebasCurso {
    private static final Logger log = LoggerFactory.getLogger(PruebasCurso.class);

    private final Flux<Persona> personasFlux;

    public PruebasCurso() {
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(Persona.builder()
                .idPersona(1)
                .nombre("Jhonny").build());
        personas1.add(Persona.builder()
                .idPersona(2)
                .nombre("Juan").build());

        personasFlux = Flux.fromIterable(personas1);
    }

    public void combinaciones() {
        log.info("OPERADORES DE COMBINACIÓN");

        List<Persona> personas1 = new ArrayList<>();
        personas1.add(Persona.builder()
                .idPersona(1)
                .nombre("Jhonny").build());
        personas1.add(Persona.builder()
                .idPersona(2)
                .nombre("Juan").build());

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(Persona.builder()
                .idPersona(3)
                .nombre("Jhon").build());
        personas2.add(Persona.builder()
                .idPersona(4)
                .nombre("Alejo").build());

        Flux<Persona> personaFlux1 = Flux.fromIterable(personas1);
        Flux<Persona> personaFlux2 = Flux.fromIterable(personas2);

        log.info("Combinaciones con flux");
        Flux.zip(personaFlux1, personaFlux2)
                .subscribe(x -> {
                    log.info(String.format("Tupla 1 %s", x.getT1().toString()));
                    log.info(String.format("Tupla 2 %s", x.getT2().toString()));
                });


        Flux<String> fnameFlux = Flux.just("Ramesh", "Amit", "Vijay");
        Flux<String> lnameFlux = Flux.just("Sharma", "Kumar", "Lamba");
        Flux<String> deptFlux = Flux.just("Admin", "IT", "Acc");

        Flux<User> userFlux = Flux.zip(fnameFlux, lnameFlux, deptFlux)
                .flatMap(dFlux ->
                        Flux.just(new User(dFlux.getT1(), dFlux.getT2(), dFlux.getT3())));

        userFlux.subscribe(System.out::println);

        log.info("Combinaciones con Mono");
        // Ejemplos de combinaciones con Mono

        Mono<Persona> monoPersona1 = Mono.just(Persona.builder()
                .idPersona(5)
                .nombre("Nestor").build());
        Mono<Persona> monoPersona2 = Mono.just(Persona.builder()
                .idPersona(6)
                .nombre("Laura").build());

        Mono.zip(monoPersona1, monoPersona2)
                .subscribe(x -> {
                    log.info(x.getT1().toString());
                    log.info(x.getT2().toString());
                });
        log.info("-------------------------");
    }

    public void convertirFluxMonoFlux() {
        /*
         * Al convertir de Flux a Mono se produce un unico elemento que contiene
         * todos los elementos de la lista en un aray
         */
        log.info("Convertir mono a flux");
        personasFlux
                .subscribe(x -> log.info(x.toString()));
        log.info("-------------------------");

        log.info("Convertir Flux a Mono");
        Mono<List<Persona>> monoPersonaList = personasFlux.collectList();
        monoPersonaList
                .subscribe(x -> {
                            log.info(String.format("Flux a Mono: %s", x.toString()));
                            log.info(String.format("Flux a Mono: Tamaño %s", x.size()));
                        }
                );

        log.info("Convertir lista de lista en flujo a una sola lista");
        Flux<List<Elemento>> flux = Flux.just(
                Arrays.asList(new Elemento("elem1"), new Elemento("elem2")),
                Arrays.asList(new Elemento("elem3"), new Elemento("elem4")),
                Arrays.asList(new Elemento("elem5"), new Elemento("elem6"))
        );

        Flux<Elemento> resultadoFlux = flux.doOnNext(elementos -> log.info(String.format("Elemento inicial flux: %s", elementos.toString())))
                .flatMap(Flux::fromIterable)
                .doOnNext(elemento -> log.info(String.format("Elemento final flux: %s", elemento.toString())));

        List<Elemento> resultadoLista = resultadoFlux.collectList().block();

        assert resultadoLista != null;

        log.info("Elemento final flux: {}", resultadoLista);

    }

    public void opeTransformacion() {
        log.info("Operadores de transformación");


        personasFlux.flatMap(x -> {
            x.setIdPersona(x.getIdPersona() + 1);
            x.setNombre(x.getNombre() + " Sierra");
            return Flux.just(x);
        }).subscribe(x -> log.info(x.toString()));

        personasFlux
                .map(x -> x.getIdPersona() + 1)
                .subscribe(x -> log.info(x.toString()));

        log.info("Flux cargado en el void");
        personasFlux.subscribe(x -> log.info(x.toString()));

        //Operaciones con Mono
        log.info("Operaciones de transformacón con Mono Map");

        Mono.just(Persona.builder()
                        .idPersona(7)
                        .nombre("Jhon").build())
                .map(persona -> persona.getNombre() + " Map")
                .subscribe(log::info);

        log.info("Operaciones de transformación con Mono FlatMap");

        Mono.just(Persona.builder()
                        .idPersona(7)
                        .nombre("Jhon").build())
                .flatMap(PruebasCurso::getPersonaMono)
                .subscribe(result -> log.info(result.toString()));
    }

    @NotNull
    private static Mono<Persona> getPersonaMono(Persona persona) {
        persona.setNombre(persona.getNombre() + " FlatMap");
        return Mono.just(persona);
    }
}
