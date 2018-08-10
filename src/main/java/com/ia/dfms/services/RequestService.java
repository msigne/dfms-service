package com.ia.dfms.services;

import com.ia.dfms.documents.Request;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RequestService {

    Mono<Request> requestAdd(Mono<Request> request);

    Mono<Request> requestGet(String requestId);

    Flux<Request> requestGetByResource(String resourceId);

}
