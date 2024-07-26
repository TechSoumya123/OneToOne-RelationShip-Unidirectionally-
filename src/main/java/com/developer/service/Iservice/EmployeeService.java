package com.developer.service.Iservice;

import java.util.List;

import com.developer.dto.responseDto.EmployeeResponse;
import com.developer.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	
	EmployeeResponse findEmployeeById(Long employeeId);

	List<EmployeeResponse> getAllEmployee();

	
}
