package com.klm.dev.exercises.devcase01.business.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.klm.dev.exercises.devcase01.business.DestinationListSorter;
import com.klm.dev.exercises.devcase01.model.Fare;
import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.service.ExchangeService;

public class RouteSorterPrice implements DestinationListSorter {
	
	@Autowired
	private ExchangeService exchangeService;

	@Override
	public List<Route> sort(List<Route> routes) {
		Collections.sort(routes, new Comparator<Route>() {

			@Override
			public int compare(Route o1, Route o2) {
				Fare lowestFare1 = o1.getLowestFare();
				Double valueRoute1 = exchangeService.normalizeValue(lowestFare1.getValue(), lowestFare1.getCurrency());
				Fare lowestFare2 = o2.getLowestFare();
				Double valueRoute2 = exchangeService.normalizeValue(lowestFare2.getValue(), lowestFare2.getCurrency());
				return valueRoute1.compareTo(valueRoute2);
			}
		});
		return routes;
	}

	/**
	 * @return the exchangeService
	 */
	public ExchangeService getExchangeService() {
		return exchangeService;
	}

	/**
	 * @param exchangeService the exchangeService to set
	 */
	public void setExchangeService(ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}
}
