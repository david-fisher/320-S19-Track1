package com.Model2;

/*import user.*;
import db.*;*/

public class LoginProcessor {

	/**
	 * Checks the credentials of the user trying to log in
	 *
	 * @param email		user's inputted email
	 * @param password	user's inputted password
	 *
	 * @return boolean indicating if the user can been logged in
	 */

	public static boolean checkCredentials(String email, String password) {
		User currentUser = Database.adapter.getUser(email);
		return currentUser.password.equals(password);
	}

	/**
	 *  Sends a request to the DB adapter to change the current
	 *  user's password to a new one.
	 *
	 *  @param		  newPassword A String to be sent to the DB to be stored as the new password for the User.
	 *  @param	verifyNewPassword Another string that should be identical to newPassword, otherwise condition fails.
	 *  @param				email email for the user
	 *
	 *  @return boolean indicating if the password is reset
	 */

	public boolean resetPassword(String newPassword, String verifyNewPassword, String email) {
		if(newPassword.equals(verifyNewPassword)) { // Check newPassword against verifyNewPassword
			Database.adapter.updateUser(email, "password", newPassword);
			return true;
		}
		return false;
	}
}
