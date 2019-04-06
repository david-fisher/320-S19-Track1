package DBpackage;
import org.json.*;
import java.sql.*;


public class DBAdapter {
	
	private String DBAddress = null;
	
	private Connection getConnection() {
		//function used to get connection to database
		//stub
		return null;
	}
	
	/*
	 * USER FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
	 * 
	 */
	public JSONObject createUser(JSONObject usr){
		//stub
		return null;
	}

	public JSONObject getUser(String id) { 
		//general function- returns entire user.
		//stub
		return null;
	}
	
	public boolean setUser(String id, JSONObject user) { 
		//general function - sets entire user based on new json object.
		//stub
		return true;
	}
	
	public String getUserType(String id) {
		//stub
		return null;
	}
	
	public boolean setUserType(String id, String type) {
		//stub
		return true;
	}

	public String getUserName(String id) {
		//stub
		return null;
	}
	
	public boolean setUserName(String id, String name) {
		//stub
		return true;
	}
	
	public JSONObject getUserAddress(String id) {
		//stub
		return null;
	}
	
	public boolean setUserAddress(String id, JSONObject address) {
		//stub
		return true;
	}
	
	public int getUserPhone(String id) {
		//stub
		return 0;
	}
	
	public boolean setUserPhone(String id, int number) {
		//stub
		return true;
	}
	
	public String getUserBirthday(String id) {
		//stub
		return null;
	}
	
	public boolean setUserBirthday(String id, String bday) {
		//stub
		return true;
	}
	
	public int getUserPoints(String id) {
		//stub
		return 0;
	}
	
	public boolean setUserPoints(String id, int amt) {
		//stub
		return true;
	}
	
	public int[] getUserFollowing(String id) {
		//stub
		return null;
	}
	
	public boolean setUserFollowing(String id, int[] users) {
		//stub
		return true;
	}
	
	public int getUserInviter(String id) {
		//stub
		return 0;
	}
	
	public boolean setUserInviter(String id, int inviter) {
		//stub
		return true;
	}
	
	public int[] getUsersInvited(String id) {
		//stub
		return null;
	}
	
	public boolean setUsersInvited(String id, int[] users) {
		//stub
		return true;
	}
	
	public boolean getUserHasInvited(String id) {
		//stub
		return true;
	}
	
	public boolean setUserHasInvited(String id, boolean status) {
		//stub
		return true;
	}
	
	public JSONObject getUserCard(String id) {
		//stub
		return null;
	}
	
	public boolean setUserCard(String id, JSONObject card) {
		//stub
		return true;
	}
	
	public boolean getUserPrivate(String id){
		//stub
		return true;
	}

	public boolean setUserPrivate(String id, boolean state) {
		//stub
		return true;
	}
	
	public boolean deleteUser(String id) {
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

	public JSONObject createPost(JSONObject post) {
		//stub
		return new JSONObject();
	}

	public JSONObject readPost(String str) {
		//stub
		return new JSONObject();
	}

	public JSONObject updatePost(String str, JSONObject post) {
		//stub
		return new JSONObject();
	}

	public boolean deletePost(JSONObject post){
		//stub
		return true;
	}
	
	public boolean createURL (JSONObject url) {
		//stub
		return true;
	}
	
	public String getOrgURL (String id) {
		//stub
		return null;
	}
}
