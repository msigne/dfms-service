package com.ia.dfms.converter;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Request;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.dtos.RequestTrackingDTO;
import com.ia.dfms.services.request.RequestService;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class HistoryConverter implements Converter<RequestTrackingDTO, RequestTracking> {
    private final RequestService requestService;
    private final ResourceService resourceService;

    @Override
    public Mono<RequestTracking> convert(Mono<RequestTrackingDTO> source) {
        return source.flatMap(s -> {
            final Mono<Request> monoRequest = requestService.requestGet(s.getRequestId());
            final Mono<Resource> monoResource = resourceService.resourceGet(s.getManagerId());
            return monoRequest.flatMap(request -> {
                return monoResource.map(resource -> {
                    return RequestTracking.builder()
                            .id(s.getId())
                            .manager(resource)
                            .observation(s.getObservation())
                            .request(request)
                            .requestStatus(s.getRequestStatus())
                            .trackingTime(s.getTrackingTime())
                            .build();
                });
            });
        });
    }

    @Override
    public Mono<RequestTrackingDTO> reverse(Mono<RequestTracking> source) {
        return source.map(s -> {
            return RequestTrackingDTO.of(s).build();
        });
    }

}
