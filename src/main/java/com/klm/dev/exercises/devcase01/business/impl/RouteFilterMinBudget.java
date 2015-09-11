/**
 * 
 */
package com.klm.dev.exercises.devcase01.business.impl;

import java.util.ArrayList;
import java.util.List;

import com.klm.dev.exercises.devcase01.model.Route;

/**
 * @author antonio.cirasole
 *
 */
public class RouteFilterMinBudget {

	/* (non-Javadoc)
	 * @see com.klm.dev.exercises.devcase01.business.DestinationListFilter#filter(com.klm.dev.exercises.devcase01.model.DestinationList)
	 */
	public List<Route> filter(List<Route> routes, Double minBudget) {
		List<Route> returnList = new ArrayList<>();
		
		for (Route route : routes) {
			if (route.getLowestFare().getValue().compareTo(minBudget) >= 0) {
				returnList.add(route);
			}
		}
		return returnList;
	}

}
