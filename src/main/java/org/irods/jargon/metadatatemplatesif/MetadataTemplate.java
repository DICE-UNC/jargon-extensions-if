/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic domain object for a metadata template
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */

/*
 * avu unit should be mdtemplatename::elementname
 */

public class MetadataTemplate {

	/**
	 * Public name for a metadata template, represents a generic template of a
	 * type (e.g. Dublin Core) of which there may be multiple unique
	 * representations
	 */
	private final String name = "";

	/**
	 * Unique name (for any arbitrary resolver) that uniquely identifies the
	 * template. For example, in iRODS, this would be the fully qualified
	 * logical name of a iRODS file that holds a representaiton of the template.
	 */
	private final String uniqueName = "";

	/**
	 * description, helpful text
	 */
	private final String description = "";

	/**
	 * ???: does this belong here, Indicates whether the given template is
	 * required
	 */
	private final boolean required = false;

	/**
	 * List of optional templates that are linked by this template, such that a
	 * non-required template could be bound by a resolver as required, or by
	 * composition.
	 * <p/>
	 * This will be by the 'public' name, versus by the unique name, and the
	 * resolver will find the closest template through the standard rule of 1)
	 * nearest to the collection 2) user home dir 3) public viewable dirs
	 */
	private final List<String> linkedTemplates = new ArrayList<String>();

	/**
	 * 
	 */
	public MetadataTemplate() {
	}

}
