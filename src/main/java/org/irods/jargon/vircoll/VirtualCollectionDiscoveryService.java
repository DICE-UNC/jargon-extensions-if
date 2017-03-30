package org.irods.jargon.vircoll;

import java.util.List;

import org.irods.jargon.vircoll.exception.VirtualCollectionProfileException;

public interface VirtualCollectionDiscoveryService {

	/**
	 * Create a list of virtual collection types for a user. This will include
	 * the default set (root, user home, starred, shared) as well as custom
	 * configured virtual collections in the user home directory
	 *
	 * @return
	 * @throws VirtualCollectionProfileException
	 */
	public List<AbstractVirtualCollection> listDefaultUserCollections() throws VirtualCollectionProfileException;

	/**
	 * Create a list of the virtual collections that are temporary collections
	 * for a user.
	 *
	 * @param userName
	 *            <code>String</code> that can be blank | null. If provided, do
	 *            queries for that user, if not, use the logged in identity
	 * @return <code>List</code>{@link AbstractVirtualCollection}
	 * @throws VirtualCollectionProfileException
	 */
	public List<AbstractVirtualCollection> listUserRecentQueries(final String userName)
			throws VirtualCollectionProfileException;

	/**
	 * Obtain the {@link UserVirtualCollectionProfile} for a given user. The
	 * user name may be left blank, in which case, it returns the profile of the
	 * currently logged-in iRODS user
	 *
	 * @param userName
	 *            <code>String</code> with the iRODS user name, or blank | null
	 *            if the current logged in user should be used to build the
	 *            profile
	 * @return {@link UserVirtualCollectionProfile} for the user
	 */
	public UserVirtualCollectionProfile userVirtualCollectionProfile(final String userName)
			throws VirtualCollectionProfileException;

}