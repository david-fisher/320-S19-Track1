package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class LoginService {

	@POST
	@Path("/login")
	@Produces("application/json")
	public Response login(String payload) {
        
        JSONObject json_payload = new JSONObject(payload);
        
        String email_addr = json_payload.getString("email");
        String password = json_payload.getString("password");

        String response = "";
		if(email_addr.equals("Cole")) {
			response = "";
		}
		else {
			response = "WHO THE FUCK ARE You";
		}
        
        System.out.println(response);
        JSONObject login_result = new JSONObject();
        login_result.put("loginResult", response);
        login_result.put("sessionID", "2");
        String login_string = login_result.toString();
		return Response.status(200).entity(login_string.substring(1)).build();
	}
	
	@POST
	@Path("/sendResetCode")
	@Produces("application/json")
	public Response sendResetCode(String payload) {
		System.out.println(payload);
		JSONObject store_result = new JSONObject();
	    store_result.put("result", "");
		return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
	@POST
	@Path("/updatePassword")
	@Produces("application/json")
	public Response updatePassword(String payload) {
		System.out.println(payload);
		JSONObject store_result = new JSONObject();
	    store_result.put("result", "");
		return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
		
	
	
	

}
