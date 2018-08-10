package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ia.dfms.documents.Artifact;

@Repository
public interface ArtifactRepository extends ReactiveMongoRepository<Artifact, String>{

}
