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
import com.cov.beans.Department;
import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API to perform Operations on Department", description = "This API provides different CRUD operations on Department respository")
@RestController
@RequestMapping("/department")
public class DepartmentController {
	static Logger logger = Logger.getLogger(DepartmentController.class);
	@Autowired
	DepartmentService departmentService;

	@ApiOperation(value = "search a single Employee by ID given", response = Department.class)
	@GetMapping("/{id}")
	public Department find(@PathVariable int id) throws InvalidDepartmentIdException, InvalidEmployeeIdException {
		logger.info("Finding an department with id " + id);
		Department department = departmentService.findById(id);
		logger.info("Department found with id " + id + " is" + department.getName());
		return department;
	}

	@ApiOperation(value = "Read all Department details from repository", produces = "application/xml")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of Department"),
			@ApiResponse(code = 401, message = "You are not authorized to view the repository"),
			@ApiResponse(code = 403, message = "accesing the resources you are trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping()
	public List<Department> findAll() {
		logger.info("Find all departments");
		return departmentService.findAll();
	}

	@ApiOperation(value = "Insert Department details into the repository", produces = "application/xml")
	@PostMapping()
	public Department insert(@RequestBody Department department) {
		logger.info("Inserting a department with " + department.getName());
		return departmentService.save(department);
	}

	@ApiOperation(value = "Edit Department details in the repository", produces = "application/xml")
	@PutMapping()
	public Department edit(@RequestBody Department department) throws InvalidDepartmentIdException {
		logger.info("Editing a department with " + department.getName());
		return departmentService.update(department);
	}

	@ApiOperation(value = "Delete Department from repository", produces = "application/xml")
	@DeleteMapping("/{id}")
	public Department delete(@PathVariable int id) throws InvalidDepartmentIdException {
		logger.info("Deleting a department with id " + id);
		return departmentService.delete(id);
	}
}
