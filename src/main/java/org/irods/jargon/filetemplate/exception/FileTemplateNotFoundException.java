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
	public FileTemplateNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileTemplateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public FileTemplateNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateNotFoundException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateNotFoundException(String message,
			int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
