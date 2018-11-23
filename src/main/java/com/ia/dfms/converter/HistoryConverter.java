package com.ia.dfms.converter;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.dtos.RequestTrackingDTO;
import com.ia.dfms.services.request.RequestService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class HistoryConverter implements Converter<RequestTracking, RequestTrackingDTO> {
    private final RequestService requestService;

    @Override
    public Mono<RequestTrackingDTO> convert(Mono<RequestTracking> source) {
        return source.map(s -> {
            return RequestTrackingDTO.of(s).build();
        });
    }

    @Override
    public Mono<RequestTracking> reverse(Mono<RequestTrackingDTO> source) {
        return source.flatMap(s -> {
            return requestService.requestGet(s.getRequestId()).map(r -> {
                return r.getSteps().stream().filter(t -> t.getId().equalsIgnoreCase(s.getId())).findFirst().orElse(null);
            });
        });
    }
}
