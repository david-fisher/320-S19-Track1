package registration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Registration_Test {

	/* Registration - Testing
	Null values           [eg. Value not provided]
	String provided       [eg. Field requires integer]
	String not supplied   [eg. Field requires a String]
	String password1, 2 not matching [eg. User misspelled password 2nd time]
	String email not unique in DB [eg. User tries to use another person’s email]
	Data not in DB [eg. db.DBAdapter returns false on storeData()]
	Values not realistically valid [eg. zip code is int, but too long]
    */

	@Test
	public void identityTester() {
	   // Checks if check() methods correctly identify
	   // like Strings and correctly reject unlike Strings
	}

	@Test
	public void testStoredData() {
	   // Checks with DB API to see if a new User is created
	   // with correct credentials after calling storeData()
	}

}



