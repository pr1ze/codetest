package dk.lunar.codetest.application.web

import dk.lunar.codetest.domain.CountableResource
import dk.lunar.codetest.domain.service.CountableResourceService
import dk.lunar.codetest.generated.api.CountableResourcesApi
import dk.lunar.codetest.generated.model.CountableResourceDTO
import groovy.util.logging.Log
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
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

    private final HttpServletRequest request
    private final CountableResourceService countableResourceService

    @Autowired
    CountableResourcesController(HttpServletRequest request, CountableResourceService countableResourceService) {
        this.request = request
        this.countableResourceService = countableResourceService
    }

    @Override
    ResponseEntity<Void> addCountableResource(@Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema()) @Valid @RequestBody CountableResourceDTO body) {
        log.info("Adding $body")
        CountableResource created = countableResourceService.create(body.name, body.count)
        return ResponseEntity.created(URI.create('/countable-resource/' + new String(Base64.urlEncoder.encode(created.name.bytes))))
                .eTag(created.md5().toString())
                .build()
    }

    @Override
    ResponseEntity<CountableResourceDTO> getCountableResourceByName(@Size(min = 3, max = 20) @Parameter(in = ParameterIn.PATH, description = "The name of the countable resource", required = true, schema = @Schema()) @PathVariable("name") String name) {
        log.info("Getting $name")
        CountableResource countableResource = countableResourceService.get(name)
        return ResponseEntity.ok()
                .eTag(countableResource.md5().toString())
                .body(new CountableResourceDTO()
                        .name(countableResource.name)
                        .count(countableResource.count)
                )
    }

    @Override
    ResponseEntity<Void> updateCountableResource(@Size(min = 3, max = 20) @Parameter(in = ParameterIn.PATH, description = "The name of the countable resource", required = true, schema = @Schema()) @PathVariable("name") String name, @Parameter(in = ParameterIn.DEFAULT, description = "Countable resource object that needs to be updated", required = true, schema = @Schema()) @Valid @RequestBody CountableResourceDTO body) {
        log.info("Updating $body ${request.getHeader(HttpHeaders.IF_MATCH)}")
        CountableResource updated = countableResourceService.update(
                new CountableResource(
                        name: body.name,
                        count: body.count,
                        version: request.getHeader(HttpHeaders.IF_MATCH)
                )
        )

        return ResponseEntity.ok()
                .eTag(updated.md5())
                .build()
    }

}
