package org.irods.jargon.metadatatemplate.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Element;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Template
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-16T19:38:53.663Z")

public class Template   {
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
  private List<Element> elements = null;

  public Template id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Template templateName(String templateName) {
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

  public Template createTs(OffsetDateTime createTs) {
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

  public Template modifyTs(OffsetDateTime modifyTs) {
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

  public Template version(Integer version) {
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

  public Template description(String description) {
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

  public Template guid(String guid) {
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

  public Template accessType(String accessType) {
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

  public Template owner(String owner) {
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

  public Template elements(List<Element> elements) {
    this.elements = elements;
    return this;
  }

  public Template addElementsItem(Element elementsItem) {
    if (this.elements == null) {
      this.elements = new ArrayList<Element>();
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

  public List<Element> getElements() {
    return elements;
  }

  public void setElements(List<Element> elements) {
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
    Template template = (Template) o;
    return Objects.equals(this.id, template.id) &&
        Objects.equals(this.templateName, template.templateName) &&
        Objects.equals(this.createTs, template.createTs) &&
        Objects.equals(this.modifyTs, template.modifyTs) &&
        Objects.equals(this.version, template.version) &&
        Objects.equals(this.description, template.description) &&
        Objects.equals(this.guid, template.guid) &&
        Objects.equals(this.accessType, template.accessType) &&
        Objects.equals(this.owner, template.owner) &&
        Objects.equals(this.elements, template.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, templateName, createTs, modifyTs, version, description, guid, accessType, owner, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Template {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

