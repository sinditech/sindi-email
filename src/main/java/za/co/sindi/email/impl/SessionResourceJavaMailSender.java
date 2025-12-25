/**
 * 
 */
package za.co.sindi.email.impl;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import za.co.sindi.email.AbstractJavaTransportMailSender;

/**
 * @author Bienfait Sindi
 * @since 28 December 2015
 *
 */
public class SessionResourceJavaMailSender extends AbstractJavaTransportMailSender {

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
	
	public SessionResourceJavaMailSender(Properties properties) throws NoSuchProviderException {
		this(properties, null);
	}
	
	public SessionResourceJavaMailSender(Properties properties, Authenticator authenticator) throws NoSuchProviderException {
		super();
		if (properties == null) {
			throw new IllegalArgumentException("No JavaMail properties was provided..");
		}
		
		this.session = authenticator == null ? Session.getDefaultInstance(properties) : Session.getDefaultInstance(properties, authenticator);
		initializeTransportIfNecessary();
	}
}
