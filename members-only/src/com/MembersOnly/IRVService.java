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
        
        //INSERT REGISTRATION METHOD
        
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
			return Response.status(200).entity("invalid").build();
		}
		
		//INSERT Model2 Code to run verification and check the value.
        JSONObject json = new JSONObject();
        json.put("result", "");
        String new_json = json.toString();
        System.out.println(new_json);
        return Response.status(200).entity(new_json.substring(1)).build();
	}
	
	@POST
	@Path("/storePassword")
	@Produces("application/json")
	public Response storePassword(String payload) {
		return null;
	}
	
	
	
 }