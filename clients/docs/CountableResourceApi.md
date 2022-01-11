# CountableResourceApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addCountableResource**](CountableResourceApi.md#addCountableResource) | **POST** /countable-resources | Add a countable resource
[**getCountableResourceByName**](CountableResourceApi.md#getCountableResourceByName) | **GET** /countable-resources/{name} | Find countable resource by name
[**updateCountableResource**](CountableResourceApi.md#updateCountableResource) | **PUT** /countable-resources/{name} | Update an existing countable resource

<a name="addCountableResource"></a>
# **addCountableResource**
> addCountableResource(body)

Add a countable resource

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CountableResourceApi;


CountableResourceApi apiInstance = new CountableResourceApi();
CountableResource body = new CountableResource(); // CountableResource | 
try {
    apiInstance.addCountableResource(body);
} catch (ApiException e) {
    System.err.println("Exception when calling CountableResourceApi#addCountableResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CountableResource**](CountableResource.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="getCountableResourceByName"></a>
# **getCountableResourceByName**
> getCountableResourceByName(name)

Find countable resource by name

Returns a single countable resource

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CountableResourceApi;


CountableResourceApi apiInstance = new CountableResourceApi();
String name = "name_example"; // String | The name of the countable resource
try {
    apiInstance.getCountableResourceByName(name);
} catch (ApiException e) {
    System.err.println("Exception when calling CountableResourceApi#getCountableResourceByName");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| The name of the countable resource |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="updateCountableResource"></a>
# **updateCountableResource**
> updateCountableResource(body, ifMatch, name)

Update an existing countable resource

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CountableResourceApi;


CountableResourceApi apiInstance = new CountableResourceApi();
CountableResource body = new CountableResource(); // CountableResource | Countable resource object that needs to be updated
String ifMatch = "ifMatch_example"; // String | 
String name = "name_example"; // String | The name of the countable resource
try {
    apiInstance.updateCountableResource(body, ifMatch, name);
} catch (ApiException e) {
    System.err.println("Exception when calling CountableResourceApi#updateCountableResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CountableResource**](CountableResource.md)| Countable resource object that needs to be updated |
 **ifMatch** | **String**|  |
 **name** | **String**| The name of the countable resource |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

