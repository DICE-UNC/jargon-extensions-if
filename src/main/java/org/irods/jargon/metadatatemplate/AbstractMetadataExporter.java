package org.irods.jargon.metadatatemplate;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;

/**
 * Abstract superclass for a metadata template/element exporter, which is
 * responsible for storing a populated metadata template/element to permanent
 * storage in a metadata catalog. </p>
 * 
 * <p>
 * Implemented as abstract because each metadata store implementation will
 * require different implementations of these functions.
 * </p>
 * 
 * 
 * 
 * @author rskarbez
 *
 */

public abstract class AbstractMetadataExporter extends AbstractJargonService {

	public AbstractMetadataExporter() {
		super();
	}

	public AbstractMetadataExporter(
			final IRODSAccessObjectFactory irodsAccessObjectFactory,
			final IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}
	
	public abstract void saveTemplateToSystemMetadataOnObject(MetadataTemplate template, String pathToObject) throws JargonException, FileNotFoundException;
	public abstract void saveElementToSystemMetadataOnObject(MetadataElement element, String pathToObject) throws JargonException, FileNotFoundException;
}
