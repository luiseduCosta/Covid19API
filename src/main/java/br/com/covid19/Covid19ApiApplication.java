package br.com.covid19;

import java.util.Timer;
import java.util.TimerTask;

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
           long minutes = 50;
           new Timer().scheduleAtFixedRate(new TimerTask() {
        	   public void run() {
        		   crawler.search();
				}
           }, 0, minutes * 60000);
		};
	} 
}
