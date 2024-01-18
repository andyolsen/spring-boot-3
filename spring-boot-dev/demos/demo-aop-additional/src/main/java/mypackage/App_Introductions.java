package mypackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class App_Introductions {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(App_Introductions.class);
		MyService bean = context.getBean(MyService.class);

		try {
			bean.goodOp1();
			bean.goodOp2();
			bean.badOp();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		CallTracker t = (CallTracker) bean;
		System.out.println("Bean tracking details: " + t.describe());
	}
}
