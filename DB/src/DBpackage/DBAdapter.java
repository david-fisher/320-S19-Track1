package DBpackage;
import org.json.*;
import java.io.*;
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
		//general function - sets all fields of user based on input object.
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
	
	public boolean getUserCanLogin(String id) {
		//stub
		return true;
	}
	
	public boolean setUserCanLogin(String id, boolean state) {
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
	
	public int[] getUserPosts(String id) {
		//returns post IDs, not post
		//stub
		return null;
	}
	
	public boolean setUserPosts(String id, int[] posts) {
		//stub
		return true;
	}
	
	public boolean deleteUser(String id) {
		//stub
		return true;
	}
	
/*
* POST FUNCTIONS - GETTERS & SETTERS FOR ALL FIELDS
*/
	
	public JSONObject createPost(JSONObject post) {
		//stub
		return new JSONObject();
	}

	public JSONObject getPost(String id) {
		//general function - returns entire post object.
		//stub
		return new JSONObject();
	}

	public JSONObject setPost(String id, JSONObject post) {
		//general function - sets all post fields based on input object
		//stub
		return new JSONObject();
	}
	
	public String getPostType(String id) {
		//stub
		return null;
	}
	
	public boolean setPostType(String id, String type) {
		//stub
		return true;
	}
	
	public int getPostAuthor(String id) {
		//stub
		return 0;
	}
	
	
	public boolean setPostAuthor(String id) {
		//stub
		return true;
	}
	
	public int getPostImage(String id) {
		//returns image ID, not image
		//stub
		return 0;
	}
	
	public boolean setPostImage(String id, int picId) {
		//sets image ID, not image
		//stub
		return true;
	}
	
	public JSONObject getPostUserView(String id) {
		//stub
		return null;
	}
	
	public boolean setPostUserView(String id, JSONObject data) {
		//stub
		return true;
	}
	
	public JSONObject getPostWorldView(String id) {
		//stub
		return null;
	}
	
	public boolean setPostWorldView(String id, JSONObject data) {
		//stub
		return true;
	}
	
	public boolean getPostIsExplicit(String id) {
		//stub
		return true;
	}
	
	public boolean setPostIsExplicit(String id) {
		//stub
		return true;
	}
	
	public boolean getPostVisibility(String id) {
		//stub
		return true;
	}
	
	public boolean setPostVisibility(String id, boolean state) {
		//stub
		return true;
	}
	
	public JSONObject getPostComments(String id) {
		//stub
		return null;
	}
	
	public boolean setPostComments(String id, JSONObject comments) {
		//stub
		return true;
	}
	
	public boolean deletePost(JSONObject post){
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
	
	public JSONObject getAsset(String id) {
		//stub
		return null;
	}
	
	public InputStream getAssetPhoto(String id) {
		//stub
		return null;
	}
	
	public boolean setAssetPhoto(InputStream pic) {
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
	
	public JSONObject getURL(String id) {
		//general function - returns all fields in a URL JSONObject
		//stub
		return null;
	}
	
	public boolean setURL(JSONObject url) {
		//general function - sets all fields in a URL JSONObject
		//stub
		return true;
	}
	
	public String getURLOrg(String id) {
		//stub
		return null;
	}
	
	public boolean setURLOrg(String id, String address) {
		//stub
		return true;
	}
	
	public String getURLShort(String id) {
		//stub
		return null;
	}
	
	public boolean setURLShort(String id, String address) {
		//stub
		return true;
	}
}
