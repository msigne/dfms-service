package com.ia.dfms.documents;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private Task task;
    private Collection<Artifact> artifacts;
    private ZonedDateTime requestDate;
    private Resource requester;
    private RequestStatus requestStatus;
    private Map<String, Object> requestDetails;
    private Collection<Step> steps;
}
