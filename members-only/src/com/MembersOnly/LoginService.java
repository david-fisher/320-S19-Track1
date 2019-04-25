package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class LoginService {

	@GET
	@Path("/login")
	@Produces("application/json")
	public Response loginResult(/*@FormParam("username") String username, @FormParam("password") String password*/) {

        JSONObject json = new JSONObject();
        json.put("loginResult", "thevalue");
        String new_json = json.toString();
        //json.
		
        
        System.out.println(new_json);
		return Response.status(200).entity(new_json.substring(1)).build();
	}
	
	

}
