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

        System.out.printf("Total cart cost £%.2f, sales tax £%.2f, delivery charge £%.2f\n",
                service.calculateCartCost(),
                service.calculateSalesTax(),
                service.calculateDeliveryCharge());

        ShoppingSession shoppingSession = context.getBean(ShoppingSession.class);
        shoppingSession.displayStartDateTime();

        // Get profile-specific properties.
        ResourcesBean resourcesBean = context.getBean(ResourcesBean.class);
        System.out.println("Profile-specific properties: " + resourcesBean);
    }
}