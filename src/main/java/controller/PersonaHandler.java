package controller;

import lombok.RequiredArgsConstructor;
import model.Persona;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PersonaHandler {


    public Mono<ServerResponse> buscarPersona(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Mono.just(new Persona(1, "jhonny")));
    }
}
