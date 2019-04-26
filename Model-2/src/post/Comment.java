package post;

public class Comment extends Post
{
	Post associatedPostID;
	int pointsForComment = 5;

	/* The constructor for comment will extend from Post
	 *
	 * @param Post is the Post that this comment is associated with
	 */
	public Comment(user poster, String ID, String text, Post associatedPostID)
	{
		super(poster, ID, text);
		this.associatedPostID = associatedPostID;
		super.pointsForPost = pointsForComment;
		//this.linkPostToComment();
    addPoints();
	}

	/* This function will get the associated posts unique id
	 * @param none
	 * @return int that represents the associated test.post id
	 */
  public Post getAssociatedPostID()
	{
		return associatedPostID;
	}

	/* This function will add this comment to the list of comments
	 * associated with the original post
	 * @param none
	 * @return none
	 */
//	public void linkPostToComment()
//  {
//    getPost(associatedPostID).addComment(this);
//  }

   /* Adds Points to the users point stack
	* @params none
	* @return a boolean indicating success or failure
    */
	void addPoints()
	{
    //this.poster.addpoints();
		return;
	}

}
