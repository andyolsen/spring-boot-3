package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/productSuggestions")
@CrossOrigin
public class ProductSuggestionController {

	@Autowired
	private ProductSuggestionCrudRepository repository;

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
		URI uri = URI.create("/productSuggestions/" + productSuggestion.getId());
		return ResponseEntity.created(uri).body(productSuggestion);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllProductSuggestions() {
		repository.deleteAll();
		return ResponseEntity.ok().build();
	}

	@PutMapping("/modifyPrice/{id}")
	public ResponseEntity<Void> modifyPrice(@PathVariable long id, @RequestParam double newPrice) {
		if (repository.modifyPrice(id, newPrice) == 0) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping("/modifySales/{id}")
	public ResponseEntity<Void> modifySales(@PathVariable long id, @RequestParam long newSales) {
		if (repository.modifySales(id, newSales) == 0) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping("/increasePriceForPopularProducts")
	public ResponseEntity<Void> increasePriceForPopularProducts(@RequestParam long sales) {
		repository.increasePriceForPopularProducts(sales);
		return ResponseEntity.ok().build();
	}
}
