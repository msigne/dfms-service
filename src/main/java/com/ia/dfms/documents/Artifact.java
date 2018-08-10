package com.ia.dfms.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Artifacts")
public class Artifact {
    @Id
    private Mono<String> id;
    private Mono<String> description;
    private Mono<String> uri;
    @DBRef
    private Mono<Organization> organization;
}
