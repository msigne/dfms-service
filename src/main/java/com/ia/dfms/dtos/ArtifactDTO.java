package com.ia.dfms.dtos;

import org.springframework.util.Assert;

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
    private String companyId;

    public static ArtifactDTOBuilder of(Artifact a) {
        Assert.notNull(a.getCompany(), "Company is required");
        return ArtifactDTO.builder()
                .description(a.getDescription())
                .id(a.getId())
                .companyId(a.getCompany().getId())
                .uri(a.getUri());
    }
}
