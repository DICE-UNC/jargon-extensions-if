/**
 * 
 */
package org.irods.jargon.metadatatemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.irods.jargon.core.exception.JargonException;

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

	// TODO Should public template locations be database keys?
	// TODO Locator == fqName?
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
	// TODO maybe refactor name?
	public abstract List<MetadataTemplate> listTemplatesInDirectoryHierarchyAbovePath(
			final String absolutePath) throws FileNotFoundException,
			IOException, MetadataTemplateProcessingException,
			MetadataTemplateParsingException;

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

	public List<MetadataTemplate> listAllTemplates(final String path)
			throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException,
			MetadataTemplateParsingException {

		List<MetadataTemplate> allTemplates = new ArrayList<MetadataTemplate>();
		List<MetadataTemplate> hierarchyTemplates;
		List<MetadataTemplate> publicTemplates;

		hierarchyTemplates = this
				.listTemplatesInDirectoryHierarchyAbovePath(path);
		publicTemplates = this.listPublicTemplates();

		// Both the directory hierarchy list and the public list have already
		// had duplicates accounted for. HOWEVER, there exists the possibility
		// that there is a template name that appears both in the hierarchy and
		// the public lists. This check accounts for that.
		allTemplates.addAll(hierarchyTemplates);
		if (!hierarchyTemplates.isEmpty() && !publicTemplates.isEmpty()) {
			for (MetadataTemplate mtPublic : publicTemplates) {
				boolean duplicate = false;
				for (MetadataTemplate mtHierarchy : hierarchyTemplates) {
					if (mtPublic.getName().equalsIgnoreCase(
							mtHierarchy.getName())) {
						duplicate = true;
						break;
					}
				}

				if (!duplicate) {
					allTemplates.add(mtPublic);
				}
			}
		}

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

	public abstract MetadataTemplate findTemplateByNameInDirectoryHierarchy(
			String name, String activeDir) throws FileNotFoundException,
			IOException, MetadataTemplateProcessingException,
			MetadataTemplateParsingException;

	public abstract MetadataTemplate findTemplateByNameInPublicTemplates(
			String name) throws FileNotFoundException, IOException,
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

	public boolean renameTemplateByUUID(String uuid, String newFqName)
			throws FileNotFoundException, IOException {
		return this.renameTemplateByFqName(
				getFqNameForUUID(UUID.fromString(uuid)), newFqName);
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

	public boolean updateFormBasedTemplateByUUID(String uuid,
			FormBasedMetadataTemplate metadataTemplate)
			throws FileNotFoundException, IOException {
		return this.updateFormBasedTemplateByFqName(
				getFqNameForUUID(UUID.fromString(uuid)), metadataTemplate);
	}

	public abstract boolean deleteTemplateByFqName(String uniqueName)
			throws FileNotFoundException, IOException;

	public boolean deleteTemplateByUUID(UUID uuid)
			throws FileNotFoundException, IOException {
		return this.deleteTemplateByFqName(getFqNameForUUID(uuid));
	}

	public boolean deleteTemplateByUUID(String uuid)
			throws FileNotFoundException, IOException {
		return this.deleteTemplateByFqName(getFqNameForUUID(UUID
				.fromString(uuid)));
	}

	public abstract MetadataTemplate cloneTemplateByFqName(String fqName,
			String newTemplateName, String destDir)
			throws FileNotFoundException, IOException,
			MetadataTemplateParsingException,
			MetadataTemplateProcessingException;

	public MetadataTemplate cloneTemplateByUUID(UUID uuid,
			String newTemplateName, String destDir)
			throws FileNotFoundException, IOException,
			MetadataTemplateParsingException,
			MetadataTemplateProcessingException {
		return this.cloneTemplateByFqName(getFqNameForUUID(uuid),
				newTemplateName, destDir);
	}

	public MetadataTemplate cloneTemplateByUUID(String uuid,
			String newTemplateName, String destDir)
			throws FileNotFoundException, IOException,
			MetadataTemplateParsingException,
			MetadataTemplateProcessingException {
		return this.cloneTemplateByFqName(
				getFqNameForUUID(UUID.fromString(uuid)), newTemplateName,
				destDir);
	}

	public abstract String getFqNameForUUID(UUID uuid);

	public String getFqNameForUUID(String uuid) {
		return this.getFqNameForUUID(UUID.fromString(uuid));
	}
	
/*	
 	public abstract void saveTemplateToSystemMetadataOnObject(
			MetadataTemplate metadataTemplate, String pathToObject)
			throws FileNotFoundException, JargonException;
*/
}
