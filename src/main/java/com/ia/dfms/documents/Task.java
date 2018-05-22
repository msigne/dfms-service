package com.ia.dfms.documents;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    private String id;
    private String description;
    private Collection<Step> steps;
    private Collection<Artifact> artifacts;
}
