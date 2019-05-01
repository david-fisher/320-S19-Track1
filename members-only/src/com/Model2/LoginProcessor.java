package com.Model2;

//import user.*;
//import db.*;

import java.util.HashMap;

public class LoginProcessor {
	
	private String email;
	private String password;
	
	public LoginProcessor(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	/** 
	 *  Returns a boolean value indicating whether the user
	 *  credentials are correct
	 *
	 *  @return boolean indicating if the User has been logged in
	 */

	public boolean checkCredentials() {
		/*User currentUser = DBAdapter.getUser(this.email);
		if(currentUser.getPassword() == password) return true;
		else return false;*/
		return false;
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
			//DBAdapter.setUserPassword(email,newPassword); // set old password to newPassword
			return true;
		}
		return false;
	}
}
