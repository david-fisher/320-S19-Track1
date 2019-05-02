package login;

import db.Database;
import sun.rmi.runtime.Log;
import user.*;
import org.junit.jupiter.api.Test;

public class LogicProcessor_Test {

    public User testUser = new User("test@umass.edu", "Test", "UMass", 0, null,"member",null);
    Database.adapter.updateUser(testUser.email, "password", "password");
    public LoginProcessor login = new LoginProcessor();

    @Test
    public void testLoginCorrect() {
        assert(LoginProcessor.checkCredentials("test@umass.edu", "password")); // TODO need to add his password
    }

    @Test
    public void testLoginIncorrect() {
        assert(login.checkCredentials("wrongTest@umass.edu", "wrongPassword")); // TODO need to add his password
        assert(login.checkCredentials("test@umass.edu", "wrongPassword")); // TODO need to add his password
        assert(login.checkCredentials("wrongTest@umass.edu", "password")); // TODO need to add his password
    }

    @Test
    public void testPassReset() {
        assert(login.resetPassword("newPassword", "newPassword", "test@umass.edu")); // TODO need to add his password
    }

    @Test
    public void testBadPassReset() {
        assert(login.resetPassword("newPassword", "wrongNewPassword", "test@umass.edu")); // TODO need to add his password
    }

}
