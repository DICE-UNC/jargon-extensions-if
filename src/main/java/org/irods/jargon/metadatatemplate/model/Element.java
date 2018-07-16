package org.irods.jargon.metadatatemplate.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Element;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Element
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-16T19:38:53.663Z")

public class Element   {
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
  private List<Element> elements = null;

  public Element id(Long id) {
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

  public Element attribute(String attribute) {
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

  public Element defaultValue(String defaultValue) {
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

  public Element attributeUnit(String attributeUnit) {
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

  public Element type(String type) {
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

  public Element required(Boolean required) {
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

  public Element options(String options) {
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

  public Element accessType(String accessType) {
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

  public Element validationExp(String validationExp) {
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

  public Element guid(String guid) {
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

  public Element elements(List<Element> elements) {
    this.elements = elements;
    return this;
  }

  public Element addElementsItem(Element elementsItem) {
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
    Element element = (Element) o;
    return Objects.equals(this.id, element.id) &&
        Objects.equals(this.attribute, element.attribute) &&
        Objects.equals(this.defaultValue, element.defaultValue) &&
        Objects.equals(this.attributeUnit, element.attributeUnit) &&
        Objects.equals(this.type, element.type) &&
        Objects.equals(this.required, element.required) &&
        Objects.equals(this.options, element.options) &&
        Objects.equals(this.accessType, element.accessType) &&
        Objects.equals(this.validationExp, element.validationExp) &&
        Objects.equals(this.guid, element.guid) &&
        Objects.equals(this.elements, element.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attribute, defaultValue, attributeUnit, type, required, options, accessType, validationExp, guid, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Element {\n");
    
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

