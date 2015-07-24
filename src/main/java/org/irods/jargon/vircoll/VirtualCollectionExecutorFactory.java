package org.irods.jargon.vircoll;

import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;

/**
 * Represents a factory service that can find and initialize lists of virtual
 * collections, as well as create new instances of a virtual collection type
 * 
 * @author Mike Conway - DICE
 * 
 */
public interface VirtualCollectionExecutorFactory {

	/**
	 * Given some form of virtual collection, return the associated executor
	 * 
	 * @param virtualCollectiono
	 *            {@link AbstractVirtualCollection} subtype
	 * @return associated {@link AbstractVirtualCollectionExecutor}
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	AbstractVirtualCollectionExecutor<?> instanceExecutorBasedOnVirtualCollection(
			final AbstractVirtualCollection virtualCollection)
			throws DataNotFoundException, JargonException;

	/**
	 * For fallback in non-path-hintable virtual collections, get a reference to
	 * the collection based virtual collection(normal iRODS file browsing). In
	 * virtual collections where additional path information cannot be
	 * processed, it will delegate those paths to this normal inquiry mode.
	 * 
	 * @return {@link AbstractVirtualCollectionExecutor} oriented towards the
	 *         iRODS root path '/'.
	 * @throws JargonException
	 */
	AbstractVirtualCollectionExecutor<?> instanceCollectionBasedVirtualCollectionExecutorAtRoot()
			throws JargonException;

}