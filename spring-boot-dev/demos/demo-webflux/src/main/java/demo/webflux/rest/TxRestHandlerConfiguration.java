package demo.webflux.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@Profile("handler-style-endpoint")
public class TxRestHandlerConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(TxRestHandler handler) {
        return RouterFunctions.route(GET("/tx"), handler::getAll)
                .andRoute(GET("/tx/{id}"), handler::getById)
                .andRoute(POST("/tx"), handler::create)
                .andRoute(PUT("/tx/{id}"), handler::update)
                .andRoute(DELETE("/tx/{id}"), handler::delete);
    }
}
