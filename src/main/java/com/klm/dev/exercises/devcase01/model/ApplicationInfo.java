package com.klm.dev.exercises.devcase01.model;

import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ApplicationInfo {

	public abstract void increaseRequestCount();

	/**
	 * @return the version
	 */
	public abstract String getVersion();

	/**
	 * @return the requestCount
	 */
	public abstract AtomicLong getRequestCount();

	/**
	 * @return the averageRequestProcessingTime
	 */
	@JsonIgnore
	public abstract Long getAverageRequestProcessingTime();
	
	/**
	 * @return the averageRequestProcessingTime
	 */
	public abstract String getAverageRequestProcessingSeconds();

}