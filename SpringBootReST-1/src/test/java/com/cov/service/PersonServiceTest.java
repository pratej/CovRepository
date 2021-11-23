
package com.cov.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.cov.beans.Person;
import com.cov.repo.PersonRepository;

@TestInstance(Lifecycle.PER_CLASS)
class PersonServiceTest {
	@InjectMocks
	PersonService personService;
	@Mock
	PersonRepository personRepository;

	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<Person> person = new ArrayList<>();
		person.add(new Person(1, "Pratej", "yadav"));
		person.add(new Person(1, "kajol", "singh"));
		person.add(new Person(1, "Anoushka", "nijhawan"));
		List<Person> personList = personService.findAll();
		when(personRepository.findAll()).thenReturn(person);
	}

	@Test
	void testFindAll() {
		List<Person> personList = personService.findAll();
		assertNotNull(personList);
		assertEquals(3, personList.size());
	}

	@Test
	void testFindById() {
		Person personExisting = new Person(2, "hari", "charan");
		when(personRepository.findById(2)).thenReturn(Optional.of(personExisting));
		Person person = personRepository.findById(2).get();
		assertNotNull(person);
		assertSame(person.getFirstname(), "hari");
		assertEquals(person.getId(), 2);
	}
}
// @Test
// void testInsert() {

// fail("Not yet implemented");
// }
//
// @Test
// void testUpdate() {
// fail("Not yet implemented");
// }
//
// @Test
// void testDelete() {
// fail("Not yet implemented");
// }}
