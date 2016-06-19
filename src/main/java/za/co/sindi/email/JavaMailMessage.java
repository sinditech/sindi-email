/**
 * 
 */
package za.co.sindi.email;

import javax.mail.Address;
import javax.mail.Message;

import za.co.sindi.email.exception.MailAddressException;
import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 19 April 2014
 *
 */
public interface JavaMailMessage extends MailMessage {

	public void setFrom(Address address) throws MailAddressException;
	public Address getFrom() throws MailAddressException;
	public void addTo(Address address) throws MailAddressException;
	public Address[] getTo() throws MailAddressException;
	public void addCC(Address address) throws MailAddressException;
	public Address[] getCC() throws MailAddressException;
	public void addBCC(Address address) throws MailAddressException;
	public Address[] getBCC() throws MailAddressException;
	public void addReplyTo(Address address) throws MailAddressException;
	public Address[] getReplyTo() throws MailAddressException;
	public Message getMessage() throws MailException;
}
