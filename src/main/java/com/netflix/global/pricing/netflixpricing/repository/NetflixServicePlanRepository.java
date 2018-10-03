package com.netflix.global.pricing.netflixpricing.repository;

import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nimra on 02/10/18.
 */
@Repository
public interface NetflixServicePlanRepository extends MongoRepository<NetflixServicePlans, String> {

    public NetflixServicePlans findByCountryId(String countryId);


}
