package com.nttdata.btc.card.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NttdataBtcCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(NttdataBtcCardApplication.class, args);
	}

}
