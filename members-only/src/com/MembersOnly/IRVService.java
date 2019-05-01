package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

@Path("/")
public class IRVService {
	@POST
	@Path("/storeInfo")
	@Produces("application/json")
	public Response storeInfo(String payload) {
		
        JSONObject json_payload= new JSONObject(payload);        
        String firstName = json_payload.getString("firstName");
        String lastName = json_payload.getString("lastName");
        String addressLineOne = json_payload.getString("addressLineOne");
        String addressLineTwo = json_payload.getString("addressLineTwo");
        String city = json_payload.getString("city");
        String state = json_payload.getString("state");
        String zipCode = json_payload.getString("zipCode");
        String phoneNumber = json_payload.getString("phoneNumber");
        String email = json_payload.getString("email");
        String creditCardNumber = json_payload.getString("creditCardNumber");
        String expiration = json_payload.getString("expiration");
        String CVV = json_payload.getString("CVV"); 
        System.out.println(firstName);
        
        //TODO: CALL REGISTRATION METHOD AND RETURN PROPER RESULT
        
        JSONObject store_result = new JSONObject();
        store_result.put("result", "");
        return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
	
	//Please note that payload in this method is not a JSON.
	@POST
	@Path("/chargeVerify")
	@Produces("application/json")
	public Response chargeVerify(String payload) {
		double charge = 0.0;
		try {
		    charge = Double.parseDouble(payload);
		}
		catch(Exception e) {
			return Response.status(200).entity("error").build();
		}
		
		//TODO: INSERT MODEL2 CODE TO VERIFY THE CHARGE
		
        JSONObject store_result = new JSONObject();
        store_result.put("result", "");
        return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
	@POST
	@Path("/storePassword")
	@Produces("application/json")
	/***
	 * 
	 * @param payload: pass, confirmPass
	 * @return
	 */
	public Response storePassword(String payload) {
		JSONObject json_payload = new JSONObject(payload);
		String pass = json_payload.getString("pass");
		String confirmPass = json_payload.getString("confirmPass");
		
		//CALL THE CODE IN MODEL2 to store the password.
		return null;
		//return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
	
	
 }