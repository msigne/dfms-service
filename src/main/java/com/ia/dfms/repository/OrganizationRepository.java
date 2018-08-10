package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Organization;

@Repository
public interface OrganizationRepository extends ReactiveMongoRepository<Organization, String>{

}
