package demo.asyncAndScheduledMethods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.Future;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		// Uncomment one of the following statements...
		// demoAsyncMethods(ctx);
		// demoAsyncMethodsWithResult(ctx);
	}

	public static void demoAsyncMethods(ApplicationContext ctx) {

		MyBean bean = ctx.getBean(MyBean.class);
		
		Util.display("Before");
		
		bean.myAsyncMethod();

		try {
			Thread.sleep(10_000);
		}
		catch (InterruptedException ex) {}
		
		Util.display("After");
	}

	public static void demoAsyncMethodsWithResult(ApplicationContext ctx) {

		MyBean bean = ctx.getBean(MyBean.class);
		
		Util.display("Before");
		
		Future<Integer> futureResult = bean.myAsyncMethodWithResult();

		try {
			Thread.sleep(10_000);
		}
		catch (InterruptedException ex) {}
		
		try {
			int result = futureResult.get();
			Util.display("Result is " + result);
		}
		catch (Exception ex) {
			System.out.println("Exception occurred trying to get future result: " + ex.getMessage());
		}
		
		Util.display("After");
	}
}
