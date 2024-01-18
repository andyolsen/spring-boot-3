package demo.taskexecution;

class MyRunnableTask implements Runnable {
	
	private String taskName;
	private int iterations;
	
	public MyRunnableTask(String name, int num) {
		taskName = name;
		iterations = num;
	}
	
	public void run() {
		for (int i = 1; i <= iterations; i++) {
            try {
                String message = String.format("%s %d", taskName, i);
                Util.display(message);
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) {}
        }
	}
}