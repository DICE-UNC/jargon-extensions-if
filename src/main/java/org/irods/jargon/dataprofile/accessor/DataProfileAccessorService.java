/**
 * 
 */
package org.irods.jargon.dataprofile.accessor;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.dataprofile.accessor.exception.ObjectNotFoundException;

/**
 * Service to obtain data from iRODS to answer template requests from a little
 * templating language.
 * <p/>
 * This was created for the metadata template service but provides a simple way
 * to access things about iRODS using a little template language, so that a
 * request for data.size will retrieve the size of the data object from iRODS.
 * <p/>
 * The {@link AccessorValuesEnum} gives the enumeration of all of the possible
 * values that this service will respond to. Currently the response is always a
 * String representation.
 * 
 * @author Mike Conway - DICE
 *
 */
public interface DataProfileAccessorService {

	public String retriveValueFromKey(final String accessorTextValue)
			throws ObjectNotFoundException, JargonException;

}
