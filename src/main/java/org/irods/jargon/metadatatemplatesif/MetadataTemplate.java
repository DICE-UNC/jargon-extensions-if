/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Basic domain object for a metadata template
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */

/*
 * avu unit should be mdtemplatename::elementname
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MetadataTemplate {
	/**
	 * UUID that uniquely identifies this metadata template. Helpful to
	 * implement versioning and template linking.
	 */
	private UUID uuid = new UUID(0, 0);

	/**
	 * Public name for a metadata template, represents a generic template of a
	 * type (e.g. Dublin Core) of which there may be multiple unique
	 * representations.
	 * 
	 * By convention, the template name is the same as the filename, for
	 * example, a template named DC would be stored as a file named DC.json.
	 */
	private String name = "";

	/**
	 * A resource locator that specifies where the template can be found. For
	 * example, in iRODS, this would be the fully qualified logical name of the
	 * iRODS directory that contains the template file.
	 * 
	 * By convention, the fully-qualified identifier location/name.json must be
	 * unique.
	 */
	private String fqName = "";

	/**
	 * description, helpful text
	 */
	private String description = "";

	/**
	 * Author of the template
	 */
	private String author = "";

	/**
	 * When template was created
	 */
	private Date created = new Date(0);

	/**
	 * When template was last modified
	 */
	private Date modified = new Date(0);

	/**
	 * What version of the template
	 */
	private String version = "";

	/**
	 * Specifies the source of data that will populate the metadata template.
	 * 
	 * XXX ONLY USER MODE IS CURRENTLY SUPPORTED
	 */
	private SourceEnum source = SourceEnum.USER;

	/**
	 * Used in conjunction with <code>source</code>, driver specifies the
	 * computation that will be run to generate data for <code>DRIVER</code> or
	 * <code>MIXED</code> modes. <code>driver</code> is not used if
	 * <code>source=USER</code>.
	 * 
	 * XXX NOT YET SUPPORTED
	 */
	private MetadataDriver driver = new MetadataDriver();

	/**
	 * Indicates whether the given template is required. If true, the validator
	 * will reject a file/metadata if the attributes marked as required are not
	 * populated with valid values.
	 */
	private boolean required = false;

	/**
	 * List of optional templates that are linked by this template, such that a
	 * non-required template could be bound by a resolver as required, or by
	 * composition.
	 * <p/>
	 * This will be by the 'public' name, versus by the unique name, and the
	 * resolver will find the closest template through the standard rule of 1)
	 * nearest to the collection 2) user home dir 3) public viewable dirs
	 */
	private List<String> linkedTemplates = new ArrayList<String>();

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFqName() {
		return fqName;
	}

	public void setFqName(String fqName) {
		this.fqName = fqName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public SourceEnum getSource() {
		return source;
	}

	public void setSource(SourceEnum source) {
		this.source = source;
	}

	public MetadataDriver getDriver() {
		return driver;
	}

	public void setDriver(MetadataDriver driver) {
		this.driver = driver;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public List<String> getLinkedTemplates() {
		return linkedTemplates;
	}

	public void setLinkedTemplates(List<String> linkedTemplates) {
		this.linkedTemplates = linkedTemplates;
	}

	/**
	 * 
	 */
	public MetadataTemplate() {
	}

}
