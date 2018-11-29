package com.ia.dfms.documents;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"artifacts", "requests"})
@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String description;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinProperty(name = "requests")
    private Collection<Request> requests = Collections.emptyList();
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinProperty(name = "artifacts")
    private Collection<Artifact> artifacts = Collections.emptyList();
    @DBRef
    private Company company;
}
