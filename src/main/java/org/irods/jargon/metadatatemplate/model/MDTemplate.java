package org.irods.jargon.metadatatemplate.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:53:32.552Z")

public class MDTemplate   {
  @JsonProperty("templateName")
  private String templateName = null;

  @JsonProperty("i18name")
  private String i18name = null;

  @JsonProperty("i18description")
  private String i18description = null;

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

  public MDTemplate templateName(String templateName) {
    this.templateName = templateName;
    return this;
  }

  /**
   * The name of the template, (e.g. Dublin Core). This does not need to be unique
   * @return templateName
  **/
  @ApiModelProperty(value = "The name of the template, (e.g. Dublin Core). This does not need to be unique")


  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public MDTemplate i18name(String i18name) {
    this.i18name = i18name;
    return this;
  }

  /**
   * An optional resource bundle property reference to an internationalized name for the template
   * @return i18name
  **/
  @ApiModelProperty(value = "An optional resource bundle property reference to an internationalized name for the template")


  public String getI18name() {
    return i18name;
  }

  public void setI18name(String i18name) {
    this.i18name = i18name;
  }

  public MDTemplate i18description(String i18description) {
    this.i18description = i18description;
    return this;
  }

  /**
   * An optional resource bundle property reference to an internationalized description for the template
   * @return i18description
  **/
  @ApiModelProperty(value = "An optional resource bundle property reference to an internationalized description for the template")


  public String getI18description() {
    return i18description;
  }

  public void setI18description(String i18description) {
    this.i18description = i18description;
  }

  public MDTemplate createTs(OffsetDateTime createTs) {
    this.createTs = createTs;
    return this;
  }

  /**
   * Timestamp for template creation
   * @return createTs
  **/
  @ApiModelProperty(value = "Timestamp for template creation")

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
   * Timestamp for template modification
   * @return modifyTs
  **/
  @ApiModelProperty(value = "Timestamp for template modification")

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
   * Version of the template
   * @return version
  **/
  @ApiModelProperty(value = "Version of the template")


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
   * User guidance on the purpose and use of the given template, can be displayed as help
   * @return description
  **/
  @ApiModelProperty(value = "User guidance on the purpose and use of the given template, can be displayed as help")


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
   * A system generated GUID for the template that serves as a unique identifier. The system should behave such that a blank GUID on creation will result in the generation of a GUID, while specification of a GUID on create would use that provided GUID as the unique identifier.
   * @return guid
  **/
  @ApiModelProperty(value = "A system generated GUID for the template that serves as a unique identifier. The system should behave such that a blank GUID on creation will result in the generation of a GUID, while specification of a GUID on create would use that provided GUID as the unique identifier.")


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
   * Accesibility of template (e.g. public vs. user). This acts as a role identifier for future expansion
   * @return accessType
  **/
  @ApiModelProperty(value = "Accesibility of template (e.g. public vs. user). This acts as a role identifier for future expansion")


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
   * user id of the owner of the template. This is typically the iRODS user and can interact with the access type for basic control on template usage
   * @return owner
  **/
  @ApiModelProperty(value = "user id of the owner of the template. This is typically the iRODS user and can interact with the access type for basic control on template usage")


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
   * A colletion of child template elements
   * @return elements
  **/
  @ApiModelProperty(value = "A colletion of child template elements")

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
        Objects.equals(this.i18name, mdTemplate.i18name) &&
        Objects.equals(this.i18description, mdTemplate.i18description) &&
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
    return Objects.hash(templateName, i18name, i18description, createTs, modifyTs, version, description, guid, accessType, owner, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MDTemplate {\n");
    
    sb.append("    templateName: ").append(toIndentedString(templateName)).append("\n");
    sb.append("    i18name: ").append(toIndentedString(i18name)).append("\n");
    sb.append("    i18description: ").append(toIndentedString(i18description)).append("\n");
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

