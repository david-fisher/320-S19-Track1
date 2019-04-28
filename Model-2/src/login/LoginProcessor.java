package login;

import org.junit.Test;

import user.User;

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
		User currentUser = DBAdapter.getUser(email); // TODO placeholder for DBAdapter for now
		if(currentUser.email == email && currentUser.password == password) {
			if(createSessionCookie()) return true;
			else return false;
		} else return false;
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
		User currentUser = DBAdapter.getUser(email); // TODO placeholder for DBAdapter for now
		if(server.newSession(currentUser)) return true; // TODO figure out how to call newSesssion
		else return false;
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
		User currentUser = DBAdapter.getUser(email); // TODO placeholder for DBAdapter for now
		String currentPassword = DBAdapter.getPassword(currentUser); // TODO placeholder for DBAdapter for now
		if(password == currentPassword) {
			DBAdapter.setPassword(currentUser, newPassword); // TODO placeholder for DBAdapter for now
			return true;
		} else {
			return false;
		}
	}

}
