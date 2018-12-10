package com.ia.dfms.dtos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import com.ia.dfms.documents.Artifact;
import com.ia.dfms.documents.Request;
import com.ia.dfms.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDTO {
    private String id;
    private String taskId;
    private String resourceId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime requestDate;
    @Builder.Default
    private Map<String, Object> requestDetails = Collections.emptyMap();
    private RequestStatus requestStatus;
    @Builder.Default
    private Collection<RequestTrackingDTO> steps = Collections.emptyList();
    @Builder.Default
    private Collection<ArtifactDTO> artifacts = Collections.emptyList();

    public static RequestDTOBuilder of(Request req) {
        
        Assert.notNull(req.getTask(), "The Task is required");
        Assert.notNull(req.getRequester(), "The Requester is required");
        
        final Collection<Artifact> artifacts = req.getArtifacts();
        final Collection<RequestTrackingDTO> trdtos = req.getSteps().stream()
                .map(r -> RequestTrackingDTO.of(r).build())
                .collect(Collectors.toList());
        final Collection<ArtifactDTO> adtos = artifacts == null ? Collections.emptyList() : artifacts.stream()
                                                                .map(a -> ArtifactDTO.of(a).build())
                                                                .collect(Collectors.toList());
        return RequestDTO.builder()
                .id(req.getId())
                .taskId(req.getTask().getId())
                .resourceId(req.getRequester().getId())
                .requestDate(req.getRequestDate())
                .requestDetails(req.getRequestDetails())
                .requestStatus(req.getRequestStatus())
                .steps(trdtos)
                .artifacts(adtos);
    }
}
