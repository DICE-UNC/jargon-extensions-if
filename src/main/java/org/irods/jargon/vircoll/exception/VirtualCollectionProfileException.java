/**
 * 
 */
package org.irods.jargon.vircoll.exception;


/**
 * Exception generating or maintaining a user profile of virtual collections
 * 
 * @author Mike Conway - DICE
 *
 */
public class VirtualCollectionProfileException extends
		VirtualCollectionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -810975433409675869L;

	/**
	 * @param message
	 */
	public VirtualCollectionProfileException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public VirtualCollectionProfileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public VirtualCollectionProfileException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public VirtualCollectionProfileException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public VirtualCollectionProfileException(Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public VirtualCollectionProfileException(String message,
			int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
