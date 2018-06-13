/**
 * 
 */
package org.irods.jargon.extensions.pid;

import java.util.List;

import org.irods.jargon.core.exception.DataNotFoundException;

/**
 * Interface for a pid service that resolves PIDS from iRODS paths and
 * vice-versa. This interface does not contemplate covering the issuance of PIDs
 * at this point as these requests may be complex, carry metadata, etc.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface PidResolutionServiceInterface {

	/**
	 * Given a pid, retrieve the iRODS logical path
	 * 
	 * @param persistentIdentifier
	 *            {@link String} with the pid
	 * @return {@link PersistentIdentifier} associated with the path, with the iRODS
	 *         path provisioned
	 * @throws DataNotFoundException
	 *             if resolution is not possible
	 */
	PersistentIdentifier resolvePidToIrodsPath(final String persistentIdentifier) throws DataNotFoundException;

	/**
	 * Given an iRODS logical path, retrieve the identifiers associated with that
	 * path, or an empty list
	 * 
	 * @param irodsPath
	 *            {@link String}
	 * @return {@link List} of {@link PersistentIdentifier} (may be empty if none
	 *         resolved}
	 */
	List<PersistentIdentifier> resolveIrodsPathToIdentifiers(final String irodsPath);

	/**
	 * Given an identifier type and iRODS path, return that identifier
	 * 
	 * @param identifierType
	 *            {@link String} with a registered identifier type
	 * @param irodsPath
	 *            {@link String} with the iRODS absolute path
	 * @return {@link PersistentIdentifier}
	 * @throws DataNotFoundException
	 */
	PersistentIdentifier resolveIrodsPathToIdentifier(final String identifierType, final String irodsPath)
			throws DataNotFoundException;

}
