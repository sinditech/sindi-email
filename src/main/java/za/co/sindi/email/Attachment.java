/**
 * 
 */
package za.co.sindi.email;

import jakarta.activation.DataSource;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public interface Attachment {

	public String getDisposition();
	public String getFileName();
	public DataSource getDataSource();
}
