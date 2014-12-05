/**
 * 
 */
package org.irods.jargon.metadatatemplatesif;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass for a metadata resolver that can discover, and perform
 * CRUD on metadata templates
 * <p/>
 * <h2>Rules for Resolvers</h2>
 * <h3>Primacy</h3>
 * Metadata templates have a hierarchy when disocovered, which is dependent on
 * the implementaiton of the resolver. We can use the built-in iRODS resolver as
 * a reference implementation, which will work as follows, other implementations
 * can deviate, but this provided a pretty clean and simple way of resolving.
 * <ul>
 * <li>Closest in directory hierarchy, recursively up to the parent,
 * grandparent, etc</li>
 * <li>In user home directory</li>
 * <li>In public directories</li>
 * </ul>
 * 
 * 
 * @author Mike Conway and Rick Skarbez
 *
 */
public abstract class AbstractMetadataResolver {

	private final List<String> templateGroups = new ArrayList<String>();

	/**
	 * 
	 */
	public AbstractMetadataResolver() {
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
	public abstract List<MetadataTemplate> listTemplatesAssociatedWithIrodsHierarchyForPath(
			final String absolutePath);

	/**
	 * Discover any metadata templates that are stored in the user home
	 * 
	 * @param userName
	 * @return
	 */
	public abstract List<MetadataTemplate> listTemplatesInUserHome(
			final String userName);

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
	public List<MetadataTemplate> listTemplatesInTemplateGroups() {

		return new ArrayList<MetadataTemplate>();
	}

	public List<MetadataTemplate> listAll(final String path,
			final String userName) {

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
			allTemplates = listTemplatesAssociatedWithIrodsHierarchyForPath(path);
		}

		if (userName == null || userName.isEmpty()) {
			// ignore
		} else {
			allTemplates.addAll(listTemplatesInUserHome(userName));
		}

		allTemplates.addAll(listTemplatesInTemplateGroups());
		return allTemplates;

	}

	/**
	 * Add or update existing by unique name
	 * 
	 * @param metadataTemplate
	 */
	public abstract void save(MetadataTemplate metadataTemplate); // add
																	// exceptions
																	// for
																	// duplicate

	public abstract void delete(String uniqueName);

	/**
	 * This one uses same logic as listAll to find the closest md template with
	 * given generic name
	 * 
	 * @param uniqueName
	 * @return
	 */
	public abstract MetadataTemplate findByName(String name);

	public abstract MetadataTemplate findByUniqueName(String uniqueName);
}
