/**
 * 
 */
package za.co.sindi.email.impl;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import za.co.sindi.email.AbstractJavaMailSender;

/**
 * @author Bienfait Sindi
 * @since 28 December 2015
 *
 */
public class SessionResourceJavaMailSender extends AbstractJavaMailSender {

	private Session session;
	
	/**
	 * @param session
	 * @throws NoSuchProviderException 
	 */
	public SessionResourceJavaMailSender(Session session) throws NoSuchProviderException {
		super();
		if (session == null) {
			throw new IllegalArgumentException("No JavaMail Session was specified.");
		}
		this.session = session;
		initializeTransportIfNecessary();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.AbstractJavaMailSender#getSession()
	 */
	@Override
	protected Session getSession() {
		// TODO Auto-generated method stub
		return session;
	}
}
