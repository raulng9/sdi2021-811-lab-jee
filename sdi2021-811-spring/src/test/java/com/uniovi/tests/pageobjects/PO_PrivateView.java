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

	// Método para simplificar login

	static public void loginAsAlumno(WebDriver driver, String username, String password) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// Comprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	static public void loginAsProfessor(WebDriver driver, String username, String password) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// Comprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", "99999993D");
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
