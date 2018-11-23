package com.ia.dfms.converter;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.dtos.ArtifactDTO;
import com.ia.dfms.services.resource.ResourceService;

import lombok.Value;
import reactor.core.publisher.Mono;

@Value
@Component
public class ArtifactConverter implements Converter<ArtifactDTO, Artifact> {
    ResourceService resourceService;

    @Override
    public Mono<Artifact> convert(Mono<ArtifactDTO> source) {
        return source.flatMap(s -> {
            return resourceService.organizationGet(s.getCompanyId()).map(c->{               
                return Artifact.builder().company(c).id(s.getId()).description(s.getDescription()).uri(s.getUri()).build();
            });
        });
    }

    @Override
    public Mono<ArtifactDTO> reverse(Mono<Artifact> source) {
        return source.map(s -> ArtifactDTO.of(s).build());
    }

}
