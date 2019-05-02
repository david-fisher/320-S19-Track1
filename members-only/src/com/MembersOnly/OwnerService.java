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
	@Path("/createUser")
	@Produces("application/json")
	public Response createUser(String payload) {
		//form{role, email, pass}, email, password
		System.out.println(payload);
		JSONObject json_payload = new JSONObject(payload);
		
		JSONObject form_data = (JSONObject)json_payload.get("form");
		String authEmail = json_payload.getString("email");
		String authPassword = json_payload.getString("password");
		
		String userRole = form_data.getString("role");
		String userEmail = form_data.getString("email");
		String password = form_data.getString("pass");
		System.out.println(userRole);
		
		//TODO: Call authentication method
		//TODO: Create user with User class
		JSONObject result = new JSONObject();
		result.put("result", "");
        return Response.status(200).entity(result.toString().substring(1)).build();

	}
	
}
