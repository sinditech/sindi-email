/**
 * 
 */
package za.co.sindi.email.exception;

/**
 * @author Bienfait Sindi
 * @since 16 April 2014
 *
 */
public class MailAddressException extends MailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 329678063805476239L;

	/**
	 * @param message
	 */
	public MailAddressException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MailAddressException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MailAddressException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MailAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
