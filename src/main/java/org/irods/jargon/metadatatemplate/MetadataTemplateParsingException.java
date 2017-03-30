/**
 *
 */
package org.irods.jargon.metadatatemplate;

/**
 * General exception in the serializing/deserializing of a metadata template
 *
 * @author Mike Conway - DICE
 *
 */
public class MetadataTemplateParsingException extends MetadataTemplateProcessingException {

	/**
	 *
	 */
	private static final long serialVersionUID = -4048365214725081248L;

	/**
	 * @param message
	 */
	public MetadataTemplateParsingException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MetadataTemplateParsingException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public MetadataTemplateParsingException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateParsingException(final String message, final Throwable cause,
			final int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateParsingException(final Throwable cause, final int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public MetadataTemplateParsingException(final String message, final int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
