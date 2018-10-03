package com.netflix.global.pricing.netflixpricing.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nimra on 03/10/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {

    @Builder.Default
    private int responseCode = 0;

    @Builder.Default
    private String responseDesc = "Operation completed Successfully";
    private T responseObj;
}
