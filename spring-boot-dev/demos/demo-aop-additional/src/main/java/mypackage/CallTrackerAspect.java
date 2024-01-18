package mypackage;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CallTrackerAspect {

	@DeclareParents(value = "mypackage.*", defaultImpl = CallTrackerImpl.class)
	public CallTracker mixin;

	@Pointcut("execution(* mypackage.*.*(..))")
	private void opCall() {
	}

	@AfterReturning(pointcut = "opCall() && this(tracker)")
	public void trackNormalCall(CallTracker tracker) {
		tracker.markNormal();
	}

	@AfterThrowing(pointcut = "opCall() && this(tracker)", throwing = "t")
	public void trackFailingCall(CallTracker tracker, Throwable t) {
		tracker.markFailing();
	}
}
