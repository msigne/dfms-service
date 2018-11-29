package com.ia.dfms.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.Task;
import com.ia.dfms.dtos.ArtifactDTO;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.dtos.TaskDTO;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class TaskConverter implements Converter<TaskDTO, Task> {

    private final ResourceService resourceService;
    private final Converter<ArtifactDTO, Artifact> artifactConverter;
    private final Converter<RequestDTO, Request> requestConverter;

    @Override
    public Mono<Task> convert(Mono<TaskDTO> source) {
        return source.flatMap(s -> {
            return resourceService.organizationGet(s.getCompanyId()).flatMap(c -> {
                final Mono<List<Artifact>> monoArtifact =
                        s.getArtifacts() == null ? Flux.<Artifact>empty().collectList()
                                                 : artifactConverter.convert(Flux.fromIterable(s.getArtifacts())).collectList();
                final Mono<List<Request>> monoRequest =
                        s.getRequests() == null ? Flux.<Request>empty().collectList()
                                                : requestConverter.convert(Flux.fromIterable(s.getRequests())).collectList();
                return monoArtifact.flatMap(artifacts -> {
                    return monoRequest.map(requests -> {
                        return Task.builder().artifacts(artifacts).company(c).description(s.getDescription()).requests(requests).id(s.getId()).build();
                    });
                });
            });
        }).log();
    }

    @Override
    public Mono<TaskDTO> reverse(Mono<Task> source) {
        return source.map(s -> TaskDTO.of(s).build());
    }

}
