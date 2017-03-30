/**
 *
 */
package org.irods.jargon.userprofile;

import org.irods.jargon.core.exception.JargonException;

/**
 * General error with user profile processing, may be subclassed for details
 * exceptions
 *
 * @author Mike Conway - DICE
 *
 */
public class UserProfileException extends JargonException {

	/**
	 *
	 */
	private static final long serialVersionUID = 943329540051890849L;

	/**
	 * @param message
	 */
	public UserProfileException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserProfileException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public UserProfileException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public UserProfileException(final String message, final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public UserProfileException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public UserProfileException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
