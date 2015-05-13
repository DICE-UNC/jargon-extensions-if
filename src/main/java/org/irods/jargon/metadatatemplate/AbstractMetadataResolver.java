/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Abstract superclass for a metadata resolver that can locate and perform CRUD
 * operations on metadata templates.
 * <p/>
 * <h2>Rules for Resolvers</h2>
 * <h3>Primacy</h3>
 * Metadata templates have a hierarchy when discovered, which is dependent on
 * the implementation of the resolver. We can use the built-in iRODS resolver as
 * a reference implementation, which will work as follows, other implementations
 * can deviate, but this provided a pretty clean and simple way of resolving.
 * <ul>
 * <li>Closest in directory hierarchy, recursively up to the parent,
 * grandparent, etc</li>
 * <li>In public directories</li>
 * </ul>
 * 
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */

// TODO Should all lists really be hashmaps?

public abstract class AbstractMetadataResolver {

	/**
	 * Holds locations (directories, database tables, etc.) where publicly
	 * available metadata templates reside.
	 */
	private List<String> publicTemplateLocations = new ArrayList<String>();

	/**
	 * 
	 */
	public AbstractMetadataResolver() {
	}

	public List<String> getPublicTemplateLocations() {
		return publicTemplateLocations;
	}

	/**
	 * Used in combination with <code>listPublicTemplates</code>. Call this
	 * first to indicate the directories in which public templates are
	 * stored.</p>
	 * <p>
	 * Directories should be fully-qualified. That is, if the template files are
	 * actually stored in /foo/bar/.irods/metadataTemplates, the string for this
	 * folder would be "/foo/bar/.irods/metadataTemplates", not just "/foo/bar".
	 * 
	 * @param publicTemplateLocations
	 */
	public void setPublicTemplateLocations(List<String> publicTemplateLocations) {
		this.publicTemplateLocations = publicTemplateLocations;
	}

	/**
	 * Used in combination with <code>listPublicTemplates</code>. Call this
	 * first to add a directory to the public template locations.</p>
	 * <p>
	 * The directory should be fully-qualified. That is, if the template files
	 * are actually stored in /foo/bar/.irods/metadataTemplates, the string for
	 * this folder would be "/foo/bar/.irods/metadataTemplates", not just
	 * "/foo/bar".
	 * 
	 * @param publicTemplateLocation
	 */
	public void appendToPublicTemplateLocations(String publicTemplateLocation) {
		this.publicTemplateLocations.add(publicTemplateLocation);
	}

	/**
	 * Discover any metadata templates that are bound to the given path. This
	 * path is not where the templates are stored, this path is the irods object
	 * that is having metadata applied to it.
	 * <p/>
	 * This is equivalent to saying, I want to put metadata on this file, what
	 * templates are in this collection or any parent collections that I should
	 * use or require.
	 * 
	 * @param absolutePath
	 * @return
	 */
	public abstract List<MetadataTemplate> listTemplatesInIrodsHierarchyAbovePath(
			final String absolutePath) throws FileNotFoundException,
			IOException, MetadataTemplateProcessingException,
			MetadataTemplateParsingException;

	/*
	 * /** Discover any metadata templates that are stored in the user home
	 * 
	 * XXX TO BE REMOVED ENTIRELY?
	 * 
	 * 
	 * @param userName
	 * 
	 * @return
	 * 
	 * public abstract List<MetadataTemplate> listTemplatesInUserHome( final
	 * String userName) throws FileNotFoundException, IOException;
	 */

	/**
	 * Given an abstract notion of a group, return metadata templates gathered
	 * from that group. In our iRODS based reference implementation, these
	 * groups are really just iRODS paths to directories that can contain
	 * metadata templates, you can implement this differently, or just leave as
	 * is and it returns an empty list.
	 * 
	 * @param templateGropus
	 * @return
	 */
	public abstract List<MetadataTemplate> listPublicTemplates();

	// XXX Was formerly a String userName parameter; not relevant if templates
	// are not allowed from user home
	public List<MetadataTemplate> listAllTemplates(final String path)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException {

		/*
		 * This is backwards, also, should this be a list. We want the strongest
		 * association to win, and there should only be one entry per metadata
		 * template that 'wins'
		 * 
		 * so to all by group, then override by name with user, then override
		 * again starting at top of tree down to collection the closest to the
		 * collection wins for any 'aname'
		 */

		List<MetadataTemplate> allTemplates;
		if (path == null || path.isEmpty()) {
			allTemplates = new ArrayList<MetadataTemplate>();
		} else {
			allTemplates = this.listTemplatesInIrodsHierarchyAbovePath(path);
		}
		/*
		 * if (userName == null || userName.isEmpty()) { // ignore } else {
		 * allTemplates.addAll(listTemplatesInUserHome(userName)); }
		 */
		allTemplates.addAll(this.listPublicTemplates());
		return allTemplates;

	}

	public List<MetadataTemplate> listAllRequiredTemplates(final String path)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException {
		List<MetadataTemplate> requiredTemplates = new ArrayList<MetadataTemplate>();

		List<MetadataTemplate> allTemplates = this.listAllTemplates(path);

		for (MetadataTemplate t : allTemplates) {
			if (t.isRequired()) {
				requiredTemplates.add(t);
			}
		}

		return requiredTemplates;
	}

	public abstract MetadataTemplate findTemplateByName(String name,
			String activeDir) throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException;

	public abstract MetadataTemplate findTemplateByFqName(String fqName)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException;

	public MetadataTemplate findTemplateByUUID(UUID uuid)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException {
		return findTemplateByFqName(getFqNameForUUID(uuid));
	}

	public MetadataTemplate findTemplateByUUID(String uuid)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException {
		return findTemplateByFqName(getFqNameForUUID(UUID.fromString(uuid)));
	}

	/**
	 * Add or update existing by unique name
	 * 
	 * @param metadataTemplate
	 * 
	 * @throws FileNotFoundException
	 *             if <code>location</code> is not a valid save location
	 * @throws IOException
	 *             if save fails
	 */
	public abstract String saveFormBasedTemplateAsJSON(
			FormBasedMetadataTemplate metadataTemplate, String location)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException;

	public abstract boolean renameTemplateByFqName(String uniqueName,
			String newName) throws FileNotFoundException, IOException;

	public boolean renameTemplateByUUID(UUID uuid, String newFqName)
			throws FileNotFoundException, IOException {
		return this.renameTemplateByFqName(getFqNameForUUID(uuid), newFqName);
	}

	public abstract boolean updateFormBasedTemplateByFqName(String uniqueName,
			FormBasedMetadataTemplate metadataTemplate)
			throws FileNotFoundException, IOException;

	public boolean updateFormBasedTemplateByUUID(UUID uuid,
			FormBasedMetadataTemplate metadataTemplate)
			throws FileNotFoundException, IOException {
		return this.updateFormBasedTemplateByFqName(getFqNameForUUID(uuid),
				metadataTemplate);
	}

	public abstract boolean deleteTemplateByFqName(String uniqueName)
			throws FileNotFoundException, IOException;

	public boolean deleteTemplateByUUID(UUID uuid)
			throws FileNotFoundException, IOException {
		return this.deleteTemplateByFqName(getFqNameForUUID(uuid));
	}

	public abstract String getFqNameForUUID(UUID uuid);
}
