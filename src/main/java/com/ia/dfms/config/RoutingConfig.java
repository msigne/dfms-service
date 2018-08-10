package com.ia.dfms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.handlers.RequestHandler;

@Configuration
@EnableWebFlux
public class RoutingConfig {

    @Bean
    RouterFunction<ServerResponse> requestRoutes(RequestHandler handler) {
        return RouterFunctions.route(RequestPredicates.POST("/requests"), handler::requestAdd)
                .andRoute(RequestPredicates.GET("/requests/{requestId}"), handler::requestGet)
                .andRoute(RequestPredicates.GET("/requests/{requestId}/histories"), handler::historyByRequest)
                .andRoute(RequestPredicates.GET("/requests/resources/{resourceId}/histories"), handler::historyByResource);
    }
}
