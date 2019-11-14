package org.demo.controllers;

import java.util.Optional;

import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

@Path("/hello")
public class HelloController {

	//@Path({"/hello", "/hello/:name"})
	//@Path("/hello/:name")
	@Path("/:name")
	@GET
	public String greeting(Optional<String> name) {
	  return "Hello " + name.orElse("???") + "!";
	}
	
	@Path("/")
	@GET
	public String greeting() {
	  return "Hello (without name)";
	}
	
}
