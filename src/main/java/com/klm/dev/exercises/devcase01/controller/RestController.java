/**
 * 
 */
package com.klm.dev.exercises.devcase01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klm.dev.exercises.devcase01.business.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.model.ApplicationInfo;

/**
 * @author antonio.cirasole
 *
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static Logger LOGGER = LoggerFactory.getLogger(RestController.class);

	@Autowired
	private ApplicationInfoManager applicationInfoManager;

	@RequestMapping(value = "/app-info")
	public ApplicationInfo applicationInfoImpl() {
		try {
			return applicationInfoManager.load();
		} catch (Throwable e) {
			LOGGER.error("Error while loading Application Info;", e);
		}

		return null;
	}
}
