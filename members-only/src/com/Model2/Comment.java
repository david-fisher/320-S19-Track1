package com.Model2;

/*
import db.DBAdapter;
import user.*;
*/
public class Comment extends Post
{
	public Post associatedPostID;
	int pointsForComment = 5;

	/* The constructor for comment will extend from Post
	 *
	 * @param Post is the Post that this comment is associated with
	 */
	public Comment(User poster, String ID, String text, Post associatedPost)
	{
		super(poster, ID, text);
		this.associatedPostID = associatedPost;
		super.pointsForPost = pointsForComment;
		this.linkPostToComment();
		this.addPoints();
		this.sendToDB();
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
	public void linkPostToComment()
    {
      associatedPostID.addComment(this);
    }



   /* Adds Points to the users point stack
	* @params none
	* @return a boolean indicating success or failure
    */
	void addPoints()
	{
		this.poster.addPoints(this.pointsForComment);
		return;
	}

}
