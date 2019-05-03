package com.MembersOnly;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;
import com.Model2.*;
import java.util.Scanner;

@Path("/")
public class PostService {
	private int postID;
	@POST
	@Path("/loadProfile")
	@Produces("application/json")
	public Response loadProfile(String payload) {
		//SendEmail.sendMail("cikoro@umass.edu", "Hi. This is a test of members only!");
        
		JSONObject vals = new JSONObject();
		vals.put("userId", 1);
		vals.put("avatar", 1 /*Insert user avatar IO*/);
		vals.put("userName", "name");
		ArrayList<Post> posts = new ArrayList<Post>();
		vals.put("items", posts);
		//vals.put("items", 2);
		//vals.put(key, value);
		
		return null;
	}
	
	@POST
	@Path("/persons")
	@Produces("application/json")
	public Response persons(String payload) {
		//List
		return null;
	}
	
	/*
	@POST
	@Path("/search")
	@Produces("application/json")
	public Response search(String payload) {
		return null;
	}
	*/
	
	
	@POST
	@Path("/postText")
	@Produces("application/json")
	public Response postText(String payload) {
		System.out.println("cheese: " + payload);
		JSONObject jsonPayload = new JSONObject(payload);

		String postText = jsonPayload.getString("message");
		String email = jsonPayload.getString("email");
		String password = jsonPayload.getString("password");
		
		User currentUser = Database.adapter.getUser(email);
		postID = (int)(Math.random()*Integer.MAX_VALUE);
		Post post = new Post(currentUser, "textPost", Integer.toString(postID), postText);
		
		JSONObject result = new JSONObject();
		
		result.put("result", "");
	    return Response.status(200).entity(result.toString().substring(1)).build();
	}
	
	@POST
	@Path("/postImage")
	@Produces("application/json")
	public Response postImage(String payload) {
		System.out.println(payload);
		JSONObject jsonPayload = new JSONObject(payload);
		String photo = jsonPayload.getString("photo");
		String path = "C:/Users/Chigozie/Desktop/memOnlyPics/";
		String fileName = UUID.randomUUID().toString();
		String email = jsonPayload.getString("email");
		String password = jsonPayload.getString("password");
		
		User usr = Database.adapter.getUser(email);
		postID = (int)(Math.random()*Integer.MAX_VALUE);
		ImagePost post = new ImagePost(usr, "imagePost",Integer.toString(postID), path+fileName);
				//User poster, String type, String postID, String path
		try (PrintWriter out = new PrintWriter(path + fileName)) {
		    out.println(photo);
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not found");
		}
		JSONObject result = new JSONObject();
		result.put("result", "");
	    return Response.status(200).entity(result.toString().substring(1)).build();

	}
	
	/*@POST
	@Path("/editImage")
	@Produces("application/json")
	public Response editImage(String payload) {
		return null;
		
	}
	 */
	
	@POST
	@Path("/loadComments")
	@Produces("application/json")
	public Response loadComments(String payload) {
		return null;
	}
	
	@POST
	@Path("/postComment")
	@Produces("application/json")
	public Response postComment(String payload) {
		return null;
	}
	
	@POST
	@Path("/pullPicture")
	@Produces("application/json")
	public Response pullPicture(String payload) {
		//EMPTY STRING IN THIS METHOD REPRESENTS ERROR!!
		JSONObject jsonPayload = new JSONObject(payload);
		String email = jsonPayload.getString("email");
		String password = jsonPayload.getString("password");
		boolean isLoggedIn = LoginProcessor.checkCredentials(email, password);
		String response = "";
		if(isLoggedIn) {
			//TODO: Implement backend to go retrieve profile pic from backend.
			User usr = Database.adapter.getUser(email);
			System.out.println(usr.email);
			String pic = usr.profilePic;
			if(pic == null) {
			     response = "no picture set";
			}
			else {
				try {
					Scanner scan = new Scanner(new File(pic));
					response = scan.nextLine();
	
				}
				catch (Exception e){
					e.printStackTrace();
					System.out.println("IO ERROR");
				}
	
			}
		}
		else {
			response = "error";
		}
		
		JSONObject result = new JSONObject();
		result.put("result", response);
		
		return Response.status(200).entity(result.toString().substring(1)).build();
		
	}
	



}
