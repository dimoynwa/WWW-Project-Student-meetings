package com.fmi.uni.sofia.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fmi.uni.sofia.www.services.ConferenceService;
import com.fmi.uni.sofia.www.services.UserService;
import com.fmi.uni.sofia.www.services.impl.ConferenceServiceImpl;
import com.fmi.uni.sofia.www.services.impl.UserServiceImpl;

@Configuration
@EnableJpaRepositories
@Import(com.fmi.uni.sofia.www.jpa_repositories.Config.class)
public class AppConfig {
	
	@Autowired
	com.fmi.uni.sofia.www.jpa_repositories.Config config;
	
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public ConferenceService conferenceService() {
		return new ConferenceServiceImpl();
	}
	
}
