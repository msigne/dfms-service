package com.ia.dfms.documents;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ia.dfms.enums.RequestStatus;
import com.ia.dfms.events.creation.RequestCreatedEvent;
import com.ia.dfms.events.update.RequestUpdatedEvent;

import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Requests")
@ToString(exclude= {"artifacts","steps"})
public class Request {
    @Id
    private String id;
    @DBRef
    private Task task;
    @DBRef
    private Resource requester;
    @CreatedDate
    private LocalDateTime requestDate;
    @Builder.Default
    private Map<String, Object> requestDetails = Collections.emptyMap();
    private RequestStatus requestStatus;
    @Builder.Default
    @OneToMany
    @JoinProperty(name = "steps")
    private Collection<RequestTracking> steps = Collections.emptyList();
    @Builder.Default
    @OneToMany
    @JoinProperty(name = "artifacts")
    private Collection<Artifact> artifacts = Collections.emptyList();

    
    public static RequestBuilder from(RequestCreatedEvent event) {
        return Request.builder()
                .artifacts(Artifact.from(event.getArtifacts()))
                .id(event.getId())
                .requestDate(event.getRequestDate())
                .requestDetails(event.getRequestDetails())
                .requester(Resource.from(event.getRequester()).build())
                .requestStatus(event.getRequestStatus())
                .task(Task.from(event.getTask()).build());
    }
    
    public static RequestBuilder from(RequestUpdatedEvent event) {
        return Request.builder()
                .artifacts(Artifact.fromUpdate(event.getArtifacts()))
                .id(event.getId())
                .requestDate(event.getRequestDate())
                .requestDetails(event.getRequestDetails())
                .requester(Resource.from(event.getRequester()).build())
                .requestStatus(event.getRequestStatus())
                .task(Task.from(event.getTask()).build());
    }

    public boolean isCompleted() {
        return steps.stream().anyMatch(s -> RequestStatus.CANCELED.equals(s.getRequestStatus()) || RequestStatus.COMPLETED.equals(s.getRequestStatus()));
    }
}
