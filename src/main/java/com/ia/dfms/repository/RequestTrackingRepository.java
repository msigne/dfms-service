package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.RequestTracking;

import reactor.core.publisher.Flux;

@Repository
public interface RequestTrackingRepository extends ReactiveMongoRepository<RequestTracking, String>{

    Flux<RequestTracking> findByRequest_Id(String requestId);
    Flux<RequestTracking> findByRequest_Requester_Id(String resourceId);
}
