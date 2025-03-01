/**
 * 
 */
package za.co.sindi.email;

import za.co.sindi.email.exception.MailSendException;

/**
 * @author Bienfait Sindi
 * @since 15 April 2014
 *
 */
public interface MailSender<MM extends MailMessage, MMM extends MultipartMailMessage> extends AutoCloseable {
	
	/**
	 * Creates a plain text mail message. This message only accepts <code>text/plain</code> MIME message.
	 * @return an instance of {@link MailMessage}.
	 */
	public MM createPlaintextMailMessage();
	
	/**
	 * Creates a HTML Multipart Mail Message.
	 * 
	 * @return an implemented {@link MultipartMailMessage} specific for HTML message creation.
	 */
	public MMM createMultipartMailMessage();
	
	/**
	 * Send the actual mail message
	 * @param mailMessages the list of {@link MailMessage}s.
	 * 
	 * @throws MailSendException
	 */
	public void send(@SuppressWarnings("unchecked") MM... mailMessages) throws MailSendException;
}
