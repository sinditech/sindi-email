/**
 * 
 */
package za.co.sindi.email;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public enum Priority {
	HIGH(1, "High"),
	NORMAL(3, "Normal"),
	LOW(5, "Low"),
	;
	private final int priority;
	private final String msMailPriority;
	
	/**
	 * @param priority
	 * @param msMailPriority
	 */
	private Priority(int priority, String msMailPriority) {
		this.priority = priority;
		this.msMailPriority = msMailPriority;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @return the msMailPriority
	 */
	public String getMsMailPriority() {
		return msMailPriority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.HEADER_X_PRIORITY).append(": ").append(priority).append("\n");
		sb.append(Constants.HEADER_X_MSMAIL_PRIORITY).append(": ").append(msMailPriority).append("\n");
		return sb.toString();
	}
}
