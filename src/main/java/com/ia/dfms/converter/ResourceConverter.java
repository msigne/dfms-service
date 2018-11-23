package com.ia.dfms.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.dtos.RequestTrackingDTO;
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
        return source.flatMap(s -> {
            final Collection<RequestTrackingDTO> trdtos = s.getSteps();
            final Collection<RequestTracking> steps = trdtos == null ? Collections.emptyList() : trdtos.stream().map(rt -> {
                final RequestTracking.RequestTrackingBuilder builder = RequestTracking.builder().id(rt.getId()).observation(rt.getObservation())
                        .requestStatus(rt.getRequestStatus()).trackingTime(rt.getTrackingTime());
                requestService.requestGet(rt.getRequestId()).map(r -> builder.request(r))
                        .then(resourceService.resourceGet(rt.getManagerId()).map(m -> builder.manager(m)));

                return builder.build();
            }).collect(Collectors.toList());
            final Resource.ResourceBuilder rsb = Resource.builder().id(s.getId()).description(s.getDescription()).details(s.getDetails())
                    .email(s.getEmail()).phoneNumber(s.getPhoneNumber()).steps(steps);
            return resourceService.organizationGet(s.getCompanyId()).map(o -> rsb.company(o).build());
        });
    }

    @Override
    public Mono<ResourceDTO> reverse(Mono<Resource> source) {
        return source.map(s -> ResourceDTO.of(s).build());
    }
}
