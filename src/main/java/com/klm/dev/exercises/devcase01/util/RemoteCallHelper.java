/**
 * 
 */
package com.klm.dev.exercises.devcase01.util;

import java.util.Map;

/**
 * @author antonio.cirasole
 *
 */
public class RemoteCallHelper {


	public String buildUriWithParams(String baseServiceUri, Map<String, String> params) {
		
		StringBuilder uri = new StringBuilder(baseServiceUri + "?");

		for (String key : params.keySet()) {
			uri.append(key).append("={").append(key).append("}&");
		}

		return uri.substring(0, uri.length() - 1);
	}
}
