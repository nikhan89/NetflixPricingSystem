package com.netflix.global.pricing.netflixpricing.service.interfaces;

import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;

import javax.xml.ws.Service;
import java.util.List;

/**
 * Created by nimra on 02/10/18.
 */
public interface INetflixPlansReadService{

    public List<ServicePlanDefinition> getServicePlans(String countryCode);

    public List<NetflixServicePlans> getCountries();

    public NetflixServicePlans getCountryDetails(String countryCode);

    public ServicePlanDefinition getServicePlan(String countryCode, String planId);

}
