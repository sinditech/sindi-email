/**
 * 
 */
package za.co.sindi.email.impl;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import za.co.sindi.common.utils.Strings;
import za.co.sindi.email.AbstractJavaMailMessage;
import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public class SimpleJavaMailMessage extends AbstractJavaMailMessage {

	/**
	 * @param session
	 */
	public SimpleJavaMailMessage(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setText(java.lang.String, java.lang.String)
	 */
	@Override
	public void setText(String text, String charsetName) throws MailException {
		// TODO Auto-generated method stub
		try {
			if (Strings.isNullOrEmpty(charsetName)) {
				getMessage().setText(text);
			} else if (getMessage() instanceof MimeMessage) {
				((MimeMessage)getMessage()).setText(text, charsetName);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}
}
