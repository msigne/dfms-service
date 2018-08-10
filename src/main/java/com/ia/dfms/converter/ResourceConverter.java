package com.ia.dfms.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Organization;
import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.dtos.ResourceDTO;
import com.ia.dfms.services.request.RequestService;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ResourceConverter implements Converter<ResourceDTO, Resource> {

    private final ResourceService resourceService;
    private final RequestService requestService;

    @Override
    public Mono<Resource> convert(Mono<ResourceDTO> source) {
        return source.map(s -> {    
            final Collection<RequestTracking> steps = s.getSteps().stream().map(rt->{
                final Request r = requestService.requestGet(rt.getRequestId()).block();
                final Resource m = resourceService.resourceGet(rt.getManagerId()).block();
                return RequestTracking.builder()
                        .id(rt.getId())
                        .manager(m)
                        .observation(rt.getObservation())
                        .request(r)
                        .requestStatus(rt.getRequestStatus())
                        .trackingTime(rt.getTrackingTime())
                        .build();
            }).collect(Collectors.toList());
            final Organization o = resourceService.organizationGet(s.getOrganizationId()).block();
            return Resource.builder()
                    .id(s.getId())
                    .description(s.getDescription())
                    .details(s.getDetails())
                    .email(s.getEmail())
                    .organization(o)
                    .phoneNumer(s.getPhoneNumer())
                    .steps(steps)
                    .build();
        });
    }

    @Override
    public Mono<ResourceDTO> reverse(Mono<Resource> source) {
        return source.map(s->ResourceDTO.of(s));
    }
}
