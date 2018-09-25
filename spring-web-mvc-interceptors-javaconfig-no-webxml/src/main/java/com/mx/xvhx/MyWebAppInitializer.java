package com.mx.xvhx;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		// registering a configuration class
		context.register(AppConfig.class);

		// or setting an entire package for configuration classes
		// context.setConfigLocation("com.example.app.config");

		// adding a listener to the ServletContext for load the context
		// container.addListener(new ContextLoaderListener(context));

		ServletRegistration.Dynamic dispatcher = container
				.addServlet("dispatcherServlet", new DispatcherServlet(context));

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}
}
