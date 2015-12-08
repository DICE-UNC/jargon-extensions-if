/**
 * 
 */
package org.irods.jargon.filetemplate;

/**
 * Describes a template that can be used to create a file
 * 
 * @author Mike Conway - DICE
 *
 */
public class FileTemplate {

	/**
	 * standard mime type of the file
	 */
	private String mimeType = "";
	/**
	 * optional infotype of the file, this is the content of the file, as
	 * opposed to the base format. Eg it can be a mime type of XML, but be a
	 * certain kind of XML. This is a site-defined type to allow special
	 * templates to be stored and provisioned.
	 */
	private String infoType = "";

	/**
	 * Common name of the template, may be non-unique
	 */
	private String templateName = "Default File Template";

	/**
	 * Key for an internatinalized depiction of the template name
	 */
	private String i18nTemplateName = "file.template.name";

	/**
	 * Implementation dependent unique identifier of the template, may be a path
	 * or unique key or GUID
	 */
	private String templateUniqueIdentifier = "";

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

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getI18nTemplateName() {
		return i18nTemplateName;
	}

	public void setI18nTemplateName(String i18nTemplateName) {
		this.i18nTemplateName = i18nTemplateName;
	}

	public String getTemplateUniqueIdentifier() {
		return templateUniqueIdentifier;
	}

	public void setTemplateUniqueIdentifier(String templateUniqueIdentifier) {
		this.templateUniqueIdentifier = templateUniqueIdentifier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileTemplate [");
		if (mimeType != null) {
			builder.append("mimeType=").append(mimeType).append(", ");
		}
		if (infoType != null) {
			builder.append("infoType=").append(infoType).append(", ");
		}
		if (templateName != null) {
			builder.append("templateName=").append(templateName).append(", ");
		}
		if (i18nTemplateName != null) {
			builder.append("i18nTemplateName=").append(i18nTemplateName)
					.append(", ");
		}
		if (templateUniqueIdentifier != null) {
			builder.append("templateUniqueIdentifier=").append(
					templateUniqueIdentifier);
		}
		builder.append("]");
		return builder.toString();
	}

}
