package com.ia.dfms.dtos;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.ia.dfms.documents.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String id;
    private String description;
    @Builder.Default
    private Collection<RequestDTO> requests = Collections.emptyList();
    @Builder.Default
    private Collection<ArtifactDTO> artifacts = Collections.emptyList();
    @DBRef
    private String companyId;

    public static TaskDTOBuilder of(Task task) {
        final Collection<RequestDTO> requests = task.getRequests().stream().map(r -> RequestDTO.of(r).build()).collect(Collectors.toList());
        final Collection<ArtifactDTO> artifacts = task.getArtifacts().stream().map(a -> ArtifactDTO.of(a).build()).collect(Collectors.toList());
        return TaskDTO.builder().id(task.getId()).companyId(task.getCompany().getId()).description(task.getDescription()).artifacts(artifacts)
                .requests(requests);
    }
}
