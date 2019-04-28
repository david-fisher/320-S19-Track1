
public class Admin extends User {

	public Admin(String email, 
				 String firstName, 
				 String lastName,  
				 int points,
				 User invitedBy) {
		super(email, firstName, lastName, points, null);
	}

	/**
	 *  Returns a boolean indicating that the text of a specific Post
	 *  can be replaced with newly specified text.
	 *
	 *  @param  post	the Post object to be edited
	 *  @param  text	the String to replace the Post text 
	 *  @return boolean indicating that the post was edited
	 */

	public boolean editPost(Post post, String text) {
		return true;
	}

	/* Admin - Testing */
	


}
