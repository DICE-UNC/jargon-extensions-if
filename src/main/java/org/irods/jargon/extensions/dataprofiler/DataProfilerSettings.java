/**
 * 
 */
package org.irods.jargon.extensions.dataprofiler;

/**
 * Settings to determine the behavior of a data profiler, note that individual
 * implementations may or may not respect every setting, so consider these hints
 * rather than hard configuration
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DataProfilerSettings {

	/**
	 * Obtain information on replicas
	 */
	private boolean retrieveReplicas = false;
	/**
	 * Obtain information on tickets
	 */
	private boolean retrieveTickets = false;
	/**
	 * Obtain stars or favorites
	 */
	private boolean retrieveStarred = true;
	/**
	 * Obtain sharing however the underlying implementation defines it
	 */
	private boolean retrieveShared = true;
	/**
	 * Retrieve acls
	 */
	private boolean retrieveAcls = false;
	/**
	 * Retrieve raw AVU metadata
	 */
	private boolean retrieveMetadata = true;
	/**
	 * Set mime and info type however the unerlying implementation defines the
	 * process
	 */
	private boolean detectMimeAndInfoType = true;

	/**
	 * Gather and fill in metadata as templated information
	 */
	private boolean resolveMetadataTemplates = false;

	/**
	 * Get free tags and comments
	 */
	private boolean retrieveTagsAndComments = false;

	public boolean isRetrieveReplicas() {
		return retrieveReplicas;
	}

	public void setRetrieveReplicas(boolean retrieveReplicas) {
		this.retrieveReplicas = retrieveReplicas;
	}

	public boolean isRetrieveTickets() {
		return retrieveTickets;
	}

	public void setRetrieveTickets(boolean retrieveTickets) {
		this.retrieveTickets = retrieveTickets;
	}

	public boolean isRetrieveStarred() {
		return retrieveStarred;
	}

	public void setRetrieveStarred(boolean retrieveStarred) {
		this.retrieveStarred = retrieveStarred;
	}

	public boolean isRetrieveShared() {
		return retrieveShared;
	}

	public void setRetrieveShared(boolean retrieveShared) {
		this.retrieveShared = retrieveShared;
	}

	public boolean isRetrieveAcls() {
		return retrieveAcls;
	}

	public void setRetrieveAcls(boolean retrieveAcls) {
		this.retrieveAcls = retrieveAcls;
	}

	public boolean isRetrieveMetadata() {
		return retrieveMetadata;
	}

	public void setRetrieveMetadata(boolean retrieveMetadata) {
		this.retrieveMetadata = retrieveMetadata;
	}

	public boolean isDetectMimeAndInfoType() {
		return detectMimeAndInfoType;
	}

	public void setDetectMimeAndInfoType(boolean detectMimeAndInfoType) {
		this.detectMimeAndInfoType = detectMimeAndInfoType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataProfilerSettings [retrieveReplicas=").append(retrieveReplicas).append(", retrieveTickets=")
				.append(retrieveTickets).append(", retrieveStarred=").append(retrieveStarred)
				.append(", retrieveShared=").append(retrieveShared).append(", retrieveAcls=").append(retrieveAcls)
				.append(", retrieveMetadata=").append(retrieveMetadata).append(", detectMimeAndInfoType=")
				.append(detectMimeAndInfoType).append(", resolveMetadataTemplates=").append(resolveMetadataTemplates)
				.append(", retrieveTagsAndComments=").append(retrieveTagsAndComments).append("]");
		return builder.toString();
	}

	public boolean isResolveMetadataTemplates() {
		return resolveMetadataTemplates;
	}

	public void setResolveMetadataTemplates(boolean resolveMetadataTemplates) {
		this.resolveMetadataTemplates = resolveMetadataTemplates;
	}

	public boolean isRetrieveTagsAndComments() {
		return retrieveTagsAndComments;
	}

	public void setRetrieveTagsAndComments(boolean retrieveTagsAndComments) {
		this.retrieveTagsAndComments = retrieveTagsAndComments;
	}

}
