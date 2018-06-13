/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * Abstract superclass for a metadata resolver that can locate and perform CRUD
 * operations on metadata templates.
 * <p/>
 * <h2>Rules for Resolvers</h2>
 * <h3>Primacy</h3> Metadata templates have a hierarchy when discovered, which
 * is dependent on the implementation of the resolver. We can use the built-in
 * iRODS resolver as a reference implementation, which will work as follows,
 * other implementations can deviate, but this provided a pretty clean and
 * simple way of resolving.
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
	 * Discover any metadata templates that are bound to the given path. This path
	 * is not where the templates are stored, this path is the irods object that is
	 * having metadata applied to it.
	 * <p/>
	 * This is equivalent to saying, I want to put metadata on this file, what
	 * templates are in this collection or any parent collections that I should use
	 * or require.
	 * 
	 * @param absolutePath
	 * @return
	 */
	public abstract List<MetadataTemplate> listTemplatesInDirectoryHierarchyAbovePath(final String absolutePath)
			throws FileNotFoundException, MetadataTemplateException;

	/**
	 * Given an abstract notion of a group, return metadata templates gathered from
	 * that group. In our iRODS based reference implementation, these groups are
	 * really just iRODS paths to directories that can contain metadata templates,
	 * you can implement this differently, or just leave as is and it returns an
	 * empty list.
	 * 
	 * @param templateGropus
	 * @return
	 */
	public abstract List<MetadataTemplate> listPublicTemplates();

	public abstract List<MetadataTemplate> listAllTemplates(final String path) throws MetadataTemplateException;

	public abstract List<MetadataTemplate> listAllRequiredTemplates(final String path)
			throws FileNotFoundException, MetadataTemplateException;

	public abstract MetadataTemplate findTemplateByName(String name, String activeDir)
			throws FileNotFoundException, MetadataTemplateException;

	public abstract MetadataTemplate findTemplateByNameInPublicTemplates(String name) throws MetadataTemplateException;

	public abstract MetadataTemplate findTemplateByUUID(UUID uuid)
			throws MetadataTemplateNotFoundException, MetadataTemplateException;

	public abstract UUID saveTemplate(MetadataTemplate metadataTemplate) throws MetadataTemplateException;

	public abstract boolean deleteTemplateByUUID(String uniqueName) throws MetadataTemplateException;
}
