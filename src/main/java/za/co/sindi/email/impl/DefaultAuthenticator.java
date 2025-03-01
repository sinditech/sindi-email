/**
 * 
 */
package za.co.sindi.email.impl;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

/**
 * @author Buhake Sindi
 * @since 16 July 2012
 *
 */
public class DefaultAuthenticator extends Authenticator {

	private String userName;
	private String password;
	
	/**
	 * @param userName
	 * @param password
	 */
	public DefaultAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(userName, password);
	}
}
