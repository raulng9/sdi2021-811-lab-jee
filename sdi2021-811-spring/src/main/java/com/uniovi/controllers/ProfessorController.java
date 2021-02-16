package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@RestController
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping("/professor/list")
	public String getList() {
		return professorService.getProfessors().toString();
	}
	
	@RequestMapping(value="/professor/add", method=RequestMethod.POST)
	public String addProfessor(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellidos, @RequestParam String categoria) {
		
		
		return "Added: " + nombre + " " + apellidos;
		//professorService.addProfessor(new Professor(dni,nombre,apellidos,categoria));
		//return professorService.getProfessors().toString();
	}
	
	@RequestMapping("/professor/add")
	public String setProfessor() {
		return "/professor/add";
	}
	
	@RequestMapping("/professor/detail/{dni}")
	public String getDetail(@PathVariable Long dni) {
		return professorService.getProfessor(dni).toString();
	}
	
	@RequestMapping("/professor/delete/{dni}")
	public String deleteProfessor(@PathVariable Long dni) {
		professorService.deleteProfessor(dni);
		return professorService.getProfessors().toString();
	}
}
