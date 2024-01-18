package demo.taskscheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		// Uncomment one of the following statements...
		// demoFixedDelayExecution(ctx);
		// demoFixedRateExecution(ctx);
		 demoCronTriggeredExecution(ctx);
	}

	public static void demoFixedDelayExecution(ApplicationContext ctx) {
		
		ThreadPoolTaskScheduler sch = ctx.getBean(ThreadPoolTaskScheduler.class);
	
		Util.display("Scheduling a task, which prints 1,2,3 (this takes 4s). Fixed delay of 5s between completion of task and start of next.");
		sch.scheduleWithFixedDelay(new MyRunnableTask("MyRunnableTask1", 3), 5_000);
	
		try {
			Thread.sleep(20_000);
		} 
		catch (InterruptedException e) {}
		
		sch.shutdown();
	}

	public static void demoFixedRateExecution(ApplicationContext ctx) {
		
		ThreadPoolTaskScheduler sch = ctx.getBean(ThreadPoolTaskScheduler.class);
	
		Util.display("Scheduling a task, which prints 1,2,3 (this takes 4s). Fixed rate of 5s between starting each task.");
		sch.scheduleAtFixedRate(new MyRunnableTask("MyRunnableTask1", 3), 5_000);
	
		try {
			Thread.sleep(20_000);
		} 
		catch (InterruptedException e) {}
		
		sch.shutdown();
	}

	public static void demoCronTriggeredExecution(ApplicationContext ctx) {
	
		ThreadPoolTaskScheduler sch = ctx.getBean(ThreadPoolTaskScheduler.class);
	
		Util.display("Scheduling a task to run 15 minutes past each hour, but only for 9am-5pm, Monday to Friday.");
		sch.schedule(new MyRunnableTask("MyRunnableTask1", 3), new CronTrigger("0 15 9-17 * * MON-FRI"));
	}
}
