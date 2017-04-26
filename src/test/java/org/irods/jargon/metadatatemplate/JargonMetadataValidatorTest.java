package org.irods.jargon.metadatatemplate;

import java.util.Arrays;
import java.util.Properties;

import junit.framework.Assert;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.io.IRODSFile;
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

	public static final String IRODS_TEST_SUBDIR_PATH = "JargonMetadataValidatorTest";
	private static org.irods.jargon.testutils.IRODSTestSetupUtilities irodsTestSetupUtilities = null;

	private static final String TEST_FILE_NAME = "src/test/resources/testFile.txt";
	private static final String TEST_FILE_NOPATH = "testFile.txt";

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
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testRequiredElement");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.setRequired(true);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("Validator does not respect required flag",
				retVal == ValidationReturnEnum.VALUE_IS_REQUIRED);
	}

	@Test
	public void testNonRequiredElementWithEmptyValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testNonRequiredElement");
		me.setType(ElementTypeEnum.RAW_STRING);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("Validator does not treat empty list appropriately",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}

	@Test
	public void testDefaultIntWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_INT",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultIntWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("42");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultFloatWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultFloatWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("2.718281828");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultBooleanWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();
		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.getCurrentValue().add("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultBooleanWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.getCurrentValue().add("true");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultDateWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_DATE",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultDateWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-01-24");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultTimeWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_TIME",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultTimeWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("15:10");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultDateTimeWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("badValue");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultDateTimeWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T15:10");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultListOfIntWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.getCurrentValue().add("1");
		me.getCurrentValue().add("badValue");
		me.getCurrentValue().add("3");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of LIST_INT",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultListOfIntWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.getCurrentValue().add("1");
		me.getCurrentValue().add("2");
		me.getCurrentValue().add("3");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of LIST_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testDefaultListOfFloatWithBadValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultListOfFloat");
		me.setType(ElementTypeEnum.LIST_FLOAT);
		me.getCurrentValue().add("1.618");
		me.getCurrentValue().add("badValue");
		me.getCurrentValue().add("3.14159");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of LIST_FLOAT",
				retVal == ValidationReturnEnum.BAD_TYPE);
	}

	@Test
	public void testDefaultListOfFloatWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testDefaultListOfFloat");
		me.setType(ElementTypeEnum.LIST_FLOAT);
		me.getCurrentValue().add("1.618");
		me.getCurrentValue().add("2.71828");
		me.getCurrentValue().add("3.14159");

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect default validation of LIST_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsStringWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("goodString");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_STRING",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsStringWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("badString");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsIntWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("42");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("42"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsIntWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("15");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("42"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsFloatWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("4.2");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("4.2"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsFloatWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("1.5");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("4.2"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsBooleanWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.getCurrentValue().add("true");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("true"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsBooleanWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsBoolean");
		me.setType(ElementTypeEnum.RAW_BOOLEAN);
		me.getCurrentValue().add("false");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("true"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_BOOLEAN",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsDateWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-01-24"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsDateWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-07-04");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-01-24"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATE",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsTimeWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("16:22"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsTimeWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_TIME",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsDateTimeWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-01-24T16:22"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsDateTimeWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2017-07-04T12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfStringWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("citius", "altius", "fortius"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("citius", "altius", "fortius"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_STRING",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsListOfStringWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("slower", "lower", "weaker"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("citius", "altius", "fortius"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfStringWithTooShortValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("citius", "altius"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("citius", "altius", "fortius"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfStringWithTooLongValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("citius", "altius", "fortius",
				"another_one"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("citius", "altius", "fortius"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfIntWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("2", "3", "5"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2", "3", "5"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsListOfIntWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("2", "33", "5"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2", "3", "5"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfIntWithTooShortValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("2", "3"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2", "3", "5"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfIntWithTooLongValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("2", "3", "5", "7"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("2", "3", "5"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfFloatWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfFloat");
		me.setType(ElementTypeEnum.LIST_FLOAT);
		me.setCurrentValue(Arrays.asList("1.618", "2.71828", "3.14159"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("1.618", "2.71828", "3.14159"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testIsListOfFloatWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfInt");
		me.setType(ElementTypeEnum.LIST_FLOAT);
		me.setCurrentValue(Arrays.asList("1.618", "2.71828", "22/7"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("1.618", "2.71828", "3.14159"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfFloatWithTooShortValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfInt");
		me.setType(ElementTypeEnum.LIST_FLOAT);
		me.setCurrentValue(Arrays.asList("1.618", "2.71828"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("1.618", "2.71828", "3.14159"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testIsListOfFloatWithTooLongValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsListOfFloat");
		me.setType(ElementTypeEnum.LIST_FLOAT);
		me.setCurrentValue(Arrays
				.asList("1.618", "2.71828", "3.14159", "6.022"));
		me.setValidationStyle(ValidationStyleEnum.IS);
		me.setValidationOptions(Arrays.asList("1.618", "2.71828", "3.14159"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IS validation of LIST_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_EQUAL);
	}

	@Test
	public void testInListStringWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("goodString");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("firstString", "secondString",
				"goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_STRING",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListStringWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("badString");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("firstString", "secondString",
				"goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListIntWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("42");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("10", "42", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListIntWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("256");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("10", "42", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListFloatWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("4.2");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays
				.asList("4.2", "1.168", "2.71828", "3.14"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListFloatWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("1.414");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays
				.asList("4.2", "1.168", "2.71828", "3.14"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListDateWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-07-04", "2017-01-24",
				"2017-12-25"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListDateWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-02-28");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-07-04", "2017-01-24",
				"2017-12-25"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATE",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListTimeWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("16:22");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("16:22", "01:23", "12:00",
				"17:30"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListTimeWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("16:29");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("16:22", "01:23", "12:00",
				"17:30"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_TIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListDateTimeWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-01-24T16:22",
				"2017-12-25T09:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListDateTimeWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testIsDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T16:22");
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2017-07-04T12:00",
				"2017-12-25T09:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListListOfStringWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("firstString", "secondString"));
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("firstString", "secondString",
				"goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of LIST_STRING",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListListOfStringWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("firstString", "badString"));
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("firstString", "secondString",
				"goodString"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of LIST_STRING",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInListListOfIntWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("3", "5"));
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2", "3", "5"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of LIST_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListListOfIntWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("3", "4"));
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2", "3", "5"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of LIST_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}
	
	@Test
	public void testInListListOfFloatWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("2.71828", "3.14159"));
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2.71828", "3.14159", "6.022"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of LIST_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInListListOfFloatWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInListListOfInt");
		me.setType(ElementTypeEnum.LIST_INT);
		me.setCurrentValue(Arrays.asList("2.71828", "1.618"));
		me.setValidationStyle(ValidationStyleEnum.IN_LIST);
		me.setValidationOptions(Arrays.asList("2.71828", "3.14159", "6.022"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_LIST validation of LIST_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_LIST);
	}

	@Test
	public void testInRangeIntWithBadValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("15");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeIntWithValidValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeIntWithBadValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("15");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeIntWithValidValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_INT when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeIntWithTooFewElementsInRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}

	@Test
	public void testInRangeIntWithBadValueThreeElementsInRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("15");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeIntWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("10");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE validation should succeed if value = range endpoint",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeExclusiveIntWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeExclusiveInt");
		me.setType(ElementTypeEnum.RAW_INT);
		me.getCurrentValue().add("10");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE_EXCLUSIVE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE_EXCLUSIVE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithBadValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("15.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithValidValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("5.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeFloatWithBadValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("15.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithValidValueInReversedRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("5.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("10", "0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_FLOAT when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeFloatWithTooFewElementsInRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("5.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}

	@Test
	public void testInRangeFloatWithBadValueThreeElementsInRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("15.5");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0", "10", "100"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeFloatWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("10.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("0.0", "10.0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE validation should succeed if value = range endpoint",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeExclusiveFloatWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeExclusiveFloat");
		me.setType(ElementTypeEnum.RAW_FLOAT);
		me.getCurrentValue().add("10.0");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE_EXCLUSIVE);
		me.setValidationOptions(Arrays.asList("0.0", "10.0"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE_EXCLUSIVE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateWithBadValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2016-12-25");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateWithValidValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeDateWithBadValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2016-12-25");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-12-31", "2017-01-01"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateWithValidValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-12-31", "2017-01-01"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATE when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeDateWithTooFewElementsInRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}

	@Test
	public void testInRangeDateWithBadValueThreeElementsInRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2018-01-24");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31",
				"2018-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-12-31");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE validation should succeed if value = range endpoint",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeExclusiveDateWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDate");
		me.setType(ElementTypeEnum.RAW_DATE);
		me.getCurrentValue().add("2017-12-31");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE_EXCLUSIVE);
		me.setValidationOptions(Arrays.asList("2017-01-01", "2017-12-31"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE_EXCLUSIVE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeTimeWithBadValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeTimeWithValidValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("15:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeTimeWithBadValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("23:59", "12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeTimeWithValidValueInReversedRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("15:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("23:59", "12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeTimeWithTooFewElementsInRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("15:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}

	@Test
	public void testInRangeTimeWithBadValueThreeElementsInRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59", "03:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeTimeWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeTime");
		me.setType(ElementTypeEnum.RAW_TIME);
		me.getCurrentValue().add("23:59");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("12:00", "23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateTimeWithBadValueInValidRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00",
				"2017-01-31T23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateTimeWithValidValueInValidRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00",
				"2017-01-31T23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATETIME",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeDateTimeWithBadValueInReversedRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-31T23:59",
				"2017-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_DATETIME when range values are reversed",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateTimeWithValidValueInReversedRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-31T23:59",
				"2017-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator does not respect IN_RANGE validation of RAW_TIME when range values are reversed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testInRangeDateTimeWithTooFewElementsInRange() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should return NOT_VALIDATED if too few elements to define range",
				retVal == ValidationReturnEnum.NOT_VALIDATED);
	}

	@Test
	public void testInRangeDateTimeWithBadValueThreeElementsInRange()
			throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2016-01-24T03:54");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00",
				"2017-01-31T23:59", "2016-01-01T00:00"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"Validator should validate against range defined by first two elements",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testInRangeDateTimeWithEndpointValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testInRangeDateTime");
		me.setType(ElementTypeEnum.RAW_DATETIME);
		me.getCurrentValue().add("2017-01-31T23:59");
		me.setValidationStyle(ValidationStyleEnum.IN_RANGE);
		me.setValidationOptions(Arrays.asList("2017-01-01T00:00",
				"2017-01-31T23:59"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"IN_RANGE validation should fail if value = range endpoint",
				retVal == ValidationReturnEnum.VALUE_NOT_IN_RANGE);
	}

	@Test
	public void testRegexStringWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("info@irods.org");
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList(".+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("REGEX validation should succeed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testRegexStringWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("notAnEmailAddress");
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList(".+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("REGEX validation should fail",
				retVal == ValidationReturnEnum.REGEX_FAILED);
	}

	@Test
	public void testRegexStringWithBadPatternString() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexString");
		me.setType(ElementTypeEnum.RAW_STRING);
		me.getCurrentValue().add("info@irods.org");
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList("(.+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("Bad REGEX, should return REGEX_SYNTAX_ERROR",
				retVal == ValidationReturnEnum.REGEX_SYNTAX_ERROR);
	}
	
	@Test
	public void testRegexListOfStringWithValidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("info@irods.org", "support@irods.org"));
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList(".+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("REGEX validation should succeed",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testRegexListOfStringWithInvalidValue() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testRegexListOfString");
		me.setType(ElementTypeEnum.LIST_STRING);
		me.setCurrentValue(Arrays.asList("info@irods.org", "notAnEmailAddress"));
		me.setValidationStyle(ValidationStyleEnum.REGEX);
		me.setValidationOptions(Arrays.asList(".+\\@.+\\..+"));

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("REGEX validation should fail",
				retVal == ValidationReturnEnum.REGEX_FAILED);
	}

	@Test
	public void testFollowRefIrodsCatalogWithValidRef() throws Exception {
		String testDirName = "testFollowRefIrodsCatalogWithValidRef";

		String targetIrodsCollection = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(
						testingProperties, IRODS_TEST_SUBDIR_PATH + '/'
								+ testDirName);

		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		IRODSFile targetCollectionAsFile = accessObjectFactory
				.getIRODSFileFactory(irodsAccount).instanceIRODSFile(
						targetIrodsCollection);

		targetCollectionAsFile.mkdirs();

		DataTransferOperations dataTransferOperations = irodsFileSystem
				.getIRODSAccessObjectFactory().getDataTransferOperations(
						irodsAccount);

		// Create a file in targetIrodsCollection
		dataTransferOperations.putOperation(TEST_FILE_NAME,
				targetIrodsCollection,
				irodsAccount.getDefaultStorageResource(), null, null);
		String testFileNameFQ = targetIrodsCollection + '/' + TEST_FILE_NOPATH;

		MetadataElement me = new MetadataElement();
		me.setElementName("testFollowRefIrodsCatalog");
		me.setType(ElementTypeEnum.REF_IRODS_CATALOG);
		me.getCurrentValue().add(testFileNameFQ);
		me.setValidationStyle(ValidationStyleEnum.FOLLOW_REF);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"FOLLOW_REF validation should succeed; file exists in iRODS",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testFollowRefIrodsCatalogWithInvalidRef() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testFollowRefIrodsCatalog");
		me.setType(ElementTypeEnum.REF_IRODS_CATALOG);
		me.getCurrentValue().add("notAValidFileName");
		me.setValidationStyle(ValidationStyleEnum.FOLLOW_REF);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"FOLLOW_REF validation should fail; file does not exist in iRODS",
				retVal == ValidationReturnEnum.BAD_REF);
	}

	@Test
	public void testFollowRefUrlWithValidRef() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testFollowRefUrl");
		me.setType(ElementTypeEnum.REF_URL);
		me.getCurrentValue().add("http://www.google.com");
		me.setValidationStyle(ValidationStyleEnum.FOLLOW_REF);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue("FOLLOW_REF validation should succeed; URL is valid",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testFollowRefUrlWithInvalidRef() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testFollowRefUrl");
		me.setType(ElementTypeEnum.REF_URL);
		me.getCurrentValue().add("http://notAValidUrl");
		me.setValidationStyle(ValidationStyleEnum.FOLLOW_REF);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"FOLLOW_REF validation should fail; URL is not valid",
				retVal == ValidationReturnEnum.BAD_REF);
	}

	@Test
	public void testFollowRefIrodsQueryWithValidRef() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testFollowRefIrodsQuery");
		me.setType(ElementTypeEnum.REF_IRODS_QUERY);
		me.getCurrentValue().add("data.size");
		me.setValidationStyle(ValidationStyleEnum.FOLLOW_REF);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"FOLLOW_REF validation should succeed; iRODS query is valid",
				retVal == ValidationReturnEnum.SUCCESS);
	}

	@Test
	public void testFollowRefIrodsQueryWithInvalidRef() throws Exception {
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem
				.getIRODSAccessObjectFactory();

		MetadataElement me = new MetadataElement();
		me.setElementName("testFollowRefIrodsQuery");
		me.setType(ElementTypeEnum.REF_IRODS_QUERY);
		me.getCurrentValue().add("data.not_a_valid_data_object_attribute");
		me.setValidationStyle(ValidationStyleEnum.FOLLOW_REF);

		ValidationReturnEnum retVal = ValidatorSingleton.VALIDATOR.validate(
				irodsAccount, accessObjectFactory, me);

		Assert.assertTrue(
				"FOLLOW_REF validation should fail; iRODS query is not valid",
				retVal == ValidationReturnEnum.BAD_REF);
	}
}
