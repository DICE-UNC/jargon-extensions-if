/**
 *
 */
package org.irods.jargon.dataprofile.accessor.exception;

/**
 * Error caused by use of DATA_ accessors on a Collection or COLL_ accessors on
 * a DataObject
 *
 * @author rskarbez
 *
 */
public class WrongDataProfileTypeException extends DataProfileAccessorException {

	/**
	 *
	 */
	private static final long serialVersionUID = 9110290645200720447L;

	/**
	 * @param message
	 */
	public WrongDataProfileTypeException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WrongDataProfileTypeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public WrongDataProfileTypeException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public WrongDataProfileTypeException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public WrongDataProfileTypeException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public WrongDataProfileTypeException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
