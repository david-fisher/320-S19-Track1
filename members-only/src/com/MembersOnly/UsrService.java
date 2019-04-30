package com.MembersOnly;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class UsrService {
	
	@POST
	@Path("/loadComments")
	@Produces("application/json")
	public Response loadComments(String payload) {
		return null;
	}
	
	@POST
	@Path("/postComment")
	@Produces("application/json")
	public Response postComment(String payload) {
		return null;
	}
	
	@POST
	@Path("/updateProfileDescription")
	@Produces("application/json")
	public Response updateProfileDescription(String payload) {
		return null;
	}
	
	@POST
	@Path("/updateCCAddress")
	@Produces("application/json")
	public Response updateCCAddress(String payload) {
		return null;
	}
	
	@POST
	@Path("/updateVisibility")
	@Produces("application/json")
	public Response updateVisibility(String payload) {
		return null;
	}
	
	@POST
	@Path("/generateInvite")
	@Produces("application/json")
	public Response generateInvite(String payload) {
		return null;
	}

}

