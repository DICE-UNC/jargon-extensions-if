package org.irods.jargon.extensions.dataprofiler;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.core.utils.CollectionAndPath;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public static final Logger log = LoggerFactory.getLogger(DataProfileService.class);

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

	public DataProfilerSettings getDefaultDataProfilerSettings() {
		return defaultDataProfilerSettings;
	}

	public DataTypeResolutionService getDataTypeResolutionService() {
		return dataTypeResolutionService;
	}

	/**
	 * Retrieves base information from iRODS that can be decorated with additional
	 * metadata
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the iRODS absolute path to a data object
	 *            (file)
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that will be used to influence data
	 *            gathering
	 * @return {@link DataProfile} of a {@link DataObject} that is expected to be
	 *         decorated with additional gathered information
	 * @throws JargonException
	 * @throws FileNotFoundException
	 */
	protected DataProfile<DataObject> retrieveBaseDataObjectProfile(String irodsAbsolutePath,
			DataProfilerSettings dataProfilerSettings) throws JargonException, FileNotFoundException {
		DataObjectAO dataObjectAO = this.getIrodsAccessObjectFactory().getDataObjectAO(getIrodsAccount());
		DataObject dataObject = dataObjectAO.findByAbsolutePath(irodsAbsolutePath);
		log.info("got dataObject:{}", dataObject);

		DataProfile<DataObject> dataProfile = new DataProfile<DataObject>();
		dataProfile.setDomainObject(dataObject);
		dataProfile.setFile(true);

		if (dataProfilerSettings.isRetrieveMetadata()) {
			log.info("get AVUs");
			dataProfile.setMetadata(dataObjectAO.findMetadataValuesForDataObject(irodsAbsolutePath));
		}

		if (dataProfilerSettings.isRetrieveAcls()) {
			log.info("get ACLs...");
			dataProfile.setAcls(dataObjectAO.listPermissionsForDataObject(irodsAbsolutePath));
		}

		dataProfile.setPathComponents(MiscIRODSUtils.breakIRODSPathIntoComponents(irodsAbsolutePath));
		CollectionAndPath collectionAndPath = MiscIRODSUtils
				.separateCollectionAndPathFromGivenAbsolutePath(irodsAbsolutePath);

		dataProfile.setParentPath(collectionAndPath.getCollectionParent());
		dataProfile.setChildName(collectionAndPath.getChildName());
		return dataProfile;
	}

	/**
	 * Establish the MIME/InfoType information for a given data object. NB that this
	 * method will have only been invoked if the <code>dataProfilerSettings</code>
	 * requests this processing.
	 * 
	 * @param dataProfile
	 *            {@link DataProfile} to decorate with data type information. The
	 *            method will alter the passed in parameter object with the new
	 *            data.
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that will be used to influence data
	 *            gathering. If left <code>null</code> will use the default
	 *            settings.
	 * @throws JargonException
	 */
	protected void establishDataType(final DataProfile<DataObject> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException {

		log.info("establishDataType()");

		if (dataProfile == null) {
			throw new IllegalArgumentException("null dataProfile");
		}

		if (!dataProfile.isFile()) {
			throw new IllegalArgumentException("data type only deals with files");
		}

		if (dataProfilerSettings == null) {
			dataProfilerSettings = this.defaultDataProfilerSettings;
		}

		dataProfile.setDataType(
				this.getDataTypeResolutionService().resolveDataType(dataProfile.getDomainObject().getAbsolutePath()));
		log.info("finished! Data profile has been decorated with data type information");

	}

}