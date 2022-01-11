package dk.lunar.codetest.codetest.rest

import com.fasterxml.jackson.databind.ObjectMapper
import dk.lunar.codetest.generated.api.CountableResourcesApi
import dk.lunar.codetest.generated.model.CountableResource
import groovy.util.logging.Log
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.Size

@Log
@RestController
class CountableResourcesController implements CountableResourcesApi {

    private final ObjectMapper objectMapper
    private final HttpServletRequest request

    @Autowired
    CountableResourcesController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper
        this.request = request
    }

    @Override
    ResponseEntity<Void> addCountableResource(@Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema()) @Valid @RequestBody CountableResource body) {
        return null
    }

    @Override
    ResponseEntity<Void> getCountableResourceByName(@Size(min = 3, max = 20) @Parameter(in = ParameterIn.PATH, description = "The name of the countable resource", required = true, schema = @Schema()) @PathVariable("name") String name) {
        return null
    }

    @Override
    ResponseEntity<Void> updateCountableResource(@Size(min = 3, max = 20) @Parameter(in = ParameterIn.PATH, description = "The name of the countable resource", required = true, schema = @Schema()) @PathVariable("name") String name, @Parameter(in = ParameterIn.DEFAULT, description = "Countable resource object that needs to be updated", required = true, schema = @Schema()) @Valid @RequestBody CountableResource body) {
        return null
    }

}
