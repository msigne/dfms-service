package com.ia.dfms.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ia.dfms.converter.Converter;
import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Company;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.documents.Task;
import com.ia.dfms.dtos.ArtifactDTO;
import com.ia.dfms.dtos.ResourceDTO;
import com.ia.dfms.dtos.TaskDTO;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ResourceHandler {
    private final ResourceService resourceService;
    private final Converter<ResourceDTO, Resource> resourceConverter;
    private final Converter<TaskDTO, Task> taskConverter;
    private final Converter<ArtifactDTO, Artifact> artifactConverter;

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
        final String companyId = request.pathVariable("companyId");
        final Flux<ResourceDTO> dtos = resourceConverter.reverse(resourceService.resourceGetByOrganization(companyId));
        return ServerResponse.ok().body(dtos, ResourceDTO.class);
    }

    public Mono<ServerResponse> artifactAdd(ServerRequest request) {
        final Mono<ArtifactDTO> dto = request.bodyToMono(ArtifactDTO.class);
        final Mono<Artifact> a = resourceService.artifactAdd(artifactConverter.convert(dto));
        return ServerResponse.ok().body(artifactConverter.reverse(a), ArtifactDTO.class);
    }

    public Mono<ServerResponse> artifactGet(ServerRequest request) {
        final String artifactId = request.pathVariable("artifactId");
        final Mono<Artifact> a = resourceService.artifactGet(artifactId);
        return ServerResponse.ok().body(artifactConverter.reverse(a), ArtifactDTO.class);
    }

    public Mono<ServerResponse> artifactGetByOrganization(ServerRequest request) {
        final String companyId = request.pathVariable("companyId");
        final Flux<Artifact> dtos = resourceService.artifactGetByOrganization(companyId);
        return ServerResponse.ok().body(artifactConverter.reverse(dtos), ArtifactDTO.class);
    }

    public Mono<ServerResponse> organisationAdd(ServerRequest request) {
        final Mono<Company> dto = request.bodyToMono(Company.class);
        final Mono<Company> o = resourceService.organizationAdd(dto);
        return ServerResponse.ok().body(o, Company.class);
    }

    public Mono<ServerResponse> organisationGet(ServerRequest request) {
        final String companyId = request.pathVariable("companyId");
        final Mono<Company> o = resourceService.organizationGet(companyId);
        return ServerResponse.ok().body(o, Company.class);
    }

    public Mono<ServerResponse> organisationGetAll(ServerRequest request) {
        return ServerResponse.ok().body(resourceService.organizationGet(), Company.class);
    }

    public Mono<ServerResponse> taskAdd(ServerRequest request) {
        final Mono<TaskDTO> dto = request.bodyToMono(TaskDTO.class);
        return taskConverter.convert(dto).map(task -> resourceService.taskAdd(Mono.just(task))).flatMap(r->{
            return ServerResponse.ok().body(taskConverter.reverse(r), TaskDTO.class);
        });
        
    }

    public Mono<ServerResponse> taskGet(ServerRequest request) {
        final String taskId = request.pathVariable("taskId");
        final Mono<Task> o = resourceService.taskGet(taskId);
        return ServerResponse.ok().body(taskConverter.reverse(o), TaskDTO.class);
    }

    public Mono<ServerResponse> taskGetByOrganization(ServerRequest request) {
        final String companyId = request.pathVariable("companyId");
        final Flux<Task> dtos = resourceService.taskGetByOrganization(companyId);
        return ServerResponse.ok().body(taskConverter.reverse(dtos), TaskDTO.class);
    }
}
