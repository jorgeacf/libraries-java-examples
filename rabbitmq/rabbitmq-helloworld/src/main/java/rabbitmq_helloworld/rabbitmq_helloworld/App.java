package rabbitmq_helloworld.rabbitmq_helloworld;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;


public class App 
{
	private final static String QUEUE_NAME = "hello";
	
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        System.out.println( "Start..." );
        
        
        
        new Thread(new Runnable() {

			public void run() {
			
				try {
					System.out.println("Receiving messages...");
					receiveMessage();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ShutdownSignalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ConsumerCancelledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        }).start();
        
        Thread.sleep(3 * 1000);
        
        System.out.println("Sending message...");
        sendMessage();
        
    }
    
    private static void receiveMessage() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException
    {
    
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    	
    	QueueingConsumer consumer = new QueueingConsumer(channel);
    	channel.basicConsume(QUEUE_NAME, true, consumer);
    	
    	while(true) {
    		
    		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
    		
    		String message = new String(delivery.getBody());
    		System.out.println(" [x] Received '" + message +"'");
    		
    	}
    
    }

	private static void sendMessage() throws IOException {
		Connection connection = null;
        Channel channel = null;
        try
        {
        
	        ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        
	        connection = factory.newConnection();
	        channel = connection.createChannel();
	        
	        
	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	        
	        String message = "Hello World!";
	        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	        
	        System.out.println(" [x] Sent '" + message + "'");
        
        }
        finally {
        	
        	channel.close();
        	connection.close();
        	
        }
	}
}
