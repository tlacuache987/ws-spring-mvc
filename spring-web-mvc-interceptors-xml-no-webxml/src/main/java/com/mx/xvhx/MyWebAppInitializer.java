package com.mx.xvhx;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocation("/WEB-INF/spring/root-context.xml");

		// adding a listener to the ServletContext for load the context
		container.addListener(new ContextLoaderListener(rootContext));

		XmlWebApplicationContext webContext = new XmlWebApplicationContext();
		webContext.setConfigLocation("/WEB-INF/spring/mvc/mvc-context.xml");

		ServletRegistration.Dynamic dispatcherServlet = container
				.addServlet("dispatcherServlet", new DispatcherServlet(webContext));

		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping("/");

	}

}
