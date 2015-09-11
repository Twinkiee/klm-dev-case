package com.klm.dev.exercises.devcase01.util;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoteCallHelperTest {

	@Test
	public void testBuildUriWithParams() {
		RemoteCallHelper remoteCallHelper = new RemoteCallHelper();

		String baseServiceUri = "http://test/";

		Map<String, String> params = new HashMap<String, String>();

		params.put("param1", "param1value");
		params.put("param2", "param2value");
		params.put("param3", "param2value");

		String destinationWithParams = remoteCallHelper.buildUriWithParams(baseServiceUri, params);

		Assert.assertTrue(destinationWithParams.startsWith(baseServiceUri + "?"));
		Assert.assertNotEquals(new StringTokenizer(destinationWithParams, "=").countTokens(), 3);
		Assert.assertFalse(destinationWithParams.endsWith("&"));
	}
}
