/**
 * 
 */
package com.klm.dev.exercises.devcase01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author antonio.cirasole
 *
 */
@Configuration
@Profile("online")
public class OnlineConfiguration {

	
	
	@Bean(name="restTemplate")
	public RestOperations onlineRestTemplate() {
		return new RestTemplate();
	}
	
}
