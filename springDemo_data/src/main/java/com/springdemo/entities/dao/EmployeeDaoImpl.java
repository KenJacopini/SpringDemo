package com.springdemo.entities.dao;

import java.util.List;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entities.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Logger logger = Logger.getLogger(EmployeeDao.class.getName());
	
	@Transactional
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		logger.info("Getting current session");
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		logger.info("Saving employee to the database" + employee.toString());
		currentSession.save(employee);
			
		
	}
	
	@Transactional
	public List<Employee> getEmployees(){
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		List<Employee> employeeList = theQuery.getResultList();
		
		return employeeList;
	}
	
	@Transactional
	public Employee getEmployeeById(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Employee theEmployee = currentSession.get(Employee.class, id);
		
		return theEmployee;
	}

	@Override
	@Transactional
	public void updateEmployee(Employee theEmployee) {
		// TODO Auto-generated method stub
		
		Session currentSession =  sessionFactory.getCurrentSession();
		
		currentSession.update(theEmployee);
	}

	@Override
	@Transactional
	public void deleteEmployee(Employee tempEmployee) {
		// TODO Auto-generated method stub
		
		Session currentSession =  sessionFactory.getCurrentSession();
		
		currentSession.delete(tempEmployee);
		
		
	}

	
}
