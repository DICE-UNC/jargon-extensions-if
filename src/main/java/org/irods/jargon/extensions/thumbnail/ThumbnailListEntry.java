package org.irods.jargon.extensions.thumbnail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThumbnailListEntry {

	@JsonProperty("id")
	private int id = 0;
	@JsonProperty("name")
	private String name = "";
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("lastModified")
	private String lastModified;
	@JsonProperty("thumbnails")
	private String thumbnails = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ThumbnailListEntry [id=").append(id).append(", ");
		if (name != null) {
			builder.append("name=").append(name).append(", ");
		}
		if (lastModified != null) {
			builder.append("lastModified=").append(lastModified).append(", ");
		}
		if (thumbnails != null) {
			builder.append("thumbnails=").append(thumbnails);
		}
		builder.append("]");
		return builder.toString();
	}
}
