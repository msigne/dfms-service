package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Resource;

import reactor.core.publisher.Flux;

@Repository
public interface ResourceRepository extends ReactiveMongoRepository<Resource, String> {
    Flux<Resource> findByCompany_Id(String organizationId);
}
