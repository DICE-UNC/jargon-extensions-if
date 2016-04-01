package org.irods.jargon.vircoll;

import org.irods.jargon.core.exception.DuplicateDataException;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.vircoll.exception.VirtualCollectionException;

/**
 * Interface for a service to maintain and modify virtual collection metadata
 * <p/>
 * Consider this a transitional refactoring, iRODS specific things like iRODS
 * file semantics still pollute this prototype!
 * 
 * @author Mike Conway - DICE
 * 
 */
public interface VirtualCollectionMaintenanceService {

	/**
	 * Add the given virtual collection, in a serialized form, to the specified
	 * collection
	 * 
	 * @param configurableVirtualCollection
	 *            {@link ConfigurableVirtualCollection} which is serializable
	 *            for the user
	 * @param collection
	 *            {@link CollectionTypes} enum identifying the collection in
	 *            which to save the VC
	 * @param uniqueName
	 *            <code>String</code> naming the virtual collection
	 * @throws DuplicateDataException
	 *             if the virtual collection already exists
	 * @throws JargonException
	 */
	public abstract void addVirtualCollection(
			ConfigurableVirtualCollection configurableVirtualCollection,
			CollectionTypes collection, String uniqueName)
			throws DuplicateDataException, JargonException;

	/**
	 * Convert the given virtual collection into a JSON format
	 * 
	 * @param configurableVirtualCollection
	 *            {@link ConfigurableVirtualCollection} that may be serialized
	 *            to JSON
	 * @return <code>String</code> with the JSON formmated virtual collection
	 *         data
	 * @throws VirtualCollectionException
	 */
	public abstract String serializeVirtualCollectionToJson(
			ConfigurableVirtualCollection configurableVirtualCollection)
			throws VirtualCollectionException;

	/**
	 * Given a collection and a file name, retrieve the virtual collection from
	 * iRODS as an object
	 * 
	 * @param <code>String</code> with the name of the virtual collection file
	 * @return {@link ConfigurableVirtualCollection} available at that location
	 * @throws FileNotFoundException
	 * @throws VirtualCollectionException
	 */
	public abstract ConfigurableVirtualCollection retrieveVirtualCollectionGivenUniqueName(
			final String uniqueName) throws FileNotFoundException,
			VirtualCollectionException;

	/**
	 * Given a collection and a file name, delete the virtual collection file
	 * 
	 * @param collection
	 *            {@link CollectoinTypes} with the type of virtual collection
	 * @param <code>String</code> with the name of the virtual collection file
	 * @return {@link ConfigurableVirtualCollection} available at that location
	 * @throws FileNotFoundException
	 * @throws VirtualCollectionException
	 */
	public abstract void deleteVirtualCollection(
			final CollectionTypes collection, final String uniqueName)
			throws FileNotFoundException, VirtualCollectionException;

	/**
	 * Update the contents of the virtual collection in-place
	 * 
	 * @param configurableVirtualCollection
	 *            {@link ConfigurableVirtualCollection} which is serializable
	 *            for the user
	 * @param collection
	 *            {@link CollectionTypes} identifying the collection in which to
	 *            save the VC
	 * @throws VirtualCollectionException
	 */
	void updateVirtualCollection(
			ConfigurableVirtualCollection configurableVirtualCollection,
			CollectionTypes collection) throws VirtualCollectionException;

}