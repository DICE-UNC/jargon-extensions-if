/**
 * 
 */
package org.irods.jargon.extensions.datatyper;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the mime and info type of a data object or collection. mime-type
 * is the type of the file, info-type represents the type of information
 * embodied in a file (a spreadsheet may have an info type of financial data, or
 * an info type of population statistics).
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DataType {

	/**
	 * primary mime type, may be the only one
	 */
	private String mimeType = "";
	/**
	 * primary info type, may be the only one, may not be used at a site
	 */
	private String infoType = "";
	/**
	 * Additional mime types, if utilized
	 */
	private List<String> alternativeMimeTypes = new ArrayList<>();
	/**
	 * Additional info types, if utilized
	 */
	private List<String> alternativeInfoTypes = new ArrayList<>();

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public List<String> getAlternativeMimeTypes() {
		return alternativeMimeTypes;
	}

	public void setAlternativeMimeTypes(List<String> alternativeMimeTypes) {
		this.alternativeMimeTypes = alternativeMimeTypes;
	}

	public List<String> getAlternativeInfoTypes() {
		return alternativeInfoTypes;
	}

	public void setAlternativeInfoTypes(List<String> alternativeInfoTypes) {
		this.alternativeInfoTypes = alternativeInfoTypes;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("DataType [");
		if (mimeType != null) {
			builder.append("mimeType=").append(mimeType).append(", ");
		}
		if (infoType != null) {
			builder.append("infoType=").append(infoType).append(", ");
		}
		if (alternativeMimeTypes != null) {
			builder.append("alternativeMimeTypes=")
					.append(alternativeMimeTypes.subList(0, Math.min(alternativeMimeTypes.size(), maxLen)))
					.append(", ");
		}
		if (alternativeInfoTypes != null) {
			builder.append("alternativeInfoTypes=")
					.append(alternativeInfoTypes.subList(0, Math.min(alternativeInfoTypes.size(), maxLen)));
		}
		builder.append("]");
		return builder.toString();
	}

}
