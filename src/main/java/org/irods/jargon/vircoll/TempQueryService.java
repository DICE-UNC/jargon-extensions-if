/**
 * 
 */
package org.irods.jargon.vircoll;

import org.irods.jargon.vircoll.exception.VirtualCollectionException;

/**
 * Interface for a service to handle temporary queries
 * 
 * @author Mike Conway - DICE
 *
 */
public interface TempQueryService {

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
	 *            {@link AbstractVirtualCollection} to store. Note the unique
	 *            name will be generated and overlaid if not provided.
	 * @param userName
	 *            <code>String</code> for storage, if not entered (null or
	 *            blank) the current iRODS Account will provide the user name
	 * @return <code>String</code> with the name of the virtual collection
	 * @throws VirtualCollectionException
	 */
	public String nameAndStoreTemporaryQuery(
			final AbstractVirtualCollection virtualCollection,
			final String userName) throws VirtualCollectionException;
}
