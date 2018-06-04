package org.tottus.ventaonline;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.js/**").addResourceLocations("/assets/js/");
		registry.addResourceHandler("/*.css/**").addResourceLocations("/assets/css/");
		registry.addResourceHandler("/*.png/**").addResourceLocations("/assets/images/");
	}
	
}
