package com.jhonny.reactive.demoreactivomitocode.routerconfig;

import com.jhonny.reactive.demoreactivomitocode.controller.PersonaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Clase que permite administrar las rutas de la aplicacion
 */
@Configuration
@RequiredArgsConstructor
public class RouterConfig {


    private final PersonaHandler personaHandler;

    /**
     * Funcion que permite asignar la ruta a una funcion del controlador
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routes() {

        return RouterFunctions.route(
                POST("/api/mitocode/registrar").and(accept(MediaType.APPLICATION_JSON)),
                        personaHandler::listenPOSTRegistrarPersona)
                .andRoute(PATCH("/api/mitocode/modificar").and(accept(MediaType.APPLICATION_JSON)),
                        personaHandler::listenPATCHModificarPersona)
                .andRoute(GET("/api/mitocode/listar").and(accept(MediaType.APPLICATION_JSON)),
                        personaHandler::listenGETistarPersonas)
                .andRoute(GET("/api/mitocode/listar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        personaHandler::listenGETListarPersonaPorId)
                .andRoute(DELETE("/api/mitocode/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        personaHandler::listenDELETEEliminarPersona);
    }

}
