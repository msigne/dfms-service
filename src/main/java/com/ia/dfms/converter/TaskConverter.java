package com.ia.dfms.converter;

import java.util.ArrayList;
import java.util.Collection;

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
        final Collection<Artifact> artifacts = new ArrayList<>();
        final Collection<Request> requests = new ArrayList<>();
         /*source.flatMap(s -> {
            return resourceService.organizationGet(s.getCompanyId()).map(c -> {
                Flux<Artifact> ars = artifactConverter.convert(Flux.fromIterable(s.getArtifacts()));
                ars.buffer().map(artifacts::addAll).flatMap(ss -> requestConverter.convert(Flux.fromIterable(s.getRequests()))).buffer()
                        .map(requests::addAll).subscribe(b -> System.out.println("result of artifacts: " + artifacts));
                return Task.builder().artifacts(artifacts).company(c).description(s.getDescription()).id(s.getId()).requests(requests).build();
            });
        });*/
         return source.flatMap(s -> {
             return resourceService.organizationGet(s.getCompanyId()).map(c -> {
                 Flux<Artifact> ars = artifactConverter.convert(Flux.fromIterable(s.getArtifacts()));
                 Flux<Request> rqs = ars.flatMap(a->requestConverter.convert(Flux.fromIterable(s.getRequests())));
                 return null;
              });
         });

    }

    @Override
    public Mono<TaskDTO> reverse(Mono<Task> source) {
        return source.map(s -> TaskDTO.of(s).build());
    }

}
