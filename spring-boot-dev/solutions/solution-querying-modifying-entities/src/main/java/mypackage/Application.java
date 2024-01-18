package mypackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        CartService service = context.getBean(CartServiceImpl.class);
        service.addItemToCart(0, 1);
        service.addItemToCart(2, 1);
        service.addItemToCart(4, 3);
        service.addItemToCart(4, 2);
        service.removeItemFromCart(0);
        service.addItemToCart(2, 10);
        service.addItemToCart(3, 100);
        service.displayRecentActivity();

        System.out.printf("Total cart cost £%.2f, sales tax £%.2f, delivery charge £%.2f\n",
                service.calculateCartCost(),
                service.calculateSalesTax(),
                service.calculateDeliveryCharge());

        ShoppingSession shoppingSession = context.getBean(ShoppingSession.class);
        shoppingSession.displayStartDateTime();

        // Get profile-specific properties.
        ResourcesBean resourcesBean = context.getBean(ResourcesBean.class);
        System.out.println("Profile-specific properties: " + resourcesBean);

        // Sample code, to test our ProductSuggestionRepository implementation.
        ProductSuggestionRepository repo = context.getBean(ProductSuggestionRepository.class);

        System.out.println("\nAll ProductSuggestion objects:");
        for (ProductSuggestion ps: repo.getProductSuggestions()) {
            System.out.println(ps);
        }

        long id = repo.insertProductSuggestion(new ProductSuggestion("Skis", 600, 1000));
        repo.modifyPrice(id, 600.99);
        repo.modifySales(id, 1001);
        ProductSuggestion modifiedPs = repo.getProductSuggestion(id);
        System.out.printf("\nProduct suggestion %d, details: %s\n", id, modifiedPs);

        int rowsAffected = repo.increasePriceForPopularProducts(500);
        System.out.printf("\nAll ProductSuggestion objects after increasing price for %d products:\n", rowsAffected);
        for (ProductSuggestion ps: repo.getProductSuggestions())
            System.out.println(ps);
    }
}