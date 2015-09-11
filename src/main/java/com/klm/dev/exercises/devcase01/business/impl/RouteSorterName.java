package com.klm.dev.exercises.devcase01.business.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.klm.dev.exercises.devcase01.business.DestinationListSorter;
import com.klm.dev.exercises.devcase01.model.Route;

public class RouteSorterName implements DestinationListSorter {

	@Override
	public List<Route> sort(List<Route> routes) {
		Collections.sort(routes, new Comparator<Route>() {

			@Override
			public int compare(Route o1, Route o2) {
				String origin1 = o1.getDestination().getName();
				String origin2 = o2.getDestination().getName();
				return origin1.compareTo(origin2);
			}
		});
		return routes;
	}
}
