/**
 * 
 */
package com.klm.dev.exercises.devcase01.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author antonio.cirasole
 *
 */
public class RouteSorterFactory {


	@Autowired
	@Qualifier("destinationListSorterByPrice")
	private DestinationListSorter priceDestinationListSorter;
	
	@Autowired
	@Qualifier("destinationListSorterByName")
	private DestinationListSorter nameDestinationListSorter;
	

	public DestinationListSorter getSorter(Boolean sortByPrice) {
		return sortByPrice ? priceDestinationListSorter : nameDestinationListSorter;
	}
}
