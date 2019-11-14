package org.demo.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.demo.entities.Person;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

@Path("/person")
public class PersonController {

	//@Path("/")
	@GET
	public List<Person> getAll() {

		List<Person> list = new LinkedList<>();
		return list;
	}

	@Path({ "/:id" })
	@GET
	public Person getById(final Optional<Integer> idParam) {
		int id = idParam.orElse(0) ;
		Person person = new Person();
		person.setId(id);
		person.setFirstName("Bart");
		person.setLastName("Simpson");
		return person;
	}
}
