package DBpackage;
import java.io.File;
import java.util.Date;

public class Post {
	//Instance variables
	private User user;
	private int postId;
	private String type;
	private Date date;
	private String caption;
	private int[] comments;
	private boolean explicit;
	private boolean visible;
	private int tags;
	private File originalPhoto;
	private int parent;
	
	//Constructor
	public Post(User user, int postId, String type, Date date, String caption, int[] comments,
			boolean explicit, boolean visible, int tags, File originalPhoto, int parent){
		this.user = user;
		this.postId = postId;
		this.type = type;
		this.date = date;
		this.caption = caption;
		this.comments = comments;
		this.explicit = explicit;
		this.visible = visible;
		this.tags = tags;
		this.originalPhoto = originalPhoto;
		this.parent = parent;
	}
	
}
