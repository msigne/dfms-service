package com.ia.dfms.documents;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "RequestTrackings")
public class RequestTracking {
    @Id
    private Mono<String> id;
    @DBRef
    private Mono<Request> request;
    private Mono<String> observation;
    @DBRef
    private Mono<Resource> manager;
    private Mono<ZonedDateTime> trackingTime;
    private RequestStatus requestStatus;
}
