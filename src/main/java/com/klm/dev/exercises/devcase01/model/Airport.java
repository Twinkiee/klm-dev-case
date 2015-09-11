/**
 * 
 */
package com.klm.dev.exercises.devcase01.model;

/**
 * @author antonio.cirasole
 *
 */
public class Airport {

	private String code;
	private String name;
	private String description;
	private String country;
	private String continent;
	private Coordinates coordinates;
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}
	/**
	 * @param continent the continent to set
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}
	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Airport [code=").append(code).append(", name=")
				.append(name).append(", description=").append(description)
				.append(", country=").append(country).append(", continent=")
				.append(continent).append(", coordinates=").append(coordinates)
				.append("]");
		return builder.toString();
	}
}
