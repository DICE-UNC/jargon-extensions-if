/**
 *
 */
package org.irods.jargon.vircoll;

import java.util.List;

import org.irods.jargon.vircoll.exception.VirtualCollectionException;

/**
 * Interface for a service to handle temporary queries
 *
 * @author Mike Conway - DICE
 *
 */
public interface TemporaryQueryService {

	/**
	 * Generate a unique name for a temporary query
	 *
	 * @return <code>String</code> with the unique name for a temporary query
	 */
	public String generateTempUniqueName();

	/**
	 * Given a virtual collection, generate and insert a uniqueName and store in
	 * the temporary queries folder for the given user.
	 *
	 * @param virtualCollection
	 *            {@link ConfigurableVirtualCollection} to store. Note the
	 *            unique name will be generated and overlaid if not provided.
	 * @param userName
	 *            <code>String</code> for storage, if not entered (null or
	 *            blank) the current iRODS Account will provide the user name
	 * @param virtualCollectionMaintenanceService
	 *            {@link VirtualCollectionMaintenanceService} that handles this
	 *            type of query
	 * @return <code>String</code> with the name of the virtual collection
	 * @throws VirtualCollectionException
	 */
	public String addOrUpdateTemporaryQuery(ConfigurableVirtualCollection virtualCollection, String userName,
			VirtualCollectionMaintenanceService virtualCollectionMaintenanceService) throws VirtualCollectionException;

	/**
	 * Return a list of the last N temporary queries, sorted newest to oldest,
	 * for the given VirtualCollectionMaintenanceService
	 *
	 * @param n
	 *            <code>int</code> greater than 0, the size of list to attempt
	 *            to return
	 * @param userName
	 *            <code>String</code> for storage, if not entered (null or
	 *            blank) the current iRODS Account will provide the user name
	 * @param virtualCollectionMaintenanceService
	 *            {@link VirtualCollectionMaintenanceService} that handles this
	 *            type of query
	 * @return {@link List} of {@link ConfigurableVirtualCollection}. List will
	 *         contain at most n elements.
	 * @throws VirtualCollectionException
	 */
	public List<ConfigurableVirtualCollection> getLastNTemporaryQueries(int n, String userName,
			VirtualCollectionMaintenanceService virtualCollectionMaintenanceService) throws VirtualCollectionException;

	/**
	 * Retrieve the temporary query given the uniqueName. Note that
	 * <code>null</code> will be returned if it is not found.
	 *
	 * @param userName
	 *            <code>String</code> userName with the name of the user
	 * @param virtualCollectionMaintenanceService
	 *            {@link VirtualCollectionMaintenanceService} that handles this
	 *            type of query
	 * @param uniqueName
	 *            <code>String</code> with the uniqueName of the query
	 * @return {@link ConfigurableVirtualCollection} or <code>null</code> if not
	 *         found.
	 * @throws VirtualCollectionException
	 */
	public ConfigurableVirtualCollection getTemporaryQueryByUniqueName(final String userName,
			final VirtualCollectionMaintenanceService virtualCollectionMaintenanceService, final String uniqueName)
			throws VirtualCollectionException;
}
