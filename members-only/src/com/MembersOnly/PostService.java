package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class PostService {
	@POST
	@Path("/loadProfile")
	@Produces("application/json")
	public Response loadProfile(String payload) {
		
		return null;
	}
	
	@POST
	@Path("/search")
	@Produces("application/json")
	public Response search(String payload) {
		return null;
	}
	

	
	@POST
	@Path("/postComment")
	@Produces("application/json")
	public Response postComment(String payload) {
		return null;
	}
	
	@POST
	@Path("/postText")
	@Produces("application/json")
	public Response postText(String payload) {
		return null;
	}
	
	@POST
	@Path("/postImage")
	@Produces("application/json")
	public Response postImage(String payload) {
		return null;
	}
	
	@POST
	@Path("/editImage")
	@Produces("application/json")
	public Response editImage(String payload) {
		return null;
	}

}
