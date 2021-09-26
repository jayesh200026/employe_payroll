package test.java;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import main.java.EmployePayrollData;
import main.java.EmployePayrollService;
import main.java.EmployePayrollService.IOService;
import main.java.FileUtils;
import main.java.WatchServiceExample;
import java.util.*;

public class FileApiTest {

	private static String HOME = System.getProperty("user.home");
	private static String PLAY_WITH_NIO = "TempPlayGround";
	Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);

	@Test
	public void givenPathWhenCheckedThenConfirm() throws IOException {
		// Check File exists
		Path homePath = Paths.get(HOME);
		Assert.assertTrue(Files.exists(homePath));

		// Delete File and check file not exists

		if (Files.exists(playPath))
			FileUtils.deleteFiles(playPath.toFile());
		Assert.assertTrue(Files.notExists(playPath));

		// Create Directory
		Files.createDirectory(playPath);
		Assert.assertTrue(Files.exists(playPath));

		// Create File
		IntStream.range(1, 10).forEach(cntr -> {
			Path tempFile = Paths.get(playPath + "/temp" + cntr);
			try {
				Files.createFile(tempFile);
			} catch (IOException E) {
			}
			Assert.assertTrue(Files.exists(tempFile));
		});
		// listing of files
		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		System.out.println("----------------------------");
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		System.out.println("----------------------------");
		Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp"))
				.forEach(System.out::println);
	}

	@Test
	public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException {
		Path dir = Paths.get("/Users/jayeshkumar/eclipse-workspace/EmployePayrool/data");
		Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
		new WatchServiceExample(dir).processEvents();
	}

	@Test
	public void given4EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
		EmployePayrollData[] arrayOfEmps = { new EmployePayrollData(1, " Jeff Bezos", 100000.0),
				new EmployePayrollData(2, "Bill Gates", 200000.0),
				new EmployePayrollData(3, "Mark Zuckerberg", 300000.0),
				new EmployePayrollData(4, "Elon Musk ", 4500000.0)};
				 
		EmployePayrollService employeePayrollService;
		employeePayrollService = new EmployePayrollService(Arrays.asList(arrayOfEmps));
		employeePayrollService.writeEmployeePayrollData(IOService.FIlE_IO);
		long entries = employeePayrollService.countEntries();
		assertEquals(4, entries);
	}

}
