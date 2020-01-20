package com.springdemo.employee.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.springdemo.entities.Employee;

@ContextConfiguration("classpath:/springDemo_service_context.xml")
@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeService employeeService;

	@Before
	public void setUp() throws Exception {
		employeeService = mock(EmployeeService.class);
	}

	@Test
	public void employeeExistsTest(){
		assertNotNull(employeeService);
		
	}
	
	@Test
	public void addEmployeeTest() {
		
		Employee employee = new Employee("John", "Ken", "ijeoma@gmail.com");
		
		doNothing().when(employeeService).addEmployee(isA(Employee.class));
		employeeService.addEmployee(employee);
		
		verify(employeeService, times(1)).addEmployee(employee);
	}
	
	@Test
	public void getEmployeesTest() {
		
		Employee tempEmployee1 = new Employee("Johnsa", "Kenn", "ij@gmail.com");
		Employee tempEmployee2 = new Employee("Johnse", "Kennn", "ije@gmail.com");
		Employee tempEmployee3 = new Employee("Johnny", "Kennnn", "ijeo@gmail.com");
		
		List<Employee> theEmployees = new ArrayList<Employee>();
		theEmployees.add(tempEmployee1);
		theEmployees.add(tempEmployee2);
		theEmployees.add(tempEmployee3);
		
		when(employeeService.getEmployees()).thenReturn(new ArrayList<Employee>());
		employeeService.getEmployees();
		
		verify(employeeService, times(1)).getEmployees();
	}
	
	@Test
	public void getEmployeeByIdTest() {
		
		when(employeeService.getEmployeeById(1)).thenReturn(isA(Employee.class));
		employeeService.getEmployeeById(1);
		
		verify(employeeService, times(1)).getEmployeeById(1);
	}
	
	@Test
	public void updateEmployeeTest() {
		
		Employee employee = new Employee("John", "Ken", "ijeoma@gmail.com");
		
		doNothing().when(employeeService).updateEmployee(isA(Employee.class));
		
		employeeService.updateEmployee(employee);
		
		verify(employeeService, times(1)).updateEmployee(employee);
	}
	
	@Test
	public void deleteEmployeeTest() {
		
		Employee employee = new Employee("John", "Ken", "ijeoma@gmail.com");
		
		doNothing().when(employeeService).deleteEmployee(isA(Employee.class));
		
		employeeService.deleteEmployee(employee);
		
		verify(employeeService, times(1)).deleteEmployee(employee);
	}

}
