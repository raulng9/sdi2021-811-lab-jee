package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;	
	
	public List<Professor> getProfessors(){
		List<Professor> professors = new ArrayList<Professor>();
		professorRepository.findAll().forEach(professors::add);
		return professors;
	}
	
	public  Professor getProfessor(Long dni) {
		return professorRepository.findById(dni).get();
	}
	
	public void addProfessor(Professor prof) {
		professorRepository.save(prof);
	}
	
	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}
	
	public Professor getProfessorByDni(String dni) {
		return professorRepository.findByDni(dni);
	}
}
