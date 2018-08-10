package com.ia.dfms.documents;

import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Requests")
public class Request {
    @Id
    private Mono<String> id;
    private Mono<Task> task;
    @DBRef
    private Mono<Resource> requester;
    private Mono<ZonedDateTime> requestDate;
    @Builder.Default
    private Mono<Map<String, Object>> requestDetails = Mono.empty();
    private RequestStatus requestStatus;
    @Builder.Default
    private Flux<RequestTracking> steps = Flux.empty();
    @Builder.Default
    private Flux<Artifact> artifacts = Flux.empty();

    public boolean isCompleted() {
        return steps.toStream().anyMatch(s -> RequestStatus.CANCELED.equals(s.getRequestStatus()) || RequestStatus.COMPLETED.equals(s.getRequestStatus()));
    }
}
