package org.demo.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.demo.entities.Person;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.mvc.Body;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

@Path("/person")
public class PersonController {

	// All annotations are identical to JAX-RS
	@GET 
	@Path("/")
	public List<Person> getAll() {

		List<Person> list = new LinkedList<>();
		return list;
	}

	@GET
//	@Path("/:id")
	@Path("/{id}")
//	public Result getById(Optional<Integer> id) { // same name as name in annotation (here "id")
	public Result getById(int id) { // same name as name in annotation (here "id")
		// if invalid id (not a number)
		// status 400 : java.lang.NumberFormatException: For input string: "aaa" 
		if ( id < 1000 ) {
			// FOUND
			Person person = new Person();
			person.setId(id);
			person.setFirstName("Bart");
			person.setLastName("Simpson");
			return Results.with(person, Status.OK);
		}
		else {
			// NOT FOUND
			//return Results.with(Status.NOT_FOUND); // Without body
			return Results.with("Not found", Status.NOT_FOUND); // With body
		}
	}

	@GET
//	@Path("/multi/:id1/:id2")
	@Path("/multi/{id1}/{id2}")
	public Result getByCompositeId(Optional<Integer> id1, Optional<Integer> id2) {
		// status 400 : java.lang.NumberFormatException: For input string: "aaa" 
		if ( id1.isPresent() && id2.isPresent() ) {
			
			if ( id1.get() < 1000 ) {
				// FOUND
				Person person = new Person();
				person.setId(id1.get());
				person.setFirstName("Bart " + id2.get() );
				person.setLastName("Simpson");
				return Results.with(person, Status.OK);
			}
		}
		// NOT FOUND
		return Results.with("Not found", Status.NOT_FOUND); // With body
	}
	
	@POST
	public Result create(@Body Person person) {
		System.out.println("Person received in body : " + person);
		if ( person.getId() > 1000 ) {
			return Results.with("already exists", Status.CONFLICT);
		}
		else {
			return Results.with(person, Status.CREATED);
		}
	}

}
