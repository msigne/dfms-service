package com.ia.dfms.documents;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ia.dfms.events.creation.ArtifactCreatedEvent;
import com.ia.dfms.events.update.ArtifactUpdatedEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Artifacts")
public class Artifact {
    @Id
    private String id;
    private String description;
    private String uri;
    @DBRef
    private Company company;
    
    
    public static ArtifactBuilder from(ArtifactCreatedEvent event) {
        return Artifact.builder()
                .company(Company.from(event.getCompany()).build())
                .description(event.getDescription())
                .id(event.getId())
                .uri(event.getUri());
    }
    
    public static ArtifactBuilder from(ArtifactUpdatedEvent event) {
        return Artifact.builder()
                .company(Company.from(event.getCompany()).build())
                .description(event.getDescription())
                .id(event.getId())
                .uri(event.getUri());
    }
    
    public static Collection<Artifact> from(Collection<ArtifactCreatedEvent> events){
        return events.stream()
                .map(a->from(a).build())
                .collect(Collectors.toList());
    }
    
    public static Collection<Artifact> fromUpdate(Collection<ArtifactUpdatedEvent> events){
        return events.stream()
                .map(a->from(a).build())
                .collect(Collectors.toList());
    }


}
