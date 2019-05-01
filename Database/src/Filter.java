
public class Filter extends Photo{
	public int xPos;
	public int yPos;
	public boolean visibleToUser;
	
	public Filter(int photoId, String photoPath, int xPos, int ypos, boolean visibleToUser) {
		super(photoId, photoPath);
		this.xPos = xPos;
		this.yPos = ypos;
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
