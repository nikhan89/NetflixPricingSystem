package com.netflix.global.pricing.netflixpricing.requests.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Null;

/**
 * Created by nimra on 03/10/18.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicePlanDefinitionRq {
    @Null
    String planId;
    String name;
    String status;
    Float baseOfferPriceComponent;
    Float taxComponent;
    Integer streamConcurrency;
    Boolean hDAvailable;
    String planType;
}
