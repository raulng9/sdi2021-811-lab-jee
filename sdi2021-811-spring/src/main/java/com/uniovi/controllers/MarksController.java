package com.uniovi.controllers;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Mark;

@RestController
public class MarksController {

	@RequestMapping("/mark/list")
	public String getList() {
		return "Getting list";
	}
	
	@RequestMapping(value="/mark/add", method=RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		return "Added: " + mark.getDescription() + " with score: " 
		+ mark.getScore() + " id: " + mark.getId();
	}
	
	@RequestMapping("/mark/details/{id}")
	public String getDetail(@PathVariable long id) {
		return "Getting Details " + id;
	}
	
}
