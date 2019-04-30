package post;

import com.stripe.Stripe;
import org.junit.jupiter.api.Test;
import post.*;

import stripe.StripeCreditCard;
import user.*;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ImagePost_Test
{
  int ids = 0;

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
  User marc = new User("l", "l", "l", 0, null, "member", testCard);
  Post post1;
  ImagePost photo;
  String folder = "images/";

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
