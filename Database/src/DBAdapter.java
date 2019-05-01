
import javax.imageio.ImageIO;

import java.sql.*;
import java.util.ArrayList;


public class DBAdapter {
	

	private String DBAddress = "jdbc:mysql://localhost:3306/TrackOneDB"; //access address for database. figure out l8tr
	private Connection conn;
	
	private Connection getConnection() throws SQLException{
		//function used to get connection to database
		if(conn != null) return conn;
		Connection conn = DriverManager.getConnection(DBAddress,"root","root");
		System.out.println("Log: Connection Established!");
		return conn;
	}
	
	public boolean createUser(User usr) {
		try {
			getConnection();
			int log = (usr.loggedIn) ? 1 : 0;
			int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.User (email,firstName,lastName,password,type,loggedIn) VALUES ('"+usr.email+"','" +usr.firstName+"','" +usr.lastName+"','" +usr.password+"','"+ usr.type+"',"+log+")");
			if (usr.type == "member") { 
				this.updateUser(usr.email, "address", usr.address);
				this.updateUser(usr.email, "city", usr.city);
				this.updateUser(usr.email, "state", usr.state);
				this.updateUser(usr.email, "zip", usr.zip);
				this.updateUser(usr.email, "ccNum", usr.ccNum);
				this.updateUser(usr.email, "ccv", usr.ccv);
				this.updateUser(usr.email, "ccExpMon", usr.expM);
				this.updateUser(usr.email, "ccExpYr", usr.expY);
				this.updateUser(usr.email, "stripeID", usr.creditCard.getId());
				this.updateUser(usr.email, "phone", usr.phone);
				this.updateUser(usr.email, "birthday", usr.birthday);
				this.updateUser(usr.email, "points", usr.points);
				this.updateUser(usr.email, "inviter", usr.invitedBy);
				this.updateUser(usr.email, "hasInvited", (usr.hasInvited) ? 1 : 0);
				this.updateUser(usr.email, "validAccount", (usr.hasInvited) ? 1 : 0);
				this.updateUser(usr.email, "private", (usr.privacy) ? 1 : 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public User getUser(String email) {
		try {
			getConnection();
			User usr;
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String type = rs.getString("type");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String inviter = rs.getString("inviter");
				usr = new User (email, firstName, lastName, 0, inviter, null);
				if (type == "member") { //set member fields
					usr.type = type;
					usr.address = rs.getString("address");
					usr.city = rs.getString("city");
					usr.state = rs.getString("state");
					usr.zip = rs.getString("zip");
					usr.ccNum = rs.getString("ccNum");
					usr.ccv = rs.getString("ccv");
					usr.expM = rs.getString("ccExpMon");
					usr.expY = rs.getString("ccExpYr");
					usr.creditCard = new StripeCreditCard(email, usr.ccNum, 
										usr.zip, usr.ccv, usr.expM, usr.expY);
					usr.stripeCreditCardID = rs.getString("stripeID");
					usr.points += rs.getInt("points");
					usr.phone = rs.getString("phone");
					usr.password = rs.getString("password");
					usr.loggedIn = true;
					usr.hasInvited = (rs.getInt("hasInvited") == 1);
					usr.isValidated = (rs.getInt("validAccount") == 1);
					usr.privacy = rs.getBoolean("private");
					return usr;
				}
				return (Admin)usr;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	private <T> String formatUserUpdateString(String email, String varname, T var) {
		return String.format("UPDATE TrackOneDB.User SET %s = '"+var+"' WHERE email = '"+email+"'", varname);
	}
	
	public <T> boolean updateUser(String email, String field, T newValue) {
		String query = formatUserUpdateString(email, field, newValue);
		try {
			getConnection();
			int rs = conn.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteUser(String email) { //delete posts all posts users made?
		try {
			getConnection();
			int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.User WHERE email = '" +email+ "'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean followUser(String follower,String followee) {
		try {
			Connection conn = getConnection();
		    int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Follow (follow,userID) VALUES ('"+followee+"','"+follower+")");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// follower follows followee lol
	// #english
	
	public boolean createPhoto(Photo photo) {
		try {
			Connection conn = getConnection();
		    int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Photo (original) VALUES ('"+photo.photoPath+"')");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Photo getPhoto(String id) {
		int ID = Integer.parseInt(id);
		try {
			Connection conn = getConnection();
		    int rs = conn.createStatement().executeUpdate("SELECT * FROM TrackOneDB.Photo WHERE photoID = '" +ID+ "'");
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean deletePhoto(Photo photo) {
		try {
			Connection conn = getConnection();
		    int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Photo WHERE original = '" +photo.photoPath+ "'");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean insertFilter(Filter filter) {
		try {
			Connection conn = getConnection();
			ResultSet rs0 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Photo WHERE original = '"+filter.photoPath+"'");
		    int filterID=0;
			while(rs0.next()) {
				filterID = rs0.getInt("photoID");	
			}
		    int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.FilteredPhoto (xPos,yPos,visibleToUser,photoID,filterID) VALUES ('"+filter.xPos+"','"+filter.yPos+"','"+filter.visibleToUser+"','"+filter.photoId+"','"+filterID+"')");
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//assuming the photoID is the original photo, and the path is the filter.
	
	public boolean deleteFilter(Filter filter) {
		try {
			Connection conn = getConnection();
			ResultSet rs0 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Photo WHERE original = '"+filter.photoPath+"'");
		    int filterID=0;
			while(rs0.next()) {
				filterID = rs0.getInt("photoID");	
			}
		    int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.FilteredPhoto WHERE photoID = AND filterID ='" +filter.photoId+ "AND"+filterID+"'");
		 }catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//I am being creative here about the format and I am not sure the format of matching two columns in the table.
	//I will fix it after asking Caroline.
	
	public int[] getFilters(Photo photo) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.FilterPhoto WHERE photoID = '"+photo.photoId+"'");
			ArrayList<Integer> filterID = new ArrayList<Integer>();
			while(rs.next()) {
				filterID.add(rs.getInt("filterID"));
			}
			int[] filters = new int[filterID.size()];
			for(int i=0;i<filterID.size();i++) {
				filters[i] = filterID.get(i);
			 }
			return filters;
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	//return a list of filterID. I have this structure in case they want us to return the path.
	
	
	
	public boolean createURL(String original, String shortened) {
		try {
			Connection conn = getConnection();
		    int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.URL (original,shortened) VALUES ('"+original+"','"+shortened+"')");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//long and shortened URL are stored in the table
	
	public String getOriginalURL(String shortened){
		try {
			Connection conn = getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.URL WHERE shortened = '"+shortened+"'");
		    while (rs.next()) {
		    	String original = rs.getString("original");
			    return original;
		    }
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean deleteURL(String shortURL) {
		try {
			Connection conn = getConnection();
		    int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.URL WHERE shortened = '" +shortURL+"'");
		 }catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean createPost(Post pst) {
		try {
			getConnection();
			int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Post (postID, time, text, userID) VALUES ('" +Integer.parseInt(pst.postID)+"','" +pst.timestamp+"','" +pst.text+"','" +pst.poster.email+"')");
			this.updatePost(Integer.parseInt(pst.postID), "explicit", 0); //double check what the specific values are
			this.updatePost(Integer.parseInt(pst.postID), "visible", 0); //double check what the specific values are
			if (pst instanceof Comment) { //add to post table then add to comment table
				Comment com = (Comment)pst;
				this.updatePost(Integer.parseInt(com.postID), "type", "comment");
				this.updatePost(Integer.parseInt(com.postID), "parentID", Integer.parseInt(com.postID));
				int rs2 = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Comment (text, parentID, childID) VALUES ('"+com.text+"','" +Integer.parseInt(com.associatedPostID.postID)+"','" +Integer.parseInt(com.postID)+"')");
			}
			else if (pst instanceof ImagePost) { //add to post table then add to image table & any other relevant tables
				ImagePost img = (ImagePost)pst;
				this.updatePost(Integer.parseInt(img.postID), "type", "imagePost");
				//then add image to image table and add filters to table
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Post getPost(String postID) {
		try {
			getConnection();
			int id = Integer.parseInt(postID);
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE postID = '"+id+"'");
			while(rs.next()) {
				String type = rs.getString("type");
				Timestamp time = rs.getTimestamp("time");
				String author = rs.getString("userID");
				String text = rs.getString("text");
				int flag = rs.getInt("explicit");
				if (type == "comment") { //set  fields
					Post parent = this.getPost(Integer.toString(rs.getInt("parentID")));
					Comment com = new Comment(this.getUser(author),Integer.toString(id),text, parent);
					com.timestamp = time;
					com.flag = rs.getInt("explicit");
					return com;
				}
				ResultSet comments = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Comments WHERE parentID = '"+postID+"'");
				ArrayList<Comment> coms = new ArrayList<Comment>();
				while(comments.next()) { //populate comments
					coms.add((Comment)getPost(Integer.toString(comments.getInt("parentID"))));
				}
				if (type == "imagePost") { //do later
					String path = this.getPhoto(rs.getString("photoID")).photoPath;
					ImagePost imgP = new ImagePost(this.getUser(author), postID, path);
					imgP.timestamp = time;
					imgP.flag = rs.getInt("explicit");
					return imgP;
				}
				Post pst = new Post(this.getUser(author), postID, text);
				pst.flag = rs.getInt("explicit");
				pst.timestamp = time;
				return pst;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	private <T> String formatPostUpdateString(int id, String varname, T var) {
		return String.format("UPDATE TrackOneDB.Post SET %s = '"+var+"' WHERE postID = '"+id+"'", varname);
	}
	
	public <T> boolean updatePost(int id, String field, T newValue) { //do not use when updating comments
		String query = formatPostUpdateString(id, field, newValue);
		try {
			getConnection();
			int rs = conn.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean deletePost(String id) { //delete posts all posts users made?
		try {
			getConnection();
			int pstID = Integer.parseInt(id);
			//delete photos and filters before deleting post?
			int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.Post WHERE postID = '" +pstID+ "'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

/*

	public boolean createUser(User usr){
		//stub
		Connection conn;
		try {
			conn = getConnection();
			int log = 0;
			if(usr.loggedIn)
				log = 1;
			int rs = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.User (email, name,password,type,loggedIn) VALUES ('"+usr.email+"','" +usr.name+"','" +usr.password+"','"+ usr.type+"',"+log+")");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public User getUser(String email) { 
		//general function- returns entire user.
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String type = rs.getString("type");
				int loggedIn = rs.getInt("loggedIn");
				boolean log = false;
				if(loggedIn == 1) {
					log = true;
				}
				User usr = new User(name,email,password,type,log);
				return usr;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public String getUserType(String email) {
		//stub
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String type = rs.getString("type");
				return type;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserType(String email, String type) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET type = '"+type+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getUserName(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String name = rs.getString("name");
				return name;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserName(String email, String name) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET name = '"+name+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUserAddress(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String address = rs.getString("address");
				return address;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserAddress(String email, String address) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET address = '"+address+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUserPhone(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String phone = rs.getString("phone");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserPhone(String email, String phone) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET phone = '"+phone+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUserBirthday(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String birthday = rs.getString("birthday");
				return birthday;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean setUserBirthday(String email, String bday) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET birthday = '"+bday+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int getUserPoints(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int points = rs.getInt("points");
				return points;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	public boolean setUserPoints(String email, int amt) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET points = '"+amt+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String[] getUserFollowing(String email) {
		//returns the user's followers' email addresses in an array
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			int userID=0;
			while(rs.next()) {
				userID = rs.getInt("userID");	
			}
			rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Follow WHERE userID =" + userID);
			ArrayList<Integer> followerID = new ArrayList<Integer>();
			while(rs.next()) {
				followerID.add(rs.getInt("follow"));
			}
			String[] followers = new String[followerID.size()];
			for(int i=0;i<followerID.size();i++) {
				rs=conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + followerID.get(i).toString());
				while(rs.next()) {
					String fEmail = rs.getString("email");
					followers[i] = fEmail;
				}
			}
			return followers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addUserFollowing(String userEmail, String followerEmail) {
		//adds the new follower of the user to db
		Connection conn;
		
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+userEmail+"'");
			int userID = 0;
			while(rs.next()) {
				userID = rs.getInt("userID");
			}
			rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+followerEmail+"'");
			int followerID = 0;
			while(rs.next()) {
				followerID = rs.getInt("userID");
			}
			rs = conn.createStatement().executeQuery("SELECT Count(*) FROM TrackOneDB.Follow WHERE userID = " + userID + " AND follow = "+ followerID);
			int count = 0;
			while(rs.next()) {
				count = rs.getInt("Count(*)");
			}
			if(count>0) {
				return true;
			}else {
				int r = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Follow (userID, follow) VALUES ("+userID+","+followerID+")");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUserInviter(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				String inviterEmail = rs.getString("inviter");
				return inviterEmail;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean setUserInviter(String userEmail, String inviterEmail) {
Connection conn;
		
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+userEmail+"'");
			int userID = 0;
			while(rs.next()) {
				userID = rs.getInt("userID");
			}
			rs = conn.createStatement().executeQuery("SELECT userID FROM TrackOneDB.User WHERE email = '"+inviterEmail+"'");
			int inviterID = 0;
			while(rs.next()) {
				inviterID = rs.getInt("userID");
			}
			
			int ra = conn.createStatement().executeUpdate("UPDATE TrackOneDB.User SET inviter = '"+inviterEmail +"' WHERE userID =" + userID);
			
			rs = conn.createStatement().executeQuery("SELECT Count(*) FROM TrackOneDB.Invite WHERE invitee = " + userID + " AND inviter = "+ inviterID);
			int count = 0;
			while(rs.next()) {
				count = rs.getInt("Count(*)");
			}
			if(count>0) {
				return true;
			}else {
				int r = conn.createStatement().executeUpdate("INSERT INTO TrackOneDB.Invite (inviter, invitee) VALUES ("+inviterID+","+userID+")");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String[] getUsersInvited(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			int userID=0;
			while(rs.next()) {
				userID = rs.getInt("userID");	
			}
			rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Invite WHERE inviter =" + userID);
			ArrayList<Integer> inviteeID = new ArrayList<Integer>();
			while(rs.next()) {
				inviteeID.add(rs.getInt("invitee"));
			}
			String[] invitees = new String[inviteeID.size()];
			for(int i=0;i<inviteeID.size();i++) {
				rs=conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + inviteeID.get(i).toString());
				while(rs.next()) {
					String inEmail = rs.getString("email");
					invitees[i] = inEmail;
				}
			}
			return invitees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	public boolean setUsersInvited(int id) {
		//stub
		return true;
	}//isn't this basically the same thing as setUserInviter..?
	public boolean getUserHasInvited(String email) {
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int hasInvited = rs.getInt("hasInvited");
				boolean boolInvited = false;
				if(hasInvited == 1)
					boolInvited = true;
				return boolInvited;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean setUserHasInvited(String email, boolean status) {
		Connection conn;
		try {
			conn = getConnection();
			int hasInvited;
			if(status==false) {
				hasInvited = 0;
			}
			else {
				hasInvited = 1;
			}
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET hasInvited = '"+hasInvited+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*
	public JSONObject getUserCard(int id) {
		//stub
		return null;
	}
	
	public boolean setUserCard(int id, JSONObject card) {
		//stub
		return true;
	}
	public boolean getUserCanLogin(String email) {
		//responses to validAccount attribute in DB
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int valid = rs.getInt("validAccount");
				boolean canLogin = false;
				if(valid==1)
					canLogin = true;
				return canLogin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean setUserCanLogin(String email, boolean status) {
		//responses to validAccount attribute in DB
		Connection conn;
		try {
			conn = getConnection();
			int canLogin;
			if(status==false) {
				canLogin = 0;
			}
			else {
				canLogin = 1;
			}
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET validAccount = '"+canLogin+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean getUserPrivate(String email){
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			while(rs.next()) {
				int privacy = rs.getInt("private");
				boolean priv = false;
				if(privacy==1)
					priv = true;
				return priv;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean setUserPrivate(String email, boolean status) {
		Connection conn;
		try {
			conn = getConnection();
			int privacy;
			if(status==false) {
				privacy = 0;
			}
			else {
				privacy = 1;
			}
			int rs = conn.createStatement().executeUpdate("UPDATE TrackOneDB.USER SET private = '"+privacy+"' WHERE email = '"+email+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int[] getUserPosts(String email) {
		//returns post IDs, not post
		Connection conn;
		try {
			conn=getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE email = '"+email+"'");
			int userID=0;
			while(rs.next()) {
				userID = rs.getInt("userID");	
			}
			rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE userID =" + userID);
			ArrayList<Integer> postID = new ArrayList<Integer>();
			while(rs.next()) {
				postID.add(rs.getInt("postID"));
			}
			int[] arr = new int[postID.size()];
			for(int i =0; i<postID.size(); i++) {
				arr[i] = postID.get(i);
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	public boolean setUserPosts(int id, int[] posts) {
		//stub
		return true;
	}
	
	
	
	public boolean deleteUser(String email) {
		Connection conn;
		try {
			conn = getConnection();
			int rs = conn.createStatement().executeUpdate("DELETE FROM TrackOneDB.User WHERE email = '" + email + "'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/*
	public boolean snap() {
		//deletes half of all users
		return true;
	}
	

	/*
	public JSONObject createPost(JSONObject post) {
		//stub
		return new JSONObject();
	}
	*/
	public Post[] getPost(int num) {
		//general function - returns entire post object.
		Post[] arr = new Post[num];
		int pos = 0;
		try {
			getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post ORDER BY time DESC LIMIT " + num);
			while(rs.next()) {
				String postID = Integer.toString(rs.getInt("postID"));
				//String type = rs.getString("type");
				int explicit = rs.getInt("explicit");
				Timestamp time = rs.getTimestamp("time");
				int userID = rs.getInt("userID");
				String text = rs.getString("text");
				ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + userID);
				String email;
				while(rs2.next()) {
					email = rs2.getString("email");
				}
				arr[0] = new Post(getUser(email), postID, text);
				arr[0].timestamp = time;
				arr[0].setFlag(explicit);
				pos ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return arr;
	}
	
	public Post[] getPostFollowing(User usr) {
		String email = usr.email;
		int userID;
		try {
			getConnection();
			ResultSet rs = conn.createStatement().executeQuery("Select * FROM TrackOneDB.User WHERE email = '"+ email + "'");
			while (rs.next()) {
				userID = rs.getInt("userID");
			}
			ArrayList<Integer> arr = new ArrayList<Integer>();
			rs = conn.createStatement().executeQuery("Select * FROM TrackOneDB.Follow WHERE userID = " + userID);
			while(rs.next()) {
				arr.add(rs.getInt("follow"));
			}
			ArrayList<Post> postArr = new ArrayList<Post>();
			for(int i=0; i<arr.size(); i++) {
				rs=conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE userID =" + arr.get(i));
				while(rs.next()) {
					String postID = Integer.toString(rs.getInt("postID"));
					//String type = rs.getString("type");
					int explicit = rs.getInt("explicit");
					Timestamp time = rs.getTimestamp("time");
					String text = rs.getString("text");
					ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + arr.get(i));
					String email2;
					while(rs2.next()) {
						email = rs2.getString("email");
					}
					Post p = new Post(getUser(email2), postID, text);
					p.timestamp = time;
					p.setFlag(explicit);
					postArr.add(p);
				}
			}
			//sort by datetime****
			return (Post[]) postArr.toArray();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	public Post[] getFlaggedPost(int x) {
		ArrayList<Post> arr = new ArrayList<Post>();
		try {
			getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.Post WHERE explicit = "+ x);
			while(rs.next()) {
				String postID = Integer.toString(rs.getInt("postID"));
				//String type = rs.getString("type");
				int explicit = rs.getInt("explicit");
				Timestamp time = rs.getTimestamp("time");
				int userID = rs.getInt("userID");
				String text = rs.getString("text");
				ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM TrackOneDB.User WHERE userID = " + userID);
				String email;
				while(rs2.next()) {
					email = rs2.getString("email");
				}
				Post p = new Post(getUser(email), postID, text);
				p.timestamp = time;
				p.setFlag(explicit);
				arr.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return (Post[]) arr.toArray();
	}
/*
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
	

	
	public boolean createAsset(ImageIO photo) {
		//stub
		return true;
	}
	
	public ImageIO getAssetPhoto(int id) {
		//we will save image paths in the database and fetch them from disk
		//stub
		return null;
	}
	
	public boolean setAssetPhoto(ImageIO pic) {
		//stub
		return true;
	}
	
	public boolean deleteAsset(int id) {
		//stub
		return true;
	}

	
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
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBAdapter adapter = new DBAdapter();
		adapter.getConnection();
		User user1 = adapter.new User("Caroline Kim","ckim9@umass.edu","rbals1997!","Member",true);
		User user2 = adapter.getUser("ckim98@umass.edu");
		//adapter.setUserType("ckim9@umass.edu", "admin");
		
	}
*/
} 
