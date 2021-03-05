package com.mindtree.atlantis.loyalty.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindtree.atlantis.loyalty.core.dto.auth.ClientAuthRequestDTO;

@EnableConfigurationProperties
@Configuration
public class AuthConfig {
	
	private ClientAuthRequestDTO endUserClient = new ClientAuthRequestDTO();
	
	@Bean
	@ConfigurationProperties("loyalty-points-enduser-client")
	public ClientAuthRequestDTO getEndUserClient() {
		return endUserClient;
	}
	
}
