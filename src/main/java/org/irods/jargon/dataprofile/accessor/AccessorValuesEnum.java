/**
 * 
 */
package org.irods.jargon.dataprofile.accessor;

import org.irods.jargon.dataprofile.accessor.exception.ObjectNotFoundException;

/**
 * Universe of allowed accessor values
 * 
 * @author Mike Conway - DICE
 *
 */
public enum AccessorValuesEnum {

	DATA_ID("data.id", From.DATA_OBJECT), DATA_COLL_ID("data.coll_id",
			From.DATA_OBJECT), DATA_NAME("data.name", From.DATA_OBJECT), DATA_COLL_NAME(
			"data.coll_name", From.DATA_OBJECT), DATA_REPL_NUM("data.repl_num",
			From.DATA_OBJECT), DATA_VERSION("data.version", From.DATA_OBJECT), DATA_TYPE(
			"data.type", From.DATA_OBJECT), DATA_SIZE("data.size",
			From.DATA_OBJECT), DATA_RESC_GROUP_NAME("data.resc_group_name",
			From.DATA_OBJECT), DATA_RESC_NAME("data.resc_name",
			From.DATA_OBJECT), DATA_PHYSICAL_PATH("data.physical_path",
			From.DATA_OBJECT), DATA_OWNER_NAME("data.owner_name",
			From.DATA_OBJECT), DATA_OWNER_ZONE("data.owner_zone",
			From.DATA_OBJECT), DATA_REPL_STATUS("data.repl_status",
			From.DATA_OBJECT), DATA_STATUS("data.status", From.DATA_OBJECT), DATA_CHECKSUM(
			"data.checksum", From.DATA_OBJECT), DATA_EXPIRY("data.expiry",
			From.DATA_OBJECT), DATA_MAP_ID("data.map_id", From.DATA_OBJECT), DATA_COMMENTS(
			"data.comments", From.DATA_OBJECT), DATA_TIME_CREATED(
			"data.time_created", From.DATA_OBJECT), DATA_TIME_UPDATED(
			"data.time_updated", From.DATA_OBJECT), DATA_SPEC_COLL_TYPE(
			"data.spec_coll_type", From.DATA_OBJECT), DATA_OBJECT_PATH(
			"data.canonical_path", From.DATA_OBJECT), DATA_AVU("data.avu",
			From.DATA_OBJECT), DATA_ACL("data.acl", From.DATA_OBJECT), DATA_STARRED(
			"data.starred", From.DATA_OBJECT), DATA_SHARED("data.shared",
			From.DATA_OBJECT), DATA_MIME_TYPE("data.mime_type",
			From.DATA_OBJECT), DATA_TAG("data.tag", From.DATA_OBJECT),

	COLL_ID("coll.id", From.COLLECTION), COLL_PATH("coll.path", From.COLLECTION), COLL_PARENT_PATH(
			"coll.parent_path", From.COLLECTION), COLL_OWNER("coll.owner",
			From.COLLECTION), COLL_OWNER_ZONE("coll.owner_zone",
			From.COLLECTION), COLL_MAP_ID("coll.map_id", From.COLLECTION), COLL_INHERITANCE(
			"coll.inheritance", From.COLLECTION), COLL_COMMENTS(
			"coll.comments", From.COLLECTION), COLL_INFO1("coll.info1",
			From.COLLECTION), COLL_INFO2("coll.info2", From.COLLECTION), COLL_TIME_CREATED(
			"coll.time_created", From.COLLECTION), COLL_TIME_MODIFIED(
			"coll.time_modified", From.COLLECTION), COLL_SPEC_COLL_TYPE(
			"coll.spec_coll_type", From.COLLECTION), COLL_AVU("coll.avu",
			From.COLLECTION), COLL_ACL("coll.acl", From.COLLECTION), COLL_STARRED(
			"coll.starred", From.COLLECTION), COLL_SHARED("coll.shared",
			From.COLLECTION), COLL_TAG("coll.tag", From.COLLECTION),

	USER_NAME("user.name", From.USER), USER_ID("user.id", From.USER), USER_ZONE(
			"user.zone", From.USER), USER_INFO("user.info", From.USER), USER_COMMENT(
			"user.comment", From.USER), USER_TIME_CREATED("user.time_created",
			From.USER), USER_TIME_MODIFIED("user.time_modified", From.USER), USER_TYPE(
			"user.type", From.USER), USER_DN("user.dn", From.USER), USER_AVU(
			"user.avu", From.USER);

	public enum From {
		DATA_OBJECT, COLLECTION, USER
	}

	private String textValue;
	private From from;

	/**
	 * @param textValue
	 * @param from
	 */
	private AccessorValuesEnum(String textValue, From from) {
		this.textValue = textValue;
		this.from = from;
	}

	/**
	 * Helper method to resolve text value
	 * 
	 * @param textValue
	 *            <code>String</code> with text representation
	 * @return {@link AccessorValuesEnum}
	 * @throws ObjectNotFoundException
	 *             if no match found
	 */
	public static AccessorValuesEnum enumFromText(final String textValue)
			throws ObjectNotFoundException {

		AccessorValuesEnum foundValue = null;
		for (AccessorValuesEnum val : AccessorValuesEnum.values()) {
			if (textValue.equalsIgnoreCase(val.getTextValue())) {
				foundValue = val;
				break;
			}
		}

		if (foundValue == null) {
			// Only check for prefix match if there was not a full-term match
			for (AccessorValuesEnum val : AccessorValuesEnum.values()) {
				if (textValue.startsWith(val.getTextValue())) {
					foundValue = val;
					break;
				}
			}
		}

		if (foundValue == null) {
			throw new ObjectNotFoundException("could not find value for:"
					+ textValue);
		}

		return foundValue;
	}

	/**
	 * @return the textValue
	 */
	public String getTextValue() {
		return textValue;
	}

	/**
	 * @return the from
	 */
	public From getFrom() {
		return from;
	}

}
