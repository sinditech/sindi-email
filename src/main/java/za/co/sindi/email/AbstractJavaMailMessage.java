/**
 * 
 */
package za.co.sindi.email;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jakarta.mail.Address;
import jakarta.mail.Header;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import za.co.sindi.commons.utils.Strings;
import za.co.sindi.email.exception.MailAddressException;
import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 16 April 2014
 *
 */
public abstract class AbstractJavaMailMessage extends AbstractMailMessage implements JavaMailMessage {

	private MimeMessage message;
	
	/**
	 * @param session
	 */
	protected AbstractJavaMailMessage(Session session) {
		super();
		if (session == null) {
			throw new IllegalArgumentException("JavaMail Session may not be null.");
		}
		
		message = new MimeMessage(session);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.AbstractMailMessage#getContentID()
	 */
	@Override
	public String getContentID() throws MailException {
		// TODO Auto-generated method stub
		try {
			return message.getContentID();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.AbstractMailMessage#getMessageID()
	 */
	@Override
	public String getMessageID() throws MailException {
		// TODO Auto-generated method stub
		try {
			return message.getMessageID();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#getFrom()
	 */
	@Override
	public Address getFrom() throws MailAddressException {
		// TODO Auto-generated method stub
		Address[] from = null;
		
		try {
			from = message.getFrom();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
		
		if (from == null) {
			return null;
		}

		return from[0];
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setFrom(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setFrom(String emailAddress, String displayName, String charsetName) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			setFrom(createInternetAddress(emailAddress, displayName, charsetName));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#setFrom(javax.mail.Address)
	 */
	@Override
	public void setFrom(Address address) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			if (address != null) {
				message.setFrom(address);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#getTo()
	 */
	@Override
	public Address[] getTo() throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			return message.getRecipients(RecipientType.TO);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#addTo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addTo(String emailAddress, String displayName, String charsetName) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			addTo(createInternetAddress(emailAddress, displayName, charsetName));
		} catch (UnsupportedEncodingException | AddressException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#addTo(javax.mail.Address)
	 */
	@Override
	public void addTo(Address address) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			if (address != null) {
				message.setRecipient(RecipientType.TO, address);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#getCC()
	 */
	@Override
	public Address[] getCC() throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			return message.getRecipients(RecipientType.CC);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#addCC(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addCC(String emailAddress, String displayName, String charsetName) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			addCC(createInternetAddress(emailAddress, displayName, charsetName));
		} catch (UnsupportedEncodingException | AddressException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#addCC(javax.mail.Address)
	 */
	@Override
	public void addCC(Address address) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			if (address != null) {
				message.addRecipient(RecipientType.CC, address);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#getBCC()
	 */
	@Override
	public Address[] getBCC() throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			return message.getRecipients(RecipientType.BCC);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#addBCC(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addBCC(String emailAddress, String displayName, String charsetName) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			addBCC(createInternetAddress(emailAddress, displayName, charsetName));
		} catch (UnsupportedEncodingException | AddressException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#addBCC(javax.mail.Address)
	 */
	@Override
	public void addBCC(Address address) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			if (address != null) {
				message.addRecipient(RecipientType.BCC, address);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#getReplyTo()
	 */
	@Override
	public Address[] getReplyTo() throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			return message.getReplyTo();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#addReplyTo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addReplyTo(String emailAddress, String displayName,	String charsetName) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			addReplyTo(createInternetAddress(emailAddress, displayName, charsetName));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#addReplyTo(javax.mail.Address)
	 */
	@Override
	public void addReplyTo(Address address) throws MailAddressException {
		// TODO Auto-generated method stub
		try {
			if (address != null) {
				Address[] addresses = message.getReplyTo();
				List<Address> addressList = addresses != null ? Arrays.asList(addresses) : new ArrayList<Address>();
				addressList.add(address);
				message.setReplyTo(addressList.toArray(new Address[addressList.size()]));
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailAddressException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#getSubject()
	 */
	@Override
	public String getSubject() throws MailException {
		// TODO Auto-generated method stub
		try {
			return message.getSubject();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setSubject(java.lang.String, java.lang.String)
	 */
	@Override
	public void setSubject(String subject, String charsetName) throws MailException {
		// TODO Auto-generated method stub
		try {
			if (Strings.isNullOrEmpty(charsetName)) {
				message.setSubject(subject);
			} else /*if (message instanceof MimeMessage)*/ {
//				((MimeMessage)message).setSubject(subject, charsetName);
				message.setSubject(subject, charsetName);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setSendDate(java.util.Date)
	 */
	@Override
	public void setSentDate(Date sendDate) throws MailException {
		// TODO Auto-generated method stub
		try {
			message.setSentDate(sendDate);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#getSendDate()
	 */
	@Override
	public Date getSentDate() throws MailException {
		// TODO Auto-generated method stub
		try {
			return message.getSentDate();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageHeader#addHeader(java.lang.String, java.lang.String)
	 */
	@Override
	public void addHeader(String name, String value) throws MailException {
		// TODO Auto-generated method stub
		try {
			message.addHeader(name, value);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageHeader#containsHeader(java.lang.String)
	 */
	@Override
	public boolean containsHeader(String name) throws MailException {
		// TODO Auto-generated method stub
		try {
			return message.getHeader(name) != null;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageHeader#getHeader(java.lang.String)
	 */
	@Override
	public String[] getHeader(String name) throws MailException {
		// TODO Auto-generated method stub
		try {
			return message.getHeader(name);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageHeader#removeHeader(java.lang.String)
	 */
	@Override
	public void removeHeader(String name) throws MailException {
		// TODO Auto-generated method stub
		try {
			message.removeHeader(name);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageHeader#getHeaderNames()
	 */
	@Override
	public String[] getHeaderNames() throws MailException {
		// TODO Auto-generated method stub
		Set<String> headerSet = new TreeSet<String>();
		
		try {
			Enumeration<Header> enumerations = message.getAllHeaders();
			while (enumerations.hasMoreElements()) {
				Header header = enumerations.nextElement();
				headerSet.add(header.getName());
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
		
		return headerSet.toArray(new String[headerSet.size()]);
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#getMessage()
	 */
	@Override
	public Message getMessage() throws MailException {
		// TODO Auto-generated method stub
		return message;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailMessage#writeTo(java.io.OutputStream)
	 */
	@Override
	public void writeTo(OutputStream out) throws IOException, MailException {
		// TODO Auto-generated method stub
		try {
			message.writeTo(out);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}

	protected InternetAddress createInternetAddress(String emailAddress, String displayName, String charset) throws AddressException, UnsupportedEncodingException {
		InternetAddress address = new InternetAddress(emailAddress, true);
		if (Strings.isNullOrEmpty(charset)) {
			address.setPersonal(displayName);
		} else {
			address.setPersonal(displayName, charset);
		}
		
		//For safety sake, we validate...
		address.validate();
		
		return address;
	}
}
