package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundos a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumno userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenamos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	
	static public void fillFormAddProfessor(WebDriver driver, String dniProf, String nombreProf, String apellidosProf, String categoriaProf) {
		// Esperamos 5 segundos a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 3);
		// Rellenamos los datos de un profesor a añadir
		WebElement dni = driver.findElement(By.name("dni"));
		dni.clear();
		dni.sendKeys(dniProf);
		WebElement nombre = driver.findElement(By.name("nombre"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(nombreProf);
		WebElement apellidos = driver.findElement(By.name("apellidos"));
		apellidos.clear();
		apellidos.sendKeys(apellidosProf);
		WebElement cat = driver.findElement(By.name("categoria"));
		cat.clear();
		cat.sendKeys(categoriaProf);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	// Método para simplificar login

	static public void loginAsAlumno(WebDriver driver, String username, String password) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, username, password);
		// Comprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	static public void loginAsProfessor(WebDriver driver, String username, String password) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, username, password);
		// Comprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", username);
	}
	
	static public void loginAsAdministrator(WebDriver driver, String username, String password) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, username, password);
		// Comprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", username);
	}
	
	static public void loginAsAdministratorIndirectly(WebDriver driver, String username, String password) {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, username, password);
		// Comprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", username);
	}
	
	static public void loginAsAdminDirectly(WebDriver driver, String username, String password) {
		PO_LoginView.fillForm(driver, username, password);
	}


	// Método para ver detalles de nota
	// Método para eliminar una nota (última o con un texto en concreto)
	// Método para ir a la última página del listado de notas
	// Alumno: contar número de notas para un alumno determinado

	static public void addMark(WebDriver driver, String markDescription, String markScore) {
		// Llamada a this.fillFormAddMark
	}

	static public void deleteMark(WebDriver driver, String markName) {

	}

	static public void seeMarkDetails(WebDriver driver, String expectedMarkDetails) {

	}

	static public int countMarksAvailable(WebDriver driver) {
		return 0;
	}
}
