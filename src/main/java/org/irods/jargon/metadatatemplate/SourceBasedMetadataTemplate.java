/**
 *
 */
package org.irods.jargon.metadatatemplate;

/**
 * Metadata template that links a source (such as an ontology)
 *
 * @author Mike Conway and Rick Skarbez
 *
 */
public class SourceBasedMetadataTemplate extends MetadataTemplate {

	/**
	 * Optional link to a metadata source that can describe an ontology (like
	 * HIVE) to link to this collection
	 */
	private MetadataSourceLink metadataSourceLink = null;

	public MetadataSourceLink getMetadataSourceLink() {
		return metadataSourceLink;
	}

	public void setMetadataSourceLink(final MetadataSourceLink metadataSourceLink) {
		this.metadataSourceLink = metadataSourceLink;
	}

	/**
	 *
	 */
	public SourceBasedMetadataTemplate() {
	}

	public SourceBasedMetadataTemplate(final SourceBasedMetadataTemplate mt) {
		setAuthor(mt.getAuthor());
		setName(mt.getName());
		setFqName(mt.getFqName());
		setDescription(mt.getDescription());
		setRequired(mt.isRequired());
		setSource(mt.getSource());
		setUuid(mt.getUuid());
		setVersion(mt.getVersion());
		setMetadataSourceLink(mt.getMetadataSourceLink());
	}

}
