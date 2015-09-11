/**
 * 
 */
package com.klm.dev.exercises.devcase01.model;

/**
 * @author antonio.cirasole
 *
 */
public class Coordinates {
	 private Double lat;
	 private Double lon;
	 
	 
	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public Double getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinates [lat=").append(lat).append(", lon=")
				.append(lon).append("]");
		return builder.toString();
	}
}
