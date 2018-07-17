package org.irods.jargon.metadatatemplate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * MDTemplateElement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-17T12:07:25.464Z")

public class MDTemplateElement   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("attribute")
  private String attribute = null;

  @JsonProperty("defaultValue")
  private String defaultValue = null;

  @JsonProperty("attributeUnit")
  private String attributeUnit = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("required")
  private Boolean required = null;

  @JsonProperty("options")
  private String options = null;

  @JsonProperty("access_type")
  private String accessType = null;

  @JsonProperty("validationExp")
  private String validationExp = null;

  @JsonProperty("guid")
  private String guid = null;

  @JsonProperty("elements")
  @Valid
  private List<MDTemplateElement> elements = null;

  public MDTemplateElement id(Long id) {
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

  public MDTemplateElement attribute(String attribute) {
    this.attribute = attribute;
    return this;
  }

  /**
   * Get attribute
   * @return attribute
  **/
  @ApiModelProperty(value = "")


  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public MDTemplateElement defaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
    return this;
  }

  /**
   * Get defaultValue
   * @return defaultValue
  **/
  @ApiModelProperty(value = "")


  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public MDTemplateElement attributeUnit(String attributeUnit) {
    this.attributeUnit = attributeUnit;
    return this;
  }

  /**
   * Get attributeUnit
   * @return attributeUnit
  **/
  @ApiModelProperty(value = "")


  public String getAttributeUnit() {
    return attributeUnit;
  }

  public void setAttributeUnit(String attributeUnit) {
    this.attributeUnit = attributeUnit;
  }

  public MDTemplateElement type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public MDTemplateElement required(Boolean required) {
    this.required = required;
    return this;
  }

  /**
   * Get required
   * @return required
  **/
  @ApiModelProperty(value = "")


  public Boolean isRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public MDTemplateElement options(String options) {
    this.options = options;
    return this;
  }

  /**
   * Get options
   * @return options
  **/
  @ApiModelProperty(value = "")


  public String getOptions() {
    return options;
  }

  public void setOptions(String options) {
    this.options = options;
  }

  public MDTemplateElement accessType(String accessType) {
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

  public MDTemplateElement validationExp(String validationExp) {
    this.validationExp = validationExp;
    return this;
  }

  /**
   * Get validationExp
   * @return validationExp
  **/
  @ApiModelProperty(value = "")


  public String getValidationExp() {
    return validationExp;
  }

  public void setValidationExp(String validationExp) {
    this.validationExp = validationExp;
  }

  public MDTemplateElement guid(String guid) {
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

  public MDTemplateElement elements(List<MDTemplateElement> elements) {
    this.elements = elements;
    return this;
  }

  public MDTemplateElement addElementsItem(MDTemplateElement elementsItem) {
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
    MDTemplateElement mdTemplateElement = (MDTemplateElement) o;
    return Objects.equals(this.id, mdTemplateElement.id) &&
        Objects.equals(this.attribute, mdTemplateElement.attribute) &&
        Objects.equals(this.defaultValue, mdTemplateElement.defaultValue) &&
        Objects.equals(this.attributeUnit, mdTemplateElement.attributeUnit) &&
        Objects.equals(this.type, mdTemplateElement.type) &&
        Objects.equals(this.required, mdTemplateElement.required) &&
        Objects.equals(this.options, mdTemplateElement.options) &&
        Objects.equals(this.accessType, mdTemplateElement.accessType) &&
        Objects.equals(this.validationExp, mdTemplateElement.validationExp) &&
        Objects.equals(this.guid, mdTemplateElement.guid) &&
        Objects.equals(this.elements, mdTemplateElement.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attribute, defaultValue, attributeUnit, type, required, options, accessType, validationExp, guid, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MDTemplateElement {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
    sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
    sb.append("    attributeUnit: ").append(toIndentedString(attributeUnit)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    accessType: ").append(toIndentedString(accessType)).append("\n");
    sb.append("    validationExp: ").append(toIndentedString(validationExp)).append("\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
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

