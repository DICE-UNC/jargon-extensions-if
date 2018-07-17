package org.irods.jargon.metadatatemplate.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.irods.jargon.metadatatemplate.model.MDTemplateElement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MDTemplateElement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-17T19:36:32.386Z")

public class MDTemplateElement   {
  @JsonProperty("guid")
  private String guid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("defaultValue")
  private String defaultValue = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("unit")
  private String unit = null;

  @JsonProperty("required")
  private Boolean required = null;

  @JsonProperty("options")
  private String options = null;

  @JsonProperty("access_type")
  private String accessType = null;

  @JsonProperty("validationExp")
  private String validationExp = null;

  @JsonProperty("cardinalityMin")
  private Integer cardinalityMin = null;

  @JsonProperty("cardinalityMax")
  private Integer cardinalityMax = null;

  @JsonProperty("elements")
  @Valid
  private List<MDTemplateElement> elements = null;

  public MDTemplateElement guid(String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * GUID uniquely identifying the element
   * @return guid
  **/
  @ApiModelProperty(value = "GUID uniquely identifying the element")


  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public MDTemplateElement name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Label for the element, analagous to the avu attribute
   * @return name
  **/
  @ApiModelProperty(value = "Label for the element, analagous to the avu attribute")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MDTemplateElement defaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
    return this;
  }

  /**
   * Value to use or select given no user input
   * @return defaultValue
  **/
  @ApiModelProperty(value = "Value to use or select given no user input")


  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public MDTemplateElement type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of element [string, int, list, etc]
   * @return type
  **/
  @ApiModelProperty(value = "Type of element [string, int, list, etc]")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public MDTemplateElement unit(String unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Unit of measure associated with entered value, e.g. a length field may have a unit of meters
   * @return unit
  **/
  @ApiModelProperty(value = "Unit of measure associated with entered value, e.g. a length field may have a unit of meters")


  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public MDTemplateElement required(Boolean required) {
    this.required = required;
    return this;
  }

  /**
   * Indicates that a value must be enetered
   * @return required
  **/
  @ApiModelProperty(value = "Indicates that a value must be enetered")


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
   * Type-specific options for a field, e.g. if a list this is a delimited array of possible values. For an ontology it may be a reference to a vocabulary
   * @return options
  **/
  @ApiModelProperty(value = "Type-specific options for a field, e.g. if a list this is a delimited array of possible values. For an ontology it may be a reference to a vocabulary")


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
   * Type of metadata reference, e.g. a literal or an http referenceable link
   * @return accessType
  **/
  @ApiModelProperty(value = "Type of metadata reference, e.g. a literal or an http referenceable link")


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
   * Type-specific validation hint, primarily via regex
   * @return validationExp
  **/
  @ApiModelProperty(value = "Type-specific validation hint, primarily via regex")


  public String getValidationExp() {
    return validationExp;
  }

  public void setValidationExp(String validationExp) {
    this.validationExp = validationExp;
  }

  public MDTemplateElement cardinalityMin(Integer cardinalityMin) {
    this.cardinalityMin = cardinalityMin;
    return this;
  }

  /**
   * Minimum cardinality, minumum number of element x to be defined
   * @return cardinalityMin
  **/
  @ApiModelProperty(value = "Minimum cardinality, minumum number of element x to be defined")


  public Integer getCardinalityMin() {
    return cardinalityMin;
  }

  public void setCardinalityMin(Integer cardinalityMin) {
    this.cardinalityMin = cardinalityMin;
  }

  public MDTemplateElement cardinalityMax(Integer cardinalityMax) {
    this.cardinalityMax = cardinalityMax;
    return this;
  }

  /**
   * Maximum cardinality, maximum number of element x to be defined
   * @return cardinalityMax
  **/
  @ApiModelProperty(value = "Maximum cardinality, maximum number of element x to be defined")


  public Integer getCardinalityMax() {
    return cardinalityMax;
  }

  public void setCardinalityMax(Integer cardinalityMax) {
    this.cardinalityMax = cardinalityMax;
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
   * An array of child elements in the case of a grouping
   * @return elements
  **/
  @ApiModelProperty(value = "An array of child elements in the case of a grouping")

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
    return Objects.equals(this.guid, mdTemplateElement.guid) &&
        Objects.equals(this.name, mdTemplateElement.name) &&
        Objects.equals(this.defaultValue, mdTemplateElement.defaultValue) &&
        Objects.equals(this.type, mdTemplateElement.type) &&
        Objects.equals(this.unit, mdTemplateElement.unit) &&
        Objects.equals(this.required, mdTemplateElement.required) &&
        Objects.equals(this.options, mdTemplateElement.options) &&
        Objects.equals(this.accessType, mdTemplateElement.accessType) &&
        Objects.equals(this.validationExp, mdTemplateElement.validationExp) &&
        Objects.equals(this.cardinalityMin, mdTemplateElement.cardinalityMin) &&
        Objects.equals(this.cardinalityMax, mdTemplateElement.cardinalityMax) &&
        Objects.equals(this.elements, mdTemplateElement.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, name, defaultValue, type, unit, required, options, accessType, validationExp, cardinalityMin, cardinalityMax, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MDTemplateElement {\n");
    
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    accessType: ").append(toIndentedString(accessType)).append("\n");
    sb.append("    validationExp: ").append(toIndentedString(validationExp)).append("\n");
    sb.append("    cardinalityMin: ").append(toIndentedString(cardinalityMin)).append("\n");
    sb.append("    cardinalityMax: ").append(toIndentedString(cardinalityMax)).append("\n");
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

