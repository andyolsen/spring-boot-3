package demo.webflux.rest;

import demo.webflux.Tx;
import demo.webflux.TxRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Log4j2
@WebFluxTest
public abstract class TxRestBaseTest {

    @MockBean
    private TxRepository repo;

    private final WebTestClient client;

    public TxRestBaseTest(WebTestClient client) {
        this.client = client;
    }

    @Test
    public void getAll() {

        Mockito.when(this.repo.findAll())
               .thenReturn(Flux.just(new Tx("1", 1111, LocalDateTime.now()),
                                     new Tx("2", 2222, LocalDateTime.now())));

        this.client
                .get()
                .uri("/tx")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo("1")
                .jsonPath("$.[0].amount").isEqualTo(1111)
                .jsonPath("$.[1].id").isEqualTo("2")
                .jsonPath("$.[1].amount").isEqualTo(2222);
    }

    @Test
    public void getById() {

        Tx tx = new Tx("3", 3333, LocalDateTime.now());

        Mockito.when(this.repo.findById(tx.getId()))
                .thenReturn(Mono.just(tx));

        this.client
                .get()
                .uri("/tx/" + tx.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.amount").isEqualTo(3333);
    }

    @Test
    public void save() {

        Tx tx = new Tx("4", 4444, LocalDateTime.now());

        Mockito.when(this.repo.save(Mockito.any(Tx.class)))
               .thenReturn(Mono.just(tx));

        this.client
            .post()
            .uri("/tx")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(tx), Tx.class)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void update() {

        Tx tx = new Tx("5", 5555, LocalDateTime.now());

        Mockito.when(this.repo.findById(tx.getId()))
               .thenReturn(Mono.just(tx));

        Mockito.when(this.repo.save(tx))
               .thenReturn(Mono.just(tx));

        this.client
                .put()
                .uri("/tx/" + tx.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(tx), Tx.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void delete() {

        Tx tx = new Tx("6", 6666, LocalDateTime.now());

        Mockito.when(this.repo.findById(tx.getId()))
               .thenReturn(Mono.just(tx));

        Mockito.when(this.repo.deleteById(tx.getId()))
               .thenReturn(Mono.empty());

        this.client
            .delete()
            .uri("/tx/" + tx.getId())
            .exchange()
            .expectStatus().isOk();
    }
}
