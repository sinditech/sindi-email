/**
 * 
 */
package za.co.sindi.email;

import java.io.PrintStream;

import jakarta.mail.Message;
import za.co.sindi.email.exception.MailSendException;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public interface JavaMailSender extends MailSender<JavaMailMessage, AbstractJavaMultipartMailMessage> {

	public boolean isDebug();
	public void setDebug(boolean debug);
	public void setDebugOut(PrintStream out);
	public void send(Message... messages) throws MailSendException;
}
