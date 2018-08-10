package com.ia.dfms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ia.dfms.documents.Task;

public interface TaskRepository  extends ReactiveMongoRepository<Task, String>{

}
