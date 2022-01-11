/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.31).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package dk.lunar.codetest.generated.api;

import dk.lunar.codetest.generated.model.CountableResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-11T14:01:13.783Z[GMT]")
@Validated
public interface CountableResourcesApi {

    @Operation(summary = "Add a countable resource", description = "", tags={ "countable-resource" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created"),
        
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        
        @ApiResponse(responseCode = "409", description = "Conflict") })
    @RequestMapping(value = "/countable-resources",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> addCountableResource(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CountableResource body);

    @Operation(summary = "Find countable resource by name", description = "Returns a single countable resource", tags={ "countable-resource" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK"),
        
        @ApiResponse(responseCode = "400", description = "Invalid name supplied"),
        
        @ApiResponse(responseCode = "404", description = "Countable resource not found") })
    @RequestMapping(value = "/countable-resources/{name}",
        method = RequestMethod.GET)
    ResponseEntity<Void> getCountableResourceByName(@Size(min=3,max=20) @Parameter(in = ParameterIn.PATH, description = "The name of the countable resource", required=true, schema=@Schema()) @PathVariable("name") String name);

    @Operation(summary = "Update an existing countable resource", description = "", tags={ "countable-resource" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK"),
        
        @ApiResponse(responseCode = "400", description = "Invalid name supplied"),
        
        @ApiResponse(responseCode = "404", description = "Countable resource not found") })
    @RequestMapping(value = "/countable-resources/{name}",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateCountableResource(@Size(min=3,max=20) @Parameter(in = ParameterIn.PATH, description = "The name of the countable resource", required=true, schema=@Schema()) @PathVariable("name") String name, @Parameter(in = ParameterIn.DEFAULT, description = "Countable resource object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody CountableResource body);

}
