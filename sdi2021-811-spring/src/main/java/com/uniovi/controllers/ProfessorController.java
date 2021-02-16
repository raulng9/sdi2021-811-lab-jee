package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping("/professor/list")
	public String getList() {
		return professorService.getProfessors().toString();
	}

	@RequestMapping(value="/professor/add", method=RequestMethod.POST)
	public String setProfessor(@ModelAttribute Professor professor) {
		professorService.addProfessor(professor);
		return "redirect:/professor/list";
	}
	
	@RequestMapping("/professor/add")
	public String getProfessor() {
		return "professor/add";
	}
	
	@RequestMapping("/professor/detail/{dni}")
	public String getDetail(Model model, @PathVariable Long dni) {
		model.addAttribute("professor", professorService.getProfessor(dni));
		return "professor/details";
	}
	
	@RequestMapping(value="/professor/edit/{dni}")
	public String getEdit(Model model, @PathVariable Long dni){
		model.addAttribute("mark", professorService.getProfessor(dni));
		return "professor/edit";
	}
	
	@RequestMapping("/professor/delete/{dni}")
	public String deleteProfessor(@PathVariable Long dni) {
		professorService.deleteProfessor(dni);
		return "redirect:/professor/list";
	}
	
	
	@RequestMapping(value="/professor/edit/{dni}", method=RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long dni, @ModelAttribute Professor professor){
		professor.setDni(dni);
		professorService.addProfessor(professor);
		return "redirect:/professor/details/"+dni;
	}
	
}
