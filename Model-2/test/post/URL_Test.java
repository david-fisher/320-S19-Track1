package post;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class URL_Test {
    @Test
    public void testURLCreation() {
        String original = "www.helloworld.com";
        String shortened = URLShortner.shorten(original);
        assertEquals(shortened.length(), 23);
    }

    @Test
    public void testShortenCode() {
        String shortened = URLShortner.shorten("~");
        assertEquals(shortened.length()-18,5);
    }

    @Test
    public void testShortenFormat() {
        String original = "www.helloworld.com";
        String shortened = URLShortner.shorten(original);
        assertEquals(shortened.substring(0,18),"membersonly.com/l/");
    }
}
