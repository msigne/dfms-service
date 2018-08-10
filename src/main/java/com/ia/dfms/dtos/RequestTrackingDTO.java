package com.ia.dfms.dtos;

import java.time.ZonedDateTime;

import com.ia.dfms.documents.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTrackingDTO {
    private Mono<String> id;
    private Mono<String> requestId;
    private Mono<String> observation;
    private Mono<String> managerId;
    private Mono<ZonedDateTime> trackingTime;
    private RequestStatus requestStatus;
}
