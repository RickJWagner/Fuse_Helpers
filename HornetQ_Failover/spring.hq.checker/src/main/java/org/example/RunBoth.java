package org.example;


import java.net.URISyntaxException;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class RunBoth {

	private static String PRODUCE = "produce";
	private static String CONSUME = "consume";
		
	public static void main(String[] args) throws URISyntaxException, Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		
		RunBoth runboth = new RunBoth();		
		Runner producer = runboth.new Runner(context, PRODUCE);
		Runner consumer = runboth.new Runner(context, CONSUME);
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		producerThread.start();
		consumerThread.start();
	}
	

	
	class Runner implements Runnable{
		
		private String task;
		private ClassPathXmlApplicationContext context = null;
		
		Runner(ClassPathXmlApplicationContext context, String task){
			this.context = context;
			this.task = task;
		}

		public void run() {
			try {
				if (PRODUCE == task) {
					SpringJmsProducer springJmsProducer = 
							(SpringJmsProducer) context.getBean("springJmsProducer");
					long kount = 0;
					while (true) {
						String msg = ("SomeTask " + kount++);
						springJmsProducer.sendMessage(msg);
						System.out.println("Just sent " + msg);
						Thread.sleep(2000);
					}
					
				}else if (CONSUME == task) {
					SpringJmsConsumer springJmsConsumer = 
							(SpringJmsConsumer) context.getBean("springJmsConsumer");
					while (true) {
						System.out.println("Consumer just recieved:" + springJmsConsumer.receiveMessage());	
					}

				}
		}catch(Exception e) {}
		
		}
	}

	
	
}