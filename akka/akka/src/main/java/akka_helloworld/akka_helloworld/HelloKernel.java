package akka_helloworld.akka_helloworld;


import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;


public class HelloKernel implements Bootable {
	
	final ActorSystem system = ActorSystem.create("hellokernel");
	
	public static class HelloActor extends UntypedActor {
		final ActorRef worldActor = getContext().actorOf(
				Props.apply(WorldActor.class));

		@Override
		public void onReceive(Object message) throws Exception {
			
			if(message == "start") {
				worldActor.tell("Hello", getSelf());
			}
			else if(message instanceof String) {
				System.out.println(String.format("Received message '%s'", message));
			}
			else {
				unhandled(message);
			}
		}
	}
	
	public static class WorldActor extends UntypedActor {

		@Override
		public void onReceive(Object message) throws Exception {
			if(message instanceof String) {
				getSender().tell(((String)message).toUpperCase() + " world!",
					getSelf());
			}
			else {
				unhandled(message);
			}
				
		}
		
	}
	
	
	
	public void shutdown() {
		system.shutdown();
		
	}

	public void startup() {
		system.actorOf(Props.apply(HelloActor.class)).tell("start", null);
	}
	
	
}


