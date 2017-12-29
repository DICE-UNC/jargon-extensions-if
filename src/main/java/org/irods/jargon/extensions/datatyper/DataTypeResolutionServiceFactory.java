package org.irods.jargon.extensions.datatyper;

import org.irods.jargon.core.connection.IRODSAccount;

public interface DataTypeResolutionServiceFactory {

	/**
	 * Return a newly constructed service
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with the user
	 * @return {@link DataTypeResolutionService}
	 */
	DataTypeResolutionService instanceDataTypeResolutionService(IRODSAccount irodsAccount);

}