/**
 * 
 */
package org.irods.jargon.metadatatemplate;

/**
 * Base configuration for metadata templates. May be extended for specific
 * metadata stores
 * 
 * @author mconway
 *
 */
public class MetadataTemplateConfiguration {

	/**
	 * Identifier (specific to implementation) that indicates the location of
	 * public templates (may be expanded in later iters to a repo list)
	 */
	private String publicTemplateIdentifier = "";

	/**
	 * 
	 */
	public MetadataTemplateConfiguration() {
	}

	public String getPublicTemplateIdentifier() {
		return publicTemplateIdentifier;
	}

	public void setPublicTemplateIdentifier(String publicTemplateIdentifier) {
		this.publicTemplateIdentifier = publicTemplateIdentifier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MetadataTemplateConfiguration [");
		if (publicTemplateIdentifier != null) {
			builder.append("publicTemplateIdentifier=").append(publicTemplateIdentifier);
		}
		builder.append("]");
		return builder.toString();
	}

}
