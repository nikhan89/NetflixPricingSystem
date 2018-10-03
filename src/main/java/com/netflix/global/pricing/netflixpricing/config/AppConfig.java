package com.netflix.global.pricing.netflixpricing.config;

import com.netflix.global.pricing.netflixpricing.NetflixPricingApplication;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nimra on 03/10/18.
 */
@Configuration

public class AppConfig {

    @Bean
    public Mapper getMapper(){
        return new DozerBeanMapper();
    }
}
