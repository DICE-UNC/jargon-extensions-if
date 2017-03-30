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
	public AttributeNotFoundException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AttributeNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public AttributeNotFoundException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public AttributeNotFoundException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public AttributeNotFoundException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public AttributeNotFoundException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
		// TODO Auto-generated constructor stub
	}

}
