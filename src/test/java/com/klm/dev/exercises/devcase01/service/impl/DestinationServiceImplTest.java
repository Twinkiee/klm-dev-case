package com.klm.dev.exercises.devcase01.service.impl;

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
import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.service.DestinationService;
import com.klm.dev.exercises.devcase01.view.DestinationFinderForm;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfiguration.class, OfflineConfiguration.class })
@ActiveProfiles("offline")
public class DestinationServiceImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DestinationService destinationService;
	
	@Test
	public void retrieveDestinations() {
		DestinationFinderForm destinationFinderForm = new DestinationFinderForm();
		
		destinationFinderForm.setMaxBudget(1000.0);
		destinationFinderForm.setMinBudget(10.0);
		destinationFinderForm.setOrigin("BCN");
		destinationFinderForm.setPos("SP");
		
		
		List<Route> routes = destinationService.retrieveDestinations(destinationFinderForm);
		
		Assert.assertEquals(routes.size(), 4);
		Assert.assertEquals(routes.get(0).getDestination().getName(), "Amsterdam");
		Assert.assertEquals(routes.get(3).getDestination().getName(), "Paris");
	}
}
