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

//	/**
//	 * Sets connection timeout in milliseconds.
//	 * @param timeout in milliseconds
//	 */
//	public void setConnectionTimeout(int timeout);
//	
//	/**
//	 * Sets read timeout in milliseconds.
//	 * @param timeout in milliseconds
//	 */
//	public void setReadTimeout(int timeout);
//	public boolean isDebug();
//	public void setDebug(boolean debug);
//	public void setDebugOut(PrintStream out);
//	public void setStartTTLSEnable(boolean startTTLSEnable);
//	public void setStartTTLSRequired(boolean startTTLSRequired);
//	public void setUseSSL(boolean useSSL);
//	public void setSendPartial(boolean sendPartial);
//	public void setSSLCheckServerIdentity(boolean sslCheckServerIdentity);
	
	/**
	 * Creates a simple mail message. This message only accepts <code>text/plain</code> MIME message.
	 * @return an instance of {@link MailMessage}.
	 */
	public MM createSimpleMailMessage();
	
	/**
	 * Creates a HTML Multipart Mail Message.
	 * 
	 * @return an implemented {@link MultipartMailMessage} specific for HTML message creation.
	 */
	public MMM createHTMLMailMessage();
	
	/**
	 * Send the actual mail message
	 * @param mailMessages the list of {@link MailMessage}s.
	 * 
	 * @throws MailSendException
	 */
	public void send(@SuppressWarnings("unchecked") MM... mailMessages) throws MailSendException;
}
