/*
 * Countable Resource API
 * Countable Resource RESTful WebService
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * CountableResource
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-01-11T19:08:34.256Z[GMT]")
public class CountableResource {
  @SerializedName("name")
  private String name = null;

  @SerializedName("count")
  private Long count = null;

  public CountableResource name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(required = true, description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CountableResource count(Long count) {
    this.count = count;
    return this;
  }

   /**
   * Get count
   * minimum: 1
   * @return count
  **/
  @Schema(required = true, description = "")
  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountableResource countableResource = (CountableResource) o;
    return Objects.equals(this.name, countableResource.name) &&
        Objects.equals(this.count, countableResource.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, count);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CountableResource {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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
