package com.ia.dfms.dtos;

import java.time.LocalDateTime;

import org.springframework.util.Assert;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.enums.RequestStatus;

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
    private LocalDateTime trackingTime;
    private RequestStatus requestStatus;

    public static RequestTrackingDTOBuilder of(RequestTracking r) {
        Assert.notNull(r.getManager(), "Manager is required");
        return RequestTrackingDTO.builder()
                .id(r.getId())
                .managerId(r.getManager().getId())
                .observation(r.getObservation())
                .requestId(r.getRequest().getId())
                .requestStatus(r.getRequestStatus())
                .trackingTime(r.getTrackingTime());
    }
}
