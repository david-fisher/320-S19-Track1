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
	@Path("/getFeed")
	@Produces("application/json")
	public Response getFeed(String payload) {
		System.out.println(payload);
		JSONObject jsonPayload = new JSONObject(payload);
		
		String email = jsonPayload.getString("email").toLowerCase();
		String password = jsonPayload.getString("password");
		boolean isLoggedIn = LoginProcessor.checkCredentials(email, password);
		JSONObject result = new JSONObject();
		if(isLoggedIn) {
			User usr = Database.adapter.getUser(email);
			String picture = usr.profilePic;
			String firstName = usr.firstName;
			String blurb = usr.description;
			ArrayList<Post> fetchedPosts = Database.adapter.fetchRecentPosts(10);
			JSONArray items = new JSONArray();
			JSONArray otherItems = new JSONArray();
			for(int i = 0; i < fetchedPosts.size(); i++) {
				Post post = fetchedPosts.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("text", post.text);
				jsonObj.put("firstName", post.getUser().firstName);
				jsonObj.put("lastName", post.getUser().lastName);
				jsonObj.put("email", post.getUser().email);
				System.out.println("type: " + post.type);
				jsonObj.put("imageOrText", !post.type.equals("textPost"));
				ArrayList<Comment> comments = post.comments;
				JSONArray jcoms = new JSONArray();
				for(int j = 0; j < comments.size(); j++) {
					Comment com = comments.get(j);
					JSONObject jcom = new JSONObject();
					jcom.put("text", com.text);
					jcom.put("firstName", com.getUser().firstName);
					jcom.put("lastName", com.getUser().lastName);
					jcom.put("postID", com.postID);
					jcoms.put(jcom);
				}
				jsonObj.put("comments", jcoms);
				if(post.type.equals("imagePost")){
					Scanner scan = null;
					try {
						scan = new Scanner(new File(((ImagePost)post).path));
						System.out.println("made scanner");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jsonObj.put("imgPath", scan.nextLine());
				}
				Scanner scan = null;
				try {
					scan = new Scanner(new File(post.poster.profilePic));
					System.out.println("made scanner");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsonObj.put("avatar", scan.nextLine());
				if(i%2 == 0) {
					items.put(jsonObj);
				} else {
					otherItems.put(jsonObj);
				}
			}
			result.put("userEmail", email);
			if(picture == null)
				picture = "data:image/png;base64,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";
			result.put("avatar", picture);
			result.put("userName", firstName);
			result.put("items1", items);
			result.put("items2", otherItems);
			System.out.println("Desc: " + blurb);
			result.put("profDesc", blurb);
			System.out.println(items);
		}
		else {
			result.put("result", "error");
		}
		System.out.println("Sending thing");
	    return Response.status(200).entity(result.toString().substring(1)).build();

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
		System.out.println("posted the image");
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
		String email = jsonPayload.getString("email").toLowerCase();
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
