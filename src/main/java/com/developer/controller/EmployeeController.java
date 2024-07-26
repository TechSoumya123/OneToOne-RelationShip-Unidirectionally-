package com.developer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.dto.responseDto.EmployeeResponse;
import com.developer.exception.EmployeeNotFoundException;
import com.developer.model.Employee;
import com.developer.service.Iservice.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping(path = "/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody @Validated Employee employee) {
		try {
			Employee saveEmployee = employeeService.saveEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Object> getEmployee(@PathVariable("id") Long empId) {
		try {
			EmployeeResponse employeeResponse = employeeService.findEmployeeById(empId);
			return ResponseEntity.status(200).body(employeeResponse);
		} catch (EmployeeNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(null);
	}

	@GetMapping(path = "/getAllEmployee-Without-Mobile")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
		try {
			var allEmployee = employeeService.getAllEmployee();
			return allEmployee.isEmpty() ? ResponseEntity.noContent().build()
					: ResponseEntity.status(HttpStatus.ACCEPTED).body(allEmployee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
