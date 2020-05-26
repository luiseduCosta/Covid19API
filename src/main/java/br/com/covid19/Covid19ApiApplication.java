package br.com.covid19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.covid19.api.Crawler;

@SpringBootApplication
public class Covid19ApiApplication {	
	
	@Autowired
	private Crawler crawler;
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Covid19ApiApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner run()  {
		return args -> {
            //crawler.search();
		};
	}
}
