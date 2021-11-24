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

@RestController
@RequestMapping("/department")
public class DepartmentController {
	static Logger logger = Logger.getLogger(DepartmentController.class);
	@Autowired
	DepartmentService departmentService;

	@GetMapping("/{id}")
	public Department find(@PathVariable int id) throws InvalidDepartmentIdException, InvalidEmployeeIdException {
		logger.info("Finding an department with id " + id);
		Department department = departmentService.findById(id);
		logger.info("Department found with id " + id + " is" + department.getName());
		return department;
	}

	@GetMapping()
	public List<Department> findAll() {
		logger.info("Find all departments");
		return departmentService.findAll();
	}

	@PostMapping()
	public Department insert(@RequestBody Department department) {
		logger.info("Inserting a department with " + department.getName());
		return departmentService.save(department);
	}

	@PutMapping()
	public Department edit(@RequestBody Department department) throws InvalidDepartmentIdException {
		logger.info("Editing a department with " + department.getName());
		return departmentService.update(department);
	}

	@DeleteMapping("/{id}")
	public Department delete(@PathVariable int id) throws InvalidDepartmentIdException {
		logger.info("Deleting a department with id " + id);
		return departmentService.delete(id);
	}
}
