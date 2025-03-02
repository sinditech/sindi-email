/**
 * 
 */
package za.co.sindi.email;

import jakarta.mail.event.ConnectionListener;
import jakarta.mail.event.TransportListener;

/**
 * @author Bienfait Sindi
 * @since 18 April 2014
 *
 */
public interface JavaTransportMailSender extends JavaMailSender {

	public void addConnectionListener(ConnectionListener connectionListener);
	public void addTransportListener(TransportListener transportListener);
	public void removeConnectionListener(ConnectionListener connectionListener);
	public void removeTransportListener(TransportListener transportListener);
}
