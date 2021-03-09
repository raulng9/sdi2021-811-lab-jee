package com.uniovi.tests;

import org.junit.runners.MethodSorters;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementariosTests {

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

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Registro de profesores con datos válidos
	@Test
	public void Prueba1Extra() {
		String loginUrl = "http://localhost:8091/login";
		driver.navigate().to(loginUrl);
		String professorAddURL = "http://localhost:8091/professor/add";
		// Login como administrador
		PO_PrivateView.loginAsAdministratorIndirectly(driver, "99999988F", "123456");
		driver.navigate().to(professorAddURL);
		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "TestProfesor1", "Apellidos profesor",
				"categoria test 1.");
		PO_View.checkElement(driver, "text", "Profesores en el sistema:");
	}

	// Registro de profesores con datos inválidos (nombre y categoría)
	@Test
	public void Prueba2Extra() {
		String loginUrl = "http://localhost:8091/login";
		driver.navigate().to(loginUrl);
		String professorAddURL = "http://localhost:8091/professor/add";
		// Login como administrador
		PO_PrivateView.loginAsAdministratorIndirectly(driver, "99999988F", "123456");
		driver.navigate().to(professorAddURL);
		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "A", "Apellidos profesor", "C");
		// Ahora se debería de producir un fallo a la hora de registrar el nuevo
		// profesor
		PO_View.getP();
		// Comprobamos el error de Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
		// Comprobamos el error de categoría corta .
		PO_RegisterView.checkKey(driver, "Error.signup.categoria.length", PO_Properties.getSPANISH());
	}

	// Solo los usuarios autorizados pueden añadir un profesor (acceso con alumno
	// --> 403 Forbidden
	// y acceso con administrador)
	@Test
	public void Prueba3Extra() {
		
		String professorAddURL = "http://localhost:8091/professor/add";
		
		// Acceso como persona no autenticada, debería redirigir a /home
		driver.navigate().to(professorAddURL);
		PO_View.checkElement(driver, "text", "Iniciar sesión");

		String loginUrl = "http://localhost:8091/login";
		driver.navigate().to(loginUrl);
		SeleniumUtils.esperarSegundos(driver, 2);
		
		
		// Acceso como administrador y comprobación de la posibilidad de añadir prof.
		PO_PrivateView.loginAsAdminDirectly(driver, "99999988F", "123456");
		driver.navigate().to(professorAddURL);
		PO_View.checkElement(driver, "text", "Agregar Profesor");
		String homeUrl = "http://localhost:8091/home";
		driver.navigate().to(homeUrl);
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
		
		
		// Acceso como alumno y comprobación de acceso prohibido
		PO_PrivateView.loginAsAlumno(driver, "99999990A", "123456");
		SeleniumUtils.esperarSegundos(driver, 2);
		driver.navigate().to(professorAddURL);
		// Debe aparecer un error 403 Forbidden ya que un alumno no puede acceder a esta sección
		SeleniumUtils.esperarSegundos(driver, 2);
		SeleniumUtils.textoNoPresentePagina(driver, "Agregar Profesor");

	}

}
