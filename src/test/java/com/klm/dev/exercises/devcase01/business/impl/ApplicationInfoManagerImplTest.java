package com.klm.dev.exercises.devcase01.business.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.klm.dev.exercises.devcase01.business.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import com.klm.dev.exercises.devcase01.config.OfflineConfiguration;
import com.klm.dev.exercises.devcase01.model.ApplicationInfo;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfiguration.class, OfflineConfiguration.class })
@ActiveProfiles("offline")
@PropertySource({"classpath:./src/test/config/application.properties"})
public class ApplicationInfoManagerImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ApplicationInfoManager applicationInfoManager;

	@Test
	@DirtiesContext
	public void load() throws Throwable {

		ApplicationInfo applicationInfo = applicationInfoManager.load();

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(0l));
	}

	@BeforeMethod
	private void cleanup() {
		try {
			((ApplicationInfoManagerImpl) applicationInfoManager).setApplicationInfoStorage(new FileSystemResource(
					"./src/test/resources/application-storage.txt"));
			((ApplicationInfoManagerImpl) applicationInfoManager).getApplicationInfoStorage().getFile().delete();
		} catch (Exception e) {
		}
	}

	@Test
	@DirtiesContext
	public void updateStatistics() throws Throwable {

		ApplicationInfo applicationInfo = applicationInfoManager.load();

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(0l));

		applicationInfo = applicationInfoManager.updateStatistics(1000L);

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(1000L));

		applicationInfo = applicationInfoManager.updateStatistics(500L);

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(750L));

		applicationInfo = applicationInfoManager.updateStatistics(600L);

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(700L));
	}

	@Test(groups = "updateStatisticsParallel", invocationCount = 30, threadPoolSize = 5)
	public void updateStatisticsParallel() throws Throwable {

		applicationInfoManager.updateStatistics(1000L);
	}

	@Test(dependsOnMethods = "updateStatisticsParallel")
	public void checkStatisticsParallel() throws Throwable {

		ApplicationInfo applicationInfo = applicationInfoManager.load();

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(1000L));
		Assert.assertEquals(applicationInfo.getRequestCount().longValue(), 30L);
	}

	@Test
	@DirtiesContext
	public void storeApplicationInfo() throws Throwable {


		ApplicationInfo applicationInfo = applicationInfoManager.updateStatistics(333L);

		Assert.assertNotNull(applicationInfo);
		Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(333L));

		applicationInfoManager.storeApplicationInfo();

		List<String> readAllLines = Files.readAllLines(((ApplicationInfoManagerImpl) applicationInfoManager).getApplicationInfoStorage()
				.getFile().toPath(), Charset.defaultCharset());

		Assert.assertEquals(readAllLines.get(0), "1;333");

		((ApplicationInfoManagerImpl) applicationInfoManager).getApplicationInfoStorage().getFile().delete();
	}
}
