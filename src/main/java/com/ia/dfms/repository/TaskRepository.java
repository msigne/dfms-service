package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ia.dfms.documents.Task;

import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
    Flux<Task> findByOrganization_Id(String organizationId);
}
