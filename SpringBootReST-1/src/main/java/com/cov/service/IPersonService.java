package com.cov.service;

import java.util.List;

import com.cov.beans.Person;

public interface IPersonService {
	Person findById(int id) ;
	List<Person> findAll();
	Person insert(Person person);
	Person update(Person person);
	Person delete (int id);
	

}
