package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Artifact;

import reactor.core.publisher.Flux;

@Repository
public interface ArtifactRepository extends ReactiveMongoRepository<Artifact, String> {
    Flux<Artifact> findByOrganization_Id(String organizationId);
}
