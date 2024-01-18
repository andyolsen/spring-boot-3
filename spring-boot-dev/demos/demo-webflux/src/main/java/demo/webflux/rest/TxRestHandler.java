package demo.webflux.rest;

import demo.webflux.Tx;
import demo.webflux.TxService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;

@Log4j2
@Component
@Profile("handler-style-endpoint")
public class TxRestHandler {

    private final TxService service;

    TxRestHandler(TxService service) {
        this.service = service;
    }

    Mono<ServerResponse> getAll(ServerRequest req) {

        log.info("handler-style-endpoint, getAll()");

        Flux<Tx> gotten = this.service.getAll();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gotten, Tx.class);
    }

    Mono<ServerResponse> getById(ServerRequest req) {

        log.info("handler-style-endpoint, getById()");

        String id = req.pathVariable("id");

        Mono<Tx> gotten = this.service.getById(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gotten, Tx.class);
    }

    Mono<ServerResponse> create(ServerRequest req) {

        log.info("handler-style-endpoint, create()");

        Flux<Tx> created = req.bodyToFlux(Tx.class)
                              .flatMap(tx -> this.service.create(tx.getAmount()));

        return Mono.from(created)
                   .flatMap(tx -> ServerResponse.created(URI.create("/tx/" + tx.getId()))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .build());
    }

    Mono<ServerResponse> update(ServerRequest req) {

        log.info("handler-style-endpoint, update()");

        String id = req.pathVariable("id");

        Flux<Tx> updated = req.bodyToFlux(Tx.class)
                              .flatMap(tx -> this.service.update(id, tx.getAmount(), tx.getWhen()));

        return ServerResponse.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(updated, Tx.class);
    }

    Mono<ServerResponse> delete(ServerRequest req) {

        log.info("handler-style-endpoint, delete()");

        String id = req.pathVariable("id");

        Mono<Tx> deleted = this.service.delete(id);

        return ServerResponse.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(deleted, Tx.class);
    }
}