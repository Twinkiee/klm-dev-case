/**
 * 
 */
package com.klm.dev.exercises.devcase01.business.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.klm.dev.exercises.devcase01.business.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.model.ApplicationInfo;

/**
 * @author antonio.cirasole
 *
 */
public final class ApplicationInfoManagerImpl implements ApplicationInfoManager {

	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationInfoManagerImpl.class);

	@Value("${application.storage.location}")
	private Resource applicationInfoStorage;
	@Value("${application.version}")
	private String applicationVersion;
	private final ApplicationInfoImpl applicationInfo = new ApplicationInfoImpl();
	private boolean initialized = false;
	private Object fileLock = new Object();
	private Object updateLock = new Object();

	public ApplicationInfoManagerImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.klm.dev.exercises.devcase01.business.ApplicationInfoManager#load()
	 */
	@Override
	@PostConstruct
	public ApplicationInfo load() throws Throwable {

		if (!initialized) {
			synchronized (fileLock) {

				if (!initialized) {
					
					if (!applicationInfoStorage.exists()) {
						initApplicationInfoStatistics(new AtomicLong(0L), 0L);
					} else {
						
						List<String> lines = Files.readAllLines(applicationInfoStorage.getFile().toPath(), Charset.defaultCharset());
						LOGGER.debug("ApplicationInfo loaded values: {}", lines);
						
						StringTokenizer tokenizer = new StringTokenizer(lines.get(0), ";");
						
						initApplicationInfoStatistics(new AtomicLong(Long.parseLong(tokenizer.nextToken())), Long.parseLong(tokenizer.nextToken()));
					}
					
					applicationInfo.setVersion(applicationVersion);

					initialized = true;
				}
			}
		}
		return new ApplicationInfoImpl(applicationInfo);
	}

	private void initApplicationInfoStatistics(AtomicLong requestCount, long averageRequestProcessingTime) {
		applicationInfo.setRequestCount(requestCount);
		applicationInfo.setAverageRequestProcessingTime(averageRequestProcessingTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.klm.dev.exercises.devcase01.business.ApplicationInfoManager#
	 * updateAverageRequestProcessingTime(java.lang.Long)
	 */
	@Override
	public ApplicationInfo updateStatistics(Long newRequestProcessingTime) {


		synchronized (updateLock) {
			long averageRequestProcessingValue = applicationInfo.getAverageRequestProcessingTime().longValue();
			long requestCount = applicationInfo.getRequestCount().longValue();

			// Weighted average
			long newAverageRequestProcessingTime = (averageRequestProcessingValue * requestCount + newRequestProcessingTime)
					/ (requestCount + 1l);

			applicationInfo.setAverageRequestProcessingTime(newAverageRequestProcessingTime);
		}
		applicationInfo.increaseRequestCount();
		return new ApplicationInfoImpl(applicationInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.klm.dev.exercises.devcase01.business.ApplicationInfoManager#
	 * storeApplicationInfo()
	 */
	@Override
	public void storeApplicationInfo() throws Throwable {
		Path path = applicationInfoStorage.getFile().toPath();
		byte[] bytes = applicationInfo.toPersistString().getBytes();

		// Even if it's unlikely to happen, we want to prevent any possible
		// writing while reading/loading the information or the other way around
		synchronized (fileLock) {
			Files.write(path, bytes);
		}
	}

	/**
	 * @return the initialized
	 */
	public Boolean getInitialized() {
		return initialized;
	}

	private class ApplicationInfoImpl implements ApplicationInfo {

		private static final double NANOSECONDS_TO_SECONDS = 1000000000.0;

		public ApplicationInfoImpl() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ApplicationInfoImpl(ApplicationInfo applicationInfo) {
			super();
			this.averageRequestProcessingTime = applicationInfo.getAverageRequestProcessingTime();
			this.requestCount = applicationInfo.getRequestCount();
			this.version = applicationInfo.getVersion();
		}

		private String version;
		private AtomicLong requestCount;
		private Long averageRequestProcessingTime;

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.klm.dev.exercises.devcase01.model.ApplicationInfo#
		 * increaseRequestCount()
		 */
		@Override
		public void increaseRequestCount() {
			requestCount.incrementAndGet();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.klm.dev.exercises.devcase01.model.ApplicationInfo#getVersion()
		 */
		@Override
		public String getVersion() {
			return version;
		}

		/**
		 * @param version
		 *            the version to set
		 */
		public void setVersion(String version) {
			this.version = version;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.klm.dev.exercises.devcase01.model.ApplicationInfo#getRequestCount
		 * ()
		 */
		@Override
		public AtomicLong getRequestCount() {
			return requestCount;
		}

		/**
		 * @param requestCount
		 *            the requestCount to set
		 */
		public void setRequestCount(AtomicLong requestCount) {
			this.requestCount = requestCount;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.klm.dev.exercises.devcase01.model.ApplicationInfo#
		 * getAverageRequestProcessingTime()
		 */
		@Override
		public Long getAverageRequestProcessingTime() {
			return averageRequestProcessingTime;
		}

		/**
		 * @param averageRequestProcessingTime
		 *            the averageRequestProcessingTime to set
		 */
		public void setAverageRequestProcessingTime(Long averageRequestProcessingTime) {
			this.averageRequestProcessingTime = averageRequestProcessingTime;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return version + ";" + requestCount + ";" + averageRequestProcessingTime;
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toPersistString() {
			return requestCount + ";" + averageRequestProcessingTime;
		}

		@Override
		public String getAverageRequestProcessingSeconds() {
			return (getAverageRequestProcessingTime() / NANOSECONDS_TO_SECONDS) + "s";
		}
	}

	/**
	 * NOT TO BE USED. JUST An ENTRY POINT FOR TESTING PURPOSE.
	 * 
	 * @return the applicationInfoStorage
	 */
	public Resource getApplicationInfoStorage() {
		return applicationInfoStorage;
	}
	public void setApplicationInfoStorage(Resource applicationInfoStorage) {
		this.applicationInfoStorage = applicationInfoStorage;
	}

}
