package com.ia.dfms.services;

import com.ia.dfms.documents.RequestTracking;

import reactor.core.publisher.Flux;

public interface RequestHistoryService {
    
    Flux<RequestTracking> requestHistoryByResource(String resourceId);

    Flux<RequestTracking> requestHistoryByRequest(String requestId);
}
