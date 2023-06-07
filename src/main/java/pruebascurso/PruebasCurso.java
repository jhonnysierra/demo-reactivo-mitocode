package pruebascurso;

import model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class PruebasCurso {
    private static final Logger log = LoggerFactory.getLogger(PruebasCurso.class);

    public void combinaciones() {
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


        // Ejemplos de combinaciones con Mono

        Mono<Persona> monoPersona1 = Mono.just(Persona.builder()
                .idPersona(5)
                .nombre("Nestor").build());
        Mono<Persona> monoPersona2 = Mono.just(Persona.builder()
                .idPersona(6)
                .nombre("Laura").build());

        Mono.zip(monoPersona1, monoPersona2)
                .subscribe(x-> log.info(x.getT1().toString()));
    }

}
