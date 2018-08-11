package com.ia.dfms.documents;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Resources")
public class Resource {
    @Id
    private String id;
    private String description;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private Map<String, Object> details = Collections.emptyMap();
    @Builder.Default
    private Collection<RequestTracking> steps = Collections.emptyList();
    @DBRef
    private Organization organization;
}
