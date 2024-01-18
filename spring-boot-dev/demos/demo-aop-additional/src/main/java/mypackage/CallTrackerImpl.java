package mypackage;

import org.springframework.stereotype.Component;

@Component
public class CallTrackerImpl implements CallTracker {

	private int normalCalls, failingCalls;

	public void markNormal() {
		normalCalls++;
	}

	public void markFailing() {
		failingCalls++;
	}

	public int countNormalCalls() {
		return normalCalls;
	}

	public int countFailingCalls() {
		return failingCalls;
	}

	public String describe() {
		return toString();
	}

	@Override
	public String toString() {
		return "CallTrackerImpl: " + 
		       " normal calls="    + normalCalls + 
		       " failing calls="   + failingCalls;
	}
}
