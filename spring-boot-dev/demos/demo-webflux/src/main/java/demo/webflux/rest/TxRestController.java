package demo.webflux.rest;

import demo.webflux.Tx;
import demo.webflux.TxService;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping(value="/tx", produces=MediaType.APPLICATION_JSON_VALUE)
@Profile("controller-style-endpoint")
class TxRestController {

    private final TxService service;

    TxRestController(TxService service) {
        this.service = service;
    }

    @GetMapping
    Publisher<Tx> getAll() {
        log.info("controller-style-endpoint, getAll()");
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    Publisher<Tx> getById(@PathVariable("id") String id) {
        log.info("controller-style-endpoint, getById()");
        return this.service.getById(id);
    }

    @PostMapping
    Publisher<ResponseEntity<Tx>> create(@RequestBody Tx tx) {
        log.info("controller-style-endpoint, create()");
        return this.service
                   .create(tx.getAmount())
                   .map(t -> ResponseEntity.created(URI.create("/tx/" + t.getId()))
                                           .contentType(MediaType.APPLICATION_JSON)
                                           .build());
    }

    @PutMapping("/{id}")
    Publisher<ResponseEntity<Tx>> update(@PathVariable String id, @RequestBody Tx tx) {
        log.info("controller-style-endpoint, update()");
        return this.service
                   .update(id, tx.getAmount(), tx.getWhen())
                   .map(t -> ResponseEntity.ok()
                                           .contentType(MediaType.APPLICATION_JSON)
                                           .build());
    }

    @DeleteMapping("/{id}")
    Publisher<Tx> delete(@PathVariable String id) {
        log.info("controller-style-endpoint, delete()");
        return this.service.delete(id);
    }
}