package com.Model2;

/*import db.DBAdapter;
import db.Database;
import user.*; */

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.regex.*;

public class Post {
	public User poster;
	public String postID;
	public String text;
	public int flag = 0;
	int pointsForPost = 5;
	public Timestamp timestamp;
	public ArrayList<Comment> comments;
	public ArrayList<String> hashtags;
	public ArrayList<String> adminHashtags;
	public String type;

	/* The constructor for test.post will take in a User class variable, a String that
	 * is the unique test.post id of that test.post and the string of text associated with
	 * the test.post.
	 * @param thePoster the user object who the test.post belongs to
	 * @param postID the unique test.post ID associated with this test.post
	 * @param text the text to be populated in the UI
	 */
	public Post(User poster, String type, String postID, String text) {
		this.poster = poster;
		this.postID = postID;
		this.text = text;
		this.type = type;

		comments = new ArrayList<Comment>();
		hashtags = new ArrayList<String>();
		adminHashtags = new ArrayList<String>();

		this.parseForURLs();
		this.createTimeStamp();
		this.addPoints();
		this.parseForHashtags();
		this.sendToDB();
	}
	
	public Post(User poster, String type, String postID) {
		this.poster = poster;
		this.postID = postID;
		this.text = "";
		this.type = type;
		
		comments = new ArrayList<Comment>();
		
		this.createTimeStamp();
		this.addPoints();
	}

   /* This function will parse the text for hashtags
	* @param none
	* @return an array of the hashtags found in the text
	*/
	void parseForHashtags() {
	  if(text.length() > 0) {
		  String words[] = text.split(" ");
		  for (String word : words) if (word.charAt(0) == '#') hashtags.add(word);
	  }
	}

	public String extractAndFormat(String url) {
		// Parses for the shortened text.  Text between first and last .
		if(url.contains("www")){
			int startdom = url.indexOf(".")+1;
			int lastdom = url.lastIndexOf(".");
			String shortened = url.substring(startdom, lastdom);
			return "[" + shortened + "]" + "(" + url + ")";
		} else {
			int startdom = url.indexOf("/")+2;
			if(startdom == 1) startdom = 0;
			int lastdom = url.lastIndexOf(".");
			String shortened = url.substring(startdom, lastdom);
			return "[" + shortened + "]" + "(" + url + ")";
		}
	}

	// Approved pattern from Stack Overflow
	// Pattern for recognizing a URL, based off RFC 3986
	private static final Pattern urlPattern = Pattern.compile(
			"(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
					+ "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
					+ "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

	public void parseForURLs() {
		// look for URLs
		// modify the post text so url turns from www.cnn.com/cnnPost to [cnn](www.cnn.com/cnnPost)
		// save to the global string text, then push to db using this updatePost(int id, String field, T newValue)

		// New String
		String newText = "";
		int currPos = 0;

		// Looks through text
		Matcher matcher = urlPattern.matcher(this.text);
		String url;
		String formattedUrl;
		while (matcher.find()) {
			int matchStart = matcher.start(1);
			int matchEnd = matcher.end();
			url = this.text.substring(matchStart, matchEnd);
			formattedUrl = this.extractAndFormat(url);
			// System.out.println(url+ " " + formattedUrl);
			// System.out.println();
			if(currPos < matchStart) newText += this.text.substring(currPos, matchStart);
			newText += formattedUrl;
			currPos = matchEnd;
		}
		newText += this.text.substring(currPos, this.text.length());
		this.text = newText;
	}

   /* Removes one hashtag from the arraylist of hashtags
	* @param toBeRemoved the string to be removed from arraylist
	* @return returns true if it worked and false if not found or fail
	*/
   public boolean removeHashtag(String tag) {
   		if(adminHashtags.contains(tag)) {
			adminHashtags.remove(tag);
			return true;
		}
		return false;
   }

   /* Add a hashtag to the arraylist of hashtags
    * @param toBeAdded the string to be added to the arraylist
	* @return returns a boolean to indicate the success or failure of the action
	*/
   public boolean addHashtag(String tag) {
		if(adminHashtags.contains(tag)) return false;
		adminHashtags.add(tag);
		return true;
   }

   /* The removeComments method will remove a comment from the arraylist
	* @param none
	* @return a boolean to confirm the success or fail of this action
	*/
   public boolean removeComment(Comment comment) {
		if(comments.contains(comment)) {
			comments.remove(comment);
			return true;
		}
		return false;
   }

   /* The addComments method will add an object of type comment to the arrayList
	* of type comments
	* @param comment the comment to be added
	* @return a boolean communicating the success of the addition
	*/
   public boolean addComment(Comment comment) {
		if(comments.contains(comment)) return false;
		comments.add(comment);
		return true;
   }

   /* The getUser method will return the user object of the poster
	* @param none
	* @return an object of type user that is the user who created the test.post
	*/
   public User getUser() { return poster; }

	/*
	 * allows for flaggin a test.post
	 * @param int 0 = no flag, 1 = sent to mod, 2 = sent to owner
	 * @return none
	 */
	public void setFlag(int flag) { this.flag = flag; }

   /* getText will return the text of the test.post
	* @params none
	* @return a string containing the text of the test.post
	*/
   public String getText() { return text; }

   /* The setText class will set the text of the test.post to the string
    * @param newText will be new value of the test.post’s text
	* @return a boolean indicating success
	*/
   public void setText(String newText) {
   		text = newText;
		this.parseForHashtags();
		Database.adapter.updatePost(Integer.parseInt(this.postID),"text", text);
   }

   /* getPostId will return the unique postId
	* @param none
	* @return a string containing the unique postID
	*/
   public String getPostID() { return postID; }

   /* The createTimestamp method will create a Timestamp to help with
	* sorting of posts.
	* @params none
	* @return none;
	*/
	void createTimeStamp() { timestamp = new Timestamp(System.currentTimeMillis()); }

   /* The compareTimestamps method will compare this test.post’s timestamp with
	* another test.post’s timestamp and return true if this time is newer than the
	* incoming test.post.
	* @param test.post a test.post to compare the timestamp of to this object’s timestamp
	* @return boolean if this timestamp is newer than the object passed in
	*/
   public boolean compareTimes(Post otherPost) {
   		if(this.timestamp.compareTo(otherPost.timestamp) < 0) return true;
		return false;
	}

   /* Adds Points to the users point stack
    * @params none
    * @return a boolean indicating success or failure
    */
	void addPoints() {
		this.poster.addPoints(this.pointsForPost);
		return;
	}


	public void sendToDB() { 
		if(Database.adapter.getPost(postID)==null) {
			Database.adapter.createPost(this); 
		}
	}
}