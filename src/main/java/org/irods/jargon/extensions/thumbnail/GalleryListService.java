/**
 * 
 */
package org.irods.jargon.extensions.thumbnail;

import java.util.List;

import org.irods.jargon.core.exception.JargonException;

/**
 * Service to provide 'gallery' based listings for user interface. This focuses
 * on retrieving file directory contents where thumbnail information has been
 * added
 * 
 * @author conwaymc
 *
 */
public interface GalleryListService {

	/**
	 * List the children of a given iRODS absolute path.
	 * 
	 * @param irodsFileAbsolutePath {@code String} with the irods absolute path.
	 *                              Note that if a data object is given, it will
	 *                              return a single entry for that data object
	 *                              itself
	 * @param offset                {@code int} with the offset into the children
	 *                              list, 0 is the default
	 * @param length                {@code int} with the desired length of the
	 *                              result set
	 * @return {@code List} of {@link ThumbnailListEntry} with the results. An empty
	 *         list means no children or record was found. Note that if the given
	 *         path is a data object a one record list will be returned.
	 * @throws JargonException {@link JargonException}
	 */
	List<ThumbnailListEntry> list(final String irodsFileAbsolutePath, final int offset, final int length)
			throws JargonException;

}
