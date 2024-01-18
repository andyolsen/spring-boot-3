package demo.taskexecution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		// Uncomment one of the following statements...
		// demoSync(ctx);
		// demoSimpleAsync(ctx);
		// demoAsyncUsingThreadPool(ctx);
		// demoAsyncUsingThreadPoolWithResults(ctx);
	}

	public static void demoSync(ApplicationContext ctx) {
		TaskExecutor ex = ctx.getBean("syncTaskExecutor", TaskExecutor.class);
		Util.display("Before");
		ex.execute(new MyRunnableTask("My task", 5));
		Util.display("After");
	}

	public static void demoSimpleAsync(ApplicationContext ctx) {
		TaskExecutor ex = ctx.getBean("simpleAsyncTaskExecutor", TaskExecutor.class);
		Util.display("Before");
		for (int i = 0; i < 10; i++) 
			ex.execute(new MyRunnableTask("My task " + i, 5));
		Util.display("After");
	}

	public static void demoAsyncUsingThreadPool(ApplicationContext ctx) {
		TaskExecutor ex = ctx.getBean("threadPoolTaskExecutor", TaskExecutor.class);
		Util.display("Before");
		for (int i = 0; i < 10; i++) 
			ex.execute(new MyRunnableTask("My task " + i, i + 1));
		Util.display("After");
	}

	public static void demoAsyncUsingThreadPoolWithResults(ApplicationContext ctx) {
	
		ThreadPoolTaskExecutor ex = ctx.getBean("threadPoolTaskExecutor", ThreadPoolTaskExecutor.class);

		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		
		futures.add(ex.submit(new MyCallableTask("My task 0", 1)));
		futures.add(ex.submit(new MyCallableTask("My task 1", 2)));
		futures.add(ex.submit(new MyCallableTask("My task 2", 3)));
		futures.add(ex.submit(new MyCallableTask("My task 3", 4)));
		
		// Wait a bit for all those tasks to finish.
		try {
			Thread.sleep(1000);
			System.out.printf("Just see if result 0 is ready: %b\n", futures.get(0).isDone());
			
			Thread.sleep(1000);
			System.out.printf("Just see if result 0 is ready: %b\n", futures.get(0).isDone());

			// Can cancel a task...
			// futures.get(0).cancel(true);

			Thread.sleep(5000);
			System.out.printf("Just see if result 0 is ready: %b\n", futures.get(0).isDone());
		} 
		catch (InterruptedException e) {}
		
		// Get the results from the Future objects.
		for (int i = 0; i < futures.size(); i++) {
			
			try {
				int result = futures.get(i).get(1, TimeUnit.SECONDS);
				System.out.printf("My task %d result: %d\n", i, result);
			}
			catch (ExecutionException e) {
				System.out.printf("My task %d Execution Exception\n", i);
			}
			catch (InterruptedException e) {
				System.out.printf("My task %d Execution Exception\n", i);
			}
			catch (TimeoutException e) {
				System.out.printf("My task %d Execution Exception\n", i);
			}
		}
	}
}
