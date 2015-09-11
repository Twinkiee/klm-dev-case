/**
 * 
 */
package com.klm.dev.exercises.devcase01.model;

/**
 * @author antonio.cirasole
 *
 */
public class Fare {
	private Double value;
	private String currency;
	
	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
