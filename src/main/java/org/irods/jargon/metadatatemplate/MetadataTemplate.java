/**
 *
 */
package org.irods.jargon.metadatatemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class MetadataTemplate {
	/**
	 * UUID that uniquely identifies this metadata template. Helpful to
	 * implement versioning and template linking.
	 */
	// Not marked as "JsonProperty" because should not be serialized
	// Will be handled in iRODS as AVU
	private UUID uuid = new UUID(0, 0);

	/**
	 * Public name for a metadata template, represents a generic template of a
	 * type (e.g. Dublin Core) of which there may be multiple unique
	 * representations.
	 *
	 * By convention, the template name is the same as the filename, for
	 * example, a template named DC would be stored as a file named
	 * DC.mdtemplate.
	 */
	@JsonProperty("name")
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
	 * Specifies the source of data that will populate the metadata template.
	 *
	 * XXX ONLY USER MODE IS CURRENTLY SUPPORTED
	 */
	@JsonProperty("source")
	private SourceEnum source = SourceEnum.USER;

	/**
	 * Used in conjunction with <code>source</code>, driver specifies the
	 * computation that will be run to generate data for <code>DRIVER</code> or
	 * <code>MIXED</code> modes. <code>driver</code> is not used if
	 * <code>source=USER</code>.
	 *
	 * XXX NOT YET SUPPORTED
	 */
	// @JsonProperty("driver")
	// private MetadataDriver driver = new MetadataDriver();

	/**
	 * Specifies the format in which metadata is finally stored.
	 *
	 * XXX ONLY IRODS MODE IS CURRENTLY SUPPORTED
	 */
	@JsonProperty("destination")
	private DestinationEnum destination = DestinationEnum.IRODS;

	/**
	 * Indicates whether the given template is required. If true, the validator
	 * will reject a file/metadata if the attributes marked as required are not
	 * populated with valid values.
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

	/**
	 * Describes the list of elements in the metadata template
	 */
	// TODO Should probably end up as a hashmap, not a list
	@JsonProperty("elements")
	private List<MetadataElement> elements = new ArrayList<MetadataElement>();

	public List<MetadataElement> getElements() {
		return elements;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getFqName() {
		return fqName;
	}

	public void setFqName(final String fqName) {
		this.fqName = fqName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(final Date modified) {
		this.modified = modified;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public SourceEnum getSource() {
		return source;
	}

	public void setSource(final SourceEnum source) {
		this.source = source;
	}

	// TODO Uncomment when ready to implement multiple drivers
	/*
	 * public MetadataDriver getDriver() { return driver; }
	 *
	 * public void setDriver(MetadataDriver driver) { this.driver = driver; }
	 */

	public DestinationEnum getExporter() {
		return destination;
	}

	public void setExporter(final DestinationEnum destination) {
		this.destination = destination;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(final boolean required) {
		this.required = required;
	}

	// TODO Uncomment when ready to implement linked templates
	/*
	 * public List<String> getLinkedTemplates() { return linkedTemplates; }
	 *
	 * public void setLinkedTemplates(List<String> linkedTemplates) {
	 * this.linkedTemplates = linkedTemplates; }
	 */
	/**
	 *
	 */
	public MetadataTemplate() {
	}

	public MetadataTemplate(final MetadataTemplate mt) {
		setAuthor(mt.getAuthor());
		setName(mt.getName());
		setFqName(mt.getFqName());
		setDescription(mt.getDescription());
		setRequired(mt.isRequired());
		setSource(mt.getSource());
		setUuid(mt.getUuid());
		setVersion(mt.getVersion());
	}

	public void setElements(List<MetadataElement> elements) {
		this.elements = elements;
	}

}
