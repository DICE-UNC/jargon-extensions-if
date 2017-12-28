package org.irods.jargon.extensions.datatyper;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;

public abstract class DataTypeResolutionService extends AbstractJargonService {

	/**
	 * Default settings that control the general behavior of the data typer service
	 */
	private final DataTyperSettings defaultDataTyperSettings;

	/**
	 * Default constructor
	 * 
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory}
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 * @param dataTyperSettings
	 *            {@link DataTyperSettings}
	 */
	public DataTypeResolutionService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			final DataTyperSettings dataTyperSettings) {
		super(irodsAccessObjectFactory, irodsAccount);
		if (dataTyperSettings == null) {
			throw new IllegalArgumentException("null dataTyperSettings");
		}
		this.defaultDataTyperSettings = dataTyperSettings;
	}

	/**
	 * Resolve the data type based on the default settings
	 * 
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the irods absolute path
	 * @return {@link DataType} characterizing that file
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	public abstract DataType resolveDataType(final String irodsAbsolutePath)
			throws DataNotFoundException, JargonException;

	/**
	 * Resolve data type based on provided settings
	 * 
	 * @param irodsAbsolutePath
	 *            irodsAbsolutePath <code>String</code> with the irods absolute path
	 * @param dataTyperSettings
	 *            {@link DataTyperSettings} with override configuration
	 * @return {@link DataType} characterizing that file
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	public abstract DataType resolveDataType(final String irodsAbsolutePath, final DataTyperSettings dataTyperSettings)
			throws DataNotFoundException, JargonException;

	public DataTyperSettings getDefaultDataTyperSettings() {
		return defaultDataTyperSettings;
	}

}