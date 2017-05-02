package org.irods.jargon.metadatatemplate;

/**
 * Factory for metadata template services. This enhances the ability to plug in
 * new implementations in an easily testable manner
 *
 * @author mconway
 *
 * @param <T>
 *            {@link TemplateSourceContext} subclass that can pass
 *            implementation-specific credentials and configuration to the
 *            template service
 */
public interface MetadataTemplateServiceFactory<T extends MetadataTemplateContext> {

	/**
	 * Obtain the metadata resolver that can locate and process templates
	 *
	 * 
	 * @param templateSourceContext
	 *            {@link TemplateSourceContext} with implementation specific
	 *            information, can be set to null if not used
	 * @return {@link AbstractMetadataResolver}
	 * @throws MetadataTemplateProcessingException
	 */
	AbstractMetadataResolver<T> instanceMetadataResolver(T templateSourceContext)
			throws MetadataTemplateProcessingException;

}