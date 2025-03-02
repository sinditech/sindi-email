/**
 * 
 */
package za.co.sindi.email;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.mail.Message;
import jakarta.mail.Session;
import za.co.sindi.email.exception.MailException;
import za.co.sindi.email.exception.MailSendException;
import za.co.sindi.email.impl.HTMLMultipartJavaMailMessage;
import za.co.sindi.email.impl.PlainTextJavaMailMessage;

/**
 * @author Bienfait Sindi
 * @since 17 April 2014
 *
 */
public abstract class AbstractJavaMailSender implements JavaMailSender {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	protected Session session;

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#isDebug()
	 */
	@Override
	public boolean isDebug() {
		// TODO Auto-generated method stub
		ensureSession();
		return session.getDebug();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setDebug(boolean)
	 */
	@Override
	public void setDebug(boolean debug) {
		// TODO Auto-generated method stub
		ensureSession();
		session.setDebug(debug);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setDebugOut(java.io.PrintStream)
	 */
	@Override
	public void setDebugOut(PrintStream out) {
		// TODO Auto-generated method stub
		ensureSession();
		session.setDebugOut(out);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#createSimpleMailMessage()
	 */
	@Override
	public JavaMailMessage createPlaintextMailMessage() {
		// TODO Auto-generated method stub
		return new PlainTextJavaMailMessage(session);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#createHTMLMailMessage()
	 */
	@Override
	public AbstractJavaMultipartMailMessage createMultipartMailMessage() {
		// TODO Auto-generated method stub
		return new HTMLMultipartJavaMailMessage(session);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#send(za.co.sindi.email.MailMessage[])
	 */
	@Override
	public void send(JavaMailMessage... mailMessages) throws MailSendException {
		// TODO Auto-generated method stub
		if (mailMessages == null || mailMessages.length == 0) {
			throw new IllegalArgumentException("No mail messages were provided.");
		}
		
		List<Message> messages = new ArrayList<Message>();
		try {
			for (JavaMailMessage mailMessage : mailMessages) {
				messages.add(mailMessage.getMessage());
			}
		} catch (MailException e) {
			// TODO Auto-generated catch block
			throw new MailSendException(e);
		}
		
		send(messages.toArray(new Message[messages.size()]));
	}
	
	protected void ensureSession() {
		if (session == null) {
			throw new IllegalStateException("Java mail Session is null.");
		}
	}
}
