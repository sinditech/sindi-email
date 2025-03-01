/**
 * 
 */
package za.co.sindi.email;

import java.util.logging.Logger;

import za.co.sindi.email.exception.MailException;


/**
 * @author Bienfait Sindi
 * @since 14 April 2014
 *
 */
public abstract class AbstractMailMessage implements MailMessage {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	
	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#getContentID()
	 */
	@Override
	public String getContentID() throws MailException {
		// TODO Auto-generated method stub
		String[] values = getHeader("Content-ID");
		if (values != null && values.length == 1) {
			return values[0];
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#getMessageID()
	 */
	@Override
	public String getMessageID() throws MailException {
		// TODO Auto-generated method stub
		String[] values = getHeader("Message-ID");
		if (values != null && values.length == 1) {
			return values[0];
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setImportance(za.co.sindi.email.Importance)
	 */
	@Override
	public void setImportance(Importance importance) throws MailException {
		// TODO Auto-generated method stub
		if (importance != null) {
			removeHeader(Constants.HEADER_IMPORTANCE);
			addHeader(Constants.HEADER_IMPORTANCE, importance.toString());
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailMessage#setPriority(za.co.sindi.email.Priority)
	 */
	@Override
	public void setPriority(Priority priority) throws MailException {
		// TODO Auto-generated method stub
		if (priority != null) {
			removeHeader(Constants.HEADER_X_PRIORITY);
			removeHeader(Constants.HEADER_X_MSMAIL_PRIORITY);
			addHeader(Constants.HEADER_X_PRIORITY, String.valueOf(priority.getPriority()));
			addHeader(Constants.HEADER_X_MSMAIL_PRIORITY, priority.getMsMailPriority());
		}
	}
}
