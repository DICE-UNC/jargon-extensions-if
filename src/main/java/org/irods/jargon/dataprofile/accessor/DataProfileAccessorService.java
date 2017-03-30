/**
 *
 */
package org.irods.jargon.dataprofile.accessor;

import org.irods.jargon.dataprofile.accessor.exception.AttributeNotFoundException;
import org.irods.jargon.dataprofile.accessor.exception.ObjectNotFoundException;
import org.irods.jargon.dataprofile.accessor.exception.WrongDataProfileTypeException;

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

	public String retrieveValueFromKey(final String accessorTextValue)
			throws ObjectNotFoundException, WrongDataProfileTypeException, AttributeNotFoundException;

}
