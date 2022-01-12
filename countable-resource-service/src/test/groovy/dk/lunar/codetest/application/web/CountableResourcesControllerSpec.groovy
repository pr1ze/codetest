package dk.lunar.codetest.application.web

import com.fasterxml.jackson.databind.ObjectMapper
import dk.lunar.codetest.domain.CountableResource
import dk.lunar.codetest.domain.service.CountableResourceService
import dk.lunar.codetest.generated.model.CountableResourceDTO
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

class CountableResourcesControllerSpec extends Specification {

    CountableResourcesController countableResourcesController
    CountableResourceService countableResourceService
    HttpServletRequest request

    MockMvc mockMvc
    ObjectMapper objectMapper

    def setup() {
        objectMapper = new ObjectMapper()
        countableResourceService = Mock()
        request = Mock()
        countableResourcesController = new CountableResourcesController(request, countableResourceService)

        mockMvc = MockMvcBuilders
                .standaloneSetup(countableResourcesController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
    }

    def "AddCountableResource"() {
        given:
        CountableResourceDTO countableResourceDTO = new CountableResourceDTO(name: 'some-name', count: 1337)
        def countableResourceDTOJson = objectMapper.writeValueAsString(countableResourceDTO)

        when:
        def response = mockMvc
                .perform(MockMvcRequestBuilders.post('/countable-resources')
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(countableResourceDTOJson))

        then:
        1 * countableResourceService.create('some-name', 1337) >> new CountableResource(name: 'some-name', count: 1337, eTag: 123)
        response.andExpect(MockMvcResultMatchers.status().isCreated())
        response.andReturn().response.getHeader('ETag') == "\"123\""
    }

    def "GetCountableResourceByName"() {
        expect: true
    }

    def "UpdateCountableResource"() {
        expect: true
    }

}
