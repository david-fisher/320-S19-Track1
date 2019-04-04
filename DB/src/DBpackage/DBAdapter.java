package DBpackage;
import org.json.*;
import java.sql.*;


public class DBAdapter {
	public JSONObject createUser(JSONObject usr){
		//stub
		return null;
	}

	public JSONObject readUser(String id) {
		//stub
		return new JSONObject();
	}

	public JSONObject updateUser(String id, JSONObject data) {
		//stub
		return new JSONObject();
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

	public JSONObject[] hashtagSearch(String str) {
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
}
