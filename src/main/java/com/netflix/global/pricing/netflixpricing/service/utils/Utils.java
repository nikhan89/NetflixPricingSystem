package com.netflix.global.pricing.netflixpricing.service.utils;

/**
 * Created by nimra on 03/10/18.
 */
public class Utils {

    public static String generateServicePlanId(String countryCode, int size){

        return String.valueOf(size);

    }


    public static String generateCountryId(String countryCode){

        return String.valueOf(countryCode.toLowerCase().hashCode());

    }
}
