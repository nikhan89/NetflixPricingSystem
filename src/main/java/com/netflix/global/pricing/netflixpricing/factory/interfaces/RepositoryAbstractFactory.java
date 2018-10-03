package com.netflix.global.pricing.netflixpricing.factory.interfaces;

import com.netflix.global.pricing.netflixpricing.repository.NetflixServicePlanRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nimra on 02/10/18.
 */
public abstract class RepositoryAbstractFactory {

    abstract NetflixServicePlanRepository getRepository(String repositoryType);
}
