/**
 * 
 */
package za.co.sindi.email;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.event.ConnectionListener;
import jakarta.mail.event.TransportListener;
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
	private Transport transport;

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#isDebug()
	 */
	@Override
	public boolean isDebug() {
		// TODO Auto-generated method stub
		ensureSession();
		return getSession().getDebug();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setDebug(boolean)
	 */
	@Override
	public void setDebug(boolean debug) {
		// TODO Auto-generated method stub
		ensureSession();
		getSession().setDebug(debug);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setDebugOut(java.io.PrintStream)
	 */
	@Override
	public void setDebugOut(PrintStream out) {
		// TODO Auto-generated method stub
		ensureSession();
		getSession().setDebugOut(out);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#addConnectionListener(javax.mail.event.ConnectionListener)
	 */
	@Override
	public void addConnectionListener(ConnectionListener connectionListener) {
		// TODO Auto-generated method stub
		if (connectionListener != null) {
			transport.addConnectionListener(connectionListener);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#addTransportListener(javax.mail.event.TransportListener)
	 */
	@Override
	public void addTransportListener(TransportListener transportListener) {
		// TODO Auto-generated method stub
		if (transportListener != null) {
			transport.addTransportListener(transportListener);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#removeConnectionListener(javax.mail.event.ConnectionListener)
	 */
	@Override
	public void removeConnectionListener(ConnectionListener connectionListener) {
		// TODO Auto-generated method stub
		if (connectionListener != null) {
			transport.removeConnectionListener(connectionListener);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#removeTransportListener(javax.mail.event.TransportListener)
	 */
	@Override
	public void removeTransportListener(TransportListener transportListener) {
		// TODO Auto-generated method stub
		if (transportListener != null) {
			transport.removeTransportListener(transportListener);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#createSimpleMailMessage()
	 */
	@Override
	public JavaMailMessage createPlaintextMailMessage() {
		// TODO Auto-generated method stub
		return new PlainTextJavaMailMessage(getSession());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#createHTMLMailMessage()
	 */
	@Override
	public AbstractJavaMultipartMailMessage createMultipartMailMessage() {
		// TODO Auto-generated method stub
		return new HTMLMultipartJavaMailMessage(getSession());
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
	
	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#send(javax.mail.Message[])
	 */
	@Override
	public void send(Message... messages) throws MailSendException {
		// TODO Auto-generated method stub
		if (messages == null || messages.length == 0) {
			throw new IllegalArgumentException("No mail messages were provided.");
		}
		
		try {
			initializeTransportIfNecessary();
			if (!transport.isConnected()) {
				//Connect
				transport.connect();
			}
			
			for (Message message : messages) {
				//Just a fix, in case someone forgets to set the date....				
				if (message.getSentDate() == null) {
					message.setSentDate(new Date());
				}
				
				//Save first
				message.saveChanges();
			
				//Send message...
				transport.sendMessage(message, message.getAllRecipients());
			}
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			throw new MailSendException(e);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailSendException(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		try {
			if (transport != null && transport.isConnected()) {
				transport.close();
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.WARNING, "Error closing JavaMail Transport.", e);
			throw e;
		} finally {
			transport = null;
		}
	}

	private void ensureSession() {
		if (getSession() == null) {
			throw new IllegalStateException("Java mail Session is null.");
		}
	}
	
	protected void initializeTransportIfNecessary() throws NoSuchProviderException {
		ensureSession();
		
		if (transport == null) {
			transport = getSession().getTransport();
		}
	}
	
	protected abstract Session getSession();
}
