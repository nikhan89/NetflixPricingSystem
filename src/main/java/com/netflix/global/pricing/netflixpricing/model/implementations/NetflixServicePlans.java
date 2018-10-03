package com.netflix.global.pricing.netflixpricing.model.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.netflix.global.pricing.netflixpricing.model.interfaces.GenericNetflixServicePlans;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
@Document(collection ="CountryServicePlans")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetflixServicePlans extends GenericNetflixServicePlans {
    @Id
    private String countryId;

    @NonNull
    private String countryCode;

    @NonNull
    private String countryName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ServicePlanDefinition> servicePlanDefinitions;

}
