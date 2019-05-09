package com.nuc.a4q.Filter;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*@Configuration*/
public class WebConfigurerAdapter extends WebMvcConfigurerAdapter {

	
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminAuthFilter()).addPathPatterns("/back/**");
    }
}
