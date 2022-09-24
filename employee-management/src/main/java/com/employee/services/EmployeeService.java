package com.employee.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.employee.connection.ConnectionFactory;
import com.employee.exception.ElementNotFoundException;
import com.employee.iface.EmployeeRepository;
import com.employee.implementation.EmployeeRepositoryImp;
import com.employee.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeService {
	private EmployeeRepository<Employee, String> repo ; 
	private Connection con ; 
	private static final Logger logger = LogManager.getRootLogger() ; 
	private List<Employee> employeeList;
	Gson obj = new GsonBuilder().setPrettyPrinting().create();

	public EmployeeService() {
		super(); 
		this.con = ConnectionFactory.getMySqlConnection() ; 
		this.repo = new EmployeeRepositoryImp(con) ; 
	}

	public void startConnection() {
		try {
			if(this.con == null || this.con.isClosed()) {
				this.con = ConnectionFactory.getMySqlConnection() ;  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if(!con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addEmployee(Employee obj) {
		if(repo.save(obj)) {
			logger.info("New employee details added!");  
		}
	}

	public void getEmployeesByFirstName(String firstName) throws ElementNotFoundException {
		employeeList = this.repo.findAll().stream()
				.filter(emp -> emp.getFirstName().equals(firstName))
				.peek(emp -> logger.info(emp))
				.collect(Collectors.toList()); 
		if(employeeList.size() == 0) {
			throw new ElementNotFoundException("ERR101","Employee details not found in database.");
		}
		System.out.println(obj.toJson(employeeList));
	}

	public void getEmployeesWithFirstNameAndPhoneNumber() throws ElementNotFoundException {
		employeeList = this.repo.findAll().stream()
				.peek(emp -> logger.info(emp.getFirstName() + " " + emp.getPhoneNumber()))
				.collect(Collectors.toList());  
		if(employeeList.size() == 0) {
			throw new ElementNotFoundException("ERR102","No entries present in database.");
		}
	}

	public void updateEmailAndPhoneNumberByEmail(String newEmail, Long newPhoneNumber, String oldEmail) {
		int changed = 0 ;
		this.repo.updatePhoneNumberByPrimaryKey(oldEmail, newPhoneNumber); 
		changed = this.repo.updateEmailByPrimaryKey(oldEmail, newEmail); 
		if(changed == 0) {
			logger.warn("WARNING - No entry present with the entered email address.");
		}
		logger.info(changed + " rows updated!");

	}

	public void deleteEmployeeByFirstName(String firstName) {
		int deleted = 0 ; 
		deleted += this.repo.deleteByFirstName(firstName); 
		if(deleted == 0) {
			logger.warn("WARNING - No entry present with the entered first name.");
		}
		logger.info(deleted + " rows deleted!");
	}

	public void getEmployeesFirstNameAndEmailByBirthDate(int dayOfMonth, int monthOfYear) throws ElementNotFoundException{
		employeeList = this.repo.findAll().stream()
				.filter(emp -> {
					return dayOfMonth == emp.getDateOfBirth().getDayOfMonth() &&
							monthOfYear == emp.getDateOfBirth().getMonthValue(); 
				})
				.peek(emp -> logger.info(emp.getFirstName() + " " + emp.getEmail()))
				.collect(Collectors.toList());
		if(employeeList.size() == 0) {
			throw new ElementNotFoundException("ERR103","No entries present in database with entered date of birth.");
		}
	} 

	public void getEmployeesFirstNameAndPhoneNumberByWeddingDate(int dayOfMonth, int monthOfYear) throws ElementNotFoundException {
		employeeList = this.repo.findAll().stream()
				.filter(emp -> {
					return dayOfMonth == emp.getDateOfWedding().getDayOfMonth() &&
							monthOfYear == emp.getDateOfWedding().getMonthValue(); 
				})
				.peek(emp -> logger.info(emp.getFirstName() + " " + emp.getPhoneNumber()))
				.collect(Collectors.toList());
		if(employeeList.size() == 0) {
			throw new ElementNotFoundException("ERR104","No entries present in database with entered wedding date.");
		}
	} 
}
