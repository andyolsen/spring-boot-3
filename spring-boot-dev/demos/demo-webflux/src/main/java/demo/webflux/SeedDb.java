package demo.webflux;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
@Component
@Profile("onlyForDemoPurposes")
public class SeedDb implements ApplicationListener<ApplicationReadyEvent> {

    private final TxRepository repo;

    public SeedDb(TxRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        repo.deleteAll()
            .thenMany(Flux.just(100.0, 200.0, 300.0)
                          .map(amount -> new Tx(UUID.randomUUID().toString(), amount, LocalDateTime.now()))
                          .flatMap(tx -> repo.save(tx))
            )
            .thenMany(repo.findAll())
            .subscribe(tx -> log.info("Tx document successfully inserted: " + tx));
    }
}
