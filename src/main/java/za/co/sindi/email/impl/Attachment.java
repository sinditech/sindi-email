/**
 * 
 */
package za.co.sindi.email.impl;

import jakarta.activation.DataSource;
import za.co.sindi.email.AbstractAttachment;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public class Attachment extends AbstractAttachment {

	private String description;

	/**
	 * @param fileName
	 * @param dataSource
	 * @param description
	 */
	public Attachment(String fileName, String description, DataSource dataSource) {
		super("attachment", fileName, dataSource);
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
