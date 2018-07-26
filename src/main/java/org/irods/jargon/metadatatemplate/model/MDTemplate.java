package org.irods.jargon.metadatatemplate.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.irods.jargon.metadatatemplate.model.MDTemplateElement;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MDTemplate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-17T19:36:32.386Z")

public class MDTemplate   {

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("templateName")
	private String templateName = null;

	@JsonProperty("createTs")
	private OffsetDateTime createTs = null;

	@JsonProperty("modifyTs")
	private OffsetDateTime modifyTs = null;

	@JsonProperty("version")
	private Integer version = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("guid")
	private String guid = null;

	@JsonProperty("access_type")
	private String accessType = null;

	@JsonProperty("owner")
	private String owner = null;

	@JsonProperty("elements")
	@Valid
	private List<MDTemplateElement> elements = null;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MDTemplate templateName(String templateName) {
		this.templateName = templateName;
		return this;
	}

	/**
	 * Get templateName
	 * @return templateName
	 **/
	@ApiModelProperty(value = "")


	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public MDTemplate createTs(OffsetDateTime createTs) {
		this.createTs = createTs;
		return this;
	}

	/**
	 * Get createTs
	 * @return createTs
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public OffsetDateTime getCreateTs() {
		return createTs;
	}

	public void setCreateTs(OffsetDateTime createTs) {
		this.createTs = createTs;
	}

	public MDTemplate modifyTs(OffsetDateTime modifyTs) {
		this.modifyTs = modifyTs;
		return this;
	}

	/**
	 * Get modifyTs
	 * @return modifyTs
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public OffsetDateTime getModifyTs() {
		return modifyTs;
	}

	public void setModifyTs(OffsetDateTime modifyTs) {
		this.modifyTs = modifyTs;
	}

	public MDTemplate version(Integer version) {
		this.version = version;
		return this;
	}

	/**
	 * Get version
	 * @return version
	 **/
	@ApiModelProperty(value = "")


	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public MDTemplate description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 * @return description
	 **/
	@ApiModelProperty(value = "")


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MDTemplate guid(String guid) {
		this.guid = guid;
		return this;
	}

	/**
	 * Get guid
	 * @return guid
	 **/
	@ApiModelProperty(value = "")


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public MDTemplate accessType(String accessType) {
		this.accessType = accessType;
		return this;
	}

	/**
	 * Get accessType
	 * @return accessType
	 **/
	@ApiModelProperty(value = "")


	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public MDTemplate owner(String owner) {
		this.owner = owner;
		return this;
	}

	/**
	 * Get owner
	 * @return owner
	 **/
	@ApiModelProperty(value = "")


	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public MDTemplate elements(List<MDTemplateElement> elements) {
		this.elements = elements;
		return this;
	}

	public MDTemplate addElementsItem(MDTemplateElement elementsItem) {
		if (this.elements == null) {
			this.elements = new ArrayList<MDTemplateElement>();
		}
		this.elements.add(elementsItem);
		return this;
	}

	/**
	 * Get elements
	 * @return elements
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<MDTemplateElement> getElements() {
		return elements;
	}

	public void setElements(List<MDTemplateElement> elements) {
		this.elements = elements;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MDTemplate mdTemplate = (MDTemplate) o;
		return Objects.equals(this.templateName, mdTemplate.templateName) &&
				Objects.equals(this.createTs, mdTemplate.createTs) &&
				Objects.equals(this.modifyTs, mdTemplate.modifyTs) &&
				Objects.equals(this.version, mdTemplate.version) &&
				Objects.equals(this.description, mdTemplate.description) &&
				Objects.equals(this.guid, mdTemplate.guid) &&
				Objects.equals(this.accessType, mdTemplate.accessType) &&
				Objects.equals(this.owner, mdTemplate.owner) &&
				Objects.equals(this.elements, mdTemplate.elements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(templateName, createTs, modifyTs, version, description, guid, accessType, owner, elements);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MDTemplate {\n");

		sb.append("    templateName: ").append(toIndentedString(templateName)).append("\n");
		sb.append("    createTs: ").append(toIndentedString(createTs)).append("\n");
		sb.append("    modifyTs: ").append(toIndentedString(modifyTs)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
		sb.append("    accessType: ").append(toIndentedString(accessType)).append("\n");
		sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
		sb.append("    elements: ").append(toIndentedString(elements)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}

