package mypackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class App_MoreAspects {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(App_MoreAspects.class);

		BankService service = context.getBean(BankService.class);
		service.doDeposit(1, 100);
		service.doWithdraw(1, 25);
	}
}
