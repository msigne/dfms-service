package com.ia.dfms.services.resource;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Company;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.documents.Task;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ResourceService {

    Mono<Resource> resourceAdd(Mono<Resource> resource);

    Mono<Resource> resourceGet(String resourceId);

    Flux<Resource> resourceGetByOrganization(String organizationId);

    Mono<Artifact> artifactAdd(Mono<Artifact> artifact);

    Mono<Artifact> artifactGet(String artifactId);

    Flux<Artifact> artifactGetByOrganization(String organizationId);

    Mono<Company> organizationAdd(Mono<Company> company);

    Mono<Company> organizationGet(String organizationId);

    Flux<Company> organizationGet();
    
    Mono<Task> taskAdd(Mono<Task> task);

    Mono<Task> taskGet(String taskId);

    Flux<Task> taskGetByOrganization(String organizationId);


}
