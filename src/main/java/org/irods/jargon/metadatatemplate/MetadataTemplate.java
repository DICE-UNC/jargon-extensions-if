/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Basic domain object for a metadata template
 * 
 * @author Mike Conway and Rick Skarbez
 */

/*
 * avu unit should be mdtemplatename::elementname
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MetadataTemplate {
	/**
	 * UUID that uniquely identifies this metadata template. Helpful to implement
	 * versioning and template linking.
	 */
	// Not marked as "JsonProperty" because should not be serialized
	// Will be handled in iRODS as AVU
	private UUID uuid = new UUID(0, 0);

	/**
	 * Public name for a metadata template, represents a generic template of a type
	 * (e.g. Dublin Core) of which there may be multiple unique representations.
	 * 
	 * By convention, the template name is the same as the filename, for example, a
	 * template named DC would be stored as a file named DC.mdtemplate.
	 */
	@JsonProperty("name")
	private String name = "";

	/**
	 * description, helpful text
	 */
	@JsonProperty("description")
	private String description = "";

	/**
	 * Author of the template
	 */
	@JsonProperty("author")
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
	@JsonProperty("version")
	private String version = "";

	/**
	 * Indicates whether the given template is required. If true, the validator will
	 * reject a file/metadata if the attributes marked as required are not populated
	 * with valid values.
	 */
	@JsonProperty("required")
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
	// TODO Uncomment when ready to implement linked templates
	// private List<String> linkedTemplates = new ArrayList<String>();

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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Object getElements() {
		// FIXME: Auto-generated method stub
		return null;
	}

}
