package com.klm.dev.exercises.devcase01.business;

import java.util.List;

import com.klm.dev.exercises.devcase01.model.Route;

public interface DestinationListSorter {

	/**
	 * Sorts the given list of routes
	 * 
	 * @param destinationList
	 * @return the sorted list of routes
	 */
	public List<Route> sort (List<Route> routes);
}
