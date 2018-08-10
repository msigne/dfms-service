package com.ia.dfms.dtos;

import java.time.ZonedDateTime;

import com.ia.dfms.documents.RequestStatus;
import com.ia.dfms.documents.RequestTracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTrackingDTO {
    private String id;
    private String requestId;
    private String observation;
    private String managerId;
    private ZonedDateTime trackingTime;
    private RequestStatus requestStatus;

    public static RequestTrackingDTO of(RequestTracking r) {
        return RequestTrackingDTO.builder().id(r.getId()).managerId(r.getManager().getId()).observation(r.getObservation())
                .requestId(r.getRequest().getId()).requestStatus(r.getRequestStatus()).trackingTime(r.getTrackingTime()).build();
    }
}
