package com.ia.dfms.documents;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String description;
    @Builder.Default
    private Collection<Request> requests = Collections.unmodifiableList(Collections.emptyList());
    @Builder.Default
    private Collection<Artifact> artifacts = Collections.unmodifiableList(Collections.emptyList());
    @DBRef
    private Organization organization;
}
