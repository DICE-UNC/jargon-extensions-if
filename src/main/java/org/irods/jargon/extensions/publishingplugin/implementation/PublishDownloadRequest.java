package org.irods.jargon.extensions.publishingplugin.implementation;

public class PublishDownloadRequest {
	/**
	 * Principal for the publish (who is actually calling publish, this should be
	 * the iRODS account)
	 */
	private String publishPrincipal = "";

	/**
	 * Schema id from index inventory
	 */
	private String publishSchema = "";
	/**
	 * Endpoint that will do the search
	 */
	private String endpointUrl = "";

	public String getpublishSchema() {
		return publishSchema;
	}

	public void setPublishSchema(String publishSchema) {
		this.publishSchema = publishSchema;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PublishDownloadRequest [publishPrincipal=").append(publishPrincipal).append(", publishSchema=")
				.append(publishSchema).append(", endpointUrl=").append(endpointUrl).append("]");
		return builder.toString();
	}

	public String getPublishPrincipal() {
		return publishPrincipal;
	}

	public void setPublishPrincipal(String publishPrincipal) {
		this.publishPrincipal = publishPrincipal;
	}
}
