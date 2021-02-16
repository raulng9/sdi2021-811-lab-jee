package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;
	
	
	private List<Professor> professors = new ArrayList<Professor>();
	
	@PostConstruct
	public void init() {
		professors.add(new Professor(1L, "Raul","Nunez Garcia", "Informatica"));
		professors.add(new Professor(2L, "Mireia","Lopez Fernandez", "Fisica"));
	}
	
	public List<Professor> getProfessors(){
		List<Professor> professors = new ArrayList<Professor>();
		professorRepository.findAll().forEach(professors::add);
		return professors;
	}
	
	public  Professor getProfessor(Long dni) {
		return professors.stream()
				.filter(prof -> prof.getDni().equals(dni)).findFirst().get();
	}
	
	public void addProfessor(Professor prof) {
		professorRepository.save(prof);
		if(prof.getDni() == null) {
			prof.setDni(professors.get(professors.size() -1).getDni());
		}
	}
	
	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}
}
