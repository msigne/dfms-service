package com.ia.dfms.documents;

import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Organizations")
@Data
public class Organization {
    @Id
    private String id;
    private String name;
    @Builder.Default
    private Map<String, Object> details = Collections.emptyMap();
}
