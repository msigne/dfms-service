package com.ia.dfms.services;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Request;
import com.ia.dfms.repository.RequestRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class DefaultRequestService implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public Mono<Request> requestAdd(Mono<Request> request) {
        return request.doOnNext(r -> requestRepository.save(r).thenEmpty(Mono.empty()));
    }

    @Override
    public Mono<Request> requestGet(String requestId) {
        return requestRepository.findById(requestId);
    }

    @Override
    public Flux<Request> requestGetByResource(String resourceId) {
        return null;
    }
}
