/**
 * 
 */
package za.co.sindi.email;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import za.co.sindi.email.exception.MailAddressException;
import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 14 April 2014
 *
 */
public interface MailMessage extends MessageHeader {

	public String getContentID() throws MailException;
	public String getMessageID() throws MailException;
	public void setImportance(Importance importance) throws MailException;
	public void setPriority(Priority priority) throws MailException;
	public void setFrom(String emailAddress) throws MailAddressException;
	public void setFrom(String emailAddress, String displayName) throws MailAddressException;
	public void setFrom(String emailAddress, String displayName, String charsetName) throws MailAddressException;
	public void addTo(String emailAddress) throws MailAddressException;
	public void addTo(String emailAddress, String displayName) throws MailAddressException;
	public void addTo(String emailAddress, String displayName, String charsetName) throws MailAddressException;
	public void addCC(String emailAddress) throws MailAddressException;
	public void addCC(String emailAddress, String displayName) throws MailAddressException;
	public void addCC(String emailAddress, String displayName, String charsetName) throws MailAddressException;
	public void addBCC(String emailAddress) throws MailAddressException;
	public void addBCC(String emailAddress, String displayName) throws MailAddressException;
	public void addBCC(String emailAddress, String displayName, String charsetName) throws MailAddressException;
	public void addReplyTo(String emailAddress) throws MailAddressException;
	public void addReplyTo(String emailAddress, String displayName) throws MailAddressException;
	public void addReplyTo(String emailAddress, String displayName, String charsetName) throws MailAddressException;
	public String getSubject() throws MailException;
	public void setSubject(String subject) throws MailException;
	public void setSubject(String subject, String charsetName) throws MailException;
	public void setText(String text) throws MailException;
	public void setText(String text, String charsetName) throws MailException;
	public Date getSentDate() throws MailException;
	public void setSentDate(Date sentDate) throws MailException;
	public void writeTo(OutputStream out) throws IOException, MailException;
}
