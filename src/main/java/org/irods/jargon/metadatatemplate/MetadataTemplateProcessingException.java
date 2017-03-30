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
	public MetadataTemplateProcessingException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MetadataTemplateProcessingException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public MetadataTemplateProcessingException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateProcessingException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateProcessingException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateProcessingException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
