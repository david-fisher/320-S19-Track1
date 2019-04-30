package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class OwnerService {
	@POST
	@Path("/deletePost")
	@Produces("application/json")
	public Response deletePost(String payload) {
		
		return null;
	}
	
	@POST
	@Path("/deleteAccount")
	@Produces("application/json")
	public Response deteleAccount(String payload) {
		return null;
	}
	
	@POST
	@Path("/ignore")
	@Produces("application/json")
	public Response ignore(String payload) {
		return null;
	}
	
	@POST
	@Path("/createIdol")
	@Produces("application/json")
	public Response createIdol(String payload) {
		return null;
	}
	
	@POST
	@Path("/createAdmin")
	@Produces("application/json")
	public Response createAdmin(String payload) {
		return null;
	}
	
	@POST
	@Path("/createMod")
	@Produces("application/json")
	public Response createMod(String payload) {
		return null;
	}
	
	
	
	
	

	
}
