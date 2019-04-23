

import java.util.Date;

public class UserPost extends Post{
	//Variables
	private boolean explicit;
	private boolean visible;
	
	//Constructor
	public UserPost(User user, int postId, String type, Date date, boolean explicit, boolean visible) {
		super(user, postId, type, date);
		this.explicit = explicit;
		this.visible = visible;
	}
	
}
