/**
 * 
 */
package org.irods.jargon.favorites;

import java.util.Date;

/**
 * A favorite marked by a user
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class Favorite {

	/**
	 * Path of favorite in iRODS
	 */
	private String irodsAbsolutePath;

	/**
	 * Name of favorite in iRODS
	 */
	private String name;

	/**
	 * Indicates whether the favorite is an iRODS collection
	 */
	private boolean collection;

	/**
	 * Create time of favorite
	 */
	private Date createdAt;

	public String getIrodsAbsolutePath() {
		return irodsAbsolutePath;
	}

	public void setIrodsAbsolutePath(String irodsAbsolutePath) {
		this.irodsAbsolutePath = irodsAbsolutePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Favorite [irodsAbsolutePath=").append(irodsAbsolutePath).append(", name=").append(name)
				.append(", collection=").append(collection).append(", createdAt=").append(createdAt).append("]");
		return builder.toString();
	}

}
