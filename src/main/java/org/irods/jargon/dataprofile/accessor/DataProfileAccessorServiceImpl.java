package org.irods.jargon.dataprofile.accessor;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.UserAO;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.pub.domain.ObjStat.SpecColType;
import org.irods.jargon.core.pub.domain.User;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.dataprofile.DataProfile;
import org.irods.jargon.dataprofile.DataProfileService;
import org.irods.jargon.dataprofile.DataProfileServiceFactoryImpl;
import org.irods.jargon.dataprofile.accessor.exception.AttributeNotFoundException;
import org.irods.jargon.dataprofile.accessor.exception.ObjectNotFoundException;
import org.irods.jargon.dataprofile.accessor.exception.WrongDataProfileTypeException;

public class DataProfileAccessorServiceImpl implements
		DataProfileAccessorService {

	@SuppressWarnings("rawtypes")
	DataProfile dataProfile;
	User userProfile;

	String irodsUserName;
	String irodsAbsolutePath;

	public DataProfileAccessorServiceImpl(IRODSAccount irodsAccount,
			IRODSAccessObjectFactory irodsAccessObjectFactory,
			String irodsAbsolutePath) throws FileNotFoundException,
			JargonException {
		UserAO userAO = irodsAccessObjectFactory.getUserAO(irodsAccount);
		userProfile = userAO.findByName(irodsAccount.getUserName());
		this.setIrodsUserName(irodsAccount.getUserName());

		DataProfileService dataProfileService = new DataProfileServiceFactoryImpl(
				irodsAccessObjectFactory, irodsAccount)
				.instanceDataProfileService();
		dataProfile = dataProfileService.retrieveDataProfile(irodsAbsolutePath);
		this.setIrodsAbsolutePath(irodsAbsolutePath);
	}

	@Override
	public String retrieveValueFromKey(String accessorTextValue)
			throws ObjectNotFoundException, WrongDataProfileTypeException,
			AttributeNotFoundException {
		// AccessorValuesEnum.enumFromText throws ObjectNotFoundException
		AccessorValuesEnum enumValue = AccessorValuesEnum
				.enumFromText(accessorTextValue);

		String returnValue = null;

		switch (enumValue) {
		case DATA_ID:
			if (dataProfile.isFile())
				returnValue = Integer.toString(((DataObject) dataProfile
						.getDomainObject()).getId());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_ID, DataProfile contains Collection");
			break;
		case DATA_COLL_ID:
			if (dataProfile.isFile())
				returnValue = Integer.toString(((DataObject) dataProfile
						.getDomainObject()).getCollectionId());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_COLL_ID, DataProfile contains Collection");
			break;
		case DATA_NAME:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getDataName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_NAME, DataProfile contains Collection");
			break;
		case DATA_COLL_NAME:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getCollectionName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_COLL_NAME, DataProfile contains Collection");
			break;
		case DATA_REPL_NUM:
			if (dataProfile.isFile())
				returnValue = Integer.toString(((DataObject) dataProfile
						.getDomainObject()).getDataReplicationNumber());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_REPL_NUM, DataProfile contains Collection");
			break;
		case DATA_VERSION:
			if (dataProfile.isFile())
				returnValue = Integer.toString(((DataObject) dataProfile
						.getDomainObject()).getDataVersion());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_VERSION, DataProfile contains Collection");
			break;
		case DATA_TYPE:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getDataTypeName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_TYPE, DataProfile contains Collection");
			break;
		case DATA_SIZE:
			if (dataProfile.isFile())
				returnValue = Long.toString(((DataObject) dataProfile
						.getDomainObject()).getDataSize());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_SIZE, DataProfile contains Collection");
			break;
		case DATA_RESC_GROUP_NAME:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getResourceGroupName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_RESC_GROUP_NAME, DataProfile contains Collection");
			break;
		case DATA_RESC_NAME:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getResourceName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_RESC_NAME, DataProfile contains Collection");
			break;
		case DATA_PHYSICAL_PATH:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getDataPath();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_PHYSICAL_PATH, DataProfile contains Collection");
			break;
		case DATA_OWNER_NAME:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getDataOwnerName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_OWNER_NAME, DataProfile contains Collection");
			break;
		case DATA_OWNER_ZONE:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getDataOwnerZone();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_OWNER_ZONE, DataProfile contains Collection");
			break;
		case DATA_REPL_STATUS:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getReplicationStatus();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_REPL_STATUS, DataProfile contains Collection");
			break;
		case DATA_STATUS:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getDataStatus();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_STATUS, DataProfile contains Collection");
			break;
		case DATA_CHECKSUM:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getChecksum();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_OWNER_NAME, DataProfile contains Collection");
			break;
		case DATA_EXPIRY:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getExpiry();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_EXPIRY, DataProfile contains Collection");
			break;
		case DATA_MAP_ID:
			if (dataProfile.isFile())
				returnValue = Integer.toString(((DataObject) dataProfile
						.getDomainObject()).getDataMapId());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_MAP_ID, DataProfile contains Collection");
			break;
		case DATA_COMMENTS:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getComments();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_COMMENTS, DataProfile contains Collection");
			break;
		case DATA_TIME_CREATED:
			if (dataProfile.isFile())
				returnValue = convertDateToISO8601String(((DataObject) dataProfile
						.getDomainObject()).getCreatedAt());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_TIME_CREATED, DataProfile contains Collection");
			break;
		case DATA_TIME_UPDATED:
			if (dataProfile.isFile())
				returnValue = convertDateToISO8601String(((DataObject) dataProfile
						.getDomainObject()).getUpdatedAt());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_TIME_UPDATED, DataProfile contains Collection");
			break;
		case DATA_SPEC_COLL_TYPE:
			if (dataProfile.isFile()) {
				SpecColType coll_type = ((DataObject) dataProfile
						.getDomainObject()).getSpecColType();
				switch (coll_type) {
				case NORMAL:
					returnValue = "NORMAL";
					break;
				case STRUCT_FILE_COLL:
					returnValue = "STRUCT_FILE_COLL";
					break;
				case MOUNTED_COLL:
					returnValue = "MOUNTED_COLL";
					break;
				case LINKED_COLL:
					returnValue = "LINKED_COLL";
					break;
				default:
					break;
				}
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_SPEC_COLL_TYPE, DataProfile contains Collection");
			break;
		case DATA_OBJECT_PATH:
			if (dataProfile.isFile())
				returnValue = ((DataObject) dataProfile.getDomainObject())
						.getAbsolutePath();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_OBJECT_PATH, DataProfile contains Collection");
			break;
		case DATA_AVU:
			if (dataProfile.isFile()) {
				// 19 is from length of "data.avu.attribute." prefix
				String attributeName = accessorTextValue.substring(19);
				for (Object item : dataProfile.getMetadata()) {
					MetaDataAndDomainData mdd = (MetaDataAndDomainData) item;
					if (mdd.getAvuAttribute().equalsIgnoreCase(attributeName)) {
						returnValue = mdd.getAvuValue();
					}
				}

				if (returnValue == null) {
					throw new AttributeNotFoundException("Attribute "
							+ attributeName
							+ " was not found in the data object's metadata");
				}
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_AVU, DataProfile contains Collection");
			break;
		case DATA_ACL:
			if (dataProfile.isFile()) {
				// TODO
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_ACL, DataProfile contains Collection");
			break;
		case DATA_STARRED:
			if (dataProfile.isFile())
				returnValue = Boolean.toString(dataProfile.isStarred());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_STARRED, DataProfile contains Collection");
			break;
		case DATA_SHARED:
			if (dataProfile.isFile())
				returnValue = Boolean.toString(dataProfile.isShared());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_SHARED, DataProfile contains Collection");
			break;
		case DATA_MIME_TYPE:
			if (dataProfile.isFile())
				returnValue = dataProfile.getMimeType();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_MIME_TYPE, DataProfile contains Collection");
			break;
		case DATA_TAG:
			if (dataProfile.isFile()) {
				// TODO
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access DATA_TAG, DataProfile contains Collection");
			break;
		case COLL_ID:
			if (!dataProfile.isFile())
				returnValue = Integer.toString(((Collection) dataProfile
						.getDomainObject()).getCollectionId());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_ID, DataProfile contains DataObject");
			break;
		case COLL_PATH:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getAbsolutePath();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_PATH, DataProfile contains DataObject");
			break;
		case COLL_PARENT_PATH:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getCollectionParentName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_PARENT_PATH, DataProfile contains DataObject");
			break;
		case COLL_OWNER:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getCollectionOwnerName();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_OWNER, DataProfile contains DataObject");
			break;
		case COLL_OWNER_ZONE:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getCollectionOwnerZone();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_OWNER_ZONE, DataProfile contains DataObject");
			break;
		case COLL_MAP_ID:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getCollectionMapId();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_OWNER, DataProfile contains DataObject");
			break;
		case COLL_INHERITANCE:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getCollectionInheritance();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_INHERITANCE, DataProfile contains DataObject");
			break;
		case COLL_COMMENTS:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getComments();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_COMMENTS, DataProfile contains DataObject");
			break;
		case COLL_INFO1:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getInfo1();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_INFO1, DataProfile contains DataObject");
			break;
		case COLL_INFO2:
			if (!dataProfile.isFile())
				returnValue = ((Collection) dataProfile.getDomainObject())
						.getInfo2();
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_INFO2, DataProfile contains DataObject");
			break;
		case COLL_TIME_CREATED:
			if (!dataProfile.isFile())
				returnValue = convertDateToISO8601String(((Collection) dataProfile
						.getDomainObject()).getCreatedAt());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_TIME_CREATED, DataProfile contains DataObject");
			break;
		case COLL_TIME_MODIFIED:
			if (!dataProfile.isFile())
				returnValue = convertDateToISO8601String(((Collection) dataProfile
						.getDomainObject()).getModifiedAt());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_TIME_MODIFIED, DataProfile contains DataObject");
			break;
		case COLL_SPEC_COLL_TYPE:
			if (!dataProfile.isFile()) {
				SpecColType coll_type = ((Collection) dataProfile
						.getDomainObject()).getSpecColType();
				switch (coll_type) {
				case NORMAL:
					returnValue = "NORMAL";
					break;
				case STRUCT_FILE_COLL:
					returnValue = "STRUCT_FILE_COLL";
					break;
				case MOUNTED_COLL:
					returnValue = "MOUNTED_COLL";
					break;
				case LINKED_COLL:
					returnValue = "LINKED_COLL";
					break;
				default:
					break;
				}
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_SPEC_COLL_TYPE, DataProfile contains DataObject");
			break;
		case COLL_AVU:
			if (!dataProfile.isFile()) {
				// 19 is from length of "coll.avu.attribute." prefix
				String attributeName = accessorTextValue.substring(19);
				for (Object item : dataProfile.getMetadata()) {
					MetaDataAndDomainData mdd = (MetaDataAndDomainData) item;
					if (mdd.getAvuAttribute().equalsIgnoreCase(attributeName)) {
						returnValue = mdd.getAvuValue();
					}
				}

				if (returnValue == null) {
					throw new AttributeNotFoundException("Attribute "
							+ attributeName
							+ " was not found in the collection's metadata");
				}
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_AVU, DataProfile contains DataObject");
			break;
		case COLL_ACL:
			if (!dataProfile.isFile()) {
				// TODO
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_ACL, DataProfile contains DataObject");
			break;
		case COLL_STARRED:
			if (!dataProfile.isFile())
				returnValue = Boolean.toString(dataProfile.isStarred());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_STARRED, DataProfile contains DataObject");
			break;
		case COLL_SHARED:
			if (!dataProfile.isFile())
				returnValue = Boolean.toString(dataProfile.isShared());
			else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_SHARED, DataProfile contains DataObject");
			break;
		case COLL_TAG:
			if (!dataProfile.isFile()) {
				// TODO
			} else
				throw new WrongDataProfileTypeException(
						"Tried to access COLL_TAG, DataProfile contains DataObject");
			break;
		case USER_NAME:
			returnValue = userProfile.getName();
			break;
		case USER_ID:
			returnValue = userProfile.getId();
			break;
		case USER_ZONE:
			returnValue = userProfile.getZone();
			break;
		case USER_INFO:
			returnValue = userProfile.getInfo();
			break;
		case USER_COMMENT:
			returnValue = userProfile.getComment();
			break;
		case USER_TIME_CREATED:
			returnValue = convertDateToISO8601String(userProfile
					.getCreateTime());
			break;
		case USER_TIME_MODIFIED:
			returnValue = convertDateToISO8601String(userProfile
					.getModifyTime());
			break;
		case USER_TYPE:
			returnValue = userProfile.getUserType().getTextValue();
			break;
		case USER_DN:
			returnValue = userProfile.getUserDN();
			break;
		case USER_AVU:
			// TODO
			break;
		default:
			break;
		}

		return returnValue;
	}

	private String convertDateToISO8601String(Date inDate) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
				.withZone(ZoneId.systemDefault()).format(inDate.toInstant());
	}

	public String getIrodsUserName() {
		return irodsUserName;
	}

	private void setIrodsUserName(String irodsUserName) {
		this.irodsUserName = irodsUserName;
	}

	public String getIrodsAbsolutePath() {
		return irodsAbsolutePath;
	}

	private void setIrodsAbsolutePath(String irodsAbsolutePath) {
		this.irodsAbsolutePath = irodsAbsolutePath;
	}
}
