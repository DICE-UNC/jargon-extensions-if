/**
 *
 */
package org.irods.jargon.vircoll;

/**
 * Describes types of collections
 *
 * @author Mike Conway - DICE
 *
 */
public enum CollectionTypes {
	USER_HOME("USER_HOME"), TEMPORARY_QUERY("TEMPORARY_QUERY"), USER_HIDDEN("USER_HIDDEN"), SHARED("SHARED");

	private String textValue;

	CollectionTypes(final String textValue) {
		this.textValue = textValue;
	}

	public String getTextValue() {
		return textValue;
	}

	/**
	 * Find the matching collection type based on a string
	 *
	 * @param textValue
	 * @return
	 */
	public static CollectionTypes findTypeByString(final String textValue) {
		CollectionTypes collectionType = null;
		for (CollectionTypes collectionTypeEnum : CollectionTypes.values()) {
			if (collectionTypeEnum.textValue.equals(textValue)) {
				collectionType = collectionTypeEnum;
				break;
			}
		}

		if (collectionType == null) {
			throw new IllegalArgumentException("unknown collection type");
		}

		return collectionType;

	}

}
