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

	public void setPublicTemplateLocations(List<String> publicTemplateLocations) {
		this.publicTemplateLocations = publicTemplateLocations;
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
			IOException;

	/*
	 * /** Discover any metadata templates that are stored in the user home
	 * 
	 * XXX TO BE REMOVED?
	 * 
	 * XXX TO RECONSIDER IN FUTURE
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

	public List<MetadataTemplate> listAllTemplates(final String path,
			final String userName) throws FileNotFoundException, IOException {

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
			allTemplates = listTemplatesInIrodsHierarchyAbovePath(path);
		}
		/*
		 * if (userName == null || userName.isEmpty()) { // ignore } else {
		 * allTemplates.addAll(listTemplatesInUserHome(userName)); }
		 */
		allTemplates.addAll(listPublicTemplates());
		return allTemplates;

	}

	public List<MetadataTemplate> listAllRequiredTemplates(final String path,
			final String userName) throws FileNotFoundException, IOException {
		List<MetadataTemplate> requiredTemplates = new ArrayList<MetadataTemplate>();

		List<MetadataTemplate> allTemplates = listAllTemplates(path, userName);

		for (MetadataTemplate t : allTemplates) {
			if (t.isRequired()) {
				requiredTemplates.add(t);
			}
		}

		return requiredTemplates;
	}

	public abstract MetadataTemplate findTemplateByName(String name, String activeDir)
			throws FileNotFoundException, IOException;

	public abstract MetadataTemplate findTemplateByFqName(String fqName)
			throws FileNotFoundException, IOException;

	public MetadataTemplate findTemplateByUUID(UUID uuid)
			throws FileNotFoundException, IOException {
		return findTemplateByFqName(getFqNameForUUID(uuid));
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
	public abstract void saveTemplateAsJSON(MetadataTemplate metadataTemplate,
			String location) throws FileNotFoundException, IOException;

	public abstract void renameTemplateByFqName(String uniqueName,
			String newName) throws FileNotFoundException, IOException;

	public void renameTemplateByUUID(UUID uuid, String newFqName)
			throws FileNotFoundException, IOException {
		renameTemplateByFqName(getFqNameForUUID(uuid), newFqName);
	}

	public abstract void updateTemplateByFqName(String uniqueName,
			MetadataTemplate mdTemplate) throws FileNotFoundException,
			IOException;

	public void updateTemplateByUUID(UUID uuid, MetadataTemplate mdTemplate)
			throws FileNotFoundException, IOException {
		updateTemplateByFqName(getFqNameForUUID(uuid), mdTemplate);
	}

	public abstract void deleteTemplateByFqName(String uniqueName)
			throws FileNotFoundException, IOException;

	public void deleteTemplateByUUID(UUID uuid) throws FileNotFoundException,
			IOException {
		deleteTemplateByFqName(getFqNameForUUID(uuid));
	}

	public abstract String getFqNameForUUID(UUID uuid);
}
