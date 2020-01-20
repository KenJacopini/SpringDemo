package com.springdemo.entities.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.springdemo.entities.Employee;


@ContextConfiguration("classpath:/springDemo-data-context.xml")
@RunWith(SpringRunner.class)
public class EmployeeDaoImplTest {
	
	private Logger logger = Logger.getLogger(EmployeeDao.class.getName());
	
	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	@Autowired
	private ComboPooledDataSource dataSource;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test 
	public void dbManagerClassesInitializedTest() {
		
		assertNotNull(employeeDaoImpl);
		assertNotNull(dataSource);
		assertNotNull(sessionFactory);
	}
	
	@Test
	public void addEmployeeToDatabaseTest() {
		
		try {
			logger.info("Creating new employee object");
			
			Employee tempEmployee = new Employee("John", "Ken", "ijeoma@gmail.com");
			
			logger.info("Storing employee to the database");
			employeeDaoImpl.addEmployee(tempEmployee);
			
			logger.info("Successfully saving employee to the database");
			}
		
		catch(Exception e) {
			e.printStackTrace();
			logger.warning("data not stored in database");
		}
	}

	
	@Test
	public void getEmployeeListTest() {
		
		List<Employee> theEmployees = createEmployeeAndSave();
		
	}
	
	
	@Test
	public void getEmployeeByIdTest() {
	
		List<Employee> theEmployees = createEmployeeAndSave();
		
		int employeeId = theEmployees.get(0).getId();
		
		Employee tempEmployee4 = employeeDaoImpl.getEmployeeById(employeeId);
		
		String firstName = tempEmployee4.getFirstName();
		String lastName = tempEmployee4.getLastName();
		String email = tempEmployee4.getEmail();
		
		System.out.println("Employee found from the Database: ==>>" +tempEmployee4);
		
		assertEquals(firstName, tempEmployee4.getFirstName());
		assertEquals(lastName, tempEmployee4.getLastName());
		assertEquals(email, tempEmployee4.getEmail());
	}
	
	
	
	@Before
	public void setUp() throws Exception {
		
			String username = "kenneth";
			
			String password = "%M4nche5ter%";
			
			String jdbcDriver = "com.mysql.cj.jdbc.Driver";
			
			String DBUrl = "jdbc:mysql://localhost:3306/springDemoDB?useSSL=false&serverTimezone=UTC";
			
			Connection conn = null;
			
			QueryRunner queryRunner = new QueryRunner(dataSource);
			
			
		try {
			
			DbUtils.loadDriver(jdbcDriver);
			
			conn = DriverManager.getConnection(DBUrl, username, password);
			
			queryRunner.update("drop database springDemoDB");
			
			queryRunner.update("create database springDemoDB");
		
			
			queryRunner.update("use springDemoDB");
			
			queryRunner.update("create table `employee`(\n" + 
					"\n" + 
					"	id int(11) not null auto_increment,\n" + 
					"	first_name varchar(45) default null,\n" + 
					"	last_name varchar(45) default null,\n" + 
					"	email varchar(45) default null,\n" + 
					"	\n" + 
					"	primary key(id)\n" + 
					"\n" + 
					")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;");
		 	}
		
		
		catch(Exception e){
			
			logger.throwing(EmployeeDaoImplTest.class.getName(), "setUp method", e.getCause());
			
			e.printStackTrace();
			
		}
		
		
		finally {
			
		DbUtils.close(conn);
		
		}
	}
	
	
	@Test
	public void updateEmployee() {
		
		List<Employee> theEmployees = createEmployeeAndSave();
		
		Employee tempEmployee4 = theEmployees.get(0);
		
		displayEmployee(tempEmployee4);
		
		
		tempEmployee4.setLastName("Tolani");
	
		//call daoImpl to update employee
		employeeDaoImpl.updateEmployee(tempEmployee4);
		
		
	}

	private void displayEmployee(Employee tempEmployee4) {
		//update the employee
		
		System.out.println(tempEmployee4);
	}

	private List<Employee> createEmployeeAndSave() {
		
		//create employee
		Employee tempEmployee1 = new Employee("Johnsa", "Kenn", "ij@gmail.com");
		Employee tempEmployee2 = new Employee("Johnse", "Kennn", "ije@gmail.com");
		Employee tempEmployee3 = new Employee("Johnny", "Kennnn", "ijeo@gmail.com");
		
		//save employee to database
		logger.info("Storing employee to the database");
		employeeDaoImpl.addEmployee(tempEmployee1);
		employeeDaoImpl.addEmployee(tempEmployee2);
		employeeDaoImpl.addEmployee(tempEmployee3);
		
		//get employee from database
		List<Employee> theEmployees = employeeDaoImpl.getEmployees();
		
		assertNotNull(theEmployees.get(0));
		assertNotNull(theEmployees.get(1));
		assertNotNull(theEmployees.get(2));
		return theEmployees;
	}
	
	@Test
	public void deleteEmployeeTest() {
		
		List<Employee> theEmployees = createEmployeeAndSave();
		
		//get employee from the list of employees
		Employee tempEmployee = theEmployees.get(0);
		
		//display the employee
		displayEmployee(tempEmployee);
		
		assertNotNull(tempEmployee);
		
		employeeDaoImpl.deleteEmployee(tempEmployee);
	}


}











