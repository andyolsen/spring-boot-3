package mypackage;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeedDb {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@PostConstruct
    public void init() {
		doInsert("Swansea City shirt", 45.99, 20_000);
        doInsert("Cardiff City shirt", 0.99, 10);
        doInsert("Lear jet", 15_000_000, 100);
    }

    private void doInsert(String description, double recommendedPrice, long estimatedAnnualSales) {
        jdbcTemplate.update("insert into product_suggestion (description, recommended_price, estimated_annual_sales) values (?, ?, ?)",
                description, recommendedPrice, estimatedAnnualSales);
    }
}
