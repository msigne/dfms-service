package com.ia.dfms.services.resource;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Organization;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.documents.Task;
import com.ia.dfms.repository.ArtifactRepository;
import com.ia.dfms.repository.OrganizationRepository;
import com.ia.dfms.repository.ResourceRepository;
import com.ia.dfms.repository.TaskRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class DefaultResourceService implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final OrganizationRepository organizationRepository;
    private final ArtifactRepository artifactRepository;
    private final TaskRepository taskRepository;

    @Override
    public Mono<Resource> resourceAdd(Mono<Resource> resource) {
        return resource.flatMap(r -> resourceRepository.save(r));
    }

    @Override
    public Mono<Resource> resourceGet(String resourceId) {
        return resourceRepository.findById(resourceId);
    }

    @Override
    public Flux<Resource> resourceGetByOrganization(String organizationId) {
        return resourceRepository.findByOrganization_Id(organizationId);
    }

    @Override
    public Mono<Artifact> artifactAdd(Mono<Artifact> artifact) {
        return artifact.flatMap(a -> artifactRepository.save(a));
    }

    @Override
    public Mono<Artifact> artifactGet(String artifactId) {
        return artifactRepository.findById(artifactId);
    }

    @Override
    public Flux<Artifact> artifactGetByOrganization(String organizationId) {
        return artifactRepository.findByOrganization_Id(organizationId);
    }

    @Override
    public Mono<Organization> organizationAdd(Mono<Organization> organization) {
        return organization.flatMap(o -> organizationRepository.save(o));
    }

    @Override
    public Mono<Organization> organizationGet(String organizationId) {
        return organizationRepository.findById(organizationId);
    }

    @Override
    public Flux<Organization> organizationGet() {
        return organizationRepository.findAll();
    }

    @Override
    public Mono<Task> taskAdd(Mono<Task> task) {
        return task.flatMap(t -> taskRepository.save(t));
    }

    @Override
    public Mono<Task> taskGet(String taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Flux<Task> taskGetByOrganization(String organizationId) {
        return taskRepository.findByOrganization_Id(organizationId);
    }
}
