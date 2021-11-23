package com.cov.service;

import java.util.List;

import com.cov.beans.Person;
import com.cov.exception.InvalidPersonIdException;

public interface IPersonService {
	Person findById(int id) throws InvalidPersonIdException;
	List<Person> findAll();
	Person insert(Person person) throws InvalidPersonIdException;
	Person update(Person person) throws InvalidPersonIdException;
	Person delete (int id);
	

}
