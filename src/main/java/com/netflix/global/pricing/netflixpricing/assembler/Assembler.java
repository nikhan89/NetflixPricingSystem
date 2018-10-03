package com.netflix.global.pricing.netflixpricing.assembler;

import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import com.netflix.global.pricing.netflixpricing.requests.implementations.NetflixServicePlansRq;
import com.netflix.global.pricing.netflixpricing.requests.implementations.ServicePlanDefinitionRq;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nimra on 03/10/18.
 */
@Component
public class Assembler {

    private Mapper mapper;

    @Autowired
    public Assembler(Mapper mapper){
        this.mapper = mapper;
    }

    public ServicePlanDefinition transformServicePlanDefinitionRq(ServicePlanDefinitionRq servicePlanDefinitionRq) {
        if(servicePlanDefinitionRq != null){
            ServicePlanDefinition servicePlanDefiniton = mapper.map(servicePlanDefinitionRq, ServicePlanDefinition.class);
            return servicePlanDefiniton;
        }
        return null;
    }

    public NetflixServicePlans transformNetflixServicePlansRq(NetflixServicePlansRq netflixServicePlansRq) {
        if(netflixServicePlansRq != null){
            NetflixServicePlans netflixervicePlans = mapper.map(netflixServicePlansRq, NetflixServicePlans.class);
            return netflixervicePlans;
        }
        return null;
    }
}
