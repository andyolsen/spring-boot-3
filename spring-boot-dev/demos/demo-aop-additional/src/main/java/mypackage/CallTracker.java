package mypackage;

public interface CallTracker {
	void markNormal();
	void markFailing();
	int countNormalCalls();
	int countFailingCalls();
	String describe();
}
