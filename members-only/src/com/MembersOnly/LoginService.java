package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class LoginService {

	@POST
	@Path("/login")
	@Produces("application/json")
	/***
	 * 
	 * @param payload: email, password
	 * @return
	 */
	public Response login(String payload) {
        
        JSONObject json_payload = new JSONObject(payload);
        
        String email = json_payload.getString("email");
        String password = json_payload.getString("password");
        
        String response = "";
		if(email.equals("a@a.a")) {
			response = "";
		}
		else {
			response = "Error on login: email != a@a.a";
		}
        
        System.out.println(response);
        JSONObject result = new JSONObject();
        result.put("result", response);
		return Response.status(200).entity(result.toString().substring(1)).build();
	}
	
	@POST
	@Path("/sendResetCode")
	@Produces("application/json")
	/***
	 * 
	 * @param payload: email
	 * @return
	 */
	public Response sendResetCode(String payload) {
		System.out.println(payload);
		JSONObject store_result = new JSONObject();
		
		
	    store_result.put("result", "");
		return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
	@POST
	@Path("/updatePassword")
	@Produces("application/json")
	/***
	 * 
	 * @param payload: code, pass, confPass
	 * @return Response Object holding empty string if email was valid and anything else otherwise.
	 */
	public Response updatePassword(String payload) {		
		
		JSONObject store_result = new JSONObject();
		
		
	    store_result.put("result", "");
		return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
		
	
	
	

}
