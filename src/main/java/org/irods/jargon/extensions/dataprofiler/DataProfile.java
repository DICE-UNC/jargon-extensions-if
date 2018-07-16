/**
 * 
 */
package org.irods.jargon.extensions.dataprofiler;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.pub.domain.IRODSDomainObject;
import org.irods.jargon.core.pub.domain.UserFilePermission;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.extensions.datatyper.DataType;
import org.irods.jargon.usertagging.domain.IRODSTagValue;


/**
 * Represents a consolidated summary of a data object or collection as a simple
 * POJO
 * 
 * @author Mike Conway - DICE
 *
 */
public class DataProfile<T extends IRODSDomainObject> {

	private boolean file = false;
	/**
	 * The full iRODS absolute path
	 */
	private String absolutePath = "";
	private T domainObject;
	private List<MetaDataAndDomainData> metadata = new ArrayList<MetaDataAndDomainData>();
	private List<UserFilePermission> acls = new ArrayList<UserFilePermission>();
	/**
	 * Resolved {@link MetadataTemplate} associated with object
	 */
	//private List<Template> metadataTemplates = new ArrayList<Template>();
	private boolean starred = false;
	private boolean shared = false;
	private boolean hasTicket = false;
	/**
	 * Characterizes the mime and info type of this file
	 */
	private DataType dataType;

	/**
	 * Represents free tags for this file
	 */
	private List<IRODSTagValue> irodsTagValues = new ArrayList<IRODSTagValue>();

	/**
	 * parent of the current data object
	 */
	private String parentPath = "";
	/**
	 * child name (last path component)
	 */
	private String childName = "";
	/**
	 * List of path components suitable for generating breadcrumbs and the like
	 */
	private List<String> pathComponents = null;

	public void setDomainObject(T domainObject) {
		this.domainObject = domainObject;
	}

	public T getDomainObject() {
		return domainObject;
	}

	public DataProfile() {
	}

	public List<MetaDataAndDomainData> getMetadata() {
		return metadata;
	}

	public List<UserFilePermission> getAcls() {
		return acls;
	}

	public boolean isStarred() {
		return starred;
	}

	public boolean isShared() {
		return shared;
	}

	public void setMetadata(List<MetaDataAndDomainData> metadata) {
		this.metadata = metadata;
	}

	public void setAcls(List<UserFilePermission> acls) {
		this.acls = acls;
	}

	public void setStarred(boolean isStarred) {
		this.starred = isStarred;
	}

	public void setShared(boolean isShared) {
		this.shared = isShared;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public List<IRODSTagValue> getIrodsTagValues() {
		return irodsTagValues;
	}

	public void setIrodsTagValues(List<IRODSTagValue> irodsTagValues) {
		this.irodsTagValues = irodsTagValues;
	}

	/**
	 * @return the parentPath
	 */
	public String getParentPath() {
		return parentPath;
	}

	/**
	 * @param parentPath
	 *            the parentPath to set
	 */
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	/**
	 * @return the childName
	 */
	public String getChildName() {
		return childName;
	}

	/**
	 * @param childName
	 *            the childName to set
	 */
	public void setChildName(String childName) {
		this.childName = childName;
	}

	/**
	 * @return the pathComponents
	 */
	public List<String> getPathComponents() {
		return pathComponents;
	}

	/**
	 * @param pathComponents
	 *            the pathComponents to set
	 */
	public void setPathComponents(List<String> pathComponents) {
		this.pathComponents = pathComponents;
	}

	public boolean isHasTicket() {
		return hasTicket;
	}

	public void setHasTicket(boolean hasTicket) {
		this.hasTicket = hasTicket;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("DataProfile [file=").append(file).append(", ");
		if (absolutePath != null) {
			builder.append("absolutePath=").append(absolutePath).append(", ");
		}
		if (domainObject != null) {
			builder.append("domainObject=").append(domainObject).append(", ");
		}
		if (metadata != null) {
			builder.append("metadata=").append(metadata.subList(0, Math.min(metadata.size(), maxLen))).append(", ");
		}
		if (acls != null) {
			builder.append("acls=").append(acls.subList(0, Math.min(acls.size(), maxLen))).append(", ");
		}
		
		builder.append("starred=").append(starred).append(", shared=").append(shared).append(", hasTicket=")
				.append(hasTicket).append(", ");
		if (dataType != null) {
			builder.append("dataType=").append(dataType).append(", ");
		}
		if (irodsTagValues != null) {
			builder.append("irodsTagValues=").append(irodsTagValues.subList(0, Math.min(irodsTagValues.size(), maxLen)))
					.append(", ");
		}
		if (parentPath != null) {
			builder.append("parentPath=").append(parentPath).append(", ");
		}
		if (childName != null) {
			builder.append("childName=").append(childName).append(", ");
		}
		if (pathComponents != null) {
			builder.append("pathComponents=")
					.append(pathComponents.subList(0, Math.min(pathComponents.size(), maxLen)));
		}
		builder.append("]");
		return builder.toString();
	}

	

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

}
