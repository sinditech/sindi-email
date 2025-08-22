/**
 * 
 */
package za.co.sindi.email;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 14 April 2014
 *
 */
public interface MailMessage extends MessageHeader {

	public String getContentID(); // throws MailException;
	public String getMessageID(); // throws MailException;
	public void setImportance(Importance importance); // throws MailException;
	public void setPriority(Priority priority); // throws MailException;
	default void setFrom(String emailAddress) /* throws MailAddressException */ {
		setFrom(emailAddress, null);
	}
	default void setFrom(String emailAddress, String displayName) /* throws MailAddressException */ {
		setFrom(emailAddress, displayName, null);
	}
	public void setFrom(String emailAddress, String displayName, String charsetName) /* throws MailAddressException */;
	default void addTo(String emailAddress) /* throws MailAddressException */ {
		addTo(emailAddress, null);
	}
	default void addTo(String emailAddress, String displayName) /* throws MailAddressException */ {
		addTo(emailAddress, displayName, null);
	}
	public void addTo(String emailAddress, String displayName, String charsetName) /* throws MailAddressException */;
	default void addCC(String emailAddress) /* throws MailAddressException */ {
		addCC(emailAddress, null);
	}
	default void addCC(String emailAddress, String displayName) /* throws MailAddressException */ {
		addCC(emailAddress, displayName, null);
	}
	public void addCC(String emailAddress, String displayName, String charsetName) /* throws MailAddressException */;
	default void addBCC(String emailAddress) /* throws MailAddressException */ {
		addBCC(emailAddress, null);
	}
	default void addBCC(String emailAddress, String displayName) /* throws MailAddressException */ {
		addBCC(emailAddress, displayName, null);
	}
	public void addBCC(String emailAddress, String displayName, String charsetName) /* throws MailAddressException */;
	default void addReplyTo(String emailAddress) /* throws MailAddressException */ {
		addReplyTo(emailAddress);
	}
	default void addReplyTo(String emailAddress, String displayName) /* throws MailAddressException */ {
		addReplyTo(emailAddress, displayName, null);
	}
	public void addReplyTo(String emailAddress, String displayName, String charsetName) /* throws MailAddressException */;
	public String getSubject(); // throws MailException;
	default void setSubject(String subject) throws MailException {
		setSubject(subject, null);
	}
	public void setSubject(String subject, String charsetName); // throws MailException;
	default void setText(String text) /* throws MailException */ {
		setText(text, null);
	}
	public void setText(String text, String charsetName); // throws MailException;
	public Date getSentDate(); // throws MailException;
	public void setSentDate(Date sentDate); // throws MailException;
	public void writeTo(OutputStream out) throws IOException /*, MailException */;
}
