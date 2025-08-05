package com.oracle.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@PropertySource({"classpath:error.properties"}) //Loads the error.properties file for custom validation messages.
public class ValidationConfiguration {

	@Bean
	public LocalValidatorFactoryBean getLocalValidatorFactoryBean()  //Configures a validator bean that uses a message source for validation error messages.
	{
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(getMessageSource());
		return localValidatorFactoryBean;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource getMessageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(new String[] {"classpath:error"});
		messageSource.setUseCodeAsDefaultMessage(true); //Uses the error code as the default message if no message is found.
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
