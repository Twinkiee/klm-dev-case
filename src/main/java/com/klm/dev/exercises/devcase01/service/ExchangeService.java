package com.klm.dev.exercises.devcase01.service;

/**
 * As long as there's a currency field defined for the fare, we may assume that
 * not all the fares will be in Euro therefore we may require a service that
 * provides a normalized fare value.
 * 
 * @author antonio.cirasole
 *
 */
public interface ExchangeService {

	/**
	 * Normalize the given value according to the given currency
	 * 
	 * @param value
	 * @param currency
	 * @return the normalized value
	 */
	public Double normalizeValue(Double value, String currency);

}
