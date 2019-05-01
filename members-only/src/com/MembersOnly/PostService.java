package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

import com.Model2.*;

@Path("/")
public class PostService {
	@POST
	@Path("/loadProfile")
	@Produces("application/json")
	public Response loadProfile(String payload) {
		//SendEmail.sendMail("cikoro@umass.edu", "Hi. This is a test of members only!");
		//System.out.println("Done");
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
