/**
 * 
 */
package org.irods.jargon.extensions.pid;

/**
 * Represents a persistent identifier
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class PersistentIdentifier {

	/**
	 * String-ified representation of the pid type
	 */
	private String uniqueType = "";
	/**
	 * Opaque identifier
	 */
	private String identifier = "";
	/**
	 * iRODS path (if resolved) associated with the identifier
	 */
	private String associatedIrodsAbsolutePath = "";

	public PersistentIdentifier() {
	}

	public String getUniqueType() {
		return uniqueType;
	}

	public void setUniqueType(String uniqueType) {
		this.uniqueType = uniqueType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAssociatedIrodsAbsolutePath() {
		return associatedIrodsAbsolutePath;
	}

	public void setAssociatedIrodsAbsolutePath(String associatedIrodsAbsolutePath) {
		this.associatedIrodsAbsolutePath = associatedIrodsAbsolutePath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersistentIdentifier [");
		if (uniqueType != null) {
			builder.append("uniqueType=").append(uniqueType).append(", ");
		}
		if (identifier != null) {
			builder.append("identifier=").append(identifier).append(", ");
		}
		if (associatedIrodsAbsolutePath != null) {
			builder.append("associatedIrodsAbsolutePath=").append(associatedIrodsAbsolutePath);
		}
		builder.append("]");
		return builder.toString();
	}

}
