package com.netflix.global.pricing.netflixpricing.controller;

import com.netflix.global.pricing.netflixpricing.constants.URLConstants;
import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import com.netflix.global.pricing.netflixpricing.requests.implementations.NetflixServicePlansRq;
import com.netflix.global.pricing.netflixpricing.requests.implementations.ServicePlanDefinitionRq;
import com.netflix.global.pricing.netflixpricing.response.KeyValuePair;
import com.netflix.global.pricing.netflixpricing.service.interfaces.INetflixPlanWriteService;
import com.netflix.global.pricing.netflixpricing.service.interfaces.INetflixPlansReadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by nimra on 02/10/18.
 */
@RestController
@RequestMapping(value= URLConstants.API_VERSION)
public class NetflixServicePlansController {


    @Autowired
    private INetflixPlansReadService readServicePlanService;

    @Autowired
    private INetflixPlanWriteService writeServicePlanService;

    public NetflixServicePlansController(INetflixPlansReadService readServicePlanService,INetflixPlanWriteService writeServicePlanService) {
        this.readServicePlanService = readServicePlanService;
        this.writeServicePlanService = writeServicePlanService;
    }



    @ApiOperation(value="getCountry", notes="This api will retrieve all countries an associated plans")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class)})

    @GetMapping(value=URLConstants.COUNTRY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NetflixServicePlans>> getCountry(){
        return new ResponseEntity<List<NetflixServicePlans>>(readServicePlanService.getCountries(), HttpStatus.OK);
    }

    @ApiOperation(value="deleteCountry", notes="This api will delete all countries")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class)})

    @DeleteMapping(value = URLConstants.COUNTRY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCountry(){
        writeServicePlanService.deleteCountry();
        return new ResponseEntity<Void>( HttpStatus.OK );

    }

    @ApiOperation(value="createCountry", notes="This api will add a new country if it does not already exist")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class)})

    @PostMapping(value =URLConstants.COUNTRY_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCountry(@Valid @RequestBody NetflixServicePlansRq netflixServicePlansRequest){
        writeServicePlanService.addCountry(netflixServicePlansRequest.getCountryCode(), netflixServicePlansRequest);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }


    @ApiOperation(value="getCountryDetails", notes="This api will retrieve country details for a country code. Eg - USA")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class),
            @ApiResponse(code=404, message="countryCode parameter is missing", response = Response.class)})

    @GetMapping(value=URLConstants.COUNTRY_URL+URLConstants.COUNTRY_CODE_PARAM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NetflixServicePlans> getCountryDetails(@PathVariable(value = "countryCode", required = true) String countryCode){
        return new ResponseEntity<NetflixServicePlans>(readServicePlanService.getCountryDetails(countryCode), HttpStatus.OK);
    }


    @ApiOperation(value="deleteCountryDetails", notes="This api will delete country details for specified country code")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class)})

    @DeleteMapping(value = URLConstants.COUNTRY_URL+URLConstants.COUNTRY_CODE_PARAM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCountryDetails(@PathVariable(value = "countryCode", required = true) String countryCode){
        writeServicePlanService.deleteCountryDetails(countryCode);
        return new ResponseEntity<Void>( HttpStatus.OK );

    }


    @ApiOperation(value="createServicePlanForCountry", notes="This api will add a new service plan for a country")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class)})

    @PostMapping(value =URLConstants.COUNTRY_URL+URLConstants.COUNTRY_CODE_PARAM_URL+URLConstants.COUNTRY_PLAN_URL, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyValuePair> createServicePlanForCountry(@PathVariable("countryCode") String countryCode, @Valid @RequestBody ServicePlanDefinitionRq servicePlanDefinitionRq){
        String response = writeServicePlanService.addServicePlan(countryCode, servicePlanDefinitionRq);
        return new ResponseEntity<KeyValuePair>(new KeyValuePair("planId", response), HttpStatus.OK);


    }

    @ApiOperation(value="getServicePlans", notes="This will retrieve all service plans for a country code. Eg - USA")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class),
            @ApiResponse(code=400, message="countryCode parameter is missing", response = Response.class)})

    @GetMapping(value=URLConstants.COUNTRY_URL+URLConstants.COUNTRY_CODE_PARAM_URL+URLConstants.COUNTRY_PLAN_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServicePlanDefinition>> getServicePlans(@PathVariable(value = "countryCode", required = true) String countryCode){
        return new ResponseEntity<List<ServicePlanDefinition>>(readServicePlanService.getServicePlans(countryCode), HttpStatus.OK);
    }


    @ApiOperation(value="deleteServicePlans", notes="This will delete all service plans for a country code. Eg - USA")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class),
            @ApiResponse(code=400, message="countryCode parameter is missing", response = Response.class)})

    @DeleteMapping(value=URLConstants.COUNTRY_URL+URLConstants.COUNTRY_CODE_PARAM_URL+ URLConstants.COUNTRY_PLAN_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteServicePlans(@PathVariable(value = "countryCode", required = true) String countryCode){
        writeServicePlanService.deleteServicePlans(countryCode);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }


    @ApiOperation(value="getServicePlan", notes="This will retrieve service plan for a country code. Eg - USA and specified planId")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class),
            @ApiResponse(code=400, message="countryCode parameter is missing", response = Response.class)})

    @GetMapping(value=URLConstants.COUNTRY_URL+URLConstants.COUNTRY_CODE_PARAM_URL+ URLConstants.COUNTRY_PLAN_URL+URLConstants.PLAN_ID_PARAM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicePlanDefinition> getServicePlans(@PathVariable(value = "countryCode", required = true) String countryCode,
                                                                       @PathVariable(value = "planId", required = true) String planId){
        return new ResponseEntity<ServicePlanDefinition>(readServicePlanService.getServicePlan(countryCode, planId), HttpStatus.OK);
    }


    @ApiOperation(value="deleteServicePlan", notes="This will delete service plan for a country code and plan id. Eg - USA")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class),
            @ApiResponse(code=400, message="countryCode parameter is missing", response = Response.class)})

    @DeleteMapping(value=URLConstants.COUNTRY_URL+ URLConstants.COUNTRY_CODE_PARAM_URL+ URLConstants.COUNTRY_PLAN_URL+ URLConstants.PLAN_ID_PARAM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteServicePlan(@PathVariable(value = "countryCode", required = true) String countryCode,
                                                  @PathVariable(value = "planId", required = true) String planId){
        writeServicePlanService.deleteServicePlan(countryCode, planId);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }


    @ApiOperation(value="updateServicePlan", notes="This will update service plan for a country code. Eg - USA")
    @ApiResponses(value = { @ApiResponse(code=200, message="Operation Completed Successfully", response = Response.class),
            @ApiResponse(code=400, message="countryCode parameter is missing", response = Response.class)})

    @PutMapping(value=URLConstants.COUNTRY_URL+ URLConstants.COUNTRY_CODE_PARAM_URL+ URLConstants.COUNTRY_PLAN_URL+ URLConstants.PLAN_ID_PARAM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateServicePlan(@PathVariable(value = "countryCode", required = true) String countryCode,
                                                  @PathVariable(value = "planId", required = true) String planId,
                                                  @Valid @RequestBody ServicePlanDefinitionRq servicePlanDefinitionRq){
        writeServicePlanService.updateServicePlan(countryCode, planId, servicePlanDefinitionRq);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

}
