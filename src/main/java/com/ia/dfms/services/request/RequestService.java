package com.ia.dfms.services.request;

import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestTracking;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RequestService {

    Mono<Request> requestAdd(Mono<Request> request);

    Mono<Request> requestGet(String requestId);

    Flux<Request> requestGetByResource(String resourceId);
    
    Flux<RequestTracking> requestHistoryByResource(String resourceId);

    Flux<RequestTracking> requestHistoryByRequest(String requestId);
}
