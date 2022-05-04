package com.othmen.test.spring.webflux.validation.xml;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * controller with method validations that are not disabled in validation.xml
 */
@RestController
@Validated
@RequestMapping(value = "/controller")
public class Controller {

    @RequestMapping(value = "/model",
            method = RequestMethod.POST,
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Mono<ResponseEntity<Model>> postModel(@Valid @RequestBody(required = true) Mono<Model> modelMono) {
        return modelMono.doOnNext(model -> System.out.println("post model " + model))
                .map(ResponseEntity::ok);
    }


    @RequestMapping(value = "/model/{id}",
            method = RequestMethod.GET,
            produces = { "application/json" }
    )
    public Mono<ResponseEntity<Model>> getModel(@NotNull @Size(min = 3, max = 3) @PathVariable("id") String id) {
        return Mono.defer(() -> Mono.just(Model.withId(id)))
                .doOnNext(model -> System.out.println("get model " + model))
                .map(ResponseEntity::ok);
    }
}
