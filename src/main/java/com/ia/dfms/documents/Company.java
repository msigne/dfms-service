package com.ia.dfms.documents;

import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ia.dfms.events.creation.CompanyCreatedEvent;
import com.ia.dfms.events.update.CompanyUpdatedEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Organizations")
@Data
public class Company {
    @Id
    private String id;
    private String name;
    @Builder.Default
    private Map<String, Object> details = Collections.emptyMap();
    
    public static CompanyBuilder from(CompanyCreatedEvent event) {
        return Company.builder()
                .details(event.getDetails())
                .id(event.getId())
                .name(event.getName());
    }
    
    public static CompanyBuilder from(CompanyUpdatedEvent event) {
        return Company.builder()
                .details(event.getDetails())
                .id(event.getId())
                .name(event.getName());
    }

}
