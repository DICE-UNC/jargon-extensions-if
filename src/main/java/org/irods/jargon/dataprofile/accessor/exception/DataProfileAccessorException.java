/**
 *
 */
package org.irods.jargon.dataprofile.accessor.exception;

import org.irods.jargon.core.exception.JargonException;

/**
 * @author Mike Conway - DICE
 *
 */
public class DataProfileAccessorException extends JargonException {

	/**
	 *
	 */
	private static final long serialVersionUID = 7250681334337382714L;

	/**
	 * @param message
	 */
	public DataProfileAccessorException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataProfileAccessorException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public DataProfileAccessorException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public DataProfileAccessorException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public DataProfileAccessorException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public DataProfileAccessorException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
