package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class ModeratorService {
	
	@POST
	@Path("/falseFlag")
	@Produces("application/json")
	public Response falseFlag(String payload) {
		return null;
	}
	
	@POST
	@Path("/hide")
	@Produces("application/json")
	public Response hide(String payload) {
		return null;
	}
	
	@POST
	@Path("/sendToOwner")
	@Produces("application/json")
	public Response sendToOwner(String payload) {
		return null;
	}
	
	
	

}
