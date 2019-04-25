package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class Photo_Test {

	int ids = 0;
	user marc = new user();
	Post post1;
	Photo photo;
  String folder = "Model-2/src/post/images/src";

	@Test
	void testAddPhoto()
  {

    try
    {
      File file = new File(folder+ "//Gunners.png");
      int originalSize = folderSize(folder);
    }
    catch(IOException e)
    {
      System.out.println("Error finding test image");
    }
    photo = new Photo(marc, "1","", file);
    assertEquals(orignalSize+1, folderSize(folder));
	}

  int folderSize(String path) throws IOException
  {
    Path folder = Paths.get(path);
    int size = Files.walk(folder)
      .filter(p -> p.toFile().isFile())
      .mapToLong(p -> p.toFile().length())
      .sum();

    return size;
  }
}
