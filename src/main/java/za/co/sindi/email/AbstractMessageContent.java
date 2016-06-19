/**
 * 
 */
package za.co.sindi.email;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public abstract class AbstractMessageContent<T> implements MessageContent<T> {

	private T content;
	private String type;
	private String subType;
	private String charset;
	
	/**
	 * @param content
	 * @param type
	 * @param subType
	 * @param charset
	 */
	protected AbstractMessageContent(T content, String type, String subType, String charset) {
		super();
		this.content = content;
		this.type = type;
		this.subType = subType;
		this.charset = charset;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageContent#getContent()
	 */
	@Override
	public T getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageContent#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageContent#getSubType()
	 */
	@Override
	public String getSubType() {
		// TODO Auto-generated method stub
		return subType;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageContent#getContentType()
	 */
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return getType() +"/" + getSubType();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MessageContent#getCharset()
	 */
	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return charset;
	}
}
