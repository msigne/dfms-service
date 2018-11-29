package com.ia.dfms.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.documents.Task;
import com.ia.dfms.dtos.ArtifactDTO;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestConverter implements Converter<RequestDTO, Request> {
    
    private final ResourceService resourceService;
    private final Converter<ArtifactDTO, Artifact> artifactConverter;

    @Override
    public Mono<Request> convert(Mono<RequestDTO> source) {
        return source.flatMap(s -> {
            final Mono<Task> monoTask = resourceService.taskGet(s.getTaskId());
            final Mono<Resource> monoResource = resourceService.resourceGet(s.getResourceId());
            final Mono<List<Artifact>> monoArtifact = artifactConverter.convert(Flux.fromIterable(s.getArtifacts())).collectList();
            return monoTask.flatMap(task -> {
                return monoResource.flatMap(resource -> {
                    return monoArtifact.map(artificats -> {
                        return Request.builder()
                                .requester(resource)
                                .artifacts(artificats)
                                .requestDate(s.getRequestDate())
                                .requestDetails(s.getRequestDetails())
                                .task(task)
                                .build();
                    });
                });
            });
        });
    }

    @Override
    public Mono<RequestDTO> reverse(Mono<Request> toConvert) {
        return toConvert.map(s -> RequestDTO.of(s).build());
    }
}
