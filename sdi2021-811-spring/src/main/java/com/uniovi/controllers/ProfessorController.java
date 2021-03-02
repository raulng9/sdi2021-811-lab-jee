package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;
import com.uniovi.validators.AddProfessorValidator;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private AddProfessorValidator addProfessorValidator;

	/*
	 * @RequestMapping("/professor/list") public String getList(Model model) {
	 * model.addAttribute("professors", professorService.getProfessors()); return
	 * "/professor/list"; }
	 */

	@RequestMapping("/professor/list")
	public String getList(Model model, Pageable pageable) {
		Page<Professor> professors = new PageImpl<Professor>(new LinkedList<Professor>());
		professors = professorService.getProfessors(pageable);
		model.addAttribute("professors", professors.getContent());
		model.addAttribute("page", professors);
		return "/professor/list";
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(Model model, @Validated Professor professor, BindingResult result) {
		addProfessorValidator.validate(professor, result);
		if (result.hasErrors()) {
			return "professor/add";
		}
		professorService.addProfessor(professor);
		return "redirect:/professor/list";
	}

	@RequestMapping("/professor/add")
	public String getProfessor(Model model) {
		model.addAttribute("professor", new Professor());
		return "professor/add";
	}

	@RequestMapping("/professor/detail/{dni}")
	public String getDetail(Model model, @PathVariable Long dni) {
		model.addAttribute("professor", professorService.getProfessor(dni));
		return "professor/details";
	}

	@RequestMapping(value = "/professor/edit/{dni}")
	public String getEdit(Model model, @PathVariable Long dni) {
		model.addAttribute("professor", professorService.getProfessor(dni));
		return "professor/edit";
	}

	@RequestMapping(value = "/professor/edit/{dni}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable String dni, @ModelAttribute Professor professor) {
		professor.setDni(dni);
		professorService.addProfessor(professor);
		return "redirect:/professor/detail/" + dni;
	}

	@RequestMapping("/professor/delete/{dni}")
	public String deleteProfessor(@PathVariable Long dni) {
		professorService.deleteProfessor(dni);
		return "redirect:/professor/list";
	}

}
