package login;

import db.*;
import user.*;
import sun.rmi.runtime.Log;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LogicProcessor_Test {

    public User testUser = new User("test@umass.edu", "Test", "UMass", 0, null,"member",null);
    public DBAdapter database = Database.adapter;
    public LoginProcessor login = new LoginProcessor();

    /**
     *  Tests for when a user enters CORRECT login information
     *  Evaluates to correct if it CAN log in.
     */

    @Test
    public void testLoginCorrect() {
        database.updateUser("test@umass.edu", "password", "password");
        assertTrue(LoginProcessor.checkCredentials("test@umass.edu", "password"));
    }

    /**
     *  Tests for when a user enters INCORRECT login information
     *  Evaluates to correct if it CANNOT log in.
     *
     *  Tests for the following:
     *      - wrong email and password
     *      - right email and wrong password
     *      - wrong email and right password
     */

    @Test
    public void testLoginIncorrect() {
        database.updateUser("test@umass.edu", "password", "password");
        assertTrue(login.checkCredentials("wrongTest@umass.edu", "wrongPassword"));
        assertTrue(login.checkCredentials("test@umass.edu", "wrongPassword"));
        assertTrue(login.checkCredentials("wrongTest@umass.edu", "password"));
    }

    /**
     *  Tests that the user can update their password
     *  if the information is CORRECT
     *  Evaluates to correct if it CAN be updated.
     */

    @Test
    public void testPassReset() {
        database.updateUser("test@umass.edu", "password", "password");
        assertTrue(login.resetPassword("newPassword", "newPassword", "test@umass.edu"));
    }

    /**
     *  Tests that the user CANNOT update their password
     *  if the information is INCORRECT
     *  Evaluates to correct if it CANNOT be updated.
     */

    @Test
    public void testBadPassReset() {
        database.updateUser("test@umass.edu", "password", "password");
        assertTrue(login.resetPassword("newPassword", "wrongNewPassword", "test@umass.edu"));
    }

}
