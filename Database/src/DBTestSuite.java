

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.Object;

import org.junit.jupiter.api.Test;

class DBTestSuite {

	DBAdapter db = new DBAdapter();
	

	@Test
	public boolean testConnection(){
		//tests if we can successfully connect to the database independent of anything else.
		String DBAddress = "jdbc:mysql://localhost:3306/TrackOneDB"; //access address for database. figure out l8tr
		Connection conn;
		
		try {		
			System.out.println("Attempting Connection");
			conn = DriverManager.getConnection(DBAddress,"root","root");
			//conn.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	@Test
	public void testFollowUser() {
		db.deleteUser("nol6@fortnite.edu");
		db.deleteUser("noobl6@fortnite.edu");
		db.deleteUser("noobl26@fortnite.edu");
		User member = new User("nol6@fortnite.edu", "Korg", "Rockman", "password1", 1700, "swamy@umass.edu");
		member.type = "member";
		member.address = "100 Street Lane";
		member.city = "Townsville";
		member.state = "LA";
		member.zip = "42069";
		member.ccNum = "111111111111";
		member.ccv = "123";
		member.expM = "09";
		member.expY = "2019";
		member.birthday = "aaaaaa";
		member.phone = "2222222";
		member.password = "sss";
		member.hasInvited = false;
		member.loggedIn = true; // seems unecessary to me, included in meantime
		member.isValidated = true; // if user is currently verified by credit card
		member.privacy = false;
		String q = "www";
		if(db.createUser(member)) { System.out.println("Korg Successfully Created"); }
		User member2 = new User("noobl6@fortnite.edu", "Korg", "Rockman", "password1", 1700, "swamy@umass.edu");
		member2.type = "member";
		member2.address = "100 Street Lane";
		member2.city = "Townsville";
		member2.state = "LA";
		member2.zip = "42069";
		member2.ccNum = "111111111111";
		member2.ccv = "123";
		member2.expM = "09";
		member2.expY = "2019";
		member2.birthday = "aaaaaa";
		member2.phone = "2222222";
		member2.password = "sss";
		member2.hasInvited = false;
		member2.loggedIn = true; // seems unecessary to me, included in meantime
		member2.isValidated = true; // if user is currently verified by credit card
		member2.privacy = false;
		String r = "www";
		if(db.createUser(member2)) { System.out.println("Korg Successfully Created"); }
		db.followUser(member.email, member2.email);
		
		User member3 = new User("noobl26@fortnite.edu", "Korg", "Rockman", "password1", 1700, "swamy@umass.edu");
		member3.type = "member";
		member3.address = "100 Street Lane";
		member3.city = "Townsville";
		member3.state = "LA";
		member3.zip = "42069";
		member3.ccNum = "111111111111";
		member3.ccv = "123";
		member3.expM = "09";
		member3.expY = "2019";
		member3.birthday = "aaaaaa";
		member3.phone = "2222222";
		member3.password = "sss";
		member3.hasInvited = false;
		member3.loggedIn = true; // seems unecessary to me, included in meantime
		member3.isValidated = true; // if user is currently verified by credit card
		member3.privacy = false;
		String e = "www";
		//if(db.createUser(member2)) { System.out.println("Korg Successfully Created"); }
		db.followUser(member.email, member3.email);
	}
	
	@Test
	public void testUnFollowUser() {
		db.deleteUser("f@fortnite.edu");
		db.deleteUser("f1@fortnite.edu");
		User member = new User("f@fortnite.edu", "Korg", "Rockman", "password1", 1700, "swamy@umass.edu");
		member.type = "member";
		member.address = "100 Street Lane";
		member.city = "Townsville";
		member.state = "LA";
		member.zip = "42069";
		member.ccNum = "111111111111";
		member.ccv = "123";
		member.expM = "09";
		member.expY = "2019";
		member.birthday = "aaaaaa";
		member.phone = "2222222";
		member.password = "sss";
		member.hasInvited = false;
		member.loggedIn = true; // seems unecessary to me, included in meantime
		member.isValidated = true; // if user is currently verified by credit card
		member.privacy = false;
		String q = "www";
		if(db.createUser(member)) { System.out.println("Korg Successfully Created"); }
		User member2 = new User("f1@fortnite.edu", "Korg", "Rockman", "password1", 1700, "swamy@umass.edu");
		member2.type = "member";
		member2.address = "100 Street Lane";
		member2.city = "Townsville";
		member2.state = "LA";
		member2.zip = "42069";
		member2.ccNum = "111111111111";
		member2.ccv = "123";
		member2.expM = "09";
		member2.expY = "2019";
		member2.birthday = "aaaaaa";
		member2.phone = "2222222";
		member2.password = "sss";
		member2.hasInvited = false;
		member2.loggedIn = true; // seems unecessary to me, included in meantime
		member2.isValidated = true; // if user is currently verified by credit card
		member2.privacy = false;
		String r = "www";
		if(db.createUser(member2)) { System.out.println("Korg Successfully Created"); }
		
		db.followUser(member.email, member2.email);
		db.unfollowUser(member.email, member2.email);
	}
	
	@Test
	public void testUserCreation(){
		if(testConnection() == false) {
			return;
		}
		User admin = new User("pres@whitehouse.gov", "big", "don", "pass", 80000,"vputin@russia.ru");
		admin.type = "admin";
		if(db.createUser(admin)) { System.out.println("Admin Successfully Created"); }
		else { System.out.println("Admin Unsuccessfully Created."); }
		if(db.deleteUser(admin.email)) { System.out.println("Admin Sucessfully Deleted"); };
		User member = new User("noobkiller69@fortnite.edu", "Korg", "Rockman", "password1", 1700, "swamy@umass.edu");
		member.type = "member";
		member.address = "100 Street Lane";
		member.city = "Townsville";
		member.state = "LA";
		member.zip = "42069";
		member.ccNum = "111111111111";
		member.ccv = "123";
		member.expM = "09";
		member.expY = "2019";
		member.birthday = "aaaaaa";
		member.phone = "2222222";
		member.password = "sss";
		member.hasInvited = false;
		member.loggedIn = true; // seems unecessary to me, included in meantime
		member.isValidated = true; // if user is currently verified by credit card
		member.privacy = false;
		String q = "www";
		if(db.createUser(member)) { System.out.println("Korg Successfully Created"); }
		if(db.updateUser("noobkiller69@fortnite.edu","firstName","Borg")) {
			System.out.println("update borked");
		}
		User newUser = db.getUser("noobkiller69@fortnite.edu");
		if (newUser == null) { System.out.println("wrong"); return; }
		if(newUser.firstName.equals("Borg")){
			System.out.println("User Successfully Updated");
			db.deleteUser("noobkiller69@fortnite.edu");
		}
		else { System.out.println("User Unsuccessfully Updated"); }
	}
	
	@Test
	public void testUserModification(){
		//tests if we can successfully connect to the database,
		//edit a user's data, then fetch that updated user data from the DB.
		
		//Also covers case if user does not exist.
	}
	
	@Test
	public void testUserDeletion(){
		//tests if we can successfully connect to the database,
		//delete a user, then fail to fetch that user from the DB.
		
		//Also covers case if user does not exist.
	}
	
	@Test
	public void testPostCreation() {
		DBAdapter db = new DBAdapter();
		assertTrue(testConnection());
		User usr = new User("sgshshsk@umasss.edu","Yu","Fu", "april", 0, "yufu006@gmail.com");
		usr.type = "admin";
		if(db.createUser(usr)) { System.out.println("Adm Successfully Created"); }
		else { System.out.println("Adm Unsuccessfully Created."); }
		db.deleteUser("sgshshsk@umasss.edu");
		Post pst = new Post(usr,"post","21999", "hey");
		assertTrue(db.createPost(pst));
		System.out.println(db.deletePost(pst.postID));
//		Comment com = new Comment(usr, "comment", "3112", "comment to post", pst);
//		assertTrue(db.createPost(com));
//		db.deletePost(pst.postID);
//		db.deletePost(com.postID);
//		ImagePost imgP = new ImagePost(usr,"3220","shrek.wav","imagePost");
//		db.deletePost(imgP.postID);
//		System.out.println(db.deletePost(imgP.postID));
//		assertTrue(db.createPost(imgP));
//		db.deletePost(imgP.postID);
//		Post pst1 = new Post(usr,"32","hey"); //Posts generated by same user
//		assertTrue(db.createPost(pst1)); //successfully created
//		assertTrue(db.getPost("72").text.equals("hey")); //test if post 72 is stored successfully
//		assertTrue(db.getPost("72").text.equals(db.getPost("32").text));
//		assertFalse(db.createPost(pst1)); //create the same post should return false
		//assertFalse(db.createPost(new Post(usr,"72","yyy"))); //same postID should fail too	
		if(db.deleteUser(usr.email)) { System.out.println("Admin Sucessfully Deleted"); };
		//tests if we can successfully connect to the database,
		//create a post, then fetch that post from the DB.
		
		//Also covers case if the post already exists.
	}
	
	public void testUserPosts() {
		db.deletePost("1");
		db.deletePost("2");
		db.deletePost("3");
		db.deleteUser("email");
		User author = new User("email", "joe", "aaaa", "I want 2 die", 420, "jeff");
		author.type = "member";
		Post post1 = new Post(author, "textPost", "1", "hi1");
		Post post2 = new Post(author, "textPost", "2", "hi2");
		Post post3 = new Post(author, "textPost", "3", "hi3");
		db.createUser(author);
		db.createPost(post1);
		db.createPost(post2);
		db.createPost(post3);
		ArrayList<Post> postz = db.getUserPosts(author.email);
		for (int i = 0; i < postz.size(); i++) {System.out.println(postz.get(i).text); }
	}
//	@Test
//	public void testPostModification() {
//		DBAdapter db = new DBAdapter();
//		assertTrue(testConnection());
//		User usr = new User("yuf@umass.edu","Yu","Fu", 0, "yufu0012016@gmail.com");
//		Post pst = new Post(usr,"72", "hey");
//		Post pst1 = new Post(usr,"32","hey"); //Posts generated by same user
//		assertTrue(db.updatePost(72, "text", null)); //test if operation completed
//		assertTrue(db.getPost("72").text.equals(null)); //test if succeed
//		assertTrue(db.getPost("32").text.equals("hey")); //post 32 should not change
//		assertFalse(db.updatePost(33, "text","dsds")); //test if post does not exist
//		
//		//tests if we can successfully connect to the database,
//		//edit a post's data, then fetch that updated post data from the DB.				
//		//Also covers case if the post does not exist.
//	}
//	
//	@Test
//	public void testPostDeletion(){
//		DBAdapter db = new DBAdapter();
//		assertTrue(testConnection());
//		User usr = new User("yuf@umass.edu","Yu","Fu", 0, "yufu0012016@gmail.com");
//		Post pst = new Post(usr,"72", "hey");
//		Post pst1 = new Post(usr,"32","hey"); //Posts generated by same user
//		assertTrue(db.deletePost(pst.postID));
//		assertFalse(db.getPost("72").equals(pst));
//		assertFalse(db.deletePost("66")); // delete nonexistent post
//		assertTrue(db.getPost("32").equals(pst1));
//		assertFalse(db.updatePost(72, "text", null));
//		//tests if we can successfully connect to the database,
//		//delete a post, then fail to fetch that post from the DB.
//		
//		//Also covers case if the post does not exist.
//	}
//	
	@Test
	public void testGetUserPosts(){
		//tests if we can successfully connect to the database,
		//fetch all posts by the user, and return an array of posts.
		
		//Also covers case if the user does not exist.
	}
	
	@Test
	public void testGetFlaggedPosts(){
		//tests if we can successfully connect to the database,
		//fetch all flagged posts, and return an array of posts.

	}
	
	@Test
	public void testGetRecentPosts(){
		//tests if we can successfully connect to the database,
		//fetch all recent posts, and return an array of posts.
	}
	
	//@Test
	//public void testGetTopPosts(){
		//tests if we can successfully connect to the database,
		//fetch all "top" posts (sorted by comment #), and return an array of posts.
	//}
	
	@Test
	public void testImageCreation() {
		//tests if we can successfully connect to the database,
		//create an image entry, then fetch that image from the DB.
		
		//Also covers case if the image already exists.
	}
	
	@Test
	public void testImageModification() {
		//tests if we can successfully connect to the database,
		//edit an image's data, then fetch that updated image data from the DB.
				
		//Also covers case if the image does not exist.
	}
	
	@Test
	public void testImageDeletion() {
		//tests if we can successfully connect to the database,
		//delete an image, then fail to fetch that image's info from the DB.
				
		//Also covers case if the image does not exist.
	}
	
	@Test
	public void testImageAndFilters() {
		//tests if we can successfully connect to the database,
		//fetch an image, and return it and its filters.
				
		//Also covers case if the image does not exist.
	}
	
	
	@Test
	public boolean testURLCreation() {
		//tests if we can successfully connect to the database,
		if(testConnection() == false) {
			return false;
		}
		//Create DBAdapter
		DBAdapter DB = new DBAdapter();
		
		//create a URL entry, then fetch that URL from the DB.
		DB.createURL("www.umass.edu","u.edu");
		
		if(DB.getOriginalURL("u.edu").equals("www.umass.edu")) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		DBTestSuite test = new DBTestSuite();
//		System.out.println(test.db.deleteUser("noobkiller69@fortnite.edu"));
//		//System.out.println(test.testConnection());
//		test.testUserCreation();
//		test.testPostCreation();
		//test.testFollowUser();
		test.testUserPosts();
		//test.testUnFollowUser();
		//System.out.println(test.testURLCreation());
	}
	
}