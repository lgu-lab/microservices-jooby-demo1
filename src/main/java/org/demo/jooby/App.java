package org.demo.jooby;

import org.demo.controllers.PersonController;
import org.jooby.Jooby;
import org.jooby.json.Jackson;

public class App extends Jooby {
	
	{
	    port( 8081 );  // http://localhost:8081/
	    securePort( 8443 );  // https://localhost:8443/
	}
	
	{
		onStart(() -> {
			System.out.println("starting Jooby app");
		});

		onStarted(() -> {
			System.out.println("Jooby app started");
		});

		onStop(() -> {
			System.out.println("stopping app");
		});

	}
	
	{	
		use(new Jackson()); // Automatically switch to JSON ( Content-Type	application/json;charset=utf-8 )
		
	    get( "/hello", () -> "Hello from Jooby"); // http://localhost:8081/hello

	    get( "/user", req -> "All users ");  // "/user" and "/user/" 
	    get( "/user/:id", req -> "User for id " + req.param("id").value() );
	}
	
	// MVC controllers 
	{
		use(PersonController.class);
	}
	
    public static void main(String[] args) {
    	
    	// Run with default port 8080
    	// message: Not Found(404)

    	
        run(App::new, args);
    }
}