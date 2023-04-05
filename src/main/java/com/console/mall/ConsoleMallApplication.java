package com.console.mall;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsoleMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleMallApplication.class, args);
	}

	@Bean
	Hibernate5Module hibernate5Module(){
		Hibernate5Module hiber = new Hibernate5Module();
		hiber.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING ,true);
		return  hiber;
	}
}
