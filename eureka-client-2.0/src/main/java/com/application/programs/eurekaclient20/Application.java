package com.application.programs.eurekaclient20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@RibbonClient(name = "ribbon-app")//, configuration = RibbonConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.application.programs.eurekaclient20.controllers")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
