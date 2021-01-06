/**
 * 
 */
package org.irods.jargon.extensions.thumbnail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author conwaymc
 *
 */
public class ThumbnailList {
	private List<ThumbnailListEntry> thumbnails = new ArrayList<>();

	public List<ThumbnailListEntry> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(List<ThumbnailListEntry> thumbnails) {
		this.thumbnails = thumbnails;
	}

}
