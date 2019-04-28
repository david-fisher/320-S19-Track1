package post;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.testng.AssertJUnit.assertEquals;

class ImagePost_Test
{
  int ids = 0;
  user marc = new user();
  Post post1;
  ImagePost photo;
  String folder = "Model-2/images/";

  @Test
  void testAddPhoto()
  {
    try
    {
      File file = new File(folder + "test/Gunners.png");
      int originalSize = folderSize(folder+"src");
      photo = new ImagePost(marc, "1", file);
      assertEquals(originalSize + 1,  folderSize(folder + "src"));
      File toDelete = new File(folder+ "src/Gunners.png");
      toDelete.delete();
      assertEquals(originalSize ,  folderSize(folder + "src"));
    }
    catch(IOException e)
    {
      fail("Error finding test image");
    }
  }

  @Test
  void testGetImage()
  {
    try
    {
      File file = new File(folder+ "/test/Gunners.png");
      photo = new ImagePost(marc, "1", file);
      assertEquals(photo.getImage(), file);
      File toDelete = new File(folder+ "src/Gunners.png");
      toDelete.delete();

    }
    catch(Exception e)
    {
      fail("Error finding test image");
    }
  }

//  @Test
//  void testPathInDB()
//  {
//    File file = new File(folder+ "/test/Gunners.png");
//    photo = new ImagePost(marc, "1", file);
//    assertEquals(DB.get(photo.postID), folder+ "/src/Gunners.png" );
//    File toDelete = new File(folder+ "/src/Gunners.png");
//    toDelete.delete();
//  }

  int folderSize(String path) throws IOException
  {
    File file = new File(path);

    return file.listFiles().length;
  }
}
