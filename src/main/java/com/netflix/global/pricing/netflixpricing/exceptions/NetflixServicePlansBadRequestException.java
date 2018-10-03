package com.netflix.global.pricing.netflixpricing.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nimra on 02/10/18.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@JsonIgnoreProperties({"stackTrace", "suppressed", "cause", "localizedMessage"})
public class NetflixServicePlansBadRequestException extends RuntimeException{
        public NetflixServicePlansBadRequestException(String message) {
            super(message);
        }
}
