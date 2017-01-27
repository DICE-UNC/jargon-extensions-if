/**
 * 
 */
package org.irods.jargon.dataprofile.accessor.exception;

/**
 * Error caused by request for a metadata attribute that was not found.
 * 
 * @author rskarbez
 *
 */
public class AttributeNotFoundException extends DataProfileAccessorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2420444873815806204L;

	/**
	 * @param message
	 */
	public AttributeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AttributeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public AttributeNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public AttributeNotFoundException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public AttributeNotFoundException(Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public AttributeNotFoundException(String message,
			int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
		// TODO Auto-generated constructor stub
	}

}
