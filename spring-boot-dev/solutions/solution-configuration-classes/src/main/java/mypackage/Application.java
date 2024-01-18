package mypackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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

        // Get total cost of items in basket.
        double totalCost = service.calculateCartCost();
        System.out.printf("Total cart cost is £%.2f\n", totalCost);

        // Display info about when the customer started shopping.
        ShoppingSession shoppingSession = context.getBean(ShoppingSession.class);
        shoppingSession.displayStartDateTime();
    }
}