package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Resource;

@Repository
public interface ResourceRepository extends ReactiveMongoRepository<Resource, String>{

}
