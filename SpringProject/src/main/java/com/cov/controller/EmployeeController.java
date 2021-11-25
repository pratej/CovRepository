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
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API to perform Operations on Employee", description = "This API provides different CRUD operations on Employee respository")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	static Logger logger = Logger.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;

	@ApiOperation(value = "search a single Employee by ID given", response = Employee.class)
	@GetMapping("/{id}")
	public Employee find(@PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("Finding a employee with id " + id);
		Employee employee = employeeService.findById(id);
		logger.info("employee found with id " + id + " is" + employee.getName());
		return employee;
	}

	@ApiOperation(value = "Read all Employee details from repository", produces = "application/xml")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of Employees"),
			@ApiResponse(code = 401, message = "You are not authorized to view the repository"),
			@ApiResponse(code = 403, message = "accesing the resources you are trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping()
	public List<Employee> findAll() {
		logger.info("Finding all employees");
		return employeeService.findAll();
	}

	@ApiOperation(value = "Insert Employee details into the repository", produces = "application/xml")
	@PostMapping()
	public Employee insertPerson(@RequestBody Employee employee) {
		logger.info("Inserting a employee with " + employee.getName());
		return employeeService.save(employee);
	}

	@ApiOperation(value = "Edit Employee details in the repository", produces = "application/xml")
	@PutMapping()
	public Employee edit(@RequestBody Employee employee) throws InvalidEmployeeIdException {
		logger.info("Editing a employee with " + employee.getName());
		return employeeService.update(employee);
	}

	@ApiOperation(value = "Delete an Employee from repository", produces = "application/xml")
	@DeleteMapping("/{id}")
	public Employee delete(@PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("Deleting a employee with id " + id);
		return employeeService.delete(id);
	}
}
