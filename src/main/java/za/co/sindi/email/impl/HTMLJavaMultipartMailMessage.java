/**
 * 
 */
package za.co.sindi.email.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import za.co.sindi.common.utils.Strings;
import za.co.sindi.email.JavaMultipartMailMessage;
import za.co.sindi.email.MessageContent;
import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public class HTMLJavaMultipartMailMessage extends JavaMultipartMailMessage {

	private static final String HTML_START_MESSAGE = "<html>" + 
													 "	<body>" +
													 "		<pre>";
	private static final String HTML_END_MESSAGE = 	 "		</pre>" +
													 "	</body>" +
													 "</html>";
	private MessageContent<String> textContent;
	private MessageContent<String> htmlContent;
	private List<Attachment> attachments;
	private List<InlineAttachment> inlineAttachments;
	
	/**
	 * @param session
	 */
	public HTMLJavaMultipartMailMessage(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
		attachments = new ArrayList<Attachment>();
		inlineAttachments = new ArrayList<InlineAttachment>();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#attach(java.io.File, java.lang.String)
	 */
	@Override
	public void attach(File file, String description) throws MailException {
		// TODO Auto-generated method stub
		if (file != null) {
			attachments.add(new Attachment(file.getName(), description, new FileDataSource(file)));
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#attach(java.net.URL, java.lang.String, java.lang.String)
	 */
	@Override
	public void attach(URL url, String name, String description) throws MailException {
		// TODO Auto-generated method stub
		if (url != null) {
			attachments.add(new Attachment(name, description, new URLDataSource(url)));
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#embed(java.lang.String, java.io.File, java.lang.String)
	 */
	@Override
	public void embed(String contentID, File file, String description) throws MailException {
		// TODO Auto-generated method stub
		if (!Strings.isNullOrEmpty(contentID) && file != null) {
			inlineAttachments.add(new InlineAttachment(contentID, file.getName(), new FileDataSource(file)));
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#embed(java.lang.String, java.net.URL, java.lang.String, java.lang.String)
	 */
	@Override
	public void embed(String contentID, URL url, String name, String description) throws MailException {
		// TODO Auto-generated method stub
		if (!Strings.isNullOrEmpty(contentID) && url != null) {
			inlineAttachments.add(new InlineAttachment(contentID, name, new URLDataSource(url)));
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MultipartMailMessage#setContent(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setContent(String content, String subType, String charsetName) {
		// TODO Auto-generated method stub
		if ("plain".equals(subType)) {
			textContent = new StringMessageContent(content, subType, charsetName);
		} else if ("html".equals(subType)) {
			htmlContent = new StringMessageContent(content, subType, charsetName);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.AbstractJavaMultipartMailMessage#createMultipartMessage(javax.mail.Message)
	 */
	@Override
	public void createMultipartMessage(Message message) throws MailException {
		// TODO Auto-generated method stub
		if (textContent == null && htmlContent == null) {
			throw new IllegalStateException("No text/plain or text/html mime message content provided.");
		}
		
		try {
			if (textContent != null && htmlContent == null) {
				setContent(HTML_START_MESSAGE + textContent.getContent() + HTML_END_MESSAGE, "html", textContent.getCharset());
			}
			
			String subType = null;
			if (attachments != null && !attachments.isEmpty()) {
				subType = "mixed";
			} else if (textContent != null) {
				subType = "alternative";
			} else {
				subType = "related";
			}
			
			MimeMultipart rootContent = new MimeMultipart(subType);
			if ("alternative".equals(subType)) {
				createAlternativeMimeBodyPart(rootContent);
			} else if ("mixed".equals(subType)) {
				createMixedMimeBodyPart(rootContent);
			} else if ("related".equals(subType)) {
				createRelatedMimeBodyPart(rootContent);
			}
			
			message.setContent(rootContent);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailException(e);
		}
	}
	
	private void createMixedMimeBodyPart(MimeMultipart rootContent) throws MessagingException {
		MimeMultipart bodyContent = null;
		
		if (textContent == null) {
			bodyContent = new MimeMultipart("alternative");
			createAlternativeMimeBodyPart(bodyContent);
		} else {
			bodyContent = new MimeMultipart("related");
			createRelatedMimeBodyPart(bodyContent);
		}
		
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(bodyContent);
		rootContent.addBodyPart(bodyPart);
		
		//Add attachment
		for (Attachment attachment : attachments) {
			attach(rootContent, attachment.getDataSource(), attachment.getFileName(), attachment.getDescription());
		}
	}
	
	private void createAlternativeMimeBodyPart(MimeMultipart rootContent) throws MessagingException {
		setText(rootContent, textContent.getContent(), textContent.getCharset(), textContent.getSubType());
		if (inlineAttachments == null || inlineAttachments.isEmpty()) {
			setText(rootContent, htmlContent.getContent(), htmlContent.getCharset(), htmlContent.getSubType());
		} else {
			MimeMultipart relatedContent = new MimeMultipart("related");
			createRelatedMimeBodyPart(relatedContent);
			BodyPart relatedBodyPart = new MimeBodyPart();
			relatedBodyPart.setContent(relatedContent);
			rootContent.addBodyPart(relatedBodyPart);
		}
	}
	
	private void createRelatedMimeBodyPart(MimeMultipart related) throws MessagingException {
		setText(related, htmlContent.getContent(), htmlContent.getCharset(), htmlContent.getSubType());
		
		for (InlineAttachment inline : inlineAttachments) {
			embed(related, inline.getDataSource(), inline.getContentID(), inline.getFileName());
		}
	}
}
