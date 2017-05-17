package com.example.switchyard.switchyard_rest_example;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RequestScoped
@Path("/MyServicePath")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public interface TestResource {

	@POST
	@Path("/")
	@Produces({ "application/json" })
	public String ping(String str);

}
