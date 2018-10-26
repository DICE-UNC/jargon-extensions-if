/**
 * 
 */
package org.irods.jargon.favorites;

import java.util.List;

/**
 * Abstract interface that
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface FavoritesServiceInterface {

	/**
	 * Find all favorites for a given user and zone. Zone may be left blank for the
	 * current zone
	 * 
	 * @param zone
	 *            {@code String} with an optional (blank if not used) zone name
	 * @param userName
	 *            {@code String} with a user name
	 * @return {@link List} of {@code String} with the iRODS paths that are marked
	 *         as favorites by the given user
	 */
	List<String> findFavoritesForUserAsString(String zone, String userName);

}
