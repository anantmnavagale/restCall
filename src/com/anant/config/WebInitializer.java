package com.anant.config;

import java.util.EnumSet;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * 
 * @author Anant
 * 
 */

@Configuration
public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		WebApplicationContext context = getContext();
		// InputStream inputStream=;
		Hashtable env = new Hashtable();

		try {
			Context ctx = new InitialContext(env);
			// ctx.
			// Constants.log.debug(this.getClass()+" method - " +
			// Thread.currentThread().getStackTrace()[1].getMethodName()+" "+ctx.getEnvironment());
			// (", value));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// setSystemProperty("solr.solr.home",
		// "/home/viral/Projects/apache-tomcat-7.0.42/solr");
		// setSolrDispatcher(servletContext);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/**/*");
		dispatcher.addMapping("/*");

		servletContext.addListener(new ContextLoaderListener(context));

		// EnumSet<DispatcherType> setDispatcher = EnumSet.of(
		// DispatcherType.REQUEST, DispatcherType.INCLUDE,
		// DispatcherType.FORWARD);

		// FilterRegistration.Dynamic oncePerRequestFilter = servletContext
		// .addFilter("myOncePerRequestFilter",
		// RequestFilterSessionValidator.class);
		// oncePerRequestFilter
		// .addMappingForUrlPatterns(setDispatcher, true, "/*");

		// FilterRegistration.Dynamic springFilter = servletContext.addFilter(
		// "springSecurityFilterChain", DelegatingFilterProxy.class);
		// springFilter.addMappingForUrlPatterns(setDispatcher, true, "/*");

		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));

	}

	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.anant");
		return context;
	}

}
