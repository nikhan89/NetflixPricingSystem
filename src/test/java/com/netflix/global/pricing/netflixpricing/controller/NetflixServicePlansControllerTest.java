package com.netflix.global.pricing.netflixpricing.controller;

import com.netflix.global.pricing.netflixpricing.constants.URLConstants;
import com.netflix.global.pricing.netflixpricing.exceptions.CustomResponseEntityExceptionHandler;
import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.service.implementations.NetflixPlansWriteService;
import com.netflix.global.pricing.netflixpricing.service.interfaces.INetflixPlanWriteService;
import com.netflix.global.pricing.netflixpricing.service.interfaces.INetflixPlansReadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by nimra on 02/10/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NetflixServicePlansController.class)
@ContextConfiguration(classes = CustomResponseEntityExceptionHandler.class)
public class NetflixServicePlansControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("NetflixPlansReadService")
    private INetflixPlansReadService NetflixPlansReadService;

    @MockBean
    @Qualifier("NetflixPlansWriteService")
    private INetflixPlanWriteService NetflixPlansWriteService;



    @SpringBootApplication
    static class TestConfig{};

    @Test
    public void test_getCountryDetails_badRequest_forEmptyCountryCode() throws Exception{
       //TO DO
    }



}
