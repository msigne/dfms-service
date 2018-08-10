package com.ia.dfms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtifactDTO {
    private Mono<String> id;
    private Mono<String> description;
    private Mono<String> uri;
    private Mono<String> organizationId;
}
