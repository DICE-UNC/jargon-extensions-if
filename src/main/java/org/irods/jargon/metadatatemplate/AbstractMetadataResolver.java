/**
 *
 */
package org.irods.jargon.metadatatemplate;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.irods.jargon.core.exception.FileNotFoundException;
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

	public AbstractMetadataResolver(final T metadataTemplateContext,
			final IRODSAccessObjectFactory irodsAccessObjectFactory,
			final MetadataTemplateConfiguration metadataTemplateConfiguration) {
		super();
		this.metadataTemplateContext = metadataTemplateContext;
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
		this.metadataTemplateConfiguration = metadataTemplateConfiguration;
	}

	/**
	 * List all metadata templates available as 'public' templates
	 *
	 * @return <code>List</code> of {@link MetadataTemplate}
	 * @throws MetadataTemplateProcessingException
	 */
	public abstract List<MetadataTemplate> listPublicTemplates() throws MetadataTemplateProcessingException;

	/**
	 * List all public and otherwise visible metadata templates based on user
	 * permissions
	 *
	 * @return <code>List</code> of {@link MetadataTemplate}
	 * @throws MetadataTemplateProcessingException
	 */
	public abstract List<MetadataTemplate> listAvailableTemplates() throws MetadataTemplateProcessingException;

	/**
	 * List all metadata templates that have been linked to this path, either
	 * directly, or via a parent collection
	 *
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the iRODS absolute path for which
	 *            bound templates are associated
	 * @return <code>List</code> of {@link MetadataTemplate} with bound
	 *         templates
	 * @throws MetadataTemplateProcessingException
	 * @throws FileNotFoundException
	 */
	public abstract List<MetadataTemplate> listAllTemplatesBoundToPath(final String irodsAbsolutePath)
			throws MetadataTemplateProcessingException, FileNotFoundException;

	/**
	 * Find a template by name (it may not be unique) of a given type, based on
	 * the location (public, user, etc)
	 *
	 * @param name
	 *            <code>String</code> with the name of the template
	 * @param metadataTemplateLocationTypeEnum
	 *            {@link MetadataTemplateLocationTypeEnum} describing the
	 *            location to look for the template. If null, it will find all
	 *            templates
	 * @return <code>List</code> of {@link MetadataTemplate} with the templates
	 *         that match that name
	 * @throws MetadataTemplateProcessingException
	 * @throws MetadataTemplateParsingException
	 */
	public abstract List<MetadataTemplate> findTemplateByName(String name,
			MetadataTemplateLocationTypeEnum metadataTemplateLocationTypeEnum)
			throws MetadataTemplateProcessingException, MetadataTemplateParsingException;

	/**
	 *
	 * @param uuid
	 *            <code>String</code> form of a unique UUID that is associated
	 *            with a metadata template
	 * @return {@link MetadataTemplate} that matches the uuid
	 * @throws MetadataTemplateNotFoundException
	 *             if the metadata template cannot be found
	 * @throws MetadataTemplateProcessingException
	 * @throws MetadataTemplateParsingException
	 */
	public abstract MetadataTemplate findTemplateByUUID(final String uuid) throws MetadataTemplateNotFoundException,
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
	 * @throws MetadataTemplateProcessingException
	 */
	public abstract boolean deleteTemplate(String uuid) throws MetadataTemplateProcessingException;

	/**
	 * Bind the given metadata template to the given path, and its children
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> which is the iRODS path to which the
	 *            template will be bound
	 * @param templateUuid
	 *            <code>String</code> representation of the UUID
	 * @param required
	 *            <code>boolean</code> that indicates the template will be
	 *            required
	 * @throws FileNotFoundException
	 * @throws MetadataTemplateProcessingException
	 */
	public abstract void bindTemplateToPath(final String irodsAbsolutePath, final String templateUuid,
			final boolean required) throws FileNotFoundException, MetadataTemplateProcessingException;

	/**
	 * Unbind the given metadata template from the given path
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> which is the iRODS path from which the
	 *            template will be unbound
	 * @param templateUuid
	 *            <code>String</code> representation of the UUID
	 * @throws FileNotFoundException
	 * @throws MetadataTemplateProcessingException
	 */
	public abstract void unbindTemplate(final String irodsAbsolutePath, final String templateUuid)
			throws FileNotFoundException, MetadataTemplateProcessingException;

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
