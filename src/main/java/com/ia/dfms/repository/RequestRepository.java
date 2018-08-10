package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Request;

@Repository
public interface RequestRepository extends ReactiveMongoRepository<Request, String> {
}
