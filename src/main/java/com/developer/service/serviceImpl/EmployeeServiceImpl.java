package com.developer.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.developer.dto.responseDto.EmployeeResponse;
import com.developer.exception.EmployeeNotFoundException;
import com.developer.model.Employee;
import com.developer.model.Mobile;
import com.developer.repository.EmployeeRepository;
import com.developer.repository.MobileRepository;
import com.developer.service.Iservice.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final MobileRepository mobileRepository;

	final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee saveEmployee(Employee employee) {
		
		var mobile = new Mobile();
		mobile.setMobileName(employee.getMobile().getMobileName());
		Mobile saveMobile = mobileRepository.save(mobile);

		logger.info("First save the mobile : {}", saveMobile);

		logger.info("----------------------------------------");

		employee.setMobile(mobile);
		Employee saveEmployee = employeeRepository.save(employee);

		logger.info("Saved employee {}", saveEmployee);
		return saveEmployee;
	}

	@Override
	public EmployeeResponse findEmployeeById(Long employeeId) {
		return employeeRepository
				.findById(employeeId)
				.map(t -> new EmployeeResponse(t.getId(), t.getName()))
				.orElseThrow(() -> new EmployeeNotFoundException("ok"));
	}

	@Override
	public List<EmployeeResponse> getAllEmployee() {
		return employeeRepository
				.findAll()
				.stream()
				.map(emp -> new EmployeeResponse(emp.getId(), emp.getName()))
				.toList();
	}

}
