package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployePayrollFileIo {
	public static final String PAYROLL_FILE_NAME = "/Users/jayeshkumar/eclipse-workspace/EmployePayrool/src/data/payroll.txt";

	/**
	 * Writes the data to file
	 */
	public void writeData(List<EmployePayrollData> employeePayrollList) {
		StringBuffer empBuffer = new StringBuffer();
		employeePayrollList.forEach(employee -> {
			String emloyeeDataString = employee.toString().concat("\n");
			empBuffer.append(emloyeeDataString);
		});
		try {
			Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the number of entries/line in file
	 */
	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	/**
	 * @return the number of entries in file and also prints the entries to console
	 */
	public long printData() {
		long entries = 0;
		try {
			entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
			Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	/**
	 * @return the list containing the data read from file
	 */
	public List<EmployePayrollData> readData() {
		List<EmployePayrollData> employeePayrollList = new ArrayList<>();
		try {
			Files.lines((new File(PAYROLL_FILE_NAME).toPath())).map(line -> line.trim()).forEach(line -> {
				String[] s = line.split(",");
				employeePayrollList.add(new EmployePayrollData(Integer.parseInt(s[0]), s[1], Double.parseDouble(s[2])));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

}
