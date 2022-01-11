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

package io.swagger.client.api;

import io.swagger.client.model.CountableResource;
import org.junit.Test;
import org.junit.Ignore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for CountableResourceApi
 */
@Ignore
public class CountableResourceApiTest {

    private final CountableResourceApi api = new CountableResourceApi();

    /**
     * Add a countable resource
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void addCountableResourceTest() throws Exception {
        CountableResource body = null;
        api.addCountableResource(body);

        // TODO: test validations
    }
    /**
     * Find countable resource by name
     *
     * Returns a single countable resource
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getCountableResourceByNameTest() throws Exception {
        String name = null;
        api.getCountableResourceByName(name);

        // TODO: test validations
    }
    /**
     * Update an existing countable resource
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void updateCountableResourceTest() throws Exception {
        CountableResource body = null;
        String ifMatch = null;
        String name = null;
        api.updateCountableResource(body, ifMatch, name);

        // TODO: test validations
    }
}
