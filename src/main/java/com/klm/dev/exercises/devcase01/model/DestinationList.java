/**
 * 
 */
package com.klm.dev.exercises.devcase01.model;

import java.util.List;

/**
 * @author antonio.cirasole
 *
 */
public class DestinationList {

	private List<Route> destinations;

	/**
	 * @return the destinations
	 */
	public List<Route> getDestinations() {
		return destinations;
	}

	/**
	 * @param destinations the destinations to set
	 */
	public void setDestinations(List<Route> destinations) {
		this.destinations = destinations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DestinationList [destinations=" + destinations + "]";
	}

	
}
