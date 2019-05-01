package com.MembersOnly;

import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

import com.Model2.SendEmail;

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
        
        String response;
        //TODO: REPLACE THIS SECTION WITH MODEL2 LOGIN SECTION.
		if(email.equals("a@a.a")) {
			response = "";
		}
		else {
			response = "Error on login: email != a@a.a";
		}
        
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
		JSONObject json_payload = new JSONObject(payload);
		String email = json_payload.getString("email");
		
		
		//TODO: CHECK IF VALID EMAIL IN MEMBERS ONLY. IF NOT VALID, RETURN AN ERROR TO FRONTEND
		
		String uniqueID = UUID.randomUUID().toString().substring(0,8);
        ServerVariables.verification_codes.put(uniqueID, email);
        SendEmail.sendMail(email, "Your access code is: " + uniqueID);
		JSONObject send_code_result = new JSONObject();
	    send_code_result.put("result", "");
	    System.out.println(ServerVariables.verification_codes.toString());
		return Response.status(200).entity(send_code_result.toString().substring(1)).build();
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
		JSONObject json_payload = new JSONObject(payload);
		String code = json_payload.getString("code");
		String pass = json_payload.getString("pass");
		String confPass = json_payload.getString("confPass");
	
		JSONObject store_result = new JSONObject();
		//check if user's code is valid with our hashmap
		
		if(ServerVariables.verification_codes.containsKey(code)) {
			//if this is true, then we can proceed with the reset.
			String email = ServerVariables.verification_codes.get(code);
			store_result.put("result", "");
			//Remove code from the hash
			ServerVariables.verification_codes.remove(code);
			//TODO: CALL MODEL 2 PASSWORD RESET!
		}
		else {
			store_result.put("result", "error");
		}
		
	    
		return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
		
	
	
	

}
