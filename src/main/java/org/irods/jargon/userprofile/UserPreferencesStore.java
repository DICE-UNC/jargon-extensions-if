/**
 * 
 */
package org.irods.jargon.userprofile;

import java.util.Properties;

/**
 * General store (per application) for user preferences. Note that this abstract
 * super class is agnostic about any synchronization or thread-safety concerns,
 * which should be implemented in subclasses.
 * 
 * @author Mike Conway - DICE
 *
 */
public abstract class UserPreferencesStore {

	/**
	 * Retrieve a bag of properties for a given category or application.
	 * 
	 * @param appName
	 *            <code>String</code> for an 'app' or 'category' that serves as
	 *            a namespace for the props
	 * @return <code>Properties</code> containing known properties for an app.
	 *         An empty <code>Properties</code> is returned if no values are
	 *         found
	 * @throws UserProfileException
	 */
	abstract Properties retrievePropertiesForApp(final String appName)
			throws UserProfileException;

	/**
	 * This is an idempotent method that will either create or update a set of
	 * properties to be stored for a given app
	 * 
	 * @param appName
	 *            <code>String</code> for an 'app' or 'category' that serves as
	 *            a namespace for the props
	 * @param properties
	 *            <code>Properties</code> containing the set of properties that
	 *            should be stored for the app.
	 * @throws UserProfileException
	 */
	abstract void storePropertiesForApp(final String appName,
			final Properties properties) throws UserProfileException;

	/**
	 * Idempotent delete method that silently ignores missing target app
	 * properties. Deletes all properties for an app
	 * 
	 * @param appName
	 *            <code>String</code> for an 'app' or 'category' that serves as
	 *            a namespace for the props
	 * @throws UserProfileException
	 */
	abstract void deletePropertiesForApp(final String appName)
			throws UserProfileException;

	/**
	 * Retrieve a property in the app namespace, or <code>null</code> if the
	 * property does not exist
	 * 
	 * @param appName
	 *            <code>String</code> for an 'app' or 'category' that serves as
	 *            a namespace for the props
	 * @param propertyName
	 *            <code>String</code> with the property key
	 * @return <code>String</code> with the property value
	 * @throws UserProfileException
	 */
	abstract String retrievePropertyForApp(final String appName,
			final String propertyName) throws UserProfileException;

	/**
	 * Idempotent method to add or update a property value for the given app
	 * namespace
	 * 
	 * @param appName
	 *            appName <code>String</code> for an 'app' or 'category' that
	 *            serves as a namespace for the props
	 * @param propertyName
	 *            propertyName <code>String</code> with the name of the prop to
	 *            update
	 * @param propertyValue
	 *            appName <code>String</code> with the value to store at the
	 *            property
	 * @throws UserProfileException
	 */
	abstract void updatePropertyForApp(final String appName,
			final String propertyName, final String propertyValue)
			throws UserProfileException;

	/**
	 * Idempotent method to remove a property. If the property does not exist,
	 * it will silently ignore the delete
	 * 
	 * @param appName
	 *            appName <code>String</code> for an 'app' or 'category' that
	 *            serves as a namespace for the props
	 * @param propertyName
	 *            propertyName <code>String</code> with the name of the prop to
	 *            remove
	 * 
	 * @throws UserProfileException
	 */
	abstract void deletePropertyForApp(final String appName,
			final String propertyName) throws UserProfileException;

}
