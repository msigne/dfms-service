package com.ia.dfms.dtos;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.documents.Resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private String id;
    private String description;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private Map<String, Object> details = Collections.emptyMap();
    @Builder.Default
    private Collection<RequestTrackingDTO> steps = Collections.emptyList();

    @NonNull
    private String companyId;

    public static ResourceDTOBuilder of(Resource req) {
        final Collection<RequestTracking> tr = req.getSteps();
        final Collection<RequestTrackingDTO> trdtos =
                tr == null ? Collections.emptyList() : tr.stream().map(r -> RequestTrackingDTO.of(r).build()).collect(Collectors.toList());
        return ResourceDTO.builder().id(req.getId()).description(req.getDescription()).details(req.getDetails()).email(req.getEmail())
                .phoneNumber(req.getPhoneNumber()).steps(trdtos).companyId(req.getCompany().getId());
    }
}
