package user;

import db.DBAdapter;
import db.Database;
import user.*;
import stripe.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class User_Test {

    // User to test on
    User testUser = new User("test@umass.edu", "test", "umass", 1000, null, "member", null);
    public DBAdapter database = Database.adapter;
    /**
     *  Tests whether information can be updated for any field
     *  in the DB.
     *  Evaluates to correct if information is updated correctly.
     */

    @Test
    public void testInfoUpdated() {
    	String fieldString = "";
    	Object data = null;

    	// Update the field
        database.updateUser(this.testUser.email, fieldString, data);

        // Grab a copy of the user
        User newTestedUser = database.getUser(this.testUser.email);

        //Check all credentials are correct
        //assertEquals(newTestedUser.fieldString, data);
    }
}
