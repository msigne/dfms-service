package com.ia.dfms.converter;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.Task;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.repository.ArtifactRepository;
import com.ia.dfms.repository.TaskRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestConverter implements Converter<RequestDTO, Request> {
    private final ArtifactRepository artifactRepository;
    private final TaskRepository taskRepository;

    @Override
    public Mono<Request> convert(Mono<RequestDTO> source) {
        final RequestDTO dto = source.block();
        final Flux<Artifact> artifacts = dto.getArtifacts().flatMap(a -> artifactRepository.findById(a.getId()));
        final Mono<Task> task = taskRepository.findById(dto.getTaskId());
        return Mono
                .just(Request.builder()
                        .artifacts(artifacts)
                        .requestDate(dto.getRequestDate())
                        .requestDetails(dto.getRequestDetails())
                        .task(task)
                        .build());
    }

}
