package com.cov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cov.beans.Person;
import com.cov.exception.InvalidPersonIdException;
import com.cov.repo.PersonRepository;

@Service
public class PersonService {
	@Autowired
	PersonRepository personRepo;

	public List<Person> findAll() {
		return (List<Person>) personRepo.findAll();
	}

	public Person findById(int id) throws InvalidPersonIdException {
		Optional<Person> personOptional = personRepo.findById(id);
		if (!personOptional.isPresent()) {
			throw new InvalidPersonIdException();
		}
		return personOptional.get();
	}

	public Person savePerson(Person person) {
		return personRepo.save(person);
	}

	public Person editPerson(Person person) throws InvalidPersonIdException {
		Optional<Person> personOptional = personRepo.findById(person.getId());
		if (!personOptional.isPresent()) {
			throw new InvalidPersonIdException();
		}
		return personRepo.save(person);
	}

	public Person delete(int id) throws InvalidPersonIdException {
		Optional<Person> personOptional = personRepo.findById(id);
		if (!personOptional.isPresent()) {
			throw new InvalidPersonIdException();
		}
		Person person = personOptional.get();
		personRepo.deleteById(id);
		return person;
	}
}
