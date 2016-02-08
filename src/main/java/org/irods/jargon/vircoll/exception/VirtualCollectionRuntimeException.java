/**
 * 
 */
package org.irods.jargon.vircoll.exception;

import org.irods.jargon.core.exception.JargonRuntimeException;

/**
 * General unchecked exception in virtual collection processing
 * 
 * @author Mike Conway - DICE
 *
 */
public class VirtualCollectionRuntimeException extends JargonRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7612065951539653454L;

	/**
	 * 
	 */
	public VirtualCollectionRuntimeException() {
	}

	/**
	 * @param message
	 */
	public VirtualCollectionRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public VirtualCollectionRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public VirtualCollectionRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
