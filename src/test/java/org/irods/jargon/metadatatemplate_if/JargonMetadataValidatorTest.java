package org.irods.jargon.metadatatemplate_if;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import junit.framework.Assert;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.metadatatemplate.MetadataElement;
import org.irods.jargon.metadatatemplate.ElementTypeEnum;
import org.irods.jargon.metadatatemplate.ValidationReturnEnum;
import org.irods.jargon.metadatatemplate.ValidationStyleEnum;
import org.irods.jargon.metadatatemplate.ValidatorSingleton;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class JargonMetadataValidatorTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static IRODSFileSystem irodsFileSystem;

	private static final String TEMPLATE_FILE_NAME1 = "src/test/resources/templates/test1.mdtemplate";
	private static final String TEMPLATE_FILE_NAME2 = "src/test/resources/templates/test2.mdtemplate";
	private static final String TEMPLATE_FILE_NAME3 = "src/test/resources/templates/test3.mdtemplate";
	private static final String TEST_FILE_NAME = "src/test/resources/testFile.txt";

	private static final String TEMPLATE_NOPATH1 = "test1.mdtemplate";
	private static final String TEMPLATE_NOPATH2 = "test2.mdtemplate";
	private static final String TEMPLATE_NOPATH3 = "test3.mdtemplate";
	private static final String TEST_FILE_NOPATH = "testFile.txt";

	public static final String IRODS_TEST_SUBDIR_PATH = "JargonMetadataResolverTest";
	private static org.irods.jargon.testutils.IRODSTestSetupUtilities irodsTestSetupUtilities = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
		irodsFileSystem = IRODSFileSystem.instance();
		irodsTestSetupUtilities = new org.irods.jargon.testutils.IRODSTestSetupUtilities();
		irodsTestSetupUtilities.clearIrodsScratchDirectory();
		irodsTestSetupUtilities.initializeIrodsScratchDirectory();
		irodsTestSetupUtilities
				.initializeDirectoryForTest(IRODS_TEST_SUBDIR_PATH);
	}

	@After
	public void tearDown() throws Exception {
		irodsFileSystem.closeAndEatExceptions();
	}

	@Test
	public void testRequiredElementWithEmptyValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testRequiredElement");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setRequired(true);
		me.setCurrentValue("");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue("Validator does not respect required flag",
				retVal == ValidationReturnEnum.VALUE_IS_REQUIRED);
	}

	@Test
	public void testDefaultIntWithBadValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_INT",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultIntWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("42");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultFloatWithBadValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultFloatWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("2.718281828");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultBooleanWithBadValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.setCurrentValue("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultBooleanWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.setCurrentValue("true");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultDateWithBadValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_DATE",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultDateWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-01-24");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultTimeWithBadValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_TIME",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultTimeWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("15:10");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultDateTimeWithBadValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultDateTimeWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T15:10");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsStringWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("goodString");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_STRING",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsStringWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("badString");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	
	@Test
	public void testIsIntWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("42");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("42"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsIntWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("15");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("42"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	@Test
	public void testIsFloatWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("4.2");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("4.2"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsFloatWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("1.5");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("4.2"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	@Test
	public void testIsBooleanWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.setCurrentValue("true");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("true"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsBooleanWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.setCurrentValue("false");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("true"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	@Test
	public void testIsDateWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-01-24"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsDateWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-07-04");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-01-24"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATE",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	@Test
	public void testIsTimeWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("16:22"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsTimeWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_TIME",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	@Test
	public void testIsDateTimeWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-01-24T16:22"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testIsDateTimeWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-07-04T12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}
	
	@Test
	public void testInListStringWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInListString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("goodString");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("firstString", "secondString", "goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_STRING",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInListStringWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInListString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("badString");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("firstString", "secondString", "goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}
	
	@Test
	public void testInListIntWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("42");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("10", "42", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInListIntWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("256");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("10", "42", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}
	
	@Test
	public void testInListFloatWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("4.2");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("4.2", "1.168", "2.71828", "3.14"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInListFloatWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("1.414");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("4.2", "1.168", "2.71828", "3.14"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}
	
	@Test
	public void testInListDateWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-07-04", "2017-01-24", "2017-12-25"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInListDateWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-02-28");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-07-04", "2017-01-24", "2017-12-25"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATE",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}
	
	@Test
	public void testInListTimeWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("16:22");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("16:22", "01:23", "12:00", "17:30"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInListTimeWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("16:29");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("16:22", "01:23", "12:00", "17:30"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_TIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}
	
	@Test
	public void testInListDateTimeWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-01-24T16:22", "2017-12-25T09:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInListDateTimeWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-07-04T12:00", "2017-12-25T09:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInRangeIntWithBadValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("15");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeIntWithValidValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeIntWithBadValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("15");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeIntWithValidValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeIntWithTooFewElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}
	
	@Test
	public void testInRangeIntWithBadValueThreeElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("15");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeIntWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("10");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE validation should succeed if value = range endpoint",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeExclusiveIntWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeExclusiveInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.setCurrentValue("10");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE_EXCLUSIVE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE_EXCLUSIVE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithBadValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("15.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithValidValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("5.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeFloatWithBadValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("15.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithValidValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("5.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeFloatWithTooFewElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("5.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}
	
	@Test
	public void testInRangeFloatWithBadValueThreeElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("15.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeFloatWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("10.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0.0", "10.0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE validation should succeed if value = range endpoint",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeExclusiveFloatWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeExclusiveFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.setCurrentValue("10.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE_EXCLUSIVE);
		me.setValidationOptions(Arrays.asList("0.0", "10.0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE_EXCLUSIVE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeDateWithBadValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2016-12-25");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateWithValidValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeDateWithBadValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2016-12-25");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-12-31", "2017-01-01"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateWithValidValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-12-31", "2017-01-01"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeDateWithTooFewElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}
	
	@Test
	public void testInRangeDateWithBadValueThreeElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2018-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31", "2018-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeDateWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-12-31");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE validation should succeed if value = range endpoint",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeExclusiveDateWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.setCurrentValue("2017-12-31");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE_EXCLUSIVE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE_EXCLUSIVE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeTimeWithBadValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeTimeWithValidValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("15:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeTimeWithBadValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("23:59", "12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeTimeWithValidValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("15:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("23:59", "12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeTimeWithTooFewElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("15:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}
	
	@Test
	public void testInRangeTimeWithBadValueThreeElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59", "03:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeTimeWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.setCurrentValue("23:59");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeDateTimeWithBadValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00", "2017-01-31T23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateTimeWithValidValueInValidRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00", "2017-01-31T23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeDateTimeWithBadValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-31T23:59", "2017-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATETIME when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateTimeWithValidValueInReversedRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-31T23:59", "2017-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testInRangeDateTimeWithTooFewElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}
	
	@Test
	public void testInRangeDateTimeWithBadValueThreeElementsInRange() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00", "2017-01-31T23:59", "2016-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testInRangeDateTimeWithEndpointValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.setCurrentValue("2017-01-31T23:59");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00", "2017-01-31T23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"IN_RANGE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}
	
	@Test
	public void testRegexStringWithValidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("info@irods.org");
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList(".+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"REGEX validation should succeed",
				retVal == ValidationReturnEnum.SUCCESS);
	}
	
	@Test
	public void testRegexStringWithInvalidValue() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("notAnEmailAddress");
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList(".+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"REGEX validation should fail",
				retVal == ValidationReturnEnum.REGEX_FAILED);
	}
	
	@Test
	public void testRegexStringWithBadPatternString() throws Exception {
		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setCurrentValue("info@irods.org");
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList("(.+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(me);

		Assert.assertTrue(
				"Bad REGEX, should return REGEX_SYNTAX_ERROR",
				retVal == ValidationReturnEnum.REGEX_SYNTAX_ERROR);
	}
	
}
