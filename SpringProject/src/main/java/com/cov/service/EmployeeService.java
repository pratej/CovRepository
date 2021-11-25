package com.cov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cov.beans.Employee;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.repo.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo employeeRepository;

	public List<Employee> findAll() {
		return (List<Employee>) employeeRepository.findAll();

	}

	public Employee findById(int id) throws InvalidEmployeeIdException {
		Optional<Employee> empOptional = employeeRepository.findById(id);
		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id " + id + " not existing in repository");
		}
		return empOptional.get();

	}

	public Employee save(Employee employee) {

		return employeeRepository.save(employee);

	}

	public Employee update(Employee employee) throws InvalidEmployeeIdException {
		Optional<Employee> empOptional = employeeRepository.findById(employee.getId());
		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id" + employee.getId() + "not existing in reposiotory");
		}
		return employeeRepository.save(employee);
	}

	public Employee delete(int id) throws InvalidEmployeeIdException {
		Optional<Employee> empOptional = employeeRepository.findById(id);
		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id " + id + "not existing in repository");
		}
		Employee employee = empOptional.get();
		employeeRepository.deleteById(id);
		return employee;
	}

	public Employee findAllByDeptno(int deptid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee insert(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
}