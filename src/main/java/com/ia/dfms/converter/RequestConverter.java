package com.ia.dfms.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.Task;
import com.ia.dfms.dtos.RequestDTO;
import com.ia.dfms.repository.ArtifactRepository;
import com.ia.dfms.repository.TaskRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestConverter implements Converter<RequestDTO, Request> {
    private final ArtifactRepository artifactRepository;
    private final TaskRepository taskRepository;

    @Override
    public Mono<Request> convert(Mono<RequestDTO> source) {

        final RequestDTO s = source.block();
        final Collection<Artifact> artifacts =
                s.getArtifacts().stream().map(a -> artifactRepository.findById(a.getId()).block()).collect(Collectors.toList());
        final Task task = taskRepository.findById(s.getTaskId()).block();
        return Mono.just(Request.builder().artifacts(artifacts).requestDate(s.getRequestDate()).requestDetails(s.getRequestDetails()).task(task).build());
    }

    @Override
    public Mono<RequestDTO> reverse(Mono<Request> toConvert) {
        return toConvert.map(s -> RequestDTO.of(s));
    }
}
