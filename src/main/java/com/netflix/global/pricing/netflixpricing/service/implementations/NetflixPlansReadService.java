package com.netflix.global.pricing.netflixpricing.service.implementations;

import com.netflix.global.pricing.netflixpricing.exceptions.NetflixServicePlansBadRequestException;
import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import com.netflix.global.pricing.netflixpricing.repository.NetflixServicePlanRepository;
import com.netflix.global.pricing.netflixpricing.service.interfaces.INetflixPlansReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by nimra on 02/10/18.
 */
@Service
public class NetflixPlansReadService implements INetflixPlansReadService{

    private NetflixServicePlanRepository netflixServicePlanRepository;

    @Autowired
    public NetflixPlansReadService(NetflixServicePlanRepository netflixServicePlanRepository){
        this.netflixServicePlanRepository = netflixServicePlanRepository;
    }

    public List<ServicePlanDefinition> getServicePlans(String countryCode) {
        return netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode())).getServicePlanDefinitions();
    }

    public List<NetflixServicePlans> getCountries() {
        return netflixServicePlanRepository.findAll();
    }

    public NetflixServicePlans getCountryDetails(String countryCode) {
        if(countryCode == null || countryCode.length()==0 || !Pattern.matches("[a-zA-Z]+", countryCode))
            throw new NetflixServicePlansBadRequestException("Country code should be alphabetical.Please refer to https://www.iso.org/obp/ui/#search/code/");
        return netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode()));
    }

    public ServicePlanDefinition getServicePlan(String countryCode, String planId) {

        NetflixServicePlans netflixServicePlan = netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode()));

        List<ServicePlanDefinition> servicePlanDefinitions  = netflixServicePlan.getServicePlanDefinitions();

        if(servicePlanDefinitions == null)
            throw new NetflixServicePlansBadRequestException("Service plannot found for planId "+ planId);

        ServicePlanDefinition toReturnServicePlan = null;

        for(ServicePlanDefinition servicePlanDefinition : servicePlanDefinitions){
            if(servicePlanDefinition.getPlanId().equals(String.valueOf(countryCode.toLowerCase().hashCode())+planId))
                toReturnServicePlan = servicePlanDefinition;

        }
        if(toReturnServicePlan == null)
            throw new NetflixServicePlansBadRequestException("Service plannot found for country "+ countryCode);
        return toReturnServicePlan;
    }

}
