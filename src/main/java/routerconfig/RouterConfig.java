package routerconfig;

import controller.PersonaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
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
    public RouterFunction<ServerResponse> routes(){
        return route(GET("/v2/personas"), personaHandler::buscarPersona);
    }
}
