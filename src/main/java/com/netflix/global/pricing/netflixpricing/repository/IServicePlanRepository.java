package com.netflix.global.pricing.netflixpricing.repository;

import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;

/**
 * Created by nimra on 03/10/18.
 */
public interface IServicePlanRepository {

    public NetflixServicePlans findByCountryId(String countryId);
}
