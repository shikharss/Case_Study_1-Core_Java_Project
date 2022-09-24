package com.employee.implementation;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.employee.iface.EmployeeRepository;
import com.employee.model.Employee;

public class EmployeeRepositoryImp implements EmployeeRepository<Employee, String> {

	private Connection con; 
	private List<Employee> localStorage; 
	private boolean isLocalStorageUpdated;
	private static final Logger logger = LogManager.getRootLogger();
	
	public EmployeeRepositoryImp(Connection con){
		super(); 
		this.con = con;
		this.isLocalStorageUpdated = true;
		this.localStorage = (List<Employee>) this.findAll();  
	}
	
	@Override
	public boolean save(Employee obj) {
		String sql = "insert into lumen_employees values(?,?,?,?,?,?,?)"; 
		int rowUpdated = 0; 
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, obj.getFirstName());
			pstmt.setString(2, obj.getLastName());
			pstmt.setString(3, obj.getAddress());
			pstmt.setString(4, obj.getEmail());
			pstmt.setLong(5, obj.getPhoneNumber());
			pstmt.setDate(6, Date.valueOf(obj.getDateOfBirth()));
			pstmt.setDate(7, Date.valueOf(obj.getDateOfWedding()));
			rowUpdated = pstmt.executeUpdate();
			isLocalStorageUpdated = true; 
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}  
		return (rowUpdated > 0); 
	}

	@Override
	public Collection<Employee> findAll() {
		if(isLocalStorageUpdated) {
			List<Employee> employeeList = new ArrayList<>(); 
			try {
				String sql = "select * from lumen_employees";
				PreparedStatement pstmt = con.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery(); 
				while(rs.next()) {
					String firstName = rs.getString(1); 
					String lastName = rs.getString(2);
					String address = rs.getString(3); 
					String email = rs.getString(4);
					Long phoneNumber = rs.getLong(5); 
					LocalDate dateOfBirth = rs.getDate(6).toLocalDate() ;
					LocalDate dateOfWedding = rs.getDate(7).toLocalDate();
					employeeList.add(new Employee(firstName, lastName, 
							address, email, phoneNumber, dateOfBirth, dateOfWedding)); 
				}
				localStorage = employeeList; 
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		isLocalStorageUpdated = false; 
		return localStorage; 
	}
	
	
	@Override
	public int deleteByPrimaryKey(String email) {
		String sql = "Delete from lumen_employees where email=?";
		int rowsUpdated = 0;
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
		    rowsUpdated += pstmt.executeUpdate();
			isLocalStorageUpdated = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;  
	}
	
	@Override
	public int updateEmailByPrimaryKey(String email, String updatedEmail) {
		int updatedRows = 0;
		
		try {
			String sql = "update lumen_employees set email=? where email=?";
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt.setString(2, email);
			pstmt.setString(1, updatedEmail);
			updatedRows = pstmt.executeUpdate(); 
			isLocalStorageUpdated = true; 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return updatedRows;
	}

	@Override
	public int updatePhoneNumberByPrimaryKey(String email, Long updatedPhoneNumber) {
		int updatedRows = 0; 
		try {
			String sql = "update lumen_employees set phoneNumber=? where email=?";
			PreparedStatement pstmt = con.prepareStatement(sql) ; 
			pstmt.setString(2, email);
			pstmt.setLong(1, updatedPhoneNumber);
			updatedRows = pstmt.executeUpdate(); 
			isLocalStorageUpdated = true; 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return updatedRows;
	}

	@Override
	public int deleteByFirstName(String firstName) {
        String sql = "Delete from lumen_employees where firstName=?";
		int rowsUpdated = 0; 
		
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, firstName); 
			rowsUpdated += pstmt.executeUpdate();
			isLocalStorageUpdated = true; 	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;  
	}
}
