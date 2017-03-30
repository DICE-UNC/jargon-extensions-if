/**
 *
 */
package org.irods.jargon.filetemplate.exception;

/**
 * The given file template does not exist
 *
 * @author Mike Conway - DICE
 *
 */
public class FileTemplateNotFoundException extends FileTemplateException {

	/**
	 *
	 */
	private static final long serialVersionUID = 6304037461268076633L;

	/**
	 * @param message
	 */
	public FileTemplateNotFoundException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileTemplateNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public FileTemplateNotFoundException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateNotFoundException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateNotFoundException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
