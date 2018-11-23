package com.ia.dfms.converter;

import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Converter<T, R> {
    /**
     * Convert an object to another one.
     * 
     * @param source object that need to be converted.
     * @return an optional that contain the converted object or an empty optional if something went wrong during the conversion process.
     * @throws Throw a null pointer exception if the provided object is null.
     */
    Mono<R> convert(@NonNull Mono<T> source);

    /**
     * Convert an object to another one.
     * 
     * @param source object that need to be converted.
     * @return an optional that contain the converted object or an empty optional if something went wrong during the conversion process.
     * @throws Throw a null pointer exception if the provided object is null.
     */
    Mono<T> reverse(@NonNull Mono<R> source);

    /**
     * Convert a flux of objects to another collection based on the convert operation implementation.
     * 
     * @param sources Flux of object that need to be converted.
     * @return The associated converted flux.
     */
    default Flux<R> convert(Flux<T> sources) {
        return sources.flatMap(t -> convert(Mono.just(t)));
    }

    /**
     * Reverse operation of the convert operation
     * 
     * @param targets flux of object that need to be converted.
     * @return The reversed converted flux.
     */
    default Flux<T> reverse(Flux<R> targets) {
        return targets.flatMap(t -> reverse(Mono.just(t)));
    }
}
