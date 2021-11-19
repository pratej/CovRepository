package com.cov.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cov.beans.Department;
import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.repo.DepartmentRepo;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepo departmentRepository;

	public List<Department> findAll() {
// List<Employee> employee=new ArrayList<Employee>();
		return (List<Department>) departmentRepository.findAll();
// return employee;
	}

	public Department findById(int id) throws InvalidEmployeeIdException {
		Optional<Department> deptOptional = departmentRepository.findById(id);
		if (!deptOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Department Id " + id + " not existing in repository");
		}
		return deptOptional.get();
	}

	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	public Department update(Department department) throws InvalidDepartmentIdException {
		Optional<Department> deptOptional = departmentRepository.findById(department.getId());
		if (!deptOptional.isPresent()) {
			throw new InvalidDepartmentIdException(
					"Department Id" + department.getId() + "not existing in reposiotory");
		}
		return departmentRepository.save(department);
	}

	public Department delete(int id) throws InvalidDepartmentIdException {
		Optional<Department> deptOptional = departmentRepository.findById(id);
		if (!deptOptional.isPresent()) {
			throw new InvalidDepartmentIdException("Department Id " + id + "not existing in repository");
		}
		Department department = deptOptional.get();
		return department;
	}
}