package com.ia.dfms.dtos;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestStatus;

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
    private ZonedDateTime requestDate;
    @Builder.Default
    private Map<String, Object> requestDetails = Collections.emptyMap();
    private RequestStatus requestStatus;
    @Builder.Default
    private Collection<RequestTrackingDTO> steps = Collections.emptyList();
    @Builder.Default
    private Collection<ArtifactDTO> artifacts = Collections.emptyList();

    public static RequestDTOBuilder of(Request req) {

        final Collection<RequestTrackingDTO> trdtos = req.getSteps().stream().map(r -> RequestTrackingDTO.of(r).build()).collect(Collectors.toList());
        final Collection<ArtifactDTO> adtos = req.getArtifacts().stream().map(a -> ArtifactDTO.of(a).build()).collect(Collectors.toList());
        return RequestDTO.builder().id(req.getId()).taskId(req.getTask().getId()).resourceId(req.getRequester().getId()).requestDate(req.getRequestDate())
                .requestDetails(req.getRequestDetails()).requestStatus(req.getRequestStatus()).steps(trdtos).artifacts(adtos);
    }
}
