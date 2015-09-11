/**
 * 
 */
package com.klm.dev.exercises.devcase01.business;

import com.klm.dev.exercises.devcase01.model.ApplicationInfo;

/**
 * @author antonio.cirasole
 *
 */
public interface ApplicationInfoManager {

	/**
	 * Loads the current application information returning a defensive copy of the original object.
	 * 
	 * @return the {@link ApplicationInfoImpl}
	 * @throws Throwable 
	 */
	public ApplicationInfo load() throws Throwable;
	
	/**
	 * Updates the statistic information related to the average processing time required to process the requests and number of requests.<br>
	 * Number of requests are always incremented by one for each call.
	 * This method is required to be thread safe.
	 * 
	 * @param newRequestProcessingTime the new processing time required to process a request
	 * @return the updated {@link ApplicationInfoImpl}. This is a defensive copy of the original object.
	 */
	public ApplicationInfo updateStatistics(Long newRequestProcessingTime);
	
	
	/**
	 * Stores the current {@link ApplicationInfoImpl} in a persistent storage
	 * @throws Throwable 
	 * 
	 */
	public void storeApplicationInfo() throws Throwable;
}
