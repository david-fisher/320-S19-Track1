package post;


import post.*;
import stripe.StripeCreditCard;
import user.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Comment_Test
{
	int ids;
	// Necessary fields
	String email = "test@gmail.com";
	String cardNum = "4000056655665556";
	String zipCode = "00000";
	String cvv = "123";
	String exp_month = "10";
	String exp_year = "2020";

	// Create the test card with the information above
	StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
			cvv, exp_month, exp_year);
	User marc = new User("", "", "", 0, null, "member",testCard);
	Post post1;
	Comment comment1;

	/*
	 * test designed to test that getting the associated postedId is working
	 */
	@Test
	void testAssociatedPost()
	{
		post1 = new Post(marc, createId(), "comment test");
		comment1 = new Comment(marc, createId(), "comment test", post1);

		assertEquals(comment1.getAssociatedPostID(), post1);
	}

	/*
	 * to test that the comment was added to the Posts arraylist of comments
	 */
//	@Test
//	void testSuccessfulLink()
//  {
//    post1 = new Post(marc, createId(), "comment test");
//    comment1 = new Comment(marc, createId(), "comment test", post1);
//
//    assertEquals(post1.comments.size(), 1);
//    assertEquals(post1.comments.get(0), comment1);
//  }

	/*
	 * Helper for creating posts
	 */
	String createId()
	{
		String value = Integer.toString(ids);
		ids++;
		return value;
	}
}
