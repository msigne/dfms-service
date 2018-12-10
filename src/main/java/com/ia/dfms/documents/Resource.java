package com.ia.dfms.documents;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ia.dfms.events.creation.ResourceCreatedEvent;
import com.ia.dfms.events.update.ResourceUpdatedEvent;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Resources")
@ToString(exclude = {"steps"})
public class Resource {
    @Id
    private String id;
    private String description;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private Map<String, Object> details = Collections.emptyMap();
    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinProperty(name = "steps")
    private Collection<RequestTracking> steps = Collections.emptyList();
    @DBRef
    private Company company;
    
    public static ResourceBuilder from (ResourceCreatedEvent event) {
        return Resource.builder()
                .company(Company.from(event.getCompany()).build())
                .description(event.getDescription())
                .details(event.getDetails())
                .email(event.getEmail())
                .id(event.getId())
                .phoneNumber(event.getPhoneNumber());
    }
    
    public static ResourceBuilder from (ResourceUpdatedEvent event) {
        return Resource.builder()
                .company(Company.from(event.getCompany()).build())
                .description(event.getDescription())
                .details(event.getDetails())
                .email(event.getEmail())
                .id(event.getId())
                .phoneNumber(event.getPhoneNumber());
    }

}
