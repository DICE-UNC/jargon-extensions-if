/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

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

	public void setMetadataSourceLink(MetadataSourceLink metadataSourceLink) {
		this.metadataSourceLink = metadataSourceLink;
	}

	/**
	 * 
	 */
	public SourceBasedMetadataTemplate() {
	}
	
	public SourceBasedMetadataTemplate(SourceBasedMetadataTemplate mt) {
		this.setAuthor(mt.getAuthor());
		this.setName(mt.getName());
		this.setFqName(mt.getFqName());
		this.setDescription(mt.getDescription());
		this.setRequired(mt.isRequired());
		this.setSource(mt.getSource());
		this.setUuid(mt.getUuid());
		this.setVersion(mt.getVersion());
		this.setMetadataSourceLink(mt.getMetadataSourceLink());
	}
	
	@Override
	public SourceBasedMetadataTemplate deepCopy() {
		return new SourceBasedMetadataTemplate(this);
	}

}
