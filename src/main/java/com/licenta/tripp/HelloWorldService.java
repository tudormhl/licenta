package com.licenta.tripp;
 
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldService {
 
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{param}")
	public Response getMsg(Person p) {
		return Response.status(200).entity(p).build();
	}
}