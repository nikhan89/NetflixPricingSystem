package com.netflix.global.pricing.netflixpricing.model.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.global.pricing.netflixpricing.model.interfaces.GenericServicePlanDefiniton;
import lombok.*;

/**
 * Created by nimra on 02/10/18.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicePlanDefinition extends GenericServicePlanDefiniton{
    String planId;
    String name;
    String status;
    Float baseOfferPriceComponent;
    Float taxComponent;
    Integer streamConcurrency;
    Boolean hDAvailable;
    String planType;
}
