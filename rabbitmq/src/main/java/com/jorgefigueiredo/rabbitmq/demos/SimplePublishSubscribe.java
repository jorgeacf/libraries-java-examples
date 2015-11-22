package com.jorgefigueiredo.rabbitmq.demos;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class SimplePublishSubscribe {

	private static final String EXCHANGE_NAME = "logs";
	private static final String HOST = "localhost";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		int loggers = 5;
		
		final CountDownLatch countDownLatch = new CountDownLatch(5);
		
		for(int s = 0; s < loggers; s++) {
			
			final int logger = s;
			run(new Runnable() {

				public void run() {
					
					try {
						
						receiveLogMessage("R" + logger);
						
						countDownLatch.countDown();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} 
			});
			
		}
		
		countDownLatch.await();
		
		run(new Runnable() {

			public void run() {
				
				try {
					
					for(int m = 0; m < 5; m++) {
						
						emitLog(String.format("Message #%s...", m));
						
						Thread.sleep(3*1000);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} });
		
	}
	
	
	private static void run(Runnable runnable) {
		
		Thread thread = new Thread(runnable);
		
		thread.start();
		
	}
	
	
	private static void emitLog(String message) throws IOException {
		
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
		
		channel.close();
		connection.close();
		
	}
	
	private static void receiveLogMessage(final String logger) throws IOException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		Consumer consumer = new DefaultConsumer(channel) {
			
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope,
		                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
		    	  
		        String message = new String(body, "UTF-8");
		        System.out.println(" [x] Received '" + message + "' on receiver #" + logger);
		      }
		};
		
		channel.basicConsume(queueName, true, consumer);
		
	}

}
