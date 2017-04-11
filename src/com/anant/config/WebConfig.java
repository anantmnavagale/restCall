package com.anant.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * 
 * 
 * @author Anant
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.anant" })
@EnableAspectJAutoProxy
// @ImportResource({ "classpath:com/buzzor/config/dataSource.xml" })
// @Import({ BuzzorSecurityConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private ResourceLoader resourceLoader;

	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

	

	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


	@Bean
	public ContentNegotiatingViewResolver contentNegotation() {
		ContentNegotiatingViewResolver contentNegotiator = new ContentNegotiatingViewResolver();
		Map<String, String> mapContentType = new HashMap<String, String>();
		java.util.List<View> lstViews = new ArrayList<View>();
		mapContentType.put("html", "text/html");
		mapContentType.put("json", "application/json");
		contentNegotiator.setMediaTypes(mapContentType);

		lstViews.add(new MappingJacksonJsonView());

		contentNegotiator.setDefaultViews(lstViews);

		return contentNegotiator;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
