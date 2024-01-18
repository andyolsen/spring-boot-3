package demo.webflux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Log4j2
@DataMongoTest
@Import(TxService.class)
public class TxServiceQueryTest {

    private final TxService service;
    private final TxRepository repo;

    public TxServiceQueryTest(@Autowired TxService service, @Autowired TxRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @Test
    public void getAllTx_returnsAllTx() {

        Flux<Tx> toSave = Flux.just(
                new Tx("AAAA", 111, LocalDateTime.parse("1997-07-02T01:06:30.000")),
                new Tx("BBBB", 222, LocalDateTime.parse("1997-07-02T01:20:00.000")),
                new Tx("CCCC", 333, LocalDateTime.parse("1964-12-03T08:30:00.000")),
                new Tx("DDDD", 444, LocalDateTime.parse("1965-01-19T12:00:00.000"))
        );

        Mono<Void> deletedTxs = repo.deleteAll();
        StepVerifier
                .create(deletedTxs)
                .verifyComplete();

        Flux<Tx> savedTxs = repo.saveAll(toSave);
        StepVerifier
                .create(savedTxs)
                .expectNextCount(4)
                .verifyComplete();

        savedTxs.subscribe(s -> System.out.println("Saved!" + s));

        Flux<Tx> gottenTxs = service.getAll();

        Predicate<Tx> testTx = gtx -> toSave.any(stx -> {
            System.out.println("GOTTEN: " + gtx.getId() + " " + gtx.getAmount() + " " + gtx.getWhen());
            System.out.println("SAVED:  " + stx.getId() + " " + stx.getAmount() + " " + stx.getWhen());
            System.out.println("RESULT: " + stx.equals(gtx) + "\n");
            return stx.equals(gtx);
        }).block();

        StepVerifier.create(gottenTxs)
                .expectNextMatches(testTx)
                .expectNextMatches(testTx)
                .expectNextMatches(testTx)
                .expectNextMatches(testTx)
                .verifyComplete();
    }
}