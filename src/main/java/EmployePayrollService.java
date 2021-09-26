package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployePayrollService {
	private List<EmployePayrollData> employeePayrollList;

	EmployePayrollService(List<EmployePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	/**
	 * method to read employee detail from the console
	 */
	private void readEmployeePayrollData() {
		Scanner consoleInputReader = new Scanner(System.in);
		System.out.println("Enter employee ID: ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter employee name: ");
		String name = consoleInputReader.next();
		System.out.println("Enter employee salary: ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollList.add(new EmployePayrollData(id, name, salary));
	}

	/**
	 * method to print employee detail to the console
	 */
	private void writeEmployeePayrollData() {
		System.out.println("\nWriting Employee Payroll to Console \n" + employeePayrollList);
	}

	public static void main(String[] args) {
		ArrayList<EmployePayrollData> employeePayrollList = new ArrayList<>();
		EmployePayrollService employeePayrollService = new EmployePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData();
		employeePayrollService.writeEmployeePayrollData();
		consoleInputReader.close();
	}

}
