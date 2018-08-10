package com.ia.dfms.services.request;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.repository.RequestRepository;
import com.ia.dfms.repository.RequestTrackingRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class DefaultRequestService implements RequestService {

    private final RequestTrackingRepository requestTrackingRepository;
    private final RequestRepository requestRepository;

    @Override
    public Mono<Request> requestAdd(Mono<Request> request) {
        return request.flatMap(r -> requestRepository.save(r));
    }

    @Override
    public Mono<Request> requestGet(String requestId) {
        return requestRepository.findById(requestId);
    }

    @Override
    public Flux<Request> requestGetByResource(String resourceId) {
        return null;
    }

    @Override
    public Flux<RequestTracking> requestHistoryByResource(String resourceId) {
        return requestTrackingRepository.findByRequest_Requester_Id(resourceId);
    }

    @Override
    public Flux<RequestTracking> requestHistoryByRequest(String requestId) {
        return requestTrackingRepository.findByRequest_Id(requestId);
    }
}
