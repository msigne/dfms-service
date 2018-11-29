package com.ia.dfms.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.converter.Converter;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.dtos.RequestTrackingDTO;
import com.ia.dfms.services.request.RequestService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestHandler {

    private final RequestService requestService;
    private final Converter<RequestDTO, Request> requestConverter;
    private final Converter<RequestTracking, RequestTrackingDTO> historyConverter;

    public Mono<ServerResponse> requestAdd(ServerRequest request) {
        final Mono<RequestDTO> dto = request.bodyToMono(RequestDTO.class);
        final Mono<Request> rq = requestConverter.convert(dto);
        final Mono<Request> r = requestService.requestAdd(rq);
        return ServerResponse.ok().body(requestConverter.reverse(r), RequestDTO.class);
    }

    public Mono<ServerResponse> requestGet(ServerRequest request) {
        final String requestId = request.pathVariable("requestId");
        final Mono<Request> r = requestService.requestGet(requestId);
        return ServerResponse.ok().body(requestConverter.reverse(r), RequestDTO.class);
    }

    public Mono<ServerResponse> historyByRequest(ServerRequest request) {
        final String requestId = request.pathVariable("requestId");
        final Flux<RequestTrackingDTO> dtos = historyConverter.convert(requestService.requestHistoryByRequest(requestId));
        return ServerResponse.ok().body(dtos, RequestTrackingDTO.class);
    }

    public Mono<ServerResponse> historyByResource(ServerRequest request) {
        final String resourceId = request.pathVariable("resourceId");
        final Flux<RequestTrackingDTO> dtos = historyConverter.convert(requestService.requestHistoryByResource(resourceId));
        return ServerResponse.ok().body(dtos, RequestTrackingDTO.class);
    }
}
