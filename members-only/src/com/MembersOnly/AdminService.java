package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class AdminService {

	@POST
	@Path("/adminEditImage")
	@Produces("application/json")
	public Response adminEditImage(String payload) {
		return null;
	}
	
	@POST
	@Path("/adminEditPostHashtag")
	@Produces("application/json")
	public Response adminEditPostHashtag(String payload) {
		return null;
	}
	
	@POST
	@Path("/adminEditCommentHashtag")
	@Produces("application/json")
	public Response adminEditCommentHashtag(String payload) {
		return null;
	}
		
}
