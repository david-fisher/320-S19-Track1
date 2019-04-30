package login;

import user.*;
import db.*;

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
	 *  @param
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
	 *  @param	password	A String to be checked against the DB
	 *  					record for the password of the User
	 *  @param	newPassword	A String to be sent to the DB to be 
	 *  					stored as the new password for the User.
	 *  @return boolean indicating if the password is reset
	 */

	public boolean resetPassword(String newPassword, String verifyNewPassword, int verificationCode) {
		// Check verification code against server
//		if(verificationCode == Server.resetVerificationCode) { // TODO figure out how to ask the server for this
//			User currentUser = DBAdapter.getUser(email);
//			String currentPassword = DBAdapter.getPassword(currentUser);
//			if(newPassword == verifyNewPassword) {
//				DBAdapter.setPassword(currentUser, newPassword);
//				return true;
//			} else return false;
//		}
		return false;
	}

}
