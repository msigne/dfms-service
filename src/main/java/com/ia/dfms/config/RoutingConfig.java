package com.ia.dfms.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.handlers.RequestHandler;
import com.ia.dfms.handlers.ResourceHandler;

@Configuration
@EnableWebFlux
public class RoutingConfig {

    @Bean
    RouterFunction<ServerResponse> requestRoutes(RequestHandler handler) {
        /* @formatter:off */
        return nest(path("/requests"),route(POST("/"), handler::requestAdd)
                .andRoute(GET("/{requestId}"), handler::requestGet)
                .andRoute(GET("/{requestId}/histories"), handler::historyByRequest)
                .andRoute(GET("/resources/{resourceId}/histories"),handler::historyByResource));
       /* @formatter:on */
    }

    @Bean
    RouterFunction<ServerResponse> resourceRoutes(ResourceHandler handler) {
        /* @formatter:off */
        return nest(path("/resources"),route(POST("/"), handler::resourceAdd)
                    .andRoute(GET("/{resourceId}"), handler::resourceGet)
                    .andRoute(GET("/companies/{companyId}"), handler::resourceGetByOrganization))
               .andNest(path("/artifacts"),route(POST("/"), handler::artifactAdd)
                    .andRoute(GET("/{artifactId}"), handler::artifactGet)
                    .andRoute(GET("/companies/{companyId}"), handler::artifactGetByOrganization))
               .andNest(path("/companies"), route(POST("/"), handler::organisationAdd)
                    .andRoute(GET("/{companyId}"), handler::organisationGet)
                    .andRoute(GET("/"), handler::organisationGetAll))
               .andNest(path("/tasks"), route(POST("/"), handler::taskAdd)
                    .andRoute(GET("/{taskId}"), handler::taskGet)
                    .andRoute(GET("/companies/{companyId}"), handler::taskGetByOrganization));
        /* @formatter:on */
    }

}
