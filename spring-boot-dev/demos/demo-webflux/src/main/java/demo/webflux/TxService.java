package demo.webflux;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Log4j2
@Service
public class TxService {

    private final TxRepository repo;
    private final ApplicationEventPublisher pub;

    TxService(TxRepository repo, ApplicationEventPublisher pub) {
        this.repo = repo;
        this.pub = pub;
    }

    public Mono<Long> getNum() {
        return repo.count();
    }

    public Flux<Tx> getAll() {
        return repo.findAll();
    }

    public Mono<Tx> getById(String id) {
        return repo.findById(id);
    }

    public Mono<Tx> create(double amount) {
        checkAmount(amount);
        return repo.save(new Tx(null, amount, LocalDateTime.now()))
                   .doOnSuccess(tx -> pub.publishEvent(new TxCreatedEvent(tx)));
    }

    public Mono<Tx> update(String id, double amount, LocalDateTime when) {
        checkAmount(amount);
        return repo.findById(id)
                   .map(t -> new Tx(t.getId(), amount, when))
                   .flatMap(t -> repo.save(t).thenReturn(t));
    }

    public Mono<Tx> delete(String id) {
        return repo.findById(id)
                   .flatMap(t -> repo.deleteById(t.getId()).thenReturn(t));
    }

    private void checkAmount(double amount) {
        if (amount > 1_000_000) {
            pub.publishEvent(new TxHighValueEvent(amount));
        }
    }
}
