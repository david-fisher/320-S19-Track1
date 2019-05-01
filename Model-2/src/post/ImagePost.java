package post;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import user.*;

public class ImagePost extends Post
{
	int pointsForPhoto = 10;
	File file;
	BufferedImage image;
	public String path;


	public ImagePost(User poster, String postID, String path) { //used to return objects from DB
		super(poster, postID, "");
		this.path = path;
	}

	public ImagePost(User poster, String postID, File file)
	{
		super(poster, postID, "");
		this.file = file;

		String extension = "";
		int i = file.getName().lastIndexOf('.');
		if (i >= 0)
		{
			extension = file.getName().substring(i+1);
		}

		if(this.checkFile(extension))
		{
			if(!CP(file.getName()))
			{
				try {
					BufferedImage image = ImageIO.read(file);
					String newPhotoPath = "images/src/" + file.getName();
					ImageIO.write(image, "png", new File(newPhotoPath));
					sendAddressToBD(newPhotoPath);
					path = newPhotoPath;
					this.addPoints();
				} catch (IOException ex) {
					System.out.println("failed to upload image");
					ex.printStackTrace();
				}
			}
		}
		else
    {
		 System.out.println("wrong file type");
    }

	}

  /*Helper funciton to check file extention
   *@param String the extention
   *@return boolean true if png false if other;
   */
	public boolean checkFile(String extension)
	{
		if(extension.equals("png"))
    {
			return true;
		}
		return false;
	}

	/* Sends path of newly saved image to the DB
	 * @ Params String the path of the new file
	 * @ return void
	 */
	public void sendAddressToBD(String path)
  {
	  //send path to bd
  }

  public File getImage()
  {
    return this.file;
  }

  /* Adds Points to the users point stack
	* @params none
	* @return a boolean indicating success or failure
	*/
	void addPoints()
	{
		//poster.addpoints(this.pointsForPhoto)
		return;
	}

	/*
	 * Anti CP Stub
	 * Please don't expect this to be anything but a stub
	 * Because that will send me to prison
	 */
	public boolean CP(String filename)
	{
		if(filename.equals("shrekImg_stub"))
		{
			return true;
		}
		return false;
	}
}
