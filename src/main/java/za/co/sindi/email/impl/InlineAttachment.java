/**
 * 
 */
package za.co.sindi.email.impl;

import javax.activation.DataSource;

import za.co.sindi.email.AbstractAttachment;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public class InlineAttachment extends AbstractAttachment {

	private String contentID;
	
	/**
	 * @param disposition
	 * @param fileName
	 * @param dataSource
	 */
	public InlineAttachment(String contentID, String fileName, DataSource dataSource) {
		super("inline", fileName, dataSource);
		// TODO Auto-generated constructor stub
		this.contentID = contentID;
	}

	/**
	 * @return the contentID
	 */
	public String getContentID() {
		return contentID;
	}
}
