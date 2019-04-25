package posting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Photo extends Post
{
	int pointsForPhoto;
	File file;

	public Photo(user poster, String postID, File file)
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
			try
			{
				 BufferedImage image = ImageIO.read(file);
         ImageIO.write(image, "png",new File("images/" + file.getName()));
			}
			catch (IOException ex)
			{
			    ex.printStackTrace();
			}
		}
	}

	public boolean checkFile(String extension)
	{
		if(extension == "png")
		{
			return true;
		}
		return false;
	}

   /* Adds Points to the users point stack
	* @params none
	* @return a boolean indicating success or failure
	*/
	void addPoints()
	{
		//user.addpoints(this.pointsForPhoto)
		return;
	}
}
