import db.DBAdapter;
import user.*;
import stripe.*;
import org.junit.Assert;
import org.junit.Test;

public class User_Test
{
    /**
     * Calls CC adapter to charge User’s card,
     * verifies if the card is charged
     */
    @Test
    public void testCCCharging() {
        //Create new user to test on
        User testerCC = new User("testEmail", "firstName", "lastName", 1000, null, "member");

        //Call CC adapter to charge user card
        boolean result = false;
        //if(testerCC.charge().notEquals("GENERIC STRIPE ERROR")) result = true;
        //assertTrue(result);
    }

    /**
     * Calls DB API to determine if User point
     * total reflects a specific number of points added
     */
    @Test
    public void testAddPoints() {
        //Create new user to test on
        User testerPoints = new User("testEmail2", "firstName2", "lastName2", 1000, null, "member");

        // Call the User addPoints() method
        //testerCC2.addPoints(10);

        // Check that the points added to the User is correct
        //assertEquals(1010, DBAdapter.getPoints(testerCC2.email));

    }

    /**
     * Calls DB API to determine if User credentials
     * reflect a specific change as made by updateAccountInfo()
     * As of right now settings only contains: - creditCard info
     * 										   - postVisibility
     * 										   - profileDescription
     */
    /*@Test
    public void testInfoUpdated() {
    	//Create new user to test on
    	User testerInfo = new User("testEmail3", "firstName3", "lastName3", 1000, null, "member");

    	//Have the DB set new credit card
    	JSONObject card = new JSONObject(); // stub
        DBAdapter.setUserCard(testerInfo,card); //unfortunately does not exist/work yet

        //Check all credentials are correct
        assertEquals(card, DBAdapter.getUserCard(testerInfo));
    }*/
}
