package com.ia.dfms.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Request;
import com.ia.dfms.dtos.ArtifactDTO;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.repository.TaskRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestConverter implements Converter<RequestDTO, Request> {
    private final TaskRepository taskRepository;
    private final Converter<ArtifactDTO, Artifact> artifactConverter;

    @Override
    public Mono<Request> convert(Mono<RequestDTO> source) {
        return source.flatMap(s -> {
            final Collection<Artifact> artifacts = artifactConverter.convert(Flux.fromIterable(s.getArtifacts())).toStream().collect(Collectors.toList());
            return taskRepository.findById(s.getTaskId()).map(task -> {
                return Request.builder().artifacts(artifacts).requestDate(s.getRequestDate()).requestDetails(s.getRequestDetails()).task(task).build();
            });
        });
    }

    @Override
    public Mono<RequestDTO> reverse(Mono<Request> toConvert) {
        return toConvert.map(s -> RequestDTO.of(s).build());
    }
}
