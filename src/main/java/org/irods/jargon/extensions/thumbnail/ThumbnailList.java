/**
 * 
 */
package org.irods.jargon.extensions.thumbnail;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author conwaymc
 *
 */
public class ThumbnailList {

	@JsonProperty("location")
	private String location = "";

	@JsonProperty("items")
	private List<ThumbnailListEntry> thumbnails = new ArrayList<>();

	public List<ThumbnailListEntry> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(List<ThumbnailListEntry> thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("ThumbnailList [");
		if (location != null) {
			builder.append("location=").append(location).append(", ");
		}
		if (thumbnails != null) {
			builder.append("thumbnails=").append(thumbnails.subList(0, Math.min(thumbnails.size(), maxLen)));
		}
		builder.append("]");
		return builder.toString();
	}

}
