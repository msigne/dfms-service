package com.ia.dfms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.handlers.RequestHandler;
import com.ia.dfms.handlers.ResourceHandler;

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

    @Bean
    RouterFunction<ServerResponse> resourceRoutes(ResourceHandler handler) {
        return RouterFunctions.route(RequestPredicates.POST("/resources"), handler::resourceAdd)
                .andRoute(RequestPredicates.GET("/resources/{resourceId}"), handler::resourceGet)
                .andRoute(RequestPredicates.GET("/resources/organizations/{organizationId}"), handler::resourceGetByOrganization)
                .andRoute(RequestPredicates.POST("/artifacts"), handler::artifactAdd)
                .andRoute(RequestPredicates.GET("/artifacts/{artifactId}"), handler::artifactGet)
                .andRoute(RequestPredicates.GET("/artifacts/organizations/{organizationId}"), handler::artifactGetByOrganization)
                .andRoute(RequestPredicates.POST("/organizations"), handler::organisationAdd)
                .andRoute(RequestPredicates.GET("/organizations/{organizationId}"), handler::organisationGet)
                .andRoute(RequestPredicates.GET("/organisations"), handler::organisationGetAll)
                .andRoute(RequestPredicates.POST("/tasks"), handler::taskAdd)
                .andRoute(RequestPredicates.GET("/tasks/{taskId}"), handler::taskGet)
                .andRoute(RequestPredicates.GET("/tasks/organisations/{organizationId}"), handler::taskGetByOrganization);
    }

}
