import org.junit.Test;

public class LogicProcessor_Test {

    @Test
    public void testLoginCorrect() {
        // Checks with WS & DB API to see correct credentials
        // work to log a User in
    }

    @Test
    public void testLoginIncorrect() {
        // Checks with WS & DB API to see if wrong credentials
        // still allow a User to be logged in
    }

    @Test
    public void testCookieMonster() {
        // Checks with web server if a new session
        // is created after calling createSessionCookie()
    }

    @Test
    public void testPassReset() {
        // Checks with DB API to see if password is reset
        // after calling resetPassword()
    }

}
