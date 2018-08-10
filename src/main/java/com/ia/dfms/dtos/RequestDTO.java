package com.ia.dfms.dtos;

import java.time.ZonedDateTime;
import java.util.Map;

import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDTO {
    private Mono<String> id;
    private Mono<String> taskId;
    private Mono<String> resourceId;
    private Mono<ZonedDateTime> requestDate;
    @Builder.Default
    private Mono<Map<String, Object>> requestDetails = Mono.empty();
    private RequestStatus requestStatus;
    @Builder.Default
    private Flux<RequestTrackingDTO> steps = Flux.empty();
    @Builder.Default
    private Flux<ArtifactDTO> artifacts = Flux.empty();

    public Mono<RequestDTO> of(Mono<Request> req) {
        return req.flatMap(q -> {
            final Flux<RequestTrackingDTO> trdtos = q.getSteps().map(r -> {
                return RequestTrackingDTO.builder().id(r.getId()).managerId(r.getManager().flatMap(m -> m.getId())).observation(r.getObservation())
                        .requestId(q.getId()).requestStatus(r.getRequestStatus()).trackingTime(r.getTrackingTime()).build();
            });

            final Flux<ArtifactDTO> adtos = q.getArtifacts().map(r -> {
                return ArtifactDTO.builder().description(r.getDescription()).id(r.getId()).organizationId(r.getOrganization().flatMap(o -> o.getId()))
                        .uri(r.getUri()).build();
            });
            return Mono.just(RequestDTO.builder().id(q.getId()).taskId(q.getTask().map(r -> r.getId()))
                    .resourceId(q.getRequester().flatMap(r -> r.getId())).requestDate(q.getRequestDate()).requestDetails(q.getRequestDetails())
                    .requestStatus(q.getRequestStatus()).steps(trdtos).artifacts(adtos).build());

        });
    }
}
