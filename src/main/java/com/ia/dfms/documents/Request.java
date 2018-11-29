package com.ia.dfms.documents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public boolean isCompleted() {
        return steps.stream().anyMatch(s -> RequestStatus.CANCELED.equals(s.getRequestStatus()) || RequestStatus.COMPLETED.equals(s.getRequestStatus()));
    }
}
