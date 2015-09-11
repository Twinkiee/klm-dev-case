package com.klm.dev.exercises.devcase01.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import com.klm.dev.exercises.devcase01.config.OfflineConfiguration;
import com.klm.dev.exercises.devcase01.model.Fare;
import com.klm.dev.exercises.devcase01.model.Route;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfiguration.class, OfflineConfiguration.class })
@ActiveProfiles("offline")
public class RouteSorterPriceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	RouteSorterPrice routeSorter;

	@Test
	public void sort() {
		List<Route> testList = new ArrayList<>();
		Route route1 = new Route();
		Fare fare1 = new Fare();
		fare1.setCurrency("EUR");
		fare1.setValue(10.0);
		route1.setLowestFare(fare1);
		testList.add(route1);

		Route route2 = new Route();
		Fare fare2 = new Fare();
		fare2.setCurrency("EUR");
		fare2.setValue(30.0);
		route2.setLowestFare(fare2);
		testList.add(route2);

		Route route3 = new Route();
		Fare fare3 = new Fare();
		fare3.setCurrency("USD");
		fare3.setValue(30.0);
		route3.setLowestFare(fare3);
		testList.add(route3);

		testList = routeSorter.sort(testList);

		Assert.assertEquals(testList.get(0).getLowestFare().getValue(), 10.0);
		Assert.assertEquals(testList.get(1).getLowestFare().getValue(), 30.0);
		Assert.assertEquals(testList.get(2).getLowestFare().getValue(), 30.0);
		Assert.assertEquals(testList.get(2).getLowestFare().getCurrency(), "EUR");

		Assert.assertEquals(testList.size(), 3);
	}
}
