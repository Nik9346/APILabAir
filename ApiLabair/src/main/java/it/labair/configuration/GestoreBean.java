package it.labair.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GestoreBean {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
