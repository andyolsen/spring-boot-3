package demo.taskexecution;

import java.util.concurrent.Callable;

class MyCallableTask implements Callable<Integer> {
	
	private String taskName;
	private int iterations;
	
	public MyCallableTask(String name, int num) {
		taskName = name;
		iterations = num;
	}
	
	public Integer call() {
		int total = 0;
		for (int i = 1; i <= iterations; i++) {
            try {
                total += i;
                String message = String.format("%s %d", taskName, i);
                Util.display(message);
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) {}
        }
		return total;
	}
}