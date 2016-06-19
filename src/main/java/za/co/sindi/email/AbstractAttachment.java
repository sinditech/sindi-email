/**
 * 
 */
package za.co.sindi.email;

import javax.activation.DataSource;

/**
 * @author Bienfait Sindi
 * @since 20 April 2014
 *
 */
public abstract class AbstractAttachment implements Attachment {

	private String disposition;
	private String fileName;
	private DataSource dataSource;
	
	/**
	 * @param disposition
	 * @param fileName
	 * @param dataSource
	 */
	protected AbstractAttachment(String disposition, String fileName, DataSource dataSource) {
		super();
		this.disposition = disposition;
		this.fileName = fileName;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.Attachment#getDisposition()
	 */
	@Override
	public String getDisposition() {
		// TODO Auto-generated method stub
		return disposition;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.Attachment#getFileName()
	 */
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return fileName;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.Attachment#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		return dataSource;
	}
}
