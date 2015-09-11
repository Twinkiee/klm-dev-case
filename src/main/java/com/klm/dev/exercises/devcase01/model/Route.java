/**
 * 
 */
package com.klm.dev.exercises.devcase01.model;

/**
 * @author antonio.cirasole
 *
 */
public class Route {
	private String origin;
	private Airport destination;
	private Fare lowestFare;

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public Airport getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	/**
	 * @return the lowestFare
	 */
	public Fare getLowestFare() {
		return lowestFare;
	}

	/**
	 * @param lowestFare
	 *            the lowestFare to set
	 */
	public void setLowestFare(Fare lowestFare) {
		this.lowestFare = lowestFare;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Route [origin=").append(origin)
				.append(", destination=").append(destination)
				.append(", lowestFare=").append(lowestFare).append("]");
		return builder.toString();
	}

}
