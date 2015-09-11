/**
 * 
 */
package com.klm.dev.exercises.devcase01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestOperations;

import com.klm.dev.exercises.devcase01.business.impl.RouteFilterMaxBudget;
import com.klm.dev.exercises.devcase01.business.impl.RouteFilterOrigin;
import com.klm.dev.exercises.devcase01.service.remote.OfflineRestTemplate;

/**
 * @author antonio.cirasole
 *
 */
@Configuration
@Profile("offline")
public class OfflineConfiguration {

	
	@Bean(name="restTemplate")
	public RestOperations offlineRestTemplate() {
		return new OfflineRestTemplate();
	}
	
	@Bean
	public RouteFilterMaxBudget routeFilterMaxBudget() {
		return new RouteFilterMaxBudget();
	}
	
	@Bean
	public RouteFilterOrigin routeFilterOrigin() {
		return new RouteFilterOrigin();
	}
}
