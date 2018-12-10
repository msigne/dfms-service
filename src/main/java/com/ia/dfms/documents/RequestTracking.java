package com.ia.dfms.documents;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ia.dfms.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "RequestTrackings")
public class RequestTracking {
    @Id
    private String id;
    private Request request;
    private String observation;
    private Resource manager;
    private LocalDateTime trackingTime;
    private RequestStatus requestStatus;
 }
