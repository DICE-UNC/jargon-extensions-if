/**
 * 
 */
package org.irods.jargon.dataprofile.accessor.exception;

/**
 * Error caused by use of an invalid/unsupported accessor
 * 
 * @author Mike Conway - DICE
 *
 */
public class ObjectNotFoundException extends DataProfileAccessorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1037212511437679169L;

	/**
	 * @param message
	 */
	public ObjectNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public ObjectNotFoundException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public ObjectNotFoundException(Throwable cause, int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public ObjectNotFoundException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
