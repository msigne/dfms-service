package com.ia.dfms.documents;

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
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Resources")
public class Resource {
    @Id
    private Mono<String> id;
    private Mono<String> description;
    private Mono<String> email;
    private Mono<String> phoneNumer;
    @Builder.Default
    private Mono<Map<String, Object>> details = Mono.empty();
    @Builder.Default
    private Flux<RequestTracking> steps = Flux.empty();
    @DBRef
    private Mono<Organization> organization;
}
