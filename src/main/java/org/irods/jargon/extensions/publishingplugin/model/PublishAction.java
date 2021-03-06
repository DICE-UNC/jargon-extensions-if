/*
 * iRODS - Simple Publishing API
 * A pluggable service that can take a cart of iRODS files and apply a custom action to them, something akin to a publishing API
 *
 * OpenAPI spec version: 1.0.0
 * Contact: mike.conway@nih.gov
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.irods.jargon.extensions.publishingplugin.model;

import java.util.Objects;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PublishAction
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-02-08T14:49:24.146Z[GMT]")
public class PublishAction {
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("additionalProperties")
	private FreeformProps additionalProperties = null;

	public PublishAction id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public PublishAction additionalProperties(FreeformProps additionalProperties) {
		this.additionalProperties = additionalProperties;
		return this;
	}

	/**
	 * Get additionalProperties
	 * 
	 * @return additionalProperties
	 **/
	@Schema(description = "")
	public FreeformProps getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(FreeformProps additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PublishAction publishAction = (PublishAction) o;
		return Objects.equals(this.id, publishAction.id)
				&& Objects.equals(this.additionalProperties, publishAction.additionalProperties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, additionalProperties);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PublishAction {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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
