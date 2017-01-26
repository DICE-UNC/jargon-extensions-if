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

	DATA_SIZE("data.size", From.DATA_OBJECT);

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
			if (val.getTextValue().equals(textValue)) {
				foundValue = val;
				break;
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
