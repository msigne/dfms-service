package com.ia.dfms.services;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.repository.RequestTrackingRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Component
public class DefaultRequestHistoryService implements RequestHistoryService {

    private final RequestTrackingRepository requestTrackingRepository;

    @Override
    public Flux<RequestTracking> requestHistoryByResource(String resourceId) {
        return requestTrackingRepository.findByRequest_Requester_Id(resourceId);
    }

    @Override
    public Flux<RequestTracking> requestHistoryByRequest(String requestId) {
        return requestTrackingRepository.findByRequest_Id(requestId);
    }
}
