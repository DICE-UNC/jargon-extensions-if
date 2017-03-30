/**
 *
 */
package org.irods.jargon.vircoll.exception;

import org.irods.jargon.core.exception.JargonException;

/**
 * General exception in virtual collection processing. This deals more with CRUD
 * of virtual collections versus executing queries on them
 *
 * @author Mike Conway - DICE
 *
 */
public class VirtualCollectionException extends JargonException {

	/**
	 *
	 */
	private static final long serialVersionUID = -7870998677444041033L;

	public VirtualCollectionException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

	public VirtualCollectionException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	public VirtualCollectionException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public VirtualCollectionException(final String message) {
		super(message);
	}

	public VirtualCollectionException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	public VirtualCollectionException(final Throwable cause) {
		super(cause);
	}

}
