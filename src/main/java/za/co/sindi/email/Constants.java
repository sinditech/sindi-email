/**
 * 
 */
package za.co.sindi.email;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public final class Constants {

	private Constants() {
		
	}
	
	public static final String HEADER_IMPORTANCE = "Importance";
	public static final String HEADER_X_PRIORITY = "X-Priority";
	public static final String HEADER_X_MSMAIL_PRIORITY = "X-MSMail-Priority";
	
	public static final int SMTP_DEFAULT_PORT = 25;
	public static final int SMTPS_DEFAULT_PORT = 465;
}
