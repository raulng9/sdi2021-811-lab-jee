package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor {
	@Id
	@GeneratedValue
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String categoria;
	
	
	public Professor(String dni, String nombre, String apellidos, String categoria) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoria = categoria;
	}
	
	public Professor() {
		
	}
		
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "DNI: " + String.valueOf(dni) + " Nombre: " + nombre + " Apellidos: " + apellidos + " Categoría: " + categoria; 
	}

	
}
