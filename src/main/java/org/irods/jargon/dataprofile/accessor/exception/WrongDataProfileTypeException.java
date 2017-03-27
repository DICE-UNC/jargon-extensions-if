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
	public WrongDataProfileTypeException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WrongDataProfileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public WrongDataProfileTypeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public WrongDataProfileTypeException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public WrongDataProfileTypeException(Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public WrongDataProfileTypeException(String message,
			int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
