package org.irods.jargon.metadatatemplate;

/**
 * @author Mike Conway and Rick Skarbez
 */

/**
 * Note that type <code>RAW_TEXT</code> and <code>RAW_URL</code> can be treated
 * the same as string, however they can be used to cue the validator or
 * interface builders to behave differently.
 * 
 * The <code>REF_</code> types indicate that the metadata value stored in the
 * catalog does not represent the raw value of the attribute; rather, the value
 * is retrieved by dereferencing the value according to its type.
 */
public enum ElementTypeEnum {

	RAW_STRING, RAW_TEXT, RAW_URL, RAW_INT, RAW_FLOAT, RAW_BOOLEAN, RAW_DATE, RAW_TIME, RAW_DATETIME,
	REF_IRODS_GENQUERY, REF_IRODS_CATALOG, REF_HTTP, REF_HTTPS, REF_FTP, REF_SFTP

}
