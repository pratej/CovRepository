package com.cov.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cov.beans.Employee;
import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.service.DepartmentService;
import com.cov.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	static Logger logger = Logger.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;

	@GetMapping("/{id}")
	public Employee find(@PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("Finding an Employee with id : " + id);
		Employee employee = employeeService.findById(id);
		logger.debug(
				"Found Employee is" + employee.getId() + " " + employee.getName() + " " + employee.getDepartment());
		return employeeService.findById(id);
	}

	@GetMapping()
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@PostMapping()
	public Employee insertEmp(@RequestBody Employee employee) throws InvalidEmployeeIdException {
		logger.info("Finding an Employee with name : " + employee);
		logger.debug(
				"Inserted Employee is" + employee.getId() + " " + employee.getName() + " " + employee.getDepartment());
		return employeeService.insert(employee);
	}

	@PutMapping(" ")
	public Employee updateEmployee(@RequestBody Employee employee) throws InvalidEmployeeIdException {
		logger.info("Finding an Employee with name : " + employee);
		logger.debug(
				"Updated Employee is" + employee.getId() + " " + employee.getName() + " " + employee.getDepartment());
		return employeeService.update(employee);
	}

	@DeleteMapping
	public Employee deleteEmp(@PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("Finding an Employee with id : " + id);
		Employee employees = (Employee) employeeService.delete(id);
		logger.debug("Deleted Employee is" + employees.getId());
		return employeeService.delete(id);
	}

	@GetMapping("/{deptid}")
	public Employee findAllEmployeeByDept(@PathVariable int deptid) throws InvalidDepartmentIdException {
		logger.info("Finding an Employee with id : " + deptid);
		Employee employees = (Employee) employeeService.findAllByDeptno(deptid);
		logger.debug(
				"Found Employee is" + employees.getId() + " " + employees.getName() + " " + employees.getDepartment());
		return (Employee) employeeService.findAllByDeptno(deptid);
	}
}
