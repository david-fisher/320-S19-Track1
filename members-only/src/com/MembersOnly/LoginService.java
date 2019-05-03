package com.MembersOnly;

import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

import com.Model2.LoginProcessor;
import com.Model2.*;

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
        System.out.println(payload);

        String email = json_payload.getString("email").toLowerCase();
        String password = json_payload.getString("password");
        
        String response;
        boolean login_value = LoginProcessor.checkCredentials(email, password);
        if(login_value) {
        	response = "";
        }
        else {
        	response = "Error: Incorrect email or password";
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
		
		
		String uniqueID = UUID.randomUUID().toString().substring(0,8);
		if(ServerVariables.verification_codes.containsKey(uniqueID)) {
			uniqueID = UUID.randomUUID().toString().substring(0,8);
		}
		
        ServerVariables.verification_codes.put(uniqueID, email);
        SendEmail.sendMail(email, "Your access code is: " + uniqueID, "Your Members Only Password Reset Code");
		JSONObject send_code_result = new JSONObject();
	    send_code_result.put("result", "");
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
			LoginProcessor.resetPassword(pass, confPass, email);
		}
		else {
			store_result.put("result", "error");
		}
		
		return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
		
	
	
	

}
