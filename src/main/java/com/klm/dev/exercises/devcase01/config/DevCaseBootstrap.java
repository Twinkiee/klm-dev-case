package com.klm.dev.exercises.devcase01.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



public class DevCaseBootstrap implements WebApplicationInitializer {

	@Override
    public void onStartup(ServletContext container) throws ServletException {
        final AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(DevCaseConfiguration.class);
        final ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(webContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
