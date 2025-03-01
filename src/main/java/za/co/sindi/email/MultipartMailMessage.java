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
public interface MultipartMailMessage extends MailMessage {

	default void attach(File file) throws MailException {
		attach(file, null);
	}
	public void attach(File file, String description) throws MailException;
	default void attach(URL url, String name) throws MailException {
		attach(url, name, null);
	}
	public void attach(URL url, String name, String description) throws MailException;
	default void embed(String contentID, File file) throws MailException {
		embed(contentID, file, null);
	}
	public void embed(String contentID, File file, String description) throws MailException;
	default void embed(String contentID, URL url, String name) throws MailException {
		embed(contentID, url, name, null);
	}
	public void embed(String contentID, URL url, String name, String description) throws MailException;
	public void setContent(String content, String subType, String charsetName);
}
