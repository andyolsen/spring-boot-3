package demo.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("newCustomer", new Customer());
		model.addAttribute("Customers", Repository.getAllCustomers());
		model.addAttribute("Products", Repository.getAllProducts());
		return "home";
	}
	
	@RequestMapping(value="/addCustomer")
	public String handleAddCustomer(@ModelAttribute Customer newCustomer, Model model){
		System.out.println("Adding new customer: " + newCustomer);
		Repository.saveCustomer(newCustomer);
		return home(model);
	}
	
	@RequestMapping(value="/buyProduct", method = RequestMethod.POST)
	public String handleBuyProduct(Model model, @RequestParam(value="customerID") int customerID, @RequestParam(value="productID") int productID){
		System.out.println("Customer " + customerID + " has just bought product " + productID);
		Repository.customerBuysProduct(customerID, productID);
		return home(model);
	}
}
