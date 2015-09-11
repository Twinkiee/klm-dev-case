package com.klm.dev.exercises.devcase01.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.klm.dev.exercises.devcase01.model.Route;

public class RouteFilterOriginTest {

	@Test
	public void filter() {
		RouteFilterOrigin routeFilter = new RouteFilterOrigin();
		List<Route> testList = new ArrayList<>();
		Route route1 = new Route();
		route1.setOrigin("MIL");


		testList.add(route1);

		Assert.assertEquals(testList.size(), 1);

		testList = routeFilter.filter(testList, "MIL");

		Assert.assertEquals(testList.size(), 1);

		testList = routeFilter.filter(testList, "AMS");

		Assert.assertEquals(testList.size(), 0);
	}
}
