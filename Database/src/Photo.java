
public class Photo {
	int photoId;
	String photoPath;
	
	public Photo(int photoId, String photoPath) {
		super();
		this.photoId = photoId;
		this.photoPath = photoPath;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}
