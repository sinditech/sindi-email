/**
 * 
 */
package za.co.sindi.email;

import java.io.File;
import java.net.URL;

import za.co.sindi.email.exception.MailException;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public abstract class AbstractMultipartMailMessage extends AbstractMailMessage implements MultipartMailMessage {

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
}
