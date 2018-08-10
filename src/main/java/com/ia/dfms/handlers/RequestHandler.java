package com.ia.dfms.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.converter.Converter;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.services.RequestHistoryService;
import com.ia.dfms.services.RequestService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestHandler {

    private final RequestService requestService;
    private final RequestHistoryService historyService;
    private final Converter<RequestDTO, Request> requestConverter;

    public Mono<ServerResponse> requestAdd(ServerRequest request) {
        final Mono<RequestDTO> dto = request.bodyToMono(RequestDTO.class);
        requestService.requestAdd(requestConverter.convert(dto));
        return ServerResponse.ok().body(dto, RequestDTO.class);
    }

    public Mono<ServerResponse> requestGet(ServerRequest request) {
        final String requestId = request.pathVariable("requestId");
        return ServerResponse.ok().body(requestService.requestGet(requestId), Request.class);
    }

    public Mono<ServerResponse> historyByRequest(ServerRequest request) {
        final String requestId = request.pathVariable("requestId");
        return ServerResponse.ok().body(historyService.requestHistoryByRequest(requestId), RequestTracking.class);
    }

    public Mono<ServerResponse> historyByResource(ServerRequest request) {
        final String resourceId = request.pathVariable("requestId");
        return ServerResponse.ok().body(historyService.requestHistoryByResource(resourceId), RequestTracking.class);
    }
}
