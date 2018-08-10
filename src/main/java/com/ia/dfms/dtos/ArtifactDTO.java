package com.ia.dfms.dtos;

import com.ia.dfms.documents.Artifact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtifactDTO {
    private String id;
    private String description;
    private String uri;
    private String organizationId;

    public static ArtifactDTO of(Artifact a) {
        return ArtifactDTO.builder()
                .description(a.getDescription())
                .id(a.getId())
                .organizationId(a.getOrganization().getId())
                .uri(a.getUri())
                .build();
    }
}
