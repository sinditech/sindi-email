/**
 * 
 */
package za.co.sindi.email;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public interface MessageContent<T> {

	public T getContent();
	public String getType();
	public String getSubType();
	public String getContentType();
	public String getCharset();
}
