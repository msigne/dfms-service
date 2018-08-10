package com.ia.dfms.converter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Converter<T, R> {

    Mono<R> convert(Mono<T> source);

    default Flux<R> convert(Flux<T> sources) {
        return sources.flatMap(t -> convert(Mono.just(t)));
    }
}
