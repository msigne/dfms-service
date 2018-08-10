package com.ia.dfms.documents;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Organizations")
@Data
public class Organization {
    @Id
    private Mono<String> id;
    private Mono<String> name;
    @Builder.Default
    private Mono<Map<String, Object>> details = Mono.empty();
}
