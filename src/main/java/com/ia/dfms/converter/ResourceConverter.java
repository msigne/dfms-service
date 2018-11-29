package com.ia.dfms.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Company;
import com.ia.dfms.documents.RequestTracking;
import com.ia.dfms.documents.Resource;
import com.ia.dfms.dtos.RequestTrackingDTO;
import com.ia.dfms.dtos.ResourceDTO;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ResourceConverter implements Converter<ResourceDTO, Resource> {

    private final ResourceService resourceService;
    private final Converter<RequestTrackingDTO, RequestTracking> converter;

    @Override
    public Mono<Resource> convert(Mono<ResourceDTO> source) {
        return source.flatMap(s -> {
            final Mono<Company> monoCompany = resourceService.organizationGet(s.getCompanyId());
            final Mono<List<RequestTracking>> monoSteps =
                    s.getSteps() == null ? Flux.<RequestTracking>empty().collectList() : converter.convert(Flux.fromIterable(s.getSteps())).collectList();
            return monoCompany.flatMap(company -> {
                return monoSteps.map(steps -> {
                    return Resource.builder()
                            .company(company)
                            .description(s.getDescription())
                            .details(s.getDetails())
                            .email(s.getEmail())
                            .id(s.getId())
                            .phoneNumber(s.getPhoneNumber())
                            .steps(steps)
                            .build();
                });
            });
        });
    }

    @Override
    public Mono<ResourceDTO> reverse(Mono<Resource> source) {
        return source.map(s -> ResourceDTO.of(s).build());
    }
}
