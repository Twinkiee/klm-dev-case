/**
 * 
 */
package com.klm.dev.exercises.devcase01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.klm.dev.exercises.devcase01.business.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.business.DestinationListSorter;
import com.klm.dev.exercises.devcase01.business.RouteSorterFactory;
import com.klm.dev.exercises.devcase01.business.impl.ApplicationInfoManagerImpl;
import com.klm.dev.exercises.devcase01.business.impl.RouteSorterName;
import com.klm.dev.exercises.devcase01.business.impl.RouteSorterPrice;
import com.klm.dev.exercises.devcase01.business.impl.RouteFilterMinBudget;
import com.klm.dev.exercises.devcase01.service.DestinationService;
import com.klm.dev.exercises.devcase01.service.ExchangeService;
import com.klm.dev.exercises.devcase01.service.impl.DestinationServiceImpl;
import com.klm.dev.exercises.devcase01.service.impl.ExchangeServiceImpl;
import com.klm.dev.exercises.devcase01.util.RemoteCallHelper;

/**
 * @author antonio.cirasole
 *
 */
@Configuration
@Import({OfflineConfiguration.class, OnlineConfiguration.class})
@PropertySource({"classpath:/config/application.properties", "classpath:/sample/offline-response.xml"})
public class ApplicationConfiguration {


	@Bean
	public DestinationService destinationService() {
		return new DestinationServiceImpl();
	}

	@Bean
	public DestinationListSorter destinationListSorterByPrice() {
		return new RouteSorterPrice();
	}
	
	@Bean
	public DestinationListSorter destinationListSorterByName() {
		return new RouteSorterName();
	}
	
	@Bean
	public RouteSorterFactory routeSorterFactory() {
		return new RouteSorterFactory();
	}
	
	@Bean
	public RemoteCallHelper remoteCallHelper() {
		return new RemoteCallHelper();
	}
	
	@Bean
	public RouteFilterMinBudget routeFilterMinBudget() {
		return new RouteFilterMinBudget();
	}
	
	@Bean
	public ExchangeService exchangeService() {
		return new ExchangeServiceImpl();
	}
	
	@Bean
	public ApplicationInfoManager applicationInfoManager() {
		return new ApplicationInfoManagerImpl();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
