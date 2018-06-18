package org.irods.jargon.extensions.dataprofiler;

import org.irods.jargon.core.connection.IRODSAccount;

public interface DataProfilerFactory {

	/**
	 * Create a properly provisioned {@link DataProfilerService}
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount} for which the service was enabled
	 * @return {@link DataProfilerService}
	 */
	DataProfilerService instanceDataProfilerService(IRODSAccount irodsAccount);

	/**
	 * Create a {@link DataProfilerService} with the provided configuration settings
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount} for which the service was enabled
	 * @param dataProfilerSettings
	 *            {@link DataProfilerSettings} to tune the behavior of the profiler
	 * @return {@link DataProfilerService}
	 */
	DataProfilerService instanceDataProfilerService(IRODSAccount irodsAccount,
			DataProfilerSettings dataProfilerSettings);

}