package com.cov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cov.beans.Person;
import com.cov.exception.InvalidPersonIdException;
import com.cov.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	PersonService personservice;

	@GetMapping("/{id}")
	public Person find(@PathVariable int id) throws InvalidPersonIdException {
		return personservice.findById(id);
	}

	@GetMapping()

	public List<Person> findAll() {
		return personservice.findAll();
	}

	@PostMapping()
	public Person save(@RequestBody Person person) {
		return personservice.savePerson(person);
	}

	@DeleteMapping("/{id}")
	public Person delete(@PathVariable int id) throws InvalidPersonIdException {
		return personservice.delete(id);
	}

	@PutMapping()
	public Person update(@RequestBody Person person) throws InvalidPersonIdException {
		return personservice.editPerson(person);
	}
}
