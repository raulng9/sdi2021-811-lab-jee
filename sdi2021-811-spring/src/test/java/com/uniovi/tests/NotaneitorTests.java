package com.uniovi.tests;

import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {
	
	static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	static String Geckdriver024 = "/Users/raul/selenium/geckodriver024mac";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";
	
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		 System.setProperty("webdriver.firefox.bin", PathFirefox);
		 System.setProperty("webdriver.gecko.driver", Geckdriver);
		 WebDriver driver = new FirefoxDriver();
		 return driver;
	}
	 
	@Before
	 public void setUp() throws Exception {
		 driver.navigate().to(URL);
	 }
	 
	
	 @After
	 public void tearDown() throws Exception {
		 driver.manage().deleteAllCookies();
	 }
	 
	 
	 //Antes de la primera prueba
	 @BeforeClass
	 static public void begin() {
		 
	 }
	 
	 
	 //Al finalizar la última prueba
	 @AfterClass
	 static public void end() {
	 //Cerramos el navegador al finalizar las pruebas
	     driver.quit();
	 }
	 
	 
	 @Test
	 public void test() {
		 fail("Not yet implemented");
	 }
	 
}
