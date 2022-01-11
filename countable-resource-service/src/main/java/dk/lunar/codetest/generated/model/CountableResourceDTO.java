package dk.lunar.codetest.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * CountableResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-11T14:01:13.783Z[GMT]")


public class CountableResourceDTO {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("count")
  private Long count = null;

  public CountableResourceDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
      @NotNull

  @Size(min=3,max=20)   public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CountableResourceDTO count(Long count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * minimum: 1
   * @return count
   **/
  @Schema(required = true, description = "")
      @NotNull

  @Min(1L)  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountableResourceDTO countableResource = (CountableResourceDTO) o;
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
