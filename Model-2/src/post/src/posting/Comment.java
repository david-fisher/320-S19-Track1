package posting;

public class Comment extends Post
{
	Post associatedPostID;
	int pointsForComment;

	/* The constructor for comment will extend from Post
	 * 
	 * @param Post is the Post that this comment is associated with
	 */
	public Comment(user poster, String ID, String text, Post associatedPostID) 
	{
		super(poster, ID, text);
		this.associatedPostID = associatedPostID;
		super.pointsForPost = pointsForComment;
	}
	
	/* This function will get the associated posts unique id
	 * @param none
	 * @return int that represents the associated post id
	 */
	Post getAssociatedPostID() 
	{
		return associatedPostID;
	}
	
   /* Adds Points to the users point stack
	* @params none
	* @return a boolean indicating success or failure
    */
	void addPoints() 
	{
		
		return;
	}
	
}