/**
 * 
 */
package com.klm.dev.exercises.devcase01.service.impl;

import com.klm.dev.exercises.devcase01.service.ExchangeService;

/**
 * @author antonio.cirasole
 *
 */
public class ExchangeServiceImpl implements ExchangeService{

	private static final String CHF = "CHF";
	private static final String USD = "USD";
	private static final Double CHF_TO_EUR_EXCHANGE = 0.94;
	private static final Double USD_TO_EUR_EXCHANGE = 0.92;
//	private static final Double EUR_TO_EUR_EXCHANGE = 1.0;

	@Override
	public Double normalizeValue(Double value, String currency) {
		if (USD.endsWith(currency)) {
			return value * USD_TO_EUR_EXCHANGE;
		}
		
		if (CHF.endsWith(currency)) {
			return value * CHF_TO_EUR_EXCHANGE;
		}
		
		// Default (hopefully Euro) case
		return value;
	}

}
