package cn.com.starest.nextoa.dashboard;

import cn.com.starest.nextoa.project.web.interceptor.ProjectModulePermissionInterceptor;
import in.clouthink.daas.we.CustomExceptionHandlerExceptionResolver;
import in.clouthink.daas.we.DefaultErrorResolver;
import in.clouthink.daas.we.ErrorMappingResolver;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Configuration
public class DashboardWebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(authorityAnnotationInterceptor());
	}


	@Override
	public void addReturnValueHandlers(final List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		returnValueHandlers.add(new RequestResponseBodyMethodProcessor(messageConverters));
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(customExceptionHandlerExceptionResolver());
	}

	@Bean
	public FilterRegistrationBean httpMethodFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(httpMethodFilter());
		registration.addUrlPatterns("/*");
		registration.setName("httpMethodFilter");
		return registration;
	}

	@Bean(name = "httpMethodFilter")
	public Filter httpMethodFilter() {
		return new HttpPutFormContentFilter();
	}


	@Bean
	public HandlerExceptionResolver customExceptionHandlerExceptionResolver() {
		CustomExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new CustomExceptionHandlerExceptionResolver(
				true);
		exceptionHandlerExceptionResolver.getErrorResolver()
										 .add(new ErrorMappingResolver())
										 .setDefaultErrorResolver(new DefaultErrorResolver());
		return exceptionHandlerExceptionResolver;
	}

	@Bean
	public HandlerInterceptor authorityAnnotationInterceptor() {
		return new ProjectModulePermissionInterceptor();
	}

}
