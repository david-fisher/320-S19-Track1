package com.MembersOnly;

import javax.ws.rs.*;


@Path("/")
public class LoginService {

	@POST
	@Path("/login")
	@Produces("application/json")
	public String loginResult(
			@FormParam("username") String username,
			@FormParam("password") String password) {
		System.out.println(username);
		System.out.println(password);
		String pattern = "{ \"loginResult\":\"%s\"}";
		return String.format(pattern, "true");
	}
	
	

}
