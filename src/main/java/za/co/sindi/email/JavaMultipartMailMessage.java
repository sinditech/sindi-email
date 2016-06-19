/**
 * 
 */
package za.co.sindi.email;

import java.io.File;
import java.net.URL;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;

import za.co.sindi.common.utils.Strings;
import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 19 April 2014
 *
 */
public abstract class JavaMultipartMailMessage extends AbstractJavaMailMessage implements MultipartMailMessage {

	private boolean multipartMessageCreated;
	
	/**
	 * @param session
	 */
	protected JavaMultipartMailMessage(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#attach(java.io.File)
	 */
	@Override
	public void attach(File file) throws MailException {
		// TODO Auto-generated method stub
		attach(file, null);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#attach(java.net.URL, java.lang.String)
	 */
	@Override
	public void attach(URL url, String name) throws MailException {
		// TODO Auto-generated method stub
		attach(url, name, null);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#embed(java.lang.String, java.io.File)
	 */
	@Override
	public void embed(String contentID, File file) throws MailException {
		// TODO Auto-generated method stub
		embed(contentID, file, null);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#embed(java.lang.String, java.net.URL, java.lang.String)
	 */
	@Override
	public void embed(String contentID, URL url, String name) throws MailException {
		// TODO Auto-generated method stub
		embed(contentID, url, name, null);
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setText(java.lang.String, java.lang.String)
	 */
	@Override
	public void setText(String text, String charsetName) throws MailException {
		// TODO Auto-generated method stub
		setContent(text, "plain", charsetName);
	}

	protected void setText(Multipart multipart, String text, String charset, String subType) throws MessagingException {
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setText(text, charset, subType);
		multipart.addBodyPart(bodyPart);
	}
	
	protected void setText(Multipart multipart, int index, String text, String charset, String subType) throws MessagingException {
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setText(text, charset, subType);
		multipart.addBodyPart(bodyPart, index);
	}
	
	/**
	 * This sets file as attachment.
	 * 
	 * @param multipart
	 * @param dataSource
	 * @param name
	 * @param description
	 * @throws MessagingException 
	 */
	protected void attach(Multipart multipart, DataSource dataSource, String name, String description) throws MessagingException {
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setDataHandler(new DataHandler(dataSource));
		bodyPart.setDisposition(Part.ATTACHMENT);
		bodyPart.setFileName(Strings.isNullOrEmpty(name) ? dataSource.getName() : name);
		bodyPart.setDescription(description);
		
		//Add to multipart
		multipart.addBodyPart(bodyPart);
	}
	
	/**
	 * This sets file as inline attachment.
	 * 
	 * @param multipart
	 * @param dataSource
	 * @param contentID
	 * @param name
	 * @throws MessagingException 
	 */
	protected void embed(Multipart multipart, DataSource dataSource, String contentID, String name) throws MessagingException {
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setDataHandler(new DataHandler(dataSource));
		bodyPart.setDisposition(Part.INLINE);
		bodyPart.setFileName(Strings.isNullOrEmpty(name) ? dataSource.getName() : name);
		bodyPart.setContentID("<" + contentID + ">");
		
		//Add to multipart
		multipart.addBodyPart(bodyPart);
	}
	
	public abstract void createMultipartMessage(Message message) throws MailException;

	/* (non-Javadoc)
	 * @see za.co.sindi.email.AbstractJavaMailMessage#getMessage()
	 */
	@Override
	public Message getMessage() throws MailException {
		// TODO Auto-generated method stub
		Message message = super.getMessage();
		if (!multipartMessageCreated) {
			createMultipartMessage(message);
			multipartMessageCreated = true;
		}
		
		return message;
	}
}
