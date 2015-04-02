package guice_helloworld.guice_helloworld;


import com.google.inject.Guice;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;

public class App 
{
    public static void main( String[] args )
    {
        
    	Injector injector = Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				bind(Contacts.class).to(SimCard.class);
			}
    		
    	});
    	
    	Contacts contacts = injector.getInstance(Contacts.class);
    	
    	if(contacts == null) {
    		throw new AssertionError();
    	}
    	else {
    		System.out.println("It Worked!");
    	}
    	
    }
}
