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
public class VirtualCollectionProfileException extends VirtualCollectionException {

	/**
	 *
	 */
	private static final long serialVersionUID = -810975433409675869L;

	/**
	 * @param message
	 */
	public VirtualCollectionProfileException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public VirtualCollectionProfileException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public VirtualCollectionProfileException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public VirtualCollectionProfileException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public VirtualCollectionProfileException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public VirtualCollectionProfileException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
