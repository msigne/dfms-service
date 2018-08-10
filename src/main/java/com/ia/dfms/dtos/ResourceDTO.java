package com.ia.dfms.dtos;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.ia.dfms.documents.Resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private String id;
    private String description;
    private String email;
    private String phoneNumer;
    @Builder.Default
    private Map<String, Object> details = Collections.emptyMap();
    @Builder.Default
    private Collection<RequestTrackingDTO> steps = Collections.emptyList();
    
    private String organizationId;
    
    public static ResourceDTO of(Resource req) {
        final Collection<RequestTrackingDTO> trdtos = req.getSteps().stream().map(RequestTrackingDTO::of).collect(Collectors.toList());
        return ResourceDTO.builder()
                .id(req.getId())
                .description(req.getDescription())
                .details(req.getDetails())
                .email(req.getEmail())
                .phoneNumer(req.getPhoneNumer())
                .steps(trdtos)
                .organizationId(req.getOrganization().getId())
                .build();
    }
}
