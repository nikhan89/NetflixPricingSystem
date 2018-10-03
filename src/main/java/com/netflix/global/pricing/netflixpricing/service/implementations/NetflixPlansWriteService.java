package com.netflix.global.pricing.netflixpricing.service.implementations;

import com.netflix.global.pricing.netflixpricing.assembler.Assembler;
import com.netflix.global.pricing.netflixpricing.exceptions.NetflixServicePlansBadRequestException;
import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import com.netflix.global.pricing.netflixpricing.repository.NetflixServicePlanRepository;
import com.netflix.global.pricing.netflixpricing.requests.implementations.NetflixServicePlansRq;
import com.netflix.global.pricing.netflixpricing.requests.implementations.ServicePlanDefinitionRq;
import com.netflix.global.pricing.netflixpricing.service.interfaces.INetflixPlanWriteService;
import com.netflix.global.pricing.netflixpricing.service.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.netflix.global.pricing.netflixpricing.service.utils.Utils.generateCountryId;

/**
 * Created by nimra on 02/10/18.
 */
@Service
public class NetflixPlansWriteService implements INetflixPlanWriteService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private NetflixServicePlanRepository netflixServicePlanRepository;
    private Assembler mapper;

    @Autowired
    public NetflixPlansWriteService(NetflixServicePlanRepository netflixServicePlanRepository, Assembler mapper){
        this.netflixServicePlanRepository = netflixServicePlanRepository;
        this.mapper = mapper;
    }

    public void addCountry(String countryCode, NetflixServicePlansRq netflixServicePlansRq){
        NetflixServicePlans netflixServicePlans =
                netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode()));

        if(netflixServicePlans != null) {
            throw new NetflixServicePlansBadRequestException
                    ("Country already exists");
        }

        netflixServicePlans = mapper.transformNetflixServicePlansRq(netflixServicePlansRq);

        netflixServicePlans.setCountryId(generateCountryId(countryCode));
        netflixServicePlanRepository.save(netflixServicePlans);

    }

    public String addServicePlan(String countryCode, ServicePlanDefinitionRq servicePlanDefinitionRq) {

        if(countryCode == null || servicePlanDefinitionRq == null)
            throw new NetflixServicePlansBadRequestException("servicePlanDefinition request or country code is null.");

        ServicePlanDefinition servicePlanDefinition = mapper.transformServicePlanDefinitionRq(servicePlanDefinitionRq);

        if(servicePlanDefinition == null)
            throw new NetflixServicePlansBadRequestException(" Transformed servicePlanDefinition is null.");

        NetflixServicePlans netflixServicePlans = netflixServicePlanRepository.findByCountryId(generateCountryId(countryCode));
        if(netflixServicePlans == null)
            throw new NetflixServicePlansBadRequestException("Country "+ countryCode +" does not exist.");

        int servicePlanDefinitionSize = netflixServicePlans.getServicePlanDefinitions()==null?0:
                netflixServicePlans.getServicePlanDefinitions().size();

        String planId = Utils.generateServicePlanId(countryCode,servicePlanDefinitionSize);

        servicePlanDefinition.setPlanId(planId);
        List<ServicePlanDefinition> servicePlanDefinitions =  new ArrayList<ServicePlanDefinition>() ;
        if(netflixServicePlans.getServicePlanDefinitions() != null)
            servicePlanDefinitions.addAll(netflixServicePlans.getServicePlanDefinitions());
        servicePlanDefinitions.add(servicePlanDefinition);
        netflixServicePlans.setServicePlanDefinitions(servicePlanDefinitions);
        netflixServicePlanRepository.save(netflixServicePlans);

        return planId;

    }

    public void deleteCountry() {
        netflixServicePlanRepository.deleteAll();
    }

    public  void deleteCountryDetails(String countryCode){
        netflixServicePlanRepository.deleteById(Utils.generateCountryId(countryCode));

    }

    public void deleteServicePlan(String countryCode, String planId){

        NetflixServicePlans netflixServicePlan = netflixServicePlanRepository.findByCountryId(Utils.generateCountryId(countryCode));

        List<ServicePlanDefinition> servicePlanDefinitions  = netflixServicePlan.getServicePlanDefinitions();

        ServicePlanDefinition toRemove = null;

        for(ServicePlanDefinition servicePlanDefinition : servicePlanDefinitions){
            if(servicePlanDefinition.getPlanId().equals(planId))
                toRemove = servicePlanDefinition;

        }
        if(toRemove == null)
            throw new NetflixServicePlansBadRequestException("Service plan not found for country "+ countryCode);
        servicePlanDefinitions.remove(toRemove);

        netflixServicePlanRepository.save(netflixServicePlan);

    }


    public void deleteServicePlans(String countryCode){

        NetflixServicePlans netflixServicePlan = netflixServicePlanRepository.findByCountryId(Utils.generateCountryId(countryCode));

        netflixServicePlan.setServicePlanDefinitions(null);

        netflixServicePlanRepository.save(netflixServicePlan);

    }

    public void updateServicePlan(String countryCode, String planId, ServicePlanDefinitionRq servicePlanDefinitionRq){

        if(countryCode == null || servicePlanDefinitionRq == null)
            throw new NetflixServicePlansBadRequestException("servicePlanDefinition request or country code is empty.");


        ServicePlanDefinition servicePlanDefinition = mapper.transformServicePlanDefinitionRq(servicePlanDefinitionRq);

        if(servicePlanDefinition == null)
            throw new NetflixServicePlansBadRequestException("servicePlanDefinition request or country code is empty.");

        int toUpdateIdx = -1;

        NetflixServicePlans netflixServicePlan = netflixServicePlanRepository.findByCountryId(Utils.generateCountryId(countryCode));
        for (int i = 0; i < netflixServicePlan.getServicePlanDefinitions().size(); i++){
            if(netflixServicePlan.getServicePlanDefinitions().get(i).getPlanId().equals(planId))
                toUpdateIdx = i;
        }
        if(toUpdateIdx == -1)
            throw new NetflixServicePlansBadRequestException("Service plan "+ planId + " not found for country "+ countryCode);

        netflixServicePlan.getServicePlanDefinitions().remove(toUpdateIdx);
        servicePlanDefinition.setPlanId(planId);
        netflixServicePlan.getServicePlanDefinitions().add(toUpdateIdx, servicePlanDefinition);

        netflixServicePlanRepository.save(netflixServicePlan);


    }
}
