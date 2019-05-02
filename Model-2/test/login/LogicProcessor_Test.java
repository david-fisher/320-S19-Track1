package login;

import db.*;
import user.*;
import sun.rmi.runtime.Log;
import org.junit.jupiter.api.Test;

public class LogicProcessor_Test {

    public User testUser = new User("test@umass.edu", "Test", "UMass", 0, null,"member",null);
    public DBAdapter database = Database.adapter;
    public LoginProcessor login = new LoginProcessor();

    @Test
    public void testLoginCorrect() {
        database.updateUser("test@umass.edu", "password", "password");
        assert(LoginProcessor.checkCredentials("test@umass.edu", "password"));
    }

    @Test
    public void testLoginIncorrect() {
        database.updateUser("test@umass.edu", "password", "password");
        assert(login.checkCredentials("wrongTest@umass.edu", "wrongPassword"));
        assert(login.checkCredentials("test@umass.edu", "wrongPassword"));
        assert(login.checkCredentials("wrongTest@umass.edu", "password"));
    }

    @Test
    public void testPassReset() {
        database.updateUser("test@umass.edu", "password", "password");
        assert(login.resetPassword("newPassword", "newPassword", "test@umass.edu"));
    }

    @Test
    public void testBadPassReset() {
        database.updateUser("test@umass.edu", "password", "password");
        assert(login.resetPassword("newPassword", "wrongNewPassword", "test@umass.edu"));
    }

}
