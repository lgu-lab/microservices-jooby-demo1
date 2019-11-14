package org.demo.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.demo.entities.Person;
import org.jooby.Response;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.mvc.GET;
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

//	@GET
//	@Path("/:id")
////	@Path("/{id}")
//	public Person getById(Optional<Integer> id) { // same name as name in annotation (here "id")
//		// if invalid id (not a number)
//		// status 400 : java.lang.NumberFormatException: For input string: "aaa" 
//		if ( id.isPresent() ) {
//			Person person = new Person();
//			person.setId(id.get());
//			person.setFirstName("Bart");
//			person.setLastName("Simpson");
//			return person;
//		}
//		else {
//			Person person = new Person();
//			person.setId(0);
//			person.setFirstName("???");
//			person.setLastName("???");
//			return person;
//		}
//	}

	@GET
	@Path("/:id")
//	public Result getById(Response response, Optional<Integer> id) { // same name as name in annotation (here "id")
	public Result getById(Optional<Integer> id) { // same name as name in annotation (here "id")
		// if invalid id (not a number)
		// status 400 : java.lang.NumberFormatException: For input string: "aaa" 
		if ( id.isPresent() ) {
			
			if ( id.get() < 1000 ) {
				// FOUND
				Person person = new Person();
				person.setId(id.get());
				person.setFirstName("Bart");
				person.setLastName("Simpson");

//				response.status(Status.OK);
//				response.send(person);
//				return person;
				return Results.with(person, Status.OK);
			}
//			else {
//				// NOT FOUND
//			}
//
//		}
//		else {
//			return Results.with(Status.NOT_FOUND);
		}
		// NOT FOUND
		//return Results.with(Status.NOT_FOUND); // Without body
		return Results.with("Not found", Status.NOT_FOUND); // With body
	}

}
