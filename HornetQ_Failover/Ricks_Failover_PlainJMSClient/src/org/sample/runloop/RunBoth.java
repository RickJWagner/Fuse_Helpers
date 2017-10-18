package org.sample.runloop;

public class RunBoth {
	
	private static String PRODUCE = "produce";
	private static String CONSUME = "consume";
	
	public static void main(String[] args) throws Exception {
		RunBoth runboth = new RunBoth();		
		Runner producer = runboth.new Runner(PRODUCE);
		Runner consumer = runboth.new Runner(CONSUME);
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		producerThread.start();
		consumerThread.start();
		
		
	}
	
	
	class Runner implements Runnable{
		
		private String task;
		
		Runner(String task){
			this.task = task;
		}

		@Override
		public void run() {
			try {
				if (PRODUCE == task) {
					Producer.produce();
				}else if (CONSUME == task) {
					Consumer.consume();
				}
		}catch(Exception e) {}
		
		}
	}

}
