/**
 * 
 */
package za.co.sindi.email;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Transport;
import jakarta.mail.event.ConnectionListener;
import jakarta.mail.event.TransportListener;
import za.co.sindi.email.exception.MailSendException;

/**
 * @author Bienfait Sindi
 * @since 17 April 2014
 *
 */
public abstract class AbstractJavaTransportMailSender extends AbstractJavaMailSender implements JavaTransportMailSender {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private Transport transport;

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

	protected void initializeTransportIfNecessary() throws NoSuchProviderException {
		ensureSession();
		
		if (transport == null) {
			transport = session.getTransport();
		}
	}
}
