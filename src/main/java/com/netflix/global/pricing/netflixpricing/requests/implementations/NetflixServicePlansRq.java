package com.netflix.global.pricing.netflixpricing.requests.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Null;
import java.util.List;

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
public class NetflixServicePlansRq {
    @NonNull
    private String countryCode;

    @NonNull
    private String countryName;

    @Null
    private List<ServicePlanDefinition> servicePlanDefinitions;
}
