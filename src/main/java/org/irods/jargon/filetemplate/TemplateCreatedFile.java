/**
 *
 */
package org.irods.jargon.filetemplate;

/**
 * Represents a file created via a template, this is used as a returned value
 * from the creation service
 *
 * @author Mike Conway - DICE
 *
 */
public class TemplateCreatedFile {

	/**
	 * Absolute iRODS path to the parent collection
	 */
	private String parentCollectionAbsolutePath = "";
	/**
	 * Absolute iRODS path to the file chile name
	 */
	private String fileName = "";

	/**
	 * {@link FileTemplate} used to create this file
	 */
	private FileTemplate fileTemplate;

	public String getParentCollectionAbsolutePath() {
		return parentCollectionAbsolutePath;
	}

	public void setParentCollectionAbsolutePath(final String parentCollectionAbsolutePath) {
		this.parentCollectionAbsolutePath = parentCollectionAbsolutePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public FileTemplate getFileTemplate() {
		return fileTemplate;
	}

	public void setFileTemplate(final FileTemplate fileTemplate) {
		this.fileTemplate = fileTemplate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemplateCreatedFile [");
		if (parentCollectionAbsolutePath != null) {
			builder.append("parentCollectionAbsolutePath=").append(parentCollectionAbsolutePath).append(", ");
		}
		if (fileName != null) {
			builder.append("fileName=").append(fileName).append(", ");
		}
		if (fileTemplate != null) {
			builder.append("fileTemplate=").append(fileTemplate);
		}
		builder.append("]");
		return builder.toString();
	}

}
