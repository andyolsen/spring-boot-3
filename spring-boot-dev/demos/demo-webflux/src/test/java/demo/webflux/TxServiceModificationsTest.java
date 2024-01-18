package demo.webflux;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Log4j2
@DataMongoTest
@Import(TxService.class)
public class TxServiceModificationsTest {

    private final TxService service;
    private final TxRepository repo;

    public TxServiceModificationsTest(@Autowired TxService service, @Autowired TxRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @Test
    public void createTx_returnsCreatedTxWithId() {

        Mono<Tx> createdTx = service.create(1234);

        StepVerifier
                .create(createdTx)
                .expectNextMatches(tx -> tx.getId() != null && tx.getId().length() != 0)
                .verifyComplete();
    }

    @Test
    public void updateTx_txUpdated() {

        Mono<Tx> updatedTx = service.create(4321)
                                    .flatMap(tx -> service.update(tx.getId(), 8888, LocalDateTime.now()));

        StepVerifier
                .create(updatedTx)
                .expectNextMatches(tx -> tx.getAmount() == 8888)
                .verifyComplete();
    }

    @Test
    public void deleteTx_txDeleted() {

        Mono<Tx> deletedTx = service.create(5678)
                .flatMap(tx -> service.delete(tx.getId()));

        StepVerifier
                .create(deletedTx)
                .expectNextMatches(tx -> tx.getAmount() == 5678)
                .verifyComplete();
    }
}