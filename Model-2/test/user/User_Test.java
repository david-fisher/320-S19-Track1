import db.DBAdapter;
import user.*;
import stripe.*;
import org.junit.Assert;
import org.junit.Test;

public class User_Test
{
    @Test
    public void testCCCharging() {
        //Calls CC adapter to charge User’s card
        // verifies if the card is charged
        //Create new user
        User testerCC = new User("test@nucleardogs.com", "testy", "testable", 1000, null);
        //Call CC adapter to charge user card
        // Assert.assertTrue(CreditCard.charge());
    }

    @Test
    public void testAddPoints() {
        // Calls DB API to determine if User point
        // total reflects a specific number of points added
        User testerCC2 = new User("test@fluffernutter.com", "testytest", "testables", 1000, null);
        testerCC2.addPoints(10);
        // Assert.assertEquals(1010, DBAdapter.getPoints(testerCC2));

    }

    @Test
    public void testInfoUpdated() {
        // Calls DB API to determine if User credentials
        // reflect a specific change as made by updateAccountInfo()
        User testerCC3 = new User("test@deadbodyinthewoods.com", "testytestacles", "testablester", 1000, null);
        // DBAdapter.setEmail(testerCC3,"a@bc.dom");
        // Assert.assertEquals("a@bc.dom", DBAdapter.getEmail(testerCC3));
    }
}
