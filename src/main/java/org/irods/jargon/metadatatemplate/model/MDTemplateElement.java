package org.irods.jargon.metadatatemplate.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MDTemplateElement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:53:32.552Z")

public class MDTemplateElement   {
  @JsonProperty("guid")
  private String guid = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("i18name")
  private String i18name = null;

  @JsonProperty("i18description")
  private String i18description = null;

  @JsonProperty("description")
  private String description = null;

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

  public MDTemplateElement i18name(String i18name) {
    this.i18name = i18name;
    return this;
  }

  /**
   * An optional resource bundle property reference to an internationalized name for the template element
   * @return i18name
  **/
  @ApiModelProperty(value = "An optional resource bundle property reference to an internationalized name for the template element")


  public String getI18name() {
    return i18name;
  }

  public void setI18name(String i18name) {
    this.i18name = i18name;
  }

  public MDTemplateElement i18description(String i18description) {
    this.i18description = i18description;
    return this;
  }

  /**
   * An optional resource bundle property reference to an internationalized description for the template element
   * @return i18description
  **/
  @ApiModelProperty(value = "An optional resource bundle property reference to an internationalized description for the template element")


  public String getI18description() {
    return i18description;
  }

  public void setI18description(String i18description) {
    this.i18description = i18description;
  }

  public MDTemplateElement description(String description) {
    this.description = description;
    return this;
  }

  /**
   * User guidance on the purpose and use of the given field, can be displayed as help
   * @return description
  **/
  @ApiModelProperty(value = "User guidance on the purpose and use of the given field, can be displayed as help")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
   * Type of element [string, int, list, boolean, float, range etc]
   * @return type
  **/
  @ApiModelProperty(value = "Type of element [string, int, list, boolean, float, range etc]")


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
        Objects.equals(this.i18name, mdTemplateElement.i18name) &&
        Objects.equals(this.i18description, mdTemplateElement.i18description) &&
        Objects.equals(this.description, mdTemplateElement.description) &&
        Objects.equals(this.defaultValue, mdTemplateElement.defaultValue) &&
        Objects.equals(this.type, mdTemplateElement.type) &&
        Objects.equals(this.unit, mdTemplateElement.unit) &&
        Objects.equals(this.required, mdTemplateElement.required) &&
        Objects.equals(this.options, mdTemplateElement.options) &&
        Objects.equals(this.validationExp, mdTemplateElement.validationExp) &&
        Objects.equals(this.cardinalityMin, mdTemplateElement.cardinalityMin) &&
        Objects.equals(this.cardinalityMax, mdTemplateElement.cardinalityMax) &&
        Objects.equals(this.elements, mdTemplateElement.elements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, name, i18name, i18description, description, defaultValue, type, unit, required, options, validationExp, cardinalityMin, cardinalityMax, elements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MDTemplateElement {\n");
    
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    i18name: ").append(toIndentedString(i18name)).append("\n");
    sb.append("    i18description: ").append(toIndentedString(i18description)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("    required: ").append(toIndentedString(required)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
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

