package com.ia.dfms.handlers.events;

import java.util.function.Function;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.ia.dfms.documents.Company;
import com.ia.dfms.events.creation.CompanyCreatedEvent;
import com.ia.dfms.events.update.CompanyUpdatedEvent;
import com.ia.dfms.services.resource.ResourceService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
@ProcessingGroup("dfmsItemProcessor")
@Slf4j
public class DfmsEventHandler {

    private final ResourceService resourceService;

    @EventHandler
    public void on(CompanyCreatedEvent event) {
        logEvent(event.getClass().getName(), event, (e) -> resourceService.organizationAdd(Mono.just(Company.from(event).build())).subscribe());
    }

    @EventHandler
    public void on(CompanyUpdatedEvent event) {
        logEvent(event.getClass().getName(), event, (e) -> resourceService.organizationAdd(Mono.just(Company.from(event).build())).subscribe());
    }

    private <T, R> void logEvent(String type, T value, Function<T, R> eventFunc) {
        log.info("An event of type {} have been recieved. value = [{}].", type, value);
        final R proceeded = eventFunc.apply(value);
        log.info("The event of type {} have been successfully proceeded. Result: [{}]", type, proceeded);
    }
}
