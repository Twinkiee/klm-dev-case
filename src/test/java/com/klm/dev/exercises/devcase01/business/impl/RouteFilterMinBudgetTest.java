package com.klm.dev.exercises.devcase01.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.klm.dev.exercises.devcase01.model.Fare;
import com.klm.dev.exercises.devcase01.model.Route;

public class RouteFilterMinBudgetTest {

	@Test
	public void filter() {
		RouteFilterMinBudget routeFilter = new RouteFilterMinBudget();
		List<Route> testList = new ArrayList<>();
		Route route1 = new Route();
		Fare fare1 = new Fare();
		fare1.setCurrency("EUR");
		fare1.setValue(100.0);
		route1.setLowestFare(fare1);

		testList.add(route1);

		Assert.assertEquals(testList.size(), 1);

		testList = routeFilter.filter(testList, 100.0);

		Assert.assertEquals(testList.size(), 1);

		testList = routeFilter.filter(testList, 110.0);

		Assert.assertEquals(testList.size(), 0);
	}
}
