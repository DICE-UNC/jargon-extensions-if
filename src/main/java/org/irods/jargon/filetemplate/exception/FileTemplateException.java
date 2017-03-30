/**
 *
 */
package org.irods.jargon.filetemplate.exception;

import org.irods.jargon.core.exception.JargonException;

/**
 * General high-level exception in the file template service
 *
 * @author Mike Conway - DICE
 *
 */
public class FileTemplateException extends JargonException {

	/**
	 *
	 */
	private static final long serialVersionUID = 7953475344382201399L;

	/**
	 * @param message
	 */
	public FileTemplateException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileTemplateException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public FileTemplateException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateException(final String message, final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
