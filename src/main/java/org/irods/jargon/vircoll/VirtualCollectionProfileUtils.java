/**
 * 
 */
package org.irods.jargon.vircoll;

/**
 * Handy utility methods for looking for items in the
 * UserVirtualCollectionProfile
 * 
 * @author Mike Conway - DICE
 *
 */
public class VirtualCollectionProfileUtils {

	/**
	 * Find the collection with the given unique name in the temp queries
	 * 
	 * @param uniqueName
	 *            <code>String</code> with the unique name
	 * @param userVirtualCollectionProfile
	 *            {@link UserVirtualCollectionProfile}
	 * @return the {@link AbstractVirtualCollection} or <code>null</code> if not
	 *         found
	 */
	public static AbstractVirtualCollection findVirtualCollectionInTempQueries(
			final String uniqueName,
			final UserVirtualCollectionProfile userVirtualCollectionProfile) {

		if (uniqueName == null || uniqueName.isEmpty())
			throw new IllegalArgumentException("null or empty uniqueName");

		if (userVirtualCollectionProfile == null)
			throw new IllegalArgumentException(
					"null userVirtualCollectionProfile");

		AbstractVirtualCollection foundCollection = null;

		for (AbstractVirtualCollection collection : userVirtualCollectionProfile
				.getUserRecentQueries()) {
			if (collection.getUniqueName().equals(uniqueName)) {
				foundCollection = collection;
				break;
			}
		}

		return foundCollection;

	}

	/**
	 * Find the collection with the given unique name in the user home queries
	 * 
	 * @param uniqueName
	 *            <code>String</code> with the unique name
	 * @param userVirtualCollectionProfile
	 *            {@link UserVirtualCollectionProfile}
	 * @return the {@link AbstractVirtualCollection} or <code>null</code> if not
	 *         found
	 */
	public static AbstractVirtualCollection findVirtualCollectionInUserHomeQueries(
			final String uniqueName,
			final UserVirtualCollectionProfile userVirtualCollectionProfile) {

		if (uniqueName == null || uniqueName.isEmpty())
			throw new IllegalArgumentException("null or empty uniqueName");

		if (userVirtualCollectionProfile == null)
			throw new IllegalArgumentException(
					"null userVirtualCollectionProfile");

		AbstractVirtualCollection foundCollection = null;

		for (AbstractVirtualCollection collection : userVirtualCollectionProfile
				.getUserHomeCollections()) {
			if (collection.getUniqueName().equals(uniqueName)) {
				foundCollection = collection;
				break;
			}
		}

		return foundCollection;

	}

	/**
	 * Find the collection with the given unique name in the entire user set
	 * 
	 * @param uniqueName
	 *            <code>String</code> with the unique name
	 * @param userVirtualCollectionProfile
	 *            {@link UserVirtualCollectionProfile}
	 * @return the {@link AbstractVirtualCollection} or <code>null</code> if not
	 *         found
	 */
	public static AbstractVirtualCollection findVirtualCollectionInAnyUserVirtualCollection(
			final String uniqueName,
			final UserVirtualCollectionProfile userVirtualCollectionProfile) {

		if (uniqueName == null || uniqueName.isEmpty())
			throw new IllegalArgumentException("null or empty uniqueName");

		if (userVirtualCollectionProfile == null)
			throw new IllegalArgumentException(
					"null userVirtualCollectionProfile");

		AbstractVirtualCollection foundCollection = findVirtualCollectionInUserHomeQueries(
				uniqueName, userVirtualCollectionProfile);

		if (foundCollection == null) {
			foundCollection = findVirtualCollectionInTempQueries(uniqueName,
					userVirtualCollectionProfile);
		}

		return foundCollection;

	}

}
