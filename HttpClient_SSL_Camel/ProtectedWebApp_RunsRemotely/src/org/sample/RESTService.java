package org.sample;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

// http://localhost:8080/Protected_REST_App/rest/message/hi

@Path("/message")
public class RESTService {

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {

		String result = "GET example : " + msg;

		return Response.status(200).entity(result).build();

	}
	
	
	@POST
	@Path("/post/{param}")
	public Response postMessage(@PathParam("param") String msg) {
		String result = "POST example : " + msg;
		return Response.status(200).entity(result).build();
	}

	

} 