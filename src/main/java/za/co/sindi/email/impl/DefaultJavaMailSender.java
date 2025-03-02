/**
 * 
 */
package za.co.sindi.email.impl;

import static za.co.sindi.email.Constants.SMTPS_DEFAULT_PORT;
import static za.co.sindi.email.Constants.SMTP_DEFAULT_PORT;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Session;
import za.co.sindi.email.AbstractJavaTransportMailSender;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public class DefaultJavaMailSender extends AbstractJavaTransportMailSender {

	private Authenticator authenticator;
	private String protocol = "smtp";
	private String host;
	private int port = SMTP_DEFAULT_PORT;
	private String userName;
	private String password;
	private int connectionTimeout;
	private int readTimeout;
	private boolean useSSL;
	private boolean startTTLSEnable;
	private boolean startTTLSRequired;
	private boolean sendPartial;
	private boolean sslCheckServerIdentity;
	
	/**
	 * @param host
	 * @param userName
	 * @param password
	 */
	public DefaultJavaMailSender(String host, String userName, String password) {
		this(host, SMTP_DEFAULT_PORT, userName, password);
	}
	
	/**
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 */
	public DefaultJavaMailSender(String host, int port, String userName, String password) {
		super();
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setConnectionTimeout(int)
	 */
//	@Override
	public void setConnectionTimeout(int timeout) {
		// TODO Auto-generated method stub
		connectionTimeout = timeout;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setReadTimeout(int)
	 */
//	@Override
	public void setReadTimeout(int timeout) {
		// TODO Auto-generated method stub
		readTimeout = timeout;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#setStartTTLSEnable(boolean)
	 */
//	@Override
	public void setStartTTLSEnable(boolean startTTLSEnable) {
		// TODO Auto-generated method stub
		this.startTTLSEnable = startTTLSEnable;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setStartTTLSRequired(boolean)
	 */
//	@Override
	public void setStartTTLSRequired(boolean startTTLSRequired) {
		// TODO Auto-generated method stub
		this.startTTLSRequired = startTTLSRequired;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#setAuthenticator(javax.mail.Authenticator)
	 */
//	@Override
	public void setAuthenticator(Authenticator authenticator) {
		// TODO Auto-generated method stub
		this.authenticator = authenticator;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.JavaMailSender#setUseSSL(boolean)
	 */
//	@Override
	public void setUseSSL(boolean useSSL) {
		// TODO Auto-generated method stub
		this.useSSL = useSSL;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setSendPartial(boolean)
	 */
//	@Override
	public void setSendPartial(boolean sendPartial) {
		// TODO Auto-generated method stub
		this.sendPartial = sendPartial;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.MailSender#setSSLCheckServerIdentity(boolean)
	 */
//	@Override
	public void setSSLCheckServerIdentity(boolean sslCheckServerIdentity) {
		// TODO Auto-generated method stub
		this.sslCheckServerIdentity = sslCheckServerIdentity;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.email.AbstractJavaMailSender#ensureSession()
	 */
	
	@Override
	protected void ensureSession() {
		// TODO Auto-generated method stub
		if (session == null){
			Properties properties = new Properties();
			
			if (useSSL) {
				protocol = "smtps";
				port = SMTPS_DEFAULT_PORT;
//				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail." + protocol + ".socketFactory.port", Integer.toString(port));
				properties.put("mail." + protocol + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				properties.put("mail." + protocol + ".socketFactory.fallback", "false");
			}
			
			if (host != null && !host.isEmpty()) {
				properties.put("mail." + protocol + ".host", host);
			}
			
			if (userName != null && !userName.isEmpty()) {
				properties.put("mail." + protocol + ".user", userName);
			}
			
			properties.put("mail." + protocol + ".port", Integer.toString(port));
			properties.put("mail." + protocol + ".starttls.enable", String.valueOf(startTTLSEnable));
			properties.put("mail." + protocol + ".starttls.required", String.valueOf(startTTLSRequired));
			properties.put("mail." + protocol + ".sendpartial", String.valueOf(sendPartial));
			
			if ((useSSL || startTTLSEnable) /* && sslCheckServerIdentity*/) {
				properties.put("mail." + protocol + ".ssl.checkserveridentity", String.valueOf(sslCheckServerIdentity));
			}
			
			if (connectionTimeout > 0) {
				properties.put("mail." + protocol + ".connectiontimeout", Integer.toString(connectionTimeout));
			}
			
			if (readTimeout > 0) {
				properties.put("mail." + protocol + ".timeout", Integer.toString(readTimeout));
			}
			
			if (authenticator == null && userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
				authenticator = new DefaultAuthenticator(userName, password);
			}
			
			if (authenticator != null) {
				properties.put("mail." + protocol + ".auth", "true");
				session = Session.getDefaultInstance(properties, authenticator);
			} else {
				session = Session.getDefaultInstance(properties);
			}
		}
	}
}
