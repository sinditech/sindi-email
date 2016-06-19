/**
 * 
 */
package za.co.sindi.email;

import za.co.sindi.email.exception.MailException;


/**
 * @author Bienfait Sindi
 * @since 16 July 2012
 *
 */
public interface MessageHeader {

	public void addHeader(String name, String value) throws MailException;
	public boolean containsHeader(String name) throws MailException;
	public String[] getHeader(String name) throws MailException;
	public void removeHeader(String name) throws MailException;
	public String[] getHeaderNames() throws MailException;
}
