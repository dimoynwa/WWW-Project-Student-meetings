package com.fmi.uni.sofia.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"com.fmi.uni.sofia.www.entities"})
@ComponentScan(basePackages={"com.fmi.uni.sofia.www.controllers", "com.fmi.uni.sofia.www.config", "com.fmi.uni.sofia.www.jpa_repositories",})
public class WwwProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WwwProjectApplication.class, args);
	}
}
