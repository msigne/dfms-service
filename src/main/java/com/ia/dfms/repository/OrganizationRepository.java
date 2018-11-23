package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Company;

@Repository
public interface OrganizationRepository extends ReactiveMongoRepository<Company, String>{

}
