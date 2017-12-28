package org.irods.jargon.extensions.dataprofiler;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;

/**
 * Defines contracts for a service that can provide summary profiles of the
 * demographics of a given file or collection.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public abstract class DataProfileService extends AbstractJargonService {

	private final DataProfilerSettings defaultDataProfilerSettings;
	private final DataTypeResolutionService dataTypeResolutionService;

	/**
	 * Default constructor with necessary values
	 * 
	 * @param defaultDataProfilerSettings
	 *            {@link DataProfilerSettings} that describes overridable defaults
	 *            for the operation of the service
	 * @param dataTypeResolutionService
	 *            {@link DataTypeResolutionService} that provides mime and info type
	 *            information
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public DataProfileService(DataProfilerSettings defaultDataProfilerSettings,
			DataTypeResolutionService dataTypeResolutionService, IRODSAccessObjectFactory irodsAccessObjectFactory,
			IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
		this.defaultDataProfilerSettings = defaultDataProfilerSettings;
		this.dataTypeResolutionService = dataTypeResolutionService;
	}

	/**
	 * Retrieve the data profile information appropriate to the given path, it can
	 * return a {@link DataProfile} with a {@link Collection{ or one with a
	 * {@link DataObject}
	 * 
	 * @param irodsAbsolutePath
	 *            irodsAbsolutePath <code>String</code> with the path to the data
	 *            object
	 * @return {@link DataProfile} describing the collection or data object at that
	 *         path
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	@SuppressWarnings("rawtypes")
	public abstract DataProfile retrieveDataProfile(final String irodsAbsolutePath)
			throws DataNotFoundException, JargonException;

	/**
	 * Retrieve the data profile information appropriate to the given path, it can
	 * return a {@link DataProfile} with a {@link Collection{ or one with a
	 * {@link DataObject}
	 * 
	 * @param irodsAbsolutePath
	 *            irodsAbsolutePath <code>String</code> with the path to the data
	 *            object
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that override the default behaviors
	 * @return {@link DataProfile} describing the collection or data object at that
	 *         path
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	@SuppressWarnings("rawtypes")
	public abstract DataProfile retrieveDataProfile(final String irodsAbsolutePath,
			final DataProfilerSettings dataProfilerSettings) throws DataNotFoundException, JargonException;

	/**
	 * Retrieve a profile of a collection (folder) given a path, taking the default
	 * settings
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the path to the data object
	 * @return {@link DataProfile} describing the collection at that path
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	public abstract DataProfile<Collection> retrieveDataProfileForCollection(final String irodsAbsolutePath)
			throws DataNotFoundException, JargonException;

	/**
	 * Retrieve a profile of a collection (folder) given a path, taking the default
	 * settings
	 * 
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the path to the collection
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that override the default behaviors
	 * @return {@link DataProfile} describing the collection at that path
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	public abstract DataProfile<Collection> retrieveDataProfileForCollection(final String irodsAbsolutePath,
			final DataProfilerSettings dataProfilerSettings) throws DataNotFoundException, JargonException;

	/**
	 * Retrieve a profile of a data object (file) given a path, taking the default
	 * settings
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the path to the data object
	 * @return {@link DataProfile} describing the data object at that path
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	public abstract DataProfile<DataObject> retrieveDataProfileForDataObject(final String irodsAbsolutePath)
			throws DataNotFoundException, JargonException;

	/**
	 * Retrieve a profile of a data object (file) given a path, taking the default
	 * settings
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the path to the data object
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that override the default behaviors
	 * @return {@link DataProfile} describing the data object at that path
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	public abstract DataProfile<DataObject> retrieveDataProfileForDataObject(final String irodsAbsolutePath,
			final DataProfilerSettings dataProfilerSettings) throws DataNotFoundException, JargonException;

	public DataProfilerSettings getDefaultDataProfilerSettings() {
		return defaultDataProfilerSettings;
	}

	public DataTypeResolutionService getDataTypeResolutionService() {
		return dataTypeResolutionService;
	}

}