package com.employee.utility;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.employee.exception.ElementNotFoundException;
import com.employee.model.Employee;
import com.employee.services.EmployeeService;

public class AppFunctionality {
	private EmployeeService service; 
	private Scanner sc; 
	private static final Logger logger = LogManager.getRootLogger();

	public AppFunctionality() {
		super();
		service = new EmployeeService(); 
		sc = new Scanner(System.in); 
	}
	
	public void displayOptions() {
		System.out.println("Please select a option between 1 to 8 "
				+ "to perform any operation : \r\n") ;
		System.out.println("1. Add employee details :\r\n"
				+ "\r\n"
				+ "2. Get the List of employees by their firstName :\r\n"
				+ "\r\n"
				+ "3. Get the List of employees with FirstName and Phone Number :\r\n"
				+ "\r\n"
				+ "4. Update the email and phoneNumber of a particular employee :\r\n"
				+ "\r\n"
				+ "5. Delete Details of a Particular employee by firstName :\r\n"
				+ "\r\n"
				+ "6. Get a list of employees with their firstName and emailAddress whose Birthday falls on the given date :\r\n"
				+ "\r\n"
				+ "7. Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date :\r\n"
				+ "\r\n"
				+ "8. Exited from console");
	}

	public void incorrectOptionSelected() {
		System.out.println("Invalid option!");
		logger.info("Invalid option!");
	}

	/**
	 * Option 1 : add employee details
	 */
	public void addEmployeeDetails() {

		System.out.println("Enter First Name : ");
		String firstName = sc.nextLine() ;

		System.out.println("Enter Last Name : ");
		String lastName = sc.nextLine() ; 

		System.out.println("Enter Address : ");
		String address = sc.nextLine() ; 

		System.out.println("Enter Email : ");
		String email = sc.nextLine() ; 

		System.out.println("Enter Mobile Number : ");
		Long phoneNumber = Long.parseLong(sc.nextLine()); 

		System.out.println("Enter Date of Birth in yyyy/mm/dd Format : ");
		String dateOfBirth = sc.nextLine() ;
		String[] parts = dateOfBirth.split("/");
		LocalDate dateOfBirthActual = LocalDate.of(Integer.parseInt(parts[0]) , 
				Integer.parseInt(parts[1]) , 
				Integer.parseInt(parts[2])); 

		System.out.println("Enter Wedding Date in yyyy/mm/dd Format : ");
		String dateOfWedding = sc.nextLine(); 
		parts = dateOfWedding.split("/"); 
		LocalDate dateOfWeddingActual = LocalDate.of(Integer.parseInt(parts[0]) , 
				Integer.parseInt(parts[1]) , 
				Integer.parseInt(parts[2]));
		service.addEmployee(
				new Employee(firstName, lastName, address, 
						email, phoneNumber, dateOfBirthActual, dateOfWeddingActual)
				); 
	}

	/**
	 * Option 2 : get list by first name
	 */
	public void getListByFirstName() {
		System.out.println("Enter First Name : ");
		String firstName = sc.nextLine(); 
		try {
			service.getEmployeesByFirstName(firstName);
		} catch (ElementNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Option 3 : get list by first name and phoneNumber
	 */
	public void getListByFirstNameAndPhoneNumber() {
		try {
			service.getEmployeesWithFirstNameAndPhoneNumber();
		} catch (ElementNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Option 4 : update email and phoneNumber
	 */
	public void updateEmailAndPhoneNumber() {
		System.out.println("Enter the email of the employee that you want to modify : ");
		String oldEmail = sc.nextLine(); 
		System.out.println("Enter New Mobile Number : ");
		Long phoneNumber = Long.parseLong(sc.nextLine()) ; 
		System.out.println("Enter New Email Id : ");
		String newEmail = sc.nextLine();
		service.updateEmailAndPhoneNumberByEmail(newEmail, phoneNumber, oldEmail);
	}

	/**
	 * Option 5 : delete by firstName
	 */
	public void deleteByFirstName() {
		System.out.println("Enter First Name : ");
		String firstName = sc.nextLine(); 
		service.deleteEmployeeByFirstName(firstName);
	}

	/**
	 * Option 6 : get firstName and email by birthday
	 */
	public void getFirstNameAndEmailByBirthday() {
		System.out.println("Enter Month in Digits : "); 
		int monthOfTheYear = sc.nextInt() ; 
		System.out.println("Enter Date in Digits : ");
		int dayOfTheMonth = sc.nextInt(); 
		try {
			service.getEmployeesFirstNameAndEmailByBirthDate(dayOfTheMonth, monthOfTheYear);
		} catch (ElementNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Option 7 : get firstName and phoneNumber by anniversary
	 */
	public void getFirstNameAndPhoneNumberByAnniversary() {
		System.out.println("Enter Month in Digits : "); 
		int monthOfTheYear = sc.nextInt(); 
		System.out.println("Enter Date in Digits : ");
		int dayOfTheMonth = sc.nextInt(); 
		try {
			service.getEmployeesFirstNameAndPhoneNumberByWeddingDate(dayOfTheMonth, monthOfTheYear);
		} catch (ElementNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	public boolean selectOptions(int option) {
		switch(option) {
		case 1: addEmployeeDetails(); 
		break ;
		case 2: getListByFirstName();
		break ;
		case 3: getListByFirstNameAndPhoneNumber();
		break ; 
		case 4: updateEmailAndPhoneNumber();
		break ; 
		case 5: deleteByFirstName();
		break ; 
		case 6: getFirstNameAndEmailByBirthday();
		break ; 
		case 7: getFirstNameAndPhoneNumberByAnniversary();
		break ;
		case 8:
			logger.info("Exit from console!");
			System.exit(0);
		default:
			return false;
		} 
		return true;
	}
}
