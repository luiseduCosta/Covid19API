package br.com.covid19.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.covid19.api.cases.country_case.CountryCase;
import br.com.covid19.api.cases.country_case.CountryCaseService;
import br.com.covid19.api.cases.state_case.StateCase;
import br.com.covid19.api.cases.state_case.StateCaseService;

@Component
public class Crawler {
	
	@Autowired
	private CountryCaseService countryService;
	@Autowired
	private StateCaseService stateService;
	
	private WebDriver webDriver;
	private List<CountryCase> countryCases;
	
	public final void search() {	
		System.setProperty("webdriver.chrome.driver", 
			new File("assets/chromedriver").getAbsolutePath()
		);	
		webDriver = new ChromeDriver(
			new ChromeOptions().setHeadless(true)
		);
		
		countryCases = searchCountries();
		countryCases.remove(0);
		saveCountryStateCases();
	}
	
	//OK
	private void saveCountryStateCases() {
		for (int i = 0; i < countryCases.size(); i++) {			
			CountryCase countryCase = this.countryCases.get(i);
			
			CountryCase countryCaseDb = countryService
					.findByDataId(countryCase.getDataId());
			
			if (countryCaseDb != null) {
				//Update
				countryCase = countryService.update(countryCase);
			} else {
				//Save
				countryCase = countryService.save(countryCase);
			}
			
			//Search and Save States
			searchSaveStates(countryCase);
		} 
		webDriver.quit();
	}
	
	//OK
	private List<CountryCase> searchCountries() {
		List<CountryCase> countryCases = new ArrayList<>();
		
		webDriver.get("https://news.google.com/covid19/map?hl=pt-BR&gl=BR&ceid=BR:pt-419");
		
		//Clicando na div para listar todos os paises
		webDriver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div[2]/"
				+ "div[5]/div/div/div[1]/div")).click();
		List<WebElement> webElementsCountries = webDriver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div"
				+ "/div[2]/div[2]/div[5]/div/div/div[1]/div/div[1]/table/tbody/tr"));
		
		for(WebElement elemCountry : webElementsCountries) {
			String[] tableData = elemCountry.getText().split("\n");
			String[] cases = tableData[1].split(" ");
			String dataId = elemCountry.getAttribute("data-id").split("/")[2];
			String urlFlag = elemCountry.findElement(By.tagName("img"))
										    .getAttribute("src");
			
			String countryName = tableData[0];
			Long confirmed = cases[0].equals("—") ? null : removePointNumber(cases[0]);
			Long recovered = cases[2].equals("—") ? null : removePointNumber(cases[2]);
			Long deaths = cases[3].equals("—") ? null : removePointNumber(cases[3]); 
			
			CountryCase countryCase = new CountryCase(null, dataId, countryName, confirmed, 
					recovered, deaths, urlFlag);
			
			countryCases.add(countryCase);
		} 
		countryCases.sort(Comparator.reverseOrder());
		return countryCases;
	}
	
	//OK
	private void searchSaveStates(CountryCase countryCase) {
		//webDriver = new ChromeDriver(
		//	new ChromeOptions().setHeadless(true)
		//);
		webDriver.get("https://news.google.com/covid19/map?hl=pt-"
				+ "BR&gl=BR&ceid=BR%3Apt-419&mid=%2Fm%2F"+countryCase.getDataId());
		
		//Clicando na div para listar todos os estados
		webDriver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div[2]/"
				 + "div[5]/div/div/div[1]/div")).click();
		
		//Acessando table
		List<WebElement> webElementsStates = webDriver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div"
				+ "/div[2]/div[2]/div[5]/div/div/div[1]/div/div[1]/table/tbody/tr"));
		
		//Excluindo os dois primeiros elementos GLOBAL e PAÍS
		webElementsStates = webElementsStates.subList(2, webElementsStates.size());
		
		for(WebElement elemCountry : webElementsStates) {
			String[] tableData = elemCountry.getText().split("\n");
			String[] cases = tableData[1].split(" ");
			String dataId = elemCountry.getAttribute("data-id").split("/")[2];
			
			String name = tableData[0];
			Long confirmed = cases[0].equals("—") ? null : removePointNumber(cases[0]);
			Long recovered = cases[2].equals("—") ? null : removePointNumber(cases[2]);
			Long deaths = cases[3].equals("—") ? null : removePointNumber(cases[3]); 
			
			StateCase stateCase = new StateCase(null, dataId, name, confirmed, recovered, 
					deaths, countryCase);
			
			StateCase stateCaseDb = stateService.findByDataId(stateCase.getDataId());
			if(stateCaseDb != null) {
				//Update
				stateService.update(stateCase);
			} else {
				//Save
				stateService.save(stateCase);
			}
		}
	}
	
	//OK
	private static Long removePointNumber(String cases) {
		return Long.parseLong(cases.replace(".", ""));
	}
}