package demo.asyncAndScheduledMethods;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class MyBean {

	@Async
	public void myAsyncMethod() {
		Util.display("Start of myAsyncMethod");
		try {
			Thread.sleep(5_000);
		}
		catch (InterruptedException e) {}
		Util.display("End of myAsyncMethod");
	}

	@Async
	public Future<Integer> myAsyncMethodWithResult() {
		Util.display("Start of myAsyncMethodWithResult");
		try {
			Thread.sleep(5_000);
		}
		catch (InterruptedException e) {}
		Util.display("End of myAsyncMethodWithResult");
		return new AsyncResult<Integer>(42);
	}
	
	@Scheduled(fixedDelay=5000)
	public void myScheduledMethodWithFixedDelay5Seconds() {
		Util.display("Start of myScheduledMethodWithFixedDelay5Seconds()");
		try {
			Thread.sleep(2_000);
		}
		catch (InterruptedException e) {}
		Util.display("End of myScheduledMethodWithFixedDelay5Seconds()");
	}

	@Scheduled(fixedRate=5000)
	public void myScheduledMethodAtFixedRate5Seconds() {
		Util.display("Start of myScheduledMethodAtFixedRate5Seconds()");
		try {
			Thread.sleep(2_000);
		}
		catch (InterruptedException e) {}
		Util.display("End of myScheduledMethodAtFixedRate5Seconds()");
	}

	@Scheduled(cron="*/5 * * * * MON-FRI")
	public void myScheduledMethodBasedOnCronTrigger() {
		Util.display("Start of myScheduledMethodBasedOnCronTrigger()");
		try {
			Thread.sleep(2_000);
		}
		catch (InterruptedException e) {}
		Util.display("End of myScheduledMethodBasedOnCronTrigger()");
	}
}
