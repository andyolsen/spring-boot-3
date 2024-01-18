package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/productSuggestions")
@CrossOrigin
@Profile("with-kafka")
public class ProductSuggestionControllerWithKafka {

	@Autowired
	private ProductSuggestionCrudRepository repository;

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;

	private final String TOPIC_NAME = "product_suggestions_topic";

	@GetMapping
    public ResponseEntity<Iterable<ProductSuggestion>> getAllProductSuggestions() {
    	Iterable<ProductSuggestion> result = repository.findAll();
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductSuggestion> getProductSuggestion(@PathVariable long id) {
		Optional<ProductSuggestion> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(optional.get());
		}
	}

	@PostMapping
	public ResponseEntity<ProductSuggestion> insertProductSuggestion(@RequestBody ProductSuggestion productSuggestion) {

		productSuggestion = repository.save(productSuggestion);

		kafkaTemplate.send(TOPIC_NAME, "inserted", productSuggestion.toString());

		URI uri = URI.create("/productSuggestions/" + productSuggestion.getId());
		return ResponseEntity.created(uri).body(productSuggestion);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllProductSuggestions() {
		repository.deleteAll();
		kafkaTemplate.send(TOPIC_NAME, "deletedAll", "");
		return ResponseEntity.ok().build();
	}

	@PutMapping("/modifyPrice/{id}")
	public ResponseEntity<Void> modifyPrice(@PathVariable long id, @RequestParam double newPrice) {

		if (repository.modifyPrice(id, newPrice) == 0) {
			return ResponseEntity.notFound().build();
		}
		else {
			String message = String.format("ProductSuggestion ID %d, new price %.2f", id, newPrice);
			kafkaTemplate.send(TOPIC_NAME, "modifiedPrice", message);
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping("/modifySales/{id}")
	public ResponseEntity<Void> modifySales(@PathVariable long id, @RequestParam long newSales) {

		if (repository.modifySales(id, newSales) == 0) {
			return ResponseEntity.notFound().build();
		}
		else {
			String message = String.format("ProductSuggestion ID %d, new sales %d", id, newSales);
			kafkaTemplate.send(TOPIC_NAME, "modifiedSales", message);
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping("/increasePriceForPopularProducts")
	public ResponseEntity<Void> increasePriceForPopularProducts(@RequestParam long sales) {
		repository.increasePriceForPopularProducts(sales);
		String message = String.format("ProductSuggestion increased price for sales >= %d", sales);
		kafkaTemplate.send(TOPIC_NAME, "increasedPriceForPopularProducts", message);
		return ResponseEntity.ok().build();
	}
}