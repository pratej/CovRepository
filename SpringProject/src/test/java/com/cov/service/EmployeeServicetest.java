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
import com.cov.beans.Department;
import com.cov.beans.Employee;
import com.cov.repo.EmployeeRepo;

@TestInstance(Lifecycle.PER_CLASS)
class EmployeeServiceTest {
	@InjectMocks
	EmployeeService employeeService;
	@Mock
	EmployeeRepo employeeRepository;

	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<Employee> employee = new ArrayList<>();
		Department department1 = new Department(1, "integrations");
		Department department2 = new Department(2, "CRM");
		Department department3 = new Department(3, "HumanResource");
		Department department4 = new Department(4, "BRM");
		employee.add(new Employee(1, "pratej", department1));
		employee.add(new Employee(2, "rahul", department2));
		employee.add(new Employee(3, "anoushka", department3));
		employee.add(new Employee(4, "kajol", department4));
		when(employeeRepository.findAll()).thenReturn(employee);
		Employee employeeExisting = new Employee(3, "anoushka", department3);
		when(employeeRepository.findById(2)).thenReturn(Optional.of(employeeExisting));
	}

	@Test
	void testFindAll() {
		List<Employee> empList = employeeService.findAll();
		assertNotNull(empList);
		assertEquals(4, empList.size());
	}

	@Test
	void testFindById() {
		Employee employee = employeeRepository.findById(2).get();
		assertNotNull(employee);
		assertSame(employee.getName(), "anoushka");
		assertEquals(employee.getId(), 3);
	}

	@Test
	void testInsert() {
		Department dept = new Department(1, "Admin");
		Employee employee = new Employee(5, "Aryan", dept);
		when(employeeRepository.save(employee)).thenReturn(employee);
		Employee e = employeeRepository.save(employee);
		assertNotNull(e);
		assertSame(e.getName(), "Aryan");
		assertEquals(e.getId(), 5);
	}

	@Test
	void testUpdate() {
		Department department4 = new Department(4, "CRM");
		Employee employee = new Employee(4, "kajol", department4);
		when((employeeRepository.findById(employee.getId()))).thenReturn(Optional.of(employee));
		when(employeeRepository.save(employee)).thenReturn(employee);
		Employee e = employeeRepository.save(employee);
		assertNotNull(e);
		assertSame(e.getName(), "kajol");
		assertEquals(e.getId(), 4);
	}
}
