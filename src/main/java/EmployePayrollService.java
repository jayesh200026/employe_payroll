package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployePayrollService {
	public enum IOService {
		CONSOLE_IO, FIlE_IO, DB_IO, REST_IO
	}

	private List<EmployePayrollData> employeePayrollList;

	public EmployePayrollService() {

	}

	public EmployePayrollService(List<EmployePayrollData> employeePayrollList) {
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
	public void writeEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO)) {
			System.out.println("\nWriting Employee Payroll roaster to console\n" + employeePayrollList);
		} else if (ioService.equals(IOService.FIlE_IO)) {
			new EmployePayrollFileIo().writeData(employeePayrollList);
		}
	}

	public static void main(String[] args) {
		ArrayList<EmployePayrollData> employeePayrollList = new ArrayList<>();
		EmployePayrollService employeePayrollService = new EmployePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData();
		employeePayrollService.writeEmployeePayrollData(IOService.FIlE_IO);
		consoleInputReader.close();
	}

	/**
	 * @return the number of entries in file
	 */
	public long countEntries() {
		return new EmployePayrollFileIo().countEntries();
	}

	/**
	 * @return the number of entries printed
	 */
	public long printData() {
		return new EmployePayrollFileIo().printData();
	}

	/**
	 * @return the size of the list indicating number of employees
	 */
	public long readFromFile() {
		this.employeePayrollList = new EmployePayrollFileIo().readData();
		System.out.println(employeePayrollList.size());
		return this.employeePayrollList.size();
	}

}
