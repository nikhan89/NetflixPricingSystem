package com.netflix.global.pricing.netflixpricing.requests.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Created by nimra on 03/10/18.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicePlanDefinitionRq {
    String name;
    String status;
    Float baseOfferPriceComponent;
    Float taxComponent;
    Integer streamConcurrency;
    Boolean hDAvailable;
    String planType;
}
