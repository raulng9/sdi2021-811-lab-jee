package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView {
	static public void fillForm(WebDriver driver, String dniToEnter, String passwordToEnter) {
		//Primero rellenamos el dni, luego la contraseña y por último hacemos el click
		WebElement dni = driver.findElement(By.name("username"));
		dni.click();
		dni.clear();
		dni.sendKeys(dniToEnter);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordToEnter);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
