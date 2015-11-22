package com.jorgefigueiredo.rabbitmq.demos;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;


public class SimpleQueueReadAndWrite 
{
	private final static String QUEUE_NAME = "simple_queue";
	private final static String HOST = "localhost";
	
	
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        System.out.println( "Start..." );
        
        
        for(int r = 0; r < 5; r++) {
        	
        	final int n = r;
	        run(new Runnable() {
	
				public void run() {
				
					try {
						
						receiveMessage("A" + n);	
						
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
				}
	        });
        }
        
        
        run(new Runnable() {

			public void run() {
				
				try {
					
					for(int i=0; i<10; i++) {
						
						sendMessage();
						Thread.sleep(500);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
        });
        
        
        System.out.println("Press any key to exit.");
        System.in.read();
    }
    
	private static void run(Runnable runnable) {
		
		Thread thread = new Thread(runnable);
		
		thread.start();
		
	}
    
    private static void receiveMessage(String receiverName) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException
    {
    	
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost(HOST);
    	
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    	
    	QueueingConsumer consumer = new QueueingConsumer(channel);
    	
    	channel.basicConsume(QUEUE_NAME, true, consumer);
    	
    	while(true) {
    		
    		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
    		
    		String message = new String(delivery.getBody());
    		System.out.println(" ["+ receiverName +"] Received '" + message +"'");
    		
    	}
    
    }

	private static void sendMessage() throws IOException {
		Connection connection = null;
        Channel channel = null;
        try
        {
        
	        ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost(HOST);
	        
	        connection = factory.newConnection();
	        channel = connection.createChannel();
	        
	        
	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	        
	        String message = "Hello World!";
	        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	        
	        System.out.print(" [x] Sent '" + message + "'");
        
        }
        finally {
        	
        	channel.close();
        	connection.close();
        	
        }
	}
}
