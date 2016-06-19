/**
 * 
 */
package za.co.sindi.email;

import java.io.PrintStream;

import javax.mail.Message;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportListener;

import za.co.sindi.email.exception.MailSendException;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public interface JavaMailSender extends MailSender<JavaMailMessage, JavaMultipartMailMessage> {

	public boolean isDebug();
	public void setDebug(boolean debug);
	public void setDebugOut(PrintStream out);
	public void addConnectionListener(ConnectionListener connectionListener);
	public void addTransportListener(TransportListener transportListener);
	public void removeConnectionListener(ConnectionListener connectionListener);
	public void removeTransportListener(TransportListener transportListener);
//	public void setAuthenticator(Authenticator authenticator);
	public void send(Message... messages) throws MailSendException;
}
