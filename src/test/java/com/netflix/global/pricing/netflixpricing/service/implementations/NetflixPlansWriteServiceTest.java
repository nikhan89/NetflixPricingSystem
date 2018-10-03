package com.netflix.global.pricing.netflixpricing.service.implementations;

import com.netflix.global.pricing.netflixpricing.assembler.Assembler;
import com.netflix.global.pricing.netflixpricing.exceptions.NetflixServicePlansBadRequestException;
import com.netflix.global.pricing.netflixpricing.model.implementations.NetflixServicePlans;
import com.netflix.global.pricing.netflixpricing.model.implementations.ServicePlanDefinition;
import com.netflix.global.pricing.netflixpricing.repository.NetflixServicePlanRepository;
import com.netflix.global.pricing.netflixpricing.requests.implementations.NetflixServicePlansRq;
import com.netflix.global.pricing.netflixpricing.requests.implementations.ServicePlanDefinitionRq;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nimra on 02/10/18.
 */
public class NetflixPlansWriteServiceTest {

    private NetflixServicePlanRepository netflixServicePlanRepository;
    private NetflixPlansWriteService netflixPlansWriteService;
    private Assembler requestMapper;


    @BeforeMethod
    public void setUp() {
        netflixServicePlanRepository = mock(NetflixServicePlanRepository.class);
        requestMapper = mock(Assembler.class);
        netflixPlansWriteService = new NetflixPlansWriteService(netflixServicePlanRepository, requestMapper);
    }

    @DataProvider(name = "netflixServicePlans")
    private Object[][] netflixServicePlans() {
        return new Object[][] {
                {"India","IN", "IN"}
        };
    }


    @Test(dataProvider = "netflixServicePlans")
    public void test_addNewCountry(String countryName, String countryCode, String expectedCountryCode) {
        NetflixServicePlansRq mockNetflixServicePlansRq =  mock(NetflixServicePlansRq.class);
        NetflixServicePlans mockNetflixServicePlans = new NetflixServicePlans();

        mockNetflixServicePlans = mockNetflixServicePlans.builder().countryCode(countryCode).countryName(countryName).build();
        when(netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode()))).thenReturn(null);
        when(requestMapper.transformNetflixServicePlansRq(mockNetflixServicePlansRq)).thenReturn(mockNetflixServicePlans);
        netflixPlansWriteService.addCountry(countryCode, mockNetflixServicePlansRq);
        when(netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode()))).thenReturn(mockNetflixServicePlans);
        assertEquals(netflixServicePlanRepository.findByCountryId(String.valueOf(countryCode.toLowerCase().hashCode())).getCountryCode(), expectedCountryCode);

    }

    @Test(expectedExceptions = NetflixServicePlansBadRequestException.class)
    public void test_addExistingCountry(){
        NetflixServicePlansRq mockNetflixServicePlansRq =  mock(NetflixServicePlansRq.class);
        NetflixServicePlans mockNetflixServicePlans =  mock(NetflixServicePlans.class);
        when(netflixServicePlanRepository.findByCountryId(String.valueOf("IN".toLowerCase().hashCode()))).thenReturn(mockNetflixServicePlans);

        netflixPlansWriteService.addCountry("IN", mockNetflixServicePlansRq);
        Mockito.verify(netflixServicePlanRepository, Mockito.times(1)).findByCountryId(String.valueOf("IN".toLowerCase().hashCode()));
    }


    @Test(expectedExceptions = NetflixServicePlansBadRequestException.class)
    public void test_addNullServicePlan(){
        ServicePlanDefinitionRq mockServicePlanDefintion = null;
        NetflixServicePlans mockNetflixServicePlans = new NetflixServicePlans();
        mockNetflixServicePlans.builder().countryCode("IN").countryName("India").servicePlanDefinitions(new ArrayList<>()).build();
        when(netflixServicePlanRepository.findByCountryId(String.valueOf("IN".toLowerCase().hashCode()))).thenReturn(mockNetflixServicePlans);
        netflixPlansWriteService.addServicePlan("IN", mockServicePlanDefintion);
        assertEquals (netflixServicePlanRepository.findByCountryId(String.valueOf("IN".toLowerCase().hashCode())).getServicePlanDefinitions().size() , 0);
    }

    @DataProvider(name = "existingServicePlanDefnTypes")
    private Object[][] existingServicePlanDefnTypes() {
        return new Object[][] {
                {new ArrayList<>(), 1, "0"},
                {null, 1, "0"},
                {Arrays.asList(new ServicePlanDefinitionRq(),new ServicePlanDefinitionRq() ), 3, "2"}
        };
    }

    @Test(dataProvider = "existingServicePlanDefnTypes")
    public void test_addServicePlan(List<ServicePlanDefinition> existingServicePlanDefnForCountry, int expectedSize , String expectedPlanId){
        ServicePlanDefinitionRq mockServicePlanDefintionRq = mock(ServicePlanDefinitionRq.class);
        ServicePlanDefinition mockServicePlanDefintion = mock(ServicePlanDefinition.class);

        NetflixServicePlans mockNetflixServicePlans = new NetflixServicePlans();
        mockNetflixServicePlans = mockNetflixServicePlans.builder().countryCode("IN").countryName("India").servicePlanDefinitions(existingServicePlanDefnForCountry).build();
        when(netflixServicePlanRepository.findByCountryId(String.valueOf("IN".toLowerCase().hashCode()))).thenReturn(mockNetflixServicePlans);
        when(requestMapper.transformServicePlanDefinitionRq(mockServicePlanDefintionRq)).thenReturn(mockServicePlanDefintion);

        String planId = netflixPlansWriteService.addServicePlan("IN", mockServicePlanDefintionRq);
        assertEquals (netflixServicePlanRepository.findByCountryId(String.valueOf("IN".toLowerCase().hashCode())).getServicePlanDefinitions().size() , expectedSize);
        assertEquals (planId , expectedPlanId);
    }


}
