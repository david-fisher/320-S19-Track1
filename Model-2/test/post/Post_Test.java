import org.junit.Test;
import post.Comment;
import post.Post;
import user.User;

import java.sql.Timestamp;

import static org.junit.Assert.*;

class Post_Test
{
	int ids = 0;
	User marc = new User("l","marc","rossi",100, null);
	Post post1;
	Post post2;
	Post post3;
	Comment comment1;

	/*
	 * Checks that a test.post is being successfully created
	 */

	@Test
	void testPostCreation()
	{
		String text = "#320isSoAmzing wow i love this class #Umass";
		String id = createId();
		post1 = new Post(marc, id, text);

		assertEquals(text, post1.getText());
		assertEquals(id, post1.getPostID());
		assertEquals(marc, post1.getUser());
		post1.setText("hi");
		assertEquals("hi", post1.text);
		assertEquals(0, post1.flag);
		post1.setFlag(1);
		assertEquals(1, post1.flag);
	}

	/*
	 * Tests to see if the hashtags are being parsed correctly
	 * and added and deleted as intended
	 */
	@Test
	void testHashtag()
	{
		String text = "#320isSoAmzing wow i love this class #Umass";
		post1 = new Post(marc, createId(), text);

		assertTrue(post1.hashtags.contains("#Umass"));
		assertTrue(post1.hashtags.contains("#320isSoAmzing"));
		assertFalse(post1.hashtags.contains("wow"));
		assertEquals(post1.hashtags.size(), 2);
		post1.addHashtag("#car");
		assertTrue(post1.adminHashtags.contains("#car"));
		assertEquals(post1.adminHashtags.size(), 1);
		post1.removeHashtag("#car");
		assertFalse(post1.adminHashtags.contains("#car"));
	}

	/*
	 * Test to see comments are functioning as intended in relation to a test.post
	 */
	@Test
	void testAttachedComments()
	{
		post1 = new Post(marc, createId(), "comment test");
		comment1 = new Comment(marc,createId(), "comment test", post1);
		assertEquals(0, post1.comments.size());
		post1.addComment(comment1);
		assertEquals(1, post1.comments.size());
		post1.removeComment(comment1);
		assertEquals(0, post1.comments.size());
	}

	/*
	 * checks to see if Timestamps are being created and functioning as intended
	 */
	@Test
	void testTimeStamps()
	{
		post1 = new Post(marc, createId(), "post1");
		post2 = new Post(marc, createId(), "post2");

		assert(post1.timestamp instanceof Timestamp);
		assertEquals(post1.timestamp.getNanos(),post2.timestamp.getNanos());
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		post3 = new Post(marc, createId(), "post3");
		assertNotEquals(post1.timestamp.getNanos(),post3.timestamp.getNanos());
		assertFalse(post3.compareTimes(post1));
	}

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
