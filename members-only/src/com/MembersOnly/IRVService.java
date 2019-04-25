package com.MembersOnly;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.*;
@Path("/")
public class IRVService {
	@POST
	@Path("/storeInfo")
	@Produces("application/json")
	public Response irvResponse(String payload) {
       
        JSONObject input = new JSONObject(payload);
        String firstName = input.getString("firstName");
        String lastName = input.getString("lastName");
        //postInformation(all info)
        System.out.println(input.get("firstName"));
        //System.out.println(lastName);
        
        JSONObject json = new JSONObject();
        json.put("result", "thevalue");
        String new_json = json.toString();
        
        return Response.status(200).entity(new_json.substring(1)).header("Access-Control-Allow-Origin", "*").build();
	}
	
 }