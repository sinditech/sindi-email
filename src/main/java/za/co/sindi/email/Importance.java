/**
 * 
 */
package za.co.sindi.email;

/**
 * <a href="https://tools.ietf.org/html/rfc4021#page-32">RFC4021 Importance header.</a>
 * 
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public enum Importance {
	HIGH("High"),
	NORMAL("Normal"),
	LOW("Low")
	;
	private final String text;

	/**
	 * @param text
	 */
	private Importance(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
}
