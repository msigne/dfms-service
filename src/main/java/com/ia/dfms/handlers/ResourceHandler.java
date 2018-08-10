package com.ia.dfms.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.converter.Converter;
import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Organization;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.documents.Task;
import com.ia.dfms.dtos.ResourceDTO;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ResourceHandler {
    private final ResourceService resourceService;
    private final Converter<ResourceDTO, Resource> resourceConverter;

    public Mono<ServerResponse> resourceAdd(ServerRequest request) {
        final Mono<ResourceDTO> dto = request.bodyToMono(ResourceDTO.class);
        final Mono<Resource> r = resourceService.resourceAdd(resourceConverter.convert(dto));
        return ServerResponse.ok().body(resourceConverter.reverse(r), ResourceDTO.class);

    }

    public Mono<ServerResponse> resourceGet(ServerRequest request) {
        final String resourceId = request.pathVariable("resourceId");
        final Mono<Resource> r = resourceService.resourceGet(resourceId);
        return ServerResponse.ok().body(resourceConverter.reverse(r), ResourceDTO.class);
    }

    public Mono<ServerResponse> resourceGetByOrganization(ServerRequest request) {
        final String organizationId = request.pathVariable("organizationId");
        final Flux<ResourceDTO> dtos = resourceConverter.reverse(resourceService.resourceGetByOrganization(organizationId));
        return ServerResponse.ok().body(dtos, ResourceDTO.class);
    }

    public Mono<ServerResponse> artifactAdd(ServerRequest request) {
        final Mono<Artifact> dto = request.bodyToMono(Artifact.class);
        final Mono<Artifact> a = resourceService.artifactAdd(dto);
        return ServerResponse.ok().body(a, Artifact.class);
    }

    public Mono<ServerResponse> artifactGet(ServerRequest request) {
        final String artifactId = request.pathVariable("artifactId");
        final Mono<Artifact> a = resourceService.artifactGet(artifactId);
        return ServerResponse.ok().body(a, Artifact.class);
    }

    public Mono<ServerResponse> artifactGetByOrganization(ServerRequest request) {
        final String organizationId = request.pathVariable("organizationId");
        final Flux<Artifact> dtos = resourceService.artifactGetByOrganization(organizationId);
        return ServerResponse.ok().body(dtos, Artifact.class);
    }

    public Mono<ServerResponse> organisationAdd(ServerRequest request) {
        final Mono<Organization> dto = request.bodyToMono(Organization.class);
        final Mono<Organization> o = resourceService.organizationAdd(dto);
        return ServerResponse.ok().body(o, Organization.class);
    }

    public Mono<ServerResponse> organisationGet(ServerRequest request) {
        final String organizationId = request.pathVariable("organizationId");
        final Mono<Organization> o = resourceService.organizationGet(organizationId);
        return ServerResponse.ok().body(o, Organization.class);
    }

    public Mono<ServerResponse> organisationGetAll(ServerRequest request) {
        return ServerResponse.ok().body(resourceService.organizationGet(), Organization.class);
    }

    public Mono<ServerResponse> taskAdd(ServerRequest request) {
        final Mono<Task> dto = request.bodyToMono(Task.class);
        final Mono<Task> t = resourceService.taskAdd(dto);
        return ServerResponse.ok().body(t, Task.class);
    }

    public Mono<ServerResponse> taskGet(ServerRequest request) {
        final String taskId = request.pathVariable("taskId");
        final Mono<Task> o = resourceService.taskGet(taskId);
        return ServerResponse.ok().body(o, Task.class);
    }

    public Mono<ServerResponse> taskGetByOrganization(ServerRequest request) {
        final String organizationId = request.pathVariable("organizationId");
        final Flux<Task> dtos = resourceService.taskGetByOrganization(organizationId);
        return ServerResponse.ok().body(dtos, Task.class);
    }
}
