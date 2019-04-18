package login;

import org.junit.Test;

public class LoginProcessor {
	
	private String email;
	private String password;
	
	public LoginProcessor(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	/** 
	 *  Returns a boolean value indicating whether the server
	 *  accepts the createSessionCookie() method and the 
	 *  User login credentials are verified against the DB.
	 *
	 *  @param  none 
	 *  @return boolean indicating if the User has been logged in
	 */

	public boolean login() {
		return true;
	}

	/**
	 *  Asks the web server to create a session for the User
	 *  that is logging in. Returns a boolean indicating if
	 *  it ended up being created.
	 *
	 *  @param  none 
	 *  @return boolean indicating if the session is created
	 */

	public boolean createSessionCookie() {
		return true;
	}

	/**
	 *  Sends a request to the DB adapter to change the current
	 *  User’s password (which is verified first) to a new one.
	 *  Returns a boolean value indicating if it passes.
	 *
	 *  @param	password	A String to be checked against the DB
	 *  				record for the password of the User
	 *  @param	newPassword	A String to be sent to the DB to be 
	 *  				stored as the new password for the User.
	 *  @return boolean indicating if the password is reset
	 */

	public boolean resetPassword(String password, String newPassword) {
		return true;
	}

	/* LoginProcessor - Testing */
	
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
