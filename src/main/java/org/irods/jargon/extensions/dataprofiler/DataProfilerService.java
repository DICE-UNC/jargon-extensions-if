package org.irods.jargon.extensions.dataprofiler;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.CollectionAndDataObjectListAndSearchAO;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.pub.domain.ObjStat;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.core.utils.CollectionAndPath;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.extensions.datatyper.DataType;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines contracts for a service that can provide summary profiles of the
 * demographics of a given file or collection.
 * <p/>
 * Note the abstract methods that subclasses must implement (and this is
 * provisional and will see expansion both in the services and the data in the
 * {@link DataProfile} as use cases emerge, and that these subclasses can by
 * default be noop.
 * <p/>
 * Note that the scope of the profile gathering is set by the
 * {@link DataProfileSettings} as a default that can be subsequently altered in
 * per-method settings passed to methods.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public abstract class DataProfilerService extends AbstractJargonService {

	private final DataProfilerSettings defaultDataProfilerSettings;
	/**
	 * Expected to be provided as a dependency, this is an implementation of a
	 * {@link DataTypeResolutionService} that can determine MIME and Info types
	 */
	private DataTypeResolutionService dataTypeResolutionService;
	public static final Logger log = LoggerFactory.getLogger(DataProfilerService.class);

	/**
	 * Default constructor with necessary values
	 * 
	 * @param defaultDataProfilerSettings
	 *            {@link DataProfilerSettings} that describes overridable defaults
	 *            for the operation of the service
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public DataProfilerService(DataProfilerSettings defaultDataProfilerSettings,
			IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
		this.defaultDataProfilerSettings = defaultDataProfilerSettings;
	}

	public DataProfilerSettings getDefaultDataProfilerSettings() {
		return defaultDataProfilerSettings;
	}

	public DataTypeResolutionService getDataTypeResolutionService() {
		return dataTypeResolutionService;
	}

	/**
	 * Decorate a data object with starring and favorites information
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
	protected abstract void addStarringDataToDataObject(DataProfile<DataObject> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a collection with starring and favorites information
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
	protected abstract void addStarringDataToCollection(DataProfile<Collection> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a data object with tagging and comments
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
	protected abstract void addTaggingAndCommentsToDataObject(DataProfile<DataObject> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a collection with tagging and comments
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
	protected abstract void addTaggingAndCommentsToCollection(DataProfile<Collection> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a data object with sharing information
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
	protected abstract void addSharingToDataObject(DataProfile<DataObject> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a collection with sharing information
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
	protected abstract void addSharingToCollection(DataProfile<Collection> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a data object with ticket information
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
	protected abstract void addTicketsToDataObject(DataProfile<DataObject> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a collection with ticket information
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
	protected abstract void addTicketsToCollection(DataProfile<Collection> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a data object with metadata template information
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
	protected abstract void addMetadataTemplatesToDataObject(DataProfile<DataObject> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

	/**
	 * Decorate a collection with metadata template information
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
	protected abstract void addMetadataTemplatesToCollection(DataProfile<Collection> dataProfile,
			DataProfilerSettings dataProfilerSettings) throws JargonException;

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

	public void setDataTypeResolutionService(DataTypeResolutionService dataTypeResolutionService) {
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
	public DataProfile retrieveDataProfile(String irodsAbsolutePath) throws DataNotFoundException, JargonException {
		return retrieveDataProfile(irodsAbsolutePath, this.getDefaultDataProfilerSettings());
	}

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
	public DataProfile retrieveDataProfile(String irodsAbsolutePath, DataProfilerSettings dataProfilerSettings)
			throws DataNotFoundException, JargonException {
		log.info("retrieveDataProfile()");

		if (irodsAbsolutePath == null || irodsAbsolutePath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsAbsolutePath");
		}

		if (dataProfilerSettings == null) {
			throw new IllegalArgumentException("null dataProfilerSettings");
		}

		log.info("irodsAbsolutePath:{}", irodsAbsolutePath);
		log.info("dataProfilerSettings:{}", dataProfilerSettings);

		CollectionAndDataObjectListAndSearchAO collectionAndDataObjectListAndSearchAO = this
				.getIrodsAccessObjectFactory().getCollectionAndDataObjectListAndSearchAO(getIrodsAccount());
		log.info("getting objStat...");

		ObjStat objStat = collectionAndDataObjectListAndSearchAO.retrieveObjectStatForPath(irodsAbsolutePath);

		if (objStat.isSomeTypeOfCollection()) {
			return retrieveDataProfileForCollection(objStat, dataProfilerSettings);
		} else {
			return retrieveDataProfileForDataObject(objStat, dataProfilerSettings);
		}

	}

	/**
	 * Main roll-up point for data object (File) processing, this will call
	 * override-able abstract methods for particular aspects of a data profile
	 * 
	 * @param objStat
	 *            {@link ObjStat} characterizing the data object
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that can override the default
	 *            behaviors
	 * @return {@link DataProfile} of a {@link DataObject}
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	protected DataProfile<DataObject> retrieveDataProfileForDataObject(ObjStat objStat,
			DataProfilerSettings dataProfilerSettings) throws DataNotFoundException, JargonException {
		log.info("retriveDataProfileForDataObject()");
		log.info("objStat:{}", objStat);
		DataObjectAO dataObjectAO = this.getIrodsAccessObjectFactory().getDataObjectAO(getIrodsAccount());
		DataObject dataObject = dataObjectAO.findByAbsolutePath(objStat.getAbsolutePath());
		log.info("got dataObject:{}", dataObject);

		DataProfile<DataObject> dataProfile = new DataProfile<DataObject>();
		dataProfile.setDomainObject(dataObject);
		dataProfile.setFile(true);
		dataProfile.setAbsolutePath(dataObject.getAbsolutePath());

		if (dataProfilerSettings.isRetrieveMetadata()) {
			log.info("get AVUs");
			dataProfile.setMetadata(dataObjectAO.findMetadataValuesForDataObject(objStat.getAbsolutePath()));
		}

		if (dataProfilerSettings.isRetrieveAcls()) {
			log.info("get ACLs...");
			dataProfile.setAcls(dataObjectAO.listPermissionsForDataObject(objStat.getAbsolutePath()));
		}

		dataProfile.setPathComponents(MiscIRODSUtils.breakIRODSPathIntoComponents(objStat.getAbsolutePath()));
		CollectionAndPath collectionAndPath = MiscIRODSUtils
				.separateCollectionAndPathFromGivenAbsolutePath(objStat.getAbsolutePath());

		dataProfile.setParentPath(collectionAndPath.getCollectionParent());
		dataProfile.setChildName(collectionAndPath.getChildName());
		log.info("look for special attributes");

		if (dataProfilerSettings.isDetectMimeAndInfoType()) {
			log.debug("get MIME type info");
			establishDataType(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveTickets()) {
			log.debug("retrieve tickets");
			addTicketsToDataObject(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isResolveMetadataTemplates()) {
			log.debug("resolve metadata templates");
			addMetadataTemplatesToDataObject(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveShared()) {
			log.debug("retrieve sharing");
			addSharingToDataObject(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveStarred()) {
			log.debug("add starring");
			addStarringDataToDataObject(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveTagsAndComments()) {
			log.debug("tags and comments");
			addTaggingAndCommentsToDataObject(dataProfile, dataProfilerSettings);
		}

		return dataProfile;
	}

	/**
	 * Main roll-up point for collection (directory) processing, this will call
	 * override-able abstract methods for particular aspects of a data profile
	 * 
	 * @param objStat
	 *            {@link ObjStat} characterizing the data object
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} that can override the default
	 *            behaviors
	 * @return {@link DataProfile} of a {@link Collection}
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	protected DataProfile<Collection> retrieveDataProfileForCollection(ObjStat objStat,
			DataProfilerSettings dataProfilerSettings) throws DataNotFoundException, JargonException {
		log.info("retriveDataProfileForCollection()");
		log.info("objStat:{}", objStat);
		CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
		Collection collection = collectionAO.findByAbsolutePath(objStat.getAbsolutePath());
		log.info("got collection:{}", collection);

		DataProfile<Collection> dataProfile = new DataProfile<Collection>();
		dataProfile.setDomainObject(collection);
		dataProfile.setAbsolutePath(collection.getAbsolutePath());
		dataProfile.setFile(false);
		dataProfile.setPathComponents(MiscIRODSUtils.breakIRODSPathIntoComponents(objStat.getAbsolutePath()));
		CollectionAndPath collectionAndPath = MiscIRODSUtils
				.separateCollectionAndPathFromGivenAbsolutePath(objStat.getAbsolutePath());

		dataProfile.setParentPath(collectionAndPath.getCollectionParent());
		dataProfile.setChildName(collectionAndPath.getChildName());

		/*
		 * Consider how to handle this in a more generalizable way, but at least put
		 * something in the MIME type for a dir - mc
		 */
		if (dataProfilerSettings.isDetectMimeAndInfoType()) {
			DataType dataType = new DataType();
			dataType.setMimeType("text/directory");
		}

		if (dataProfilerSettings.isRetrieveMetadata()) {
			log.info("get AVUs");
			try {
				dataProfile.setMetadata(collectionAO.findMetadataValuesForCollection(objStat.getAbsolutePath()));
			} catch (JargonQueryException e) {
				log.error("query exception getting metadata", e);
				throw new JargonException(e);
			}
		}

		if (dataProfilerSettings.isRetrieveAcls()) {
			log.info("get ACLs...");
			dataProfile.setAcls(collectionAO.listPermissionsForCollection(objStat.getAbsolutePath()));
		}

		log.info("look for special metadata");

		if (dataProfilerSettings.isRetrieveTickets()) {
			log.debug("retrieve tickets");
			addTicketsToCollection(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isResolveMetadataTemplates()) {
			log.debug("resolve metadata templates");
			addMetadataTemplatesToCollection(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveShared()) {
			log.debug("retrieve sharing");
			addSharingToCollection(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveStarred()) {
			log.debug("add starring");
			addStarringDataToCollection(dataProfile, dataProfilerSettings);
		}

		if (dataProfilerSettings.isRetrieveTagsAndComments()) {
			log.debug("tags and comments");
			addTaggingAndCommentsToCollection(dataProfile, dataProfilerSettings);
		}

		return dataProfile;
	}

}