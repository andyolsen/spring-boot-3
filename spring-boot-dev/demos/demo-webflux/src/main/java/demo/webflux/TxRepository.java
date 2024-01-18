package demo.webflux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TxRepository extends ReactiveMongoRepository<Tx, String> {
}