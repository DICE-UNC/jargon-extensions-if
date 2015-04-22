/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import org.irods.jargon.core.exception.JargonException;

/**
 * General exception in metadata template processing
 * 
 * @author Mike Conway - DICE
 *
 */
public class MetadataTemplateProcessingException extends JargonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2950017867032672042L;

	/**
	 * @param message
	 */
	public MetadataTemplateProcessingException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MetadataTemplateProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public MetadataTemplateProcessingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateProcessingException(String message, Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateProcessingException(Throwable cause,
			int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateProcessingException(String message,
			int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
