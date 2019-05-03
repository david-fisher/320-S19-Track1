package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;
import com.Model2.*;
import java.util.UUID;

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
        String password = json_payload.getString("pass");
        String confPassword = json_payload.getString("confpass");
        String creditCardNumber = json_payload.getString("creditCardNumber");
        String expiration = json_payload.getString("expiration");
        String CVV = json_payload.getString("CVV"); 
        String registrationCode = json_payload.getString("registrationCode");
        System.out.println(firstName);
        
        String exp_month = expiration.substring(0, 2);
        String exp_year = expiration.substring(3);
        String emailOfInviter = "";
        if(ServerVariables.reg_codes.containsKey(registrationCode)) {
        	emailOfInviter = ServerVariables.reg_codes.get(registrationCode);
        }
        //TODO: CALL REGISTRATION METHOD AND RETURN PROPER RESULT
        // 04/23
        Registration register = new Registration(firstName, lastName, addressLineOne,
        		                 addressLineTwo, phoneNumber, email, zipCode, password,
        		                 confPassword,creditCardNumber,
        		                 CVV, exp_month, exp_year, registrationCode);
        
        String registration_result = register.verify();
        
        boolean stored = false;
        if(registration_result.equals("")){
        	stored = register.storeData();
        }
        JSONObject store_result = new JSONObject();
        
        if(!stored && registration_result.length() == 0) {
        	registration_result = "Could not store User. Try again later and hope it works.";
        }
        store_result.put("result", registration_result);
        
        return Response.status(200).entity(store_result.toString().substring(1)).build();
	}
	
	
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
	@Path("/updateCCAddress")
	@Produces("application/json")
	public Response updateCCAddress(String payload) {
		JSONObject json_payload = new JSONObject(payload);
		String email = json_payload.getString("email");
		String password = json_payload.getString("password");
		
		JSONObject form_data = (JSONObject)json_payload.get("form");
		boolean isLoggedIn = LoginProcessor.checkCredentials(email, password);
		String cc_number = form_data.getString("creditCardNumber");
		String expiration = form_data.getString("expiration");
		String CVV = form_data.getString("CVV");
		String response;
		if(isLoggedIn) {
			
			//TODO: SEND OUT UPDATED CC DATA TO BACKEND
			
			response = "";
		}
		else {
			response = "User not logged in";
		}
		JSONObject result = new JSONObject();
		result.put("result", response);
		
        return Response.status(200).entity(result.toString().substring(1)).build();

	}
	
	
	//NOTE THAT THE FOLLOWING METHODS AREN'T TECHNICALLY UNDER IRV, BUT ECLIPSE WON'T ALLOW ANY
	//MORE CLASSES...
	@POST
	@Path("/updateProfileDescription")
	@Produces("application/json")
	public Response updateProfileDescription(String payload) {
		System.out.println(payload);
		JSONObject jsonPayload = new JSONObject(payload);
		String email = jsonPayload.getString("username");
		String password = jsonPayload.getString("password");
		boolean isLoggedIn = LoginProcessor.checkCredentials(email, password);
		String response;
		if(isLoggedIn) {
		JSONObject formData = (JSONObject)jsonPayload.get("form");
		formData.getString("description");
		
		
		//TODO: SEND DESCRIPTION TO BACKEND TO VALIDATE
		
		
		response = "";
		}
		else {
			response = "User not authenticated";
		}
		JSONObject result = new JSONObject();
		result.put("result", response);
        return Response.status(200).entity(result.toString().substring(1)).build();	
	}
	
	@POST
	@Path("/updateVisibility")
	@Produces("application/json")
	public Response updateVisibility(String payload) {
		JSONObject jsonPayload = new JSONObject(payload);
		String email = jsonPayload.getString("username");
		String password = jsonPayload.getString("password");
		boolean isLoggedIn = LoginProcessor.checkCredentials(email, password);
		String response;
		if(isLoggedIn) {
			JSONObject formData = (JSONObject)jsonPayload.get("form");
			String whoCanSeeMe = formData.getString("whoCanSeeMe");
			String whoDoISee = formData.getString("whoDoISee");
			
			//TODO: Send Updated Visibility Information to the backend.
			
			response = "";
		}
		else {
			response = "User is not logged in";
		}
		
		JSONObject result = new JSONObject();
		result.put("result", response);
        return Response.status(200).entity(result.toString().substring(1)).build();	
	}
	
	@POST
	@Path("/generateInvite")
	@Produces("application/json")
	public Response generateInvite(String payload) {
		//NOTE THIS METHOD DOESNT SEND A FORM.
		JSONObject jsonPayload = new JSONObject(payload);
		String email = jsonPayload.getString("email");
		String password = jsonPayload.getString("password");
		String inviteCode = UUID.randomUUID().toString().substring(0,8);
		
		//TODO: Send email and invite code to backend to store for later use.
		
		JSONObject result = new JSONObject();
		result.put("result", inviteCode);
	    return Response.status(200).entity(result.toString().substring(1)).build();

	}
	
	@POST
	@Path("/changePhoto")
	@Produces("application/json")
	public Response changePhoto(String payload) {
		JSONObject jsonPayload = new JSONObject(payload);
		String email = jsonPayload.getString("email");
		String password = jsonPayload.getString("password");
		//boolean isLoggedIn = LoginProcessor.checkCredentials(email, password);
		String response;
		if(true) {
			//TODO: CALL The code to backend to change profile picture.
			response = "";
		}
		else {
			response = "Not logged in";
		}
		JSONObject result = new JSONObject();
		result.put("result", response);
	    return Response.status(200).entity(result.toString().substring(1)).build();

	}
	

	
	
	
 }