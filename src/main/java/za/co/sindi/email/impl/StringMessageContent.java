/**
 * 
 */
package za.co.sindi.email.impl;

import za.co.sindi.email.AbstractMessageContent;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public class StringMessageContent extends AbstractMessageContent<String> {

	/**
	 * @param content
	 * @param subType
	 */
	public StringMessageContent(String content, String subType) {
		this(content, subType, "iso-8859-1");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param content
	 * @param subType
	 * @param charset
	 */
	public StringMessageContent(String content, String subType, String charset) {
		super(content, "text", subType, charset);
		// TODO Auto-generated constructor stub
	}
}
