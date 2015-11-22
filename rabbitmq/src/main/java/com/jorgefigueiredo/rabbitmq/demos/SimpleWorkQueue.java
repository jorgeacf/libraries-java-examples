package com.jorgefigueiredo.rabbitmq.demos;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class SimpleWorkQueue {

	private final static String QUEUE_NAME = "simple_queue";
	private final static String HOST = "localhost";
	
	public static void main(String[] args) {
		
		
		run(new Runnable() {
			
			public void run() {
				try {
					
					for(int r = 0; r < 5; r++) {
						createReceiver("A" + r);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		run(new Runnable() {

			public void run() {
				
				try {
					
					for(int m = 0; m < 15; m++) {
						
						sendMessage(String.format("Message #%s...", m));
						
						Thread.sleep(1 * 1000);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} 
		});
		
		
	}

	
	private static void run(Runnable runnable) {
		
		Thread thread = new Thread(runnable);
		
		thread.start();
		
	}
	
	
	private static void sendMessage(String message) throws Exception {
		
		if(message == null || message.length() == 0) {
			throw new Exception("Messge is null or empty.");
		}
		
		Connection connection = null;
		Channel channel = null;
		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(HOST);
			
			connection = factory.newConnection();
			channel = connection.createChannel();
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		
		}
		finally {
			channel.close();
			connection.close();
		}
		
	}
	
	private static void createReceiver(final String receiverName) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		Consumer consumer = new DefaultConsumer(channel) {
			
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				
				String message = new String(body, "UTF-8");
				System.out.printf("Message [%s] received on receiver [%s] and thread #%s.\n", message, receiverName, Thread.currentThread().getId());
			}
			
		};
		
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
	}
	
	
}
