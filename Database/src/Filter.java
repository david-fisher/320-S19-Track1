
public class Filter extends Photo{
	int xPos;
	int ypos;
	boolean visibleToUser;
	
	public Filter(int photoId, String photoPath, int xPos, int ypos, boolean visibleToUser) {
		super(photoId, photoPath);
		this.xPos = xPos;
		this.ypos = ypos;
		this.visibleToUser = visibleToUser;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public boolean isVisibleToUser() {
		return visibleToUser;
	}

	public void setVisibleToUser(boolean visibleToUser) {
		this.visibleToUser = visibleToUser;
	}
	
}
