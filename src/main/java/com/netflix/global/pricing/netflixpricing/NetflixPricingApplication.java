package com.netflix.global.pricing.netflixpricing;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@SpringBootApplication
public class NetflixPricingApplication {


	public static void main(String[] args) {
		SpringApplication.run(NetflixPricingApplication.class, args);
	}

}
