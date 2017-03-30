package org.irods.jargon.metadatatemplate;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.dataprofile.accessor.AccessorValuesEnum;
import org.irods.jargon.dataprofile.accessor.exception.ObjectNotFoundException;

/**
 *
 * Provides the methods to validate a <code>MetadataElement</code> or a
 * <code>MetadataTemplate</code>.
 * <p/>
 * Defined as a singleton because there should never be a need for multiple
 * validators.
 * <p/>
 * Example:<br/>
 * <code>ValidatorSingleton v = ValidatorSingleton.VALIDATOR;<br/>
 * v.validate(me);<br/>
 * v.validate(mt);</code>
 *
 * @author rskarbez
 *
 */

public final class ValidatorSingleton {
	public final static ValidatorSingleton VALIDATOR = new ValidatorSingleton();

	private ValidatorSingleton() {
		// Exists only to defeat instantiation
	}

	/**
	 * Perform validation on a metadata element.
	 *
	 * @param me
	 *            {@link MetadataElement} to be validated
	 * @return {@link ValidationReturnEnum} reporting validation result
	 */
	public ValidationReturnEnum validate(final IRODSAccount irodsAccount,
			final IRODSAccessObjectFactory irodsAccessObjectFactory, final MetadataElement me) {
		if (me.getCurrentValue().isEmpty()) {
			if (me.isRequired()) {
				return ValidationReturnEnum.VALUE_IS_REQUIRED;
			} else {
				return ValidationReturnEnum.NOT_VALIDATED;
			}
		}

		// Previous stanza checked for non-empty, this checks for non-blank
		if (me.isRequired()) {
			boolean nonBlankValueFound = false;
			for (String s : me.getCurrentValue()) {
				if (!s.isEmpty()) {
					nonBlankValueFound = true;
					break;
				}
			}
			if (!nonBlankValueFound) {
				return ValidationReturnEnum.VALUE_IS_REQUIRED;
			}
		}

		if (me.getValidationStyle() == ValidationStyleEnum.DO_NOT_VALIDATE) {
			return ValidationReturnEnum.NOT_VALIDATED;
		}

		// Default behavior is to validate type only
		if (me.getValidationStyle() == ValidationStyleEnum.DEFAULT) {
			if (me.getType() == ElementTypeEnum.RAW_STRING || me.getType() == ElementTypeEnum.RAW_TEXT
					|| me.getType() == ElementTypeEnum.RAW_URL || me.getType() == ElementTypeEnum.REF_IRODS_CATALOG
					|| me.getType() == ElementTypeEnum.REF_IRODS_QUERY || me.getType() == ElementTypeEnum.REF_URL) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.RAW_INT) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				try {
					Integer.parseInt(me.getCurrentValue().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.RAW_FLOAT) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				try {
					Float.parseFloat(me.getCurrentValue().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.RAW_BOOLEAN) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				if (me.getCurrentValue().get(0).equalsIgnoreCase("true")
						|| me.getCurrentValue().get(0).equalsIgnoreCase("false")
						|| me.getCurrentValue().get(0).equalsIgnoreCase("0")
						|| me.getCurrentValue().get(0).equalsIgnoreCase("1")) {
					return ValidationReturnEnum.SUCCESS;
				} else {
					return ValidationReturnEnum.BAD_TYPE;
				}
			}

			if (me.getType() == ElementTypeEnum.RAW_DATE) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				try {
					LocalDate.parse(me.getCurrentValue().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.RAW_TIME) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				try {
					LocalTime.parse(me.getCurrentValue().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.RAW_DATETIME) {
				if (me.getCurrentValue().size() > 1) {
					return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
				}

				try {
					LocalDateTime.parse(me.getCurrentValue().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.LIST_STRING) {
				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.LIST_INT) {
				try {
					for (String s : me.getCurrentValue()) {
						Integer.parseInt(s);
					}
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}

			if (me.getType() == ElementTypeEnum.LIST_FLOAT) {
				try {
					for (String s : me.getCurrentValue()) {
						Float.parseFloat(s);
					}
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				return ValidationReturnEnum.SUCCESS;
			}
		}

		if (me.getValidationStyle() == ValidationStyleEnum.IS) {
			try {
				if (me.getValidationOptions().size() != me.getCurrentValue().size()) {
					return ValidationReturnEnum.VALUE_NOT_EQUAL;
				}

				for (int i = 0; i < me.getValidationOptions().size(); ++i) {
					if (!me.getValidationOptions().get(i).equalsIgnoreCase(me.getCurrentValue().get(i))) {
						return ValidationReturnEnum.VALUE_NOT_EQUAL;
					}
				}
			} catch (Exception e) {
				return ValidationReturnEnum.VALUE_NOT_EQUAL;
			}

			return ValidationReturnEnum.SUCCESS;
		}

		if (me.getValidationStyle() == ValidationStyleEnum.IN_LIST) {
			for (String s1 : me.getCurrentValue()) {
				boolean matched = false;
				for (String s2 : me.getValidationOptions()) {
					if (s1.equalsIgnoreCase(s2)) {
						matched = true;
						break;
					}
				}
				if (!matched) {
					return ValidationReturnEnum.VALUE_NOT_IN_LIST;
				}
			}

			return ValidationReturnEnum.SUCCESS;
		}

		if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE
				|| me.getValidationStyle() == ValidationStyleEnum.IN_RANGE_EXCLUSIVE) {
			/*
			 * Should be exactly two values (endpoints of range) in
			 * validationOptions
			 *
			 * But there must be AT LEAST 2. If more than 2, other values are
			 * ignored.
			 */
			if (me.getValidationOptions().size() < 2) {
				return ValidationReturnEnum.NOT_VALIDATED;
			}

			/*
			 * IN_RANGE validation only makes sense for well-ordered values
			 * (i.e. numbers and dates)
			 *
			 * Need to check type, and whether endpoints are valid, before
			 * validating.
			 */

			if (me.getType() == ElementTypeEnum.RAW_INT) {
				int curVal;
				try {
					curVal = Integer.parseInt(me.getCurrentValue().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				int minVal;
				try {
					minVal = Integer.parseInt(me.getValidationOptions().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				int maxVal;
				try {
					maxVal = Integer.parseInt(me.getValidationOptions().get(1));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				// Be lenient with the order
				if (minVal > maxVal) {
					int temp = minVal;
					minVal = maxVal;
					maxVal = temp;
				}

				if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE) {
					if (curVal >= minVal && curVal <= maxVal) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				} else {
					// Already checked style was either IN_RANGE or
					// IN_RANGE_EXCLUSIVE
					if (curVal > minVal && curVal < maxVal) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				}
			}

			if (me.getType() == ElementTypeEnum.RAW_FLOAT) {
				float curVal;
				try {
					curVal = Float.parseFloat(me.getCurrentValue().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				float minVal;
				try {
					minVal = Float.parseFloat(me.getValidationOptions().get(0));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				float maxVal;
				try {
					maxVal = Float.parseFloat(me.getValidationOptions().get(1));
				} catch (NumberFormatException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				// Be lenient with the order
				if (minVal > maxVal) {
					float temp = minVal;
					minVal = maxVal;
					maxVal = temp;
				}

				if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE) {
					if (curVal >= minVal && curVal <= maxVal) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				} else {
					// Already checked style was either IN_RANGE or
					// IN_RANGE_EXCLUSIVE
					if (curVal > minVal && curVal < maxVal) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				}
			}

			if (me.getType() == ElementTypeEnum.RAW_DATE) {
				LocalDate curDate;
				try {
					curDate = LocalDate.parse(me.getCurrentValue().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				LocalDate minDate;
				try {
					minDate = LocalDate.parse(me.getValidationOptions().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				LocalDate maxDate;
				try {
					maxDate = LocalDate.parse(me.getValidationOptions().get(1));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				// Be lenient with the order
				if (minDate.isAfter(maxDate)) {
					LocalDate temp = minDate;
					minDate = maxDate;
					maxDate = temp;
				}

				if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE) {
					if ((curDate.isAfter(minDate) || curDate.isEqual(minDate))
							&& (curDate.isBefore(maxDate) || curDate.isEqual(maxDate))) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				} else {
					// Already checked style was either IN_RANGE or
					// IN_RANGE_EXCLUSIVE
					if (curDate.isAfter(minDate) && curDate.isBefore(maxDate)) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
					}
				}
			}

			if (me.getType() == ElementTypeEnum.RAW_TIME) {
				LocalTime curTime;
				try {
					curTime = LocalTime.parse(me.getCurrentValue().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				LocalTime minTime;
				try {
					minTime = LocalTime.parse(me.getValidationOptions().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				LocalTime maxTime;
				try {
					maxTime = LocalTime.parse(me.getValidationOptions().get(1));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				// Be lenient with the order
				if (minTime.isAfter(maxTime)) {
					LocalTime temp = minTime;
					minTime = maxTime;
					maxTime = temp;
				}

				// LocalTime objects do no have an isEqual operation;
				// therefore, IN_RANGE and IN_RANGE_EXCLUSIVE have the same
				// functionality.
				if (curTime.isAfter(minTime) && curTime.isBefore(maxTime)) {
					return ValidationReturnEnum.SUCCESS;
				} else {
					return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
				}
			}

			if (me.getType() == ElementTypeEnum.RAW_DATETIME) {
				LocalDateTime curDateTime;
				try {
					curDateTime = LocalDateTime.parse(me.getCurrentValue().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.BAD_TYPE;
				}

				LocalDateTime minDateTime;
				try {
					minDateTime = LocalDateTime.parse(me.getValidationOptions().get(0));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				LocalDateTime maxDateTime;
				try {
					maxDateTime = LocalDateTime.parse(me.getValidationOptions().get(1));
				} catch (DateTimeParseException e) {
					return ValidationReturnEnum.NOT_VALIDATED;
				}

				// Be lenient with the order
				if (minDateTime.isAfter(maxDateTime)) {
					LocalDateTime temp = minDateTime;
					minDateTime = maxDateTime;
					maxDateTime = temp;
				}

				// LocalDateTime objects do no have an isEqual operation;
				// therefore, IN_RANGE and IN_RANGE_EXCLUSIVE have the same
				// functionality.
				if (curDateTime.isAfter(minDateTime) && curDateTime.isBefore(maxDateTime)) {
					return ValidationReturnEnum.SUCCESS;
				} else {
					return ValidationReturnEnum.VALUE_NOT_IN_RANGE;
				}
			}

			// All other types, IN_RANGE is ill-defined.
			return ValidationReturnEnum.NOT_VALIDATED;

		}

		if (me.getValidationStyle() == ValidationStyleEnum.REGEX) {
			if (me.getValidationOptions().size() == 0) {
				return ValidationReturnEnum.NOT_VALIDATED;
			}

			Boolean regexFailed = false;

			try {
				for (String s : me.getCurrentValue()) {
					if (!Pattern.matches(me.getValidationOptions().get(0), s)) {
						regexFailed = true;
						break;
					}
				}
			} catch (PatternSyntaxException e) {
				return ValidationReturnEnum.REGEX_SYNTAX_ERROR;
			}

			if (!regexFailed) {
				return ValidationReturnEnum.SUCCESS;
			} else {
				return ValidationReturnEnum.REGEX_FAILED;
			}
		}

		if (me.getValidationStyle() == ValidationStyleEnum.FOLLOW_REF) {
			if (me.getCurrentValue().size() > 1) {
				return ValidationReturnEnum.SINGLE_VALUE_LIST_PROVIDED;
			}

			if (me.getType() == ElementTypeEnum.REF_IRODS_QUERY) {
				try {
					AccessorValuesEnum.enumFromText(me.getCurrentValue().get(0));
				} catch (ObjectNotFoundException e) {
					return ValidationReturnEnum.BAD_REF;
				}

				return ValidationReturnEnum.SUCCESS;
			} else if (me.getType() == ElementTypeEnum.REF_IRODS_CATALOG) {
				try {
					IRODSFile retFile = irodsAccessObjectFactory.getIRODSFileFactory(irodsAccount)
							.instanceIRODSFile(me.getCurrentValue().get(0));

					if (retFile.exists()) {
						return ValidationReturnEnum.SUCCESS;
					} else {
						return ValidationReturnEnum.BAD_REF;
					}
				} catch (JargonException je) {
					// File not found
					return ValidationReturnEnum.BAD_REF;
				}
			} else if (me.getType() == ElementTypeEnum.REF_URL) {
				try {
					URL url = new URL(me.getCurrentValue().get(0));
					URLConnection connection = url.openConnection();
					connection.getInputStream();
				} catch (Exception e) {
					return ValidationReturnEnum.BAD_REF;
				}

				return ValidationReturnEnum.SUCCESS;
			} else {
				/*
				 * FOLLOW_REF is only applicable for reference-type
				 * MetadataElements. If used with a raw-type element, return
				 * NOT_VALIDATED
				 */
				return ValidationReturnEnum.NOT_VALIDATED;
			}
		}

		// Failsafe
		return ValidationReturnEnum.NOT_VALIDATED;
	}

	/**
	 * Perform validation on all metadata elements in a template
	 *
	 * @param mt
	 *            {@link FormBasedMetadataTemplate} to be validated
	 * @return List<{@link ValidationReturnEnum}> reporting validation results
	 */

	public List<ValidationReturnEnum> validate(final IRODSAccount irodsAccount,
			final IRODSAccessObjectFactory irodsAccessObjectFactory, final MetadataTemplate mt) {
		List<ValidationReturnEnum> returnList = new ArrayList<ValidationReturnEnum>();
		for (MetadataElement me : mt.getElements()) {
			returnList.add(validate(irodsAccount, irodsAccessObjectFactory, me));
		}

		return returnList;
	}
}
