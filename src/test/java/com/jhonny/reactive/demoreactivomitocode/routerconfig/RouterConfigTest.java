package com.jhonny.reactive.demoreactivomitocode.routerconfig;

import com.jhonny.reactive.demoreactivomitocode.controller.PersonaHandler;
import com.jhonny.reactive.demoreactivomitocode.model.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Usar la anotación @SpringBootTest no es necesario cuando se usa mock, si se usa implica que se tenga una instancia
 * real de la clase RouterConfig. Se se hace implica que estás ejecutando una prueba de integración
 * en lugar de una prueba unitaria
 */
class RouterConfigTest {

    @Mock
    private PersonaHandler personaHandler;

    @InjectMocks
    private RouterConfig routerConfig;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //routerConfig = new RouterConfig(personaHandler);
    }

    @Test
    void routesConfigTest() {
        Persona persona = Persona.builder()
                .idPersona(1)
                .nombre("Jhonny Sierra")
                .build();

        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerConfig.routes())
                .build();

        Mono<ServerResponse> responseMono = ServerResponse.ok().body(Mono.just(persona), Persona.class);

        Mockito.when(personaHandler.listenPOSTRegistrarPersona(Mockito.any(ServerRequest.class)))
                .thenReturn(responseMono);

        client.post()
                .uri("/api/mitocode/registrar")
                //.body(Mono.just(persona), Persona.class)
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(personaHandler, Mockito.times(1))
                .listenPOSTRegistrarPersona(Mockito.any(ServerRequest.class));

    }
}