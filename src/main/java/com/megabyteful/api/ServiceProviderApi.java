package com.megabyteful.api;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megabyteful.model.CreateServiceProviderDTO;
import com.megabyteful.model.ServiceProviderDTO;
import com.megabyteful.model.UpdateServiceProviderDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Validated
public interface ServiceProviderApi {

	Logger log = LoggerFactory.getLogger(ServiceProviderApi.class);
	
	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}
	
	default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }
    
    @Operation(summary = "Create a new service provider", description = "", tags={ "ServiceProvider" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Service Provider created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceProviderDTO.class))) })
    @RequestMapping(value = "/servicesProviders",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    default ResponseEntity<ServiceProviderDTO> createServiceProvider(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateServiceProviderDTO body
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"author\" : \"John Doe\",\r\n  \"price\" : 19.99,\r\n  \"id\" : 1,\r\n  \"title\" : \"Java Programming\",\r\n  \"category\" : \"Programming\"\r\n}", ServiceProviderDTO.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ServiceProviderApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    @Operation(summary = "Delete a service provider by ID", description = "", tags={ "ServiceProvider" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Service Provider deleted"),
        
        @ApiResponse(responseCode = "400", description = "Bad request") })
    @RequestMapping(value = "/servicesProviders/{id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteServiceProviderById(@Parameter(in = ParameterIn.PATH, description = "The ID of the service provider to delete", required=true, schema=@Schema()) @PathVariable("id") Long id
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ServiceProviderApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Operation(summary = "Get a service provider by ID", description = "", tags={ "ServiceProvider" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Service Provider found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceProviderDTO.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad request"),
        
        @ApiResponse(responseCode = "404", description = "Service Provider not found") })
    @RequestMapping(value = "/servicesProviders/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<ServiceProviderDTO> getServiceProviderById(@Parameter(in = ParameterIn.PATH, description = "The ID of the service provider to retrieve", required=true, schema=@Schema()) @PathVariable("id") Long id
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"author\" : \"John Doe\",\r\n  \"price\" : 19.99,\r\n  \"id\" : 1,\r\n  \"title\" : \"Java Programming\",\r\n  \"category\" : \"Programming\"\r\n}", ServiceProviderDTO.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ServiceProviderApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Update a service provider by ID", description = "", tags={ "ServiceProvider" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Service Provider created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceProviderDTO.class))) })
    @RequestMapping(value = "/servicesProviders/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    default ResponseEntity<ServiceProviderDTO> updateServiceProviderById(@Parameter(in = ParameterIn.PATH, description = "The ID of the service provider to update", required=true, schema=@Schema()) @PathVariable("id") Long id
, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody UpdateServiceProviderDTO body
) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"author\" : \"John Doe\",\r\n  \"price\" : 19.99,\r\n  \"id\" : 1,\r\n  \"title\" : \"Java Programming\",\r\n  \"category\" : \"Programming\"\r\n}", ServiceProviderDTO.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ServiceProviderApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
