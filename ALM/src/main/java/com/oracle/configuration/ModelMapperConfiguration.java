package com.oracle.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
		
	@Bean // creating an object called bean and can be used throughout the project.Instead of creating the modelmapper in every class where required, we can use bean here and can inject in required class using @Autowired
		public ModelMapper getModelMapper()
		{
			return new ModelMapper();
		}
}
