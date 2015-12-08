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
	public FileTemplateException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileTemplateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public FileTemplateException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateException(Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public FileTemplateException(String message,
			int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
