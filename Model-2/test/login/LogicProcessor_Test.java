package login;

import login.*;
import sun.rmi.runtime.Log;
import user.*;
import org.junit.jupiter.api.Test;

public class LogicProcessor_Test {

    public User testUser = new User("test@umass.edu", "Test", "UMass", 0, null,"member",null);

    @Test
    public void testLoginCorrect() {
        assert(LoginProcessor.checkCredentials("test@umass.edu", "password")); // TODO need to add his password
    }

    @Test
    public void testLoginIncorrect() {
        assert(LoginProcessor.checkCredentials("wrongTest@umass.edu", "wrongPassword")); // TODO need to add his password
        assert(LoginProcessor.checkCredentials("test@umass.edu", "wrongPassword")); // TODO need to add his password
        assert(LoginProcessor.checkCredentials("wrongTest@umass.edu", "password")); // TODO need to add his password
    }

    @Test
    public void testPassReset() {
        assert(LoginProcessor.resetPassword("newPassword", "newPassword", "test@umass.edu"));
    }

    @Test
    public void testBadPassReset() {
        assert(LoginProcessor.resetPassword("newPassword", "wrongNewPassword", "test@umass.edu"));
    }

}
