package org.irods.jargon.metadatatemplate;

/**
 *
 * @author Rick Skarbez
 */

/**
 * 
 * ONLY iRODS AVUS currently supported
 * <p/>
 * Will specify format that "ground truth" metadata is stored in
 * <p/>
 * IRODS - Metadata exported to AVU triples, where Unit contains information
 * referring back to the metadata template and/or metadata elements from which
 * the AVU was generated.
 *
 */

public enum DestinationEnum {
	IRODS
}
