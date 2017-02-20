package org.irods.jargon.pidservice;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.domain.DataObject;

/**
 * Interface for a PID (unique identifier) Service, that defines
 * methods to create pids and access data objects associated with pids. 
 * 
 * @author Lisa Stillwell
 *
 */

public interface PidService {	
	
	/**
	 * Definition of method to create a Unique Identifier for a data object
	 * Returns a DataONE Identifier object for the iRODS Data Object specified
	 * 
	 * @param pid
	 * 				<code>DataObjectPID</code> for unique identifier to be created.
	 * @throws JargonException
	 */
	
	public DataObjectPID createPID(DataObjectPID pid)
			throws JargonException;
		

	/**
	 * Definition of method to find a data object associated with a pid
	 * Returns an iRODS DataObject
	 * 
	 * @param pid
	 * 				<code>DataObjectPID</code> for unique identifier to be retrieved.
	 * @throws JargonException
	 * 
	 */
	public DataObject resolvePID(DataObjectPID pid)
			throws JargonException;
}
