package com.cts.learning.poDownload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PodownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PodownloadApplication.class, args);
	}

}
