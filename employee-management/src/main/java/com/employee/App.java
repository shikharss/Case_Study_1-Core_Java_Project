package com.employee;

import java.util.Scanner;
import com.employee.utility.AppFunctionality;

public class App {
	public static void main( String[] args ){

		AppFunctionality function = new AppFunctionality();
		System.out.println("Employee Database Management!\r\n");

		boolean start = true ; 
		Scanner sc = null;
		
		while(start) {
			function.displayOptions();
			
			sc = new Scanner(System.in);
			int option = sc.nextInt();
			
			if(!function.selectOptions(option)) {
				function.incorrectOptionSelected();
			}
		}

		sc.close();
	}
}
