/**
 *
 */
package org.irods.jargon.metadatatemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.irods.jargon.core.pub.IRODSAccessObjectFactory;

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

public abstract class AbstractMetadataResolver<T extends MetadataTemplateContext> {

	/**
	 * {@link MetadataTemplateContext} with specific
	 */
	private final T metadataTemplateContext;

	/**
	 * Access object factory used to interact with iRODS
	 */
	private final IRODSAccessObjectFactory irodsAccessObjectFactory;

	/**
	 * Configuration object for metadata templates
	 */
	private final MetadataTemplateConfiguration metadataTemplateConfiguration;

	/**
	 * @param metadataTemplateContext
	 * @param irodsAccessObjectFactory
	 * @param metadataTemplateConfiguration
	 */
	public AbstractMetadataResolver(T metadataTemplateContext, IRODSAccessObjectFactory irodsAccessObjectFactory,
			MetadataTemplateConfiguration metadataTemplateConfiguration) {
		super();
		this.metadataTemplateContext = metadataTemplateContext;
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
		this.metadataTemplateConfiguration = metadataTemplateConfiguration;
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
	public abstract List<MetadataTemplate> listTemplatesInDirectoryHierarchyAbovePath(final String absolutePath)
			throws FileNotFoundException, IOException, MetadataTemplateProcessingException,
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

	/**
	 * @param absolutePathInIrods
	 *            <code>String</code> with the path in iRODS to which templates
	 *            may be bound
	 * @return <code>List</code> of {@link MetadataTemplate} with both public
	 *         and attached metadata templates.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MetadataTemplateProcessingException
	 * @throws MetadataTemplateParsingException
	 */
	public List<MetadataTemplate> listAllTemplatesBoundToPath(final String absolutePathInIrods)
			throws FileNotFoundException, IOException, MetadataTemplateProcessingException,
			MetadataTemplateParsingException {

		List<MetadataTemplate> allTemplates = new ArrayList<MetadataTemplate>();
		List<MetadataTemplate> hierarchyTemplates;
		List<MetadataTemplate> publicTemplates;

		hierarchyTemplates = listTemplatesInDirectoryHierarchyAbovePath(absolutePathInIrods);
		publicTemplates = listPublicTemplates();

		// Both the directory hierarchy list and the public list have already
		// had duplicates accounted for. HOWEVER, there exists the possibility
		// that there is a template name that appears both in the hierarchy and
		// the public lists. This check accounts for that.
		allTemplates.addAll(hierarchyTemplates);
		if (!hierarchyTemplates.isEmpty() && !publicTemplates.isEmpty()) {
			for (MetadataTemplate mtPublic : publicTemplates) {
				boolean duplicate = false;
				for (MetadataTemplate mtHierarchy : hierarchyTemplates) {
					if (mtPublic.getName().equalsIgnoreCase(mtHierarchy.getName())) {
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

	/**
	 * List all templates that are required to be associated with the given
	 * iRODS path, whether public or bound in the heirarchy
	 * 
	 * @param absolutePathInIrods
	 *            <code>String</code> with the path in iRODS to which templates
	 *            may be bound
	 * @return <code>List</code> of {@link MetadataTemplate} with both public
	 *         and attached metadata templates.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MetadataTemplateProcessingException
	 * @throws MetadataTemplateParsingException
	 */
	public List<MetadataTemplate> listAllRequiredTemplates(final String absolutePathInIrods)
			throws FileNotFoundException, IOException, MetadataTemplateProcessingException,
			MetadataTemplateParsingException {
		List<MetadataTemplate> requiredTemplates = new ArrayList<MetadataTemplate>();

		List<MetadataTemplate> allTemplates = listAllTemplatesBoundToPath(absolutePathInIrods);

		for (MetadataTemplate t : allTemplates) {
			if (t.isRequired()) {
				requiredTemplates.add(t);
			}
		}

		return requiredTemplates;
	}

	public abstract MetadataTemplate findTemplateByName(String name, String activeDir) throws FileNotFoundException,
			IOException, MetadataTemplateProcessingException, MetadataTemplateParsingException;

	// TODO: has file based semantics, do we need it? - mcc
	public abstract MetadataTemplate findTemplateByNameInDirectoryHierarchy(String name, String activeDir)
			throws FileNotFoundException, IOException, MetadataTemplateProcessingException,
			MetadataTemplateParsingException;

	public abstract MetadataTemplate findTemplateByNameInPublicTemplates(String name) throws FileNotFoundException,
			IOException, MetadataTemplateProcessingException, MetadataTemplateParsingException;

	public abstract MetadataTemplate findTemplateByUUID(final String string) throws FileNotFoundException, IOException,
			MetadataTemplateProcessingException, MetadataTemplateParsingException;

	/**
	 * Add a metadata template
	 *
	 * @param metadataTemplate
	 *            {@link MetadataTemplate} to be added
	 * @param metadataTemplateLocationTypeEnum
	 *            {@link MetadataTemplateLocationTypeEnum} that indicates
	 *            whether to store this in a public versus user repo
	 * @throws FileNotFoundException
	 *             if <code>location</code> is not a valid save location
	 * @throws IOException
	 *             if save fails
	 */
	public abstract UUID saveTemplate(MetadataTemplate metadataTemplate,
			MetadataTemplateLocationTypeEnum metadataTemplateLocationTypeEnum)
			throws MetadataTemplateProcessingException;

	/**
	 * Update a previously stored template, distinct from a save operation which
	 * creates a new one.
	 * 
	 * @param metadataTemplate
	 *            {@link MetadataTemplate} to update
	 * @throws MetadataTemplateParsingException
	 * @throws MetadataTemplateNotFoundException
	 * @throws MetadataTemplateProcessingException
	 */
	public abstract void updateTemplate(MetadataTemplate metadataTemplate) throws MetadataTemplateParsingException,
			MetadataTemplateNotFoundException, MetadataTemplateProcessingException;

	/**
	 * Delete a metadata template based on the uuid. Note that the actual
	 * meaning and semantics of a delete are TBD. That is, is a delete actually
	 * a deprectation? What happens when you delete a template that has metadata
	 * associated?
	 * 
	 * @param uuid
	 *            <code>String</code> representation of the UUID of the template
	 *            to delete
	 * @return <code>boolean</code> that indicates success
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public abstract boolean deleteTemplate(String uuid) throws FileNotFoundException, IOException;

	public T getMetadataTemplateContext() {
		return metadataTemplateContext;
	}

	public MetadataTemplateConfiguration getMetadataTemplateConfiguration() {
		return metadataTemplateConfiguration;
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

}
