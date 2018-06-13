package org.irods.jargon.metadatatemplate;

public class MetadataTemplateNotFoundException extends MetadataTemplateException {

	public MetadataTemplateNotFoundException(String message) {
		super(message);
	}

	public MetadataTemplateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetadataTemplateNotFoundException(Throwable cause) {
		super(cause);
	}

	public MetadataTemplateNotFoundException(String message, Throwable cause, int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	public MetadataTemplateNotFoundException(Throwable cause, int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	public MetadataTemplateNotFoundException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
