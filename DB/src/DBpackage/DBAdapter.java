package DBpackage;
import org.json.*;

import java.awt.Image;
import java.io.*;
import java.sql.*;


public class DBAdapter {
	
	private String DBAddress = null; //access address for database. figure out l8tr
	
	private Connection getConnection() {
		//function used to get connection to database
		//stub
		return null;
	}
	
/*
* USER FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS 
*/
	public JSONObject createUser(JSONObject usr){
		//stub
		return null;
	}

	public JSONObject getUser(int id) { 
		//general function- returns entire user.
		//stub
		return null;
	}
	
	public boolean setUser(int id, JSONObject user) { 
		//general function - sets all fields of user based on input object.
		//stub
		return true;
	}
	
	public String getUserType(int id) {
		//stub
		return null;
	}
	
	public boolean setUserType(int id, String type) {
		//stub
		return true;
	}

	public String getUserName(int id) {
		//stub
		return null;
	}
	
	public boolean setUserName(int id, String name) {
		//stub
		return true;
	}
	
	public JSONObject getUserAddress(int id) {
		//stub
		return null;
	}
	
	public boolean setUserAddress(int id, JSONObject address) {
		//stub
		return true;
	}
	
	public int getUserPhone(int id) {
		//stub
		return 0;
	}
	
	public boolean setUserPhone(int id, int number) {
		//stub
		return true;
	}
	
	public String getUserBirthday(int id) {
		//stub
		return null;
	}
	
	public boolean setUserBirthday(int id, String bday) {
		//stub
		return true;
	}
	
	public int getUserPoints(int id) {
		//stub
		return 0;
	}
	
	public boolean setUserPoints(int id, int amt) {
		//stub
		return true;
	}
	
	public int[] getUserFollowing(int id) {
		//stub
		return null;
	}
	
	public boolean setUserFollowing(int id, int[] users) {
		//stub
		return true;
	}
	
	public int getUserInviter(int id) {
		//stub
		return 0;
	}
	
	public boolean setUserInviter(int id, int inviter) {
		//stub
		return true;
	}
	
	public int[] getUsersInvited(int id) {
		//stub
		return null;
	}
	
	public boolean setUsersInvited(int id) {
		//stub
		return true;
	}
	
	public boolean getUserHasInvited(int id) {
		//stub
		return true;
	}
	
	public boolean setUserHasInvited(int id, boolean status) {
		//stub
		return true;
	}
	
	public JSONObject getUserCard(int id) {
		//stub
		return null;
	}
	
	public boolean setUserCard(int id, JSONObject card) {
		//stub
		return true;
	}
	
	public boolean getUserCanLogin(int id) {
		//stub
		return true;
	}
	
	public boolean setUserCanLogin(int id, boolean state) {
		//stub
		return true;
	}
	
	public boolean getUserPrivate(int id){
		//stub
		return true;
	}

	public boolean setUserPrivate(int id, boolean state) {
		//stub
		return true;
	}
	
	public int[] getUserPosts(int id) {
		//returns post IDs, not post
		//stub
		return null;
	}
	
	public boolean setUserPosts(int id, int[] posts) {
		//stub
		return true;
	}
	
	public boolean deleteUser(int id) {
		//stub
		return true;
	}
	
	public boolean snap() {
		//deletes half of all users
		return true;
	}
	
/*
* POST FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public JSONObject createPost(JSONObject post) {
		//stub
		return new JSONObject();
	}

	public JSONObject getPost(int id) {
		//general function - returns entire post object.
		//stub
		return new JSONObject();
	}

	public JSONObject setPost(int id, JSONObject post) {
		//general function - sets all post fields based on input object
		//stub
		return new JSONObject();
	}
	
	public String getPostType(int id) {
		//stub
		return null;
	}
	
	public boolean setPostType(int id, String type) {
		//stub
		return true;
	}
	
	public int getPostAuthor(int id) {
		//stub
		return 0;
	}
	
	
	public boolean setPostAuthor(int id) {
		//stub
		return true;
	}
	
	public int getPostImage(int id) {
		//returns image ID, not image
		//stub
		return 0;
	}
	
	public boolean setPostImage(int id, int picId) {
		//sets image ID, not image
		//stub
		return true;
	}
	
	public JSONObject getPostUserView(int id) {
		//stub
		return null;
	}
	
	public boolean setPostUserView(int id, JSONObject data) {
		//stub
		return true;
	}
	
	public JSONObject getPostWorldView(int id) {
		//stub
		return null;
	}
	
	public boolean setPostWorldView(int id, JSONObject data) {
		//stub
		return true;
	}
	
	public boolean getPostIsExplicit(int id) {
		//stub
		return true;
	}
	
	public boolean setPostIsExplicit(int id, boolean state) {
		//stub
		return true;
	}
	
	public boolean getPostVisibility(int id) {
		//stub
		return true;
	}
	
	public boolean setPostVisibility(int id, boolean state) {
		//stub
		return true;
	}
	
	public JSONObject getPostComments(int id) {
		//stub
		return null;
	}
	
	public boolean setPostComments(int id, JSONObject comments) {
		//stub
		return true;
	}
	
	public boolean deletePost(JSONObject post) {
		//stub
		return true;
	}
	
	public JSONObject[] getPopularPosts() {
		//stub
		return null;
	}

	public JSONObject[] getFlaggedPosts() {
		//stub
		return null;
	}
	
/*
* ASSET FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public boolean createAsset(InputStream photo) {
		//stub
		return true;
	}
	
	public Image getAssetPhoto(int id) {
		//we will save image paths in the database and fetch them from disk
		//stub
		return null;
	}
	
	public boolean setAssetPhoto(Image pic) {
		//stub
		return true;
	}
	
	public boolean deleteAsset(int id) {
		//stub
		return true;
	}
/*
* URL FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public boolean createURL(JSONObject url) {
		//stub
		return true;
	}
	
	public JSONObject getURL(int id) {
		//general function - returns all fields in a URL JSONObject
		//stub
		return null;
	}
	
	public boolean setURL(JSONObject url) {
		//general function - sets all fields in a URL JSONObject
		//stub
		return true;
	}
	
	public String getURLOrg(int id) {
		//stub
		return null;
	}
	
	public boolean setURLOrg(int id, String address) {
		//stub
		return true;
	}
	
	public String getURLShort(int id) {
		//stub
		return null;
	}
	
	public boolean setURLShort(int id, String address) {
		//stub
		return true;
	}
	
	public boolean deleteURL(int id) {
		//stub
		return true;
	}
}
