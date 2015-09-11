/**
 * 
 */
package com.klm.dev.exercises.devcase01.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestOperations;

import com.klm.dev.exercises.devcase01.business.DestinationListSorter;
import com.klm.dev.exercises.devcase01.business.RouteSorterFactory;
import com.klm.dev.exercises.devcase01.business.impl.RouteFilterMinBudget;
import com.klm.dev.exercises.devcase01.model.DestinationList;
import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.service.DestinationService;
import com.klm.dev.exercises.devcase01.util.RemoteCallHelper;
import com.klm.dev.exercises.devcase01.view.DestinationFinderForm;

/**
 * @author antonio.cirasole
 *
 */
public class DestinationServiceImpl implements DestinationService {

	private static Logger LOGGER = LoggerFactory.getLogger(DestinationServiceImpl.class);

	@Value("${service.df.endpoint}")
	private String serviceUri;

	@Autowired
	private RouteFilterMinBudget routeFilterMinBudget;

	@Autowired
	private RouteSorterFactory routesSorter;
	
	@Autowired
	RemoteCallHelper remoteCallHelper;
	
	@Autowired
	private RestOperations restTemplate;

	@Override
	public List<Route> retrieveDestinations(DestinationFinderForm destinationFinderForm) {

		Map<String, String> params = prepareServiceParams(destinationFinderForm);

		LOGGER.debug("retrieveDestinations; params: {}", params);

		DestinationList destinationList = restTemplate.getForObject(remoteCallHelper.buildUriWithParams(serviceUri, params), DestinationList.class, params);

		List<Route> routes = filterRoutes(destinationFinderForm, destinationList);
		
		routes = sortRoutes(destinationFinderForm, routes);

		return routes;
	}


	private List<Route> sortRoutes(DestinationFinderForm destinationFinderForm, List<Route> routes) {
		DestinationListSorter destinationSorter = routesSorter.getSorter(destinationFinderForm.getSortByPrice());
		
		routes = destinationSorter.sort(routes);
		return routes;
	}


	private List<Route> filterRoutes(DestinationFinderForm destinationFinderForm, DestinationList destinationList) {
		List<Route> routes = destinationList.getDestinations();

		if (destinationFinderForm.getMinBudget() != null && !destinationFinderForm.getMinBudget().isNaN()) {
			routes = routeFilterMinBudget.filter(routes, destinationFinderForm.getMinBudget());
		}
		return routes;
	}


	private Map<String, String> prepareServiceParams(DestinationFinderForm destinationFinderForm) {
		Map<String, String> params = new HashMap<String, String>();

		params.put("origin", destinationFinderForm.getOrigin());
		params.put("pos", destinationFinderForm.getPos());
		params.put("maxBudget", destinationFinderForm.getMaxBudget().toString());
		return params;
	}


	/**
	 * @return the serviceUri
	 */
	public String getServiceUri() {
		return serviceUri;
	}

	/**
	 * @param serviceUri
	 *            the serviceUri to set
	 */
	public void setServiceUri(String serviceUri) {
		this.serviceUri = serviceUri;
	}

}
