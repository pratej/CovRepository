package com.cov.service;

import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RestController;
import com.cov.beans.Person;
//import com.cov.controller.PersonController;
import com.cov.exception.InvalidPersonIdException;
import com.cov.repo.PersonRepository;

@Service
public class PersonService {
	Logger logger = Logger.getLogger(PersonService.class);
	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll() {
		return (List<Person>) personRepository.findAll();
	}

	public Person findById(int id) throws InvalidPersonIdException {
		logger.info("Finding person with ID : " + id);
		Optional<Person> personOptional = personRepository.findById(id);
		if (!personOptional.isPresent()) {
			logger.debug("Person not found with ID : " + id);
			InvalidPersonIdException invalidPersonIdException = new InvalidPersonIdException("Person ID not found");
			logger.warn(invalidPersonIdException);
			throw new InvalidPersonIdException();
		}
		Person person = personOptional.get();
		logger.info("Person found with id " + id + " is " + person.getFirstname() + " " + person.getLastname());
		return person;
	}

	public Person insert(Person person) {
		return personRepository.save(person);
	}

	public Person update(Person person) throws InvalidPersonIdException {
		Optional<Person> personOptional = personRepository.findById(person.getId());
		if (!personOptional.isPresent()) {
			throw new InvalidPersonIdException();
		}
		return personRepository.save(person);
	}

	public Person delete(int id) throws InvalidPersonIdException {
		Optional<Person> personOptional = personRepository.findById(id);
		if (!personOptional.isPresent()) {
			throw new InvalidPersonIdException();
		}
		Person person = personOptional.get();
		personRepository.deleteById(id);
		return person;
	}
}
