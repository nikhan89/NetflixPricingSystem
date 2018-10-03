package com.netflix.global.pricing.netflixpricing.service.interfaces;

import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import com.netflix.global.pricing.netflixpricing.requests.implementations.NetflixServicePlansRq;
import com.netflix.global.pricing.netflixpricing.requests.implementations.ServicePlanDefinitionRq;
import com.netflix.global.pricing.netflixpricing.requests.interfaces.IServicePlanDefinitionRq;

/**
 * Created by nimra on 02/10/18.
 */

public interface INetflixPlanWriteService{

    public void addCountry(String countryCode, NetflixServicePlansRq addServicePlanDefinitionRequest);

    public String addServicePlan(String countryCode, ServicePlanDefinitionRq servicePlanDefinitionRq);

    public void deleteCountry();

    public void deleteCountryDetails(String countryCode);

    public void deleteServicePlan(String countryCode, String planId);

    public void deleteServicePlans(String countryCode);

    public void updateServicePlan(String countryCode, String planId, ServicePlanDefinitionRq servicePlanDefinitionRq);
}
