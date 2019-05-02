package login;

import user.*;
import db.*;

import java.util.HashMap;

public class LoginProcessor {
	
	private String email;
	private String password;
	DBAdapter DB;
	
	public LoginProcessor(String email, String password) {
		this.email = email;
		this.password = password;
		DB = new DBAdapter();
	}
	
	/** 
	 *  Returns a boolean value indicating whether the user
	 *  credentials are correct
	 *
	 *  @return boolean indicating if the User has been logged in
	 */

	public boolean checkCredentials() {
		User currentUser = DB.getUser(this.email);
		return currentUser.password == password;
	}

	/**
	 *  Sends a request to the DB adapter to change the current
	 *  User’s password (which is verified first) to a new one.
	 *  Returns a boolean value indicating if it passes.
	 *
	 *  @param	newPassword	A String to be sent to the DB to be
	 *  					stored as the new password for the User.
	 *  @param	verifyNewPassword Another string that should be identical
	 *                            to newPassword, otherwise condition fails.
	 *  @param	email email for the user
	 *
	 *  @return boolean indicating if the password is reset
	 */

	public boolean resetPassword(String newPassword, String verifyNewPassword, String email) {
		if(newPassword.equals(verifyNewPassword)) { // Check newPassword against verifyNewPassword
			DB.updateUser(this.email, "password", newPassword);
			return true;
		}
		return false;
	}
}
