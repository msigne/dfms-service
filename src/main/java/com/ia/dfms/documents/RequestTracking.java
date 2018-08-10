package com.ia.dfms.documents;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @DBRef
    private Request request;
    private String observation;
    @DBRef
    private Resource manager;
    private ZonedDateTime trackingTime;
    private RequestStatus requestStatus;
 }
