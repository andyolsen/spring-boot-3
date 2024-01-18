package mypackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        CartService service = context.getBean(CartServiceImpl.class);

        // Buy an Apple Mac Book Pro.
        service.addItemToCart(0, 1);

        // Buy an OLED 64in TV.
        service.addItemToCart(2, 1);

        // Buy 5 Virtual Reality HeadSets.
        service.addItemToCart(4, 3);
        service.addItemToCart(4, 2);

        // Remove the Apple Mac Book Pro from cart.
        service.removeItemFromCart(0);

        // Get total cost of items in basket.
        double totalCost = service.calculateCartCost();
        System.out.printf("Total cart cost is £%.2f\n", totalCost);

        // Display info about when the customer started shopping.
        ShoppingSession shoppingSession = context.getBean(ShoppingSession.class);
        shoppingSession.displayStartDateTime();
    }
}