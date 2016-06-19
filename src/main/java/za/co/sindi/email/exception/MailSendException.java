/**
 * 
 */
package za.co.sindi.email.exception;

/**
 * @author Bienfait Sindi
 * @since 16 April 2014
 *
 */
public class MailSendException extends MailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3359753058568866217L;

	/**
	 * @param message
	 */
	public MailSendException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MailSendException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MailSendException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MailSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
