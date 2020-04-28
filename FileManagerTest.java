/**
 * DateManagerTest.java created by aTeam 147 in Proudly_Cheese project
 * 
 * Author: Hairong Yin (hyin55@wisc.edu) (Lec 002), Haonan Shen
 * (hshen37@wisc.edu) (Lec 001), Xiaoxi Sun (xsun279@wisc.edu) (Lec 002), Zhiwei
 * Song (zsong96@wisc.edu) (Lec 002)
 * 
 * Date: 04/19/2020
 * 
 * Course: CS400 Semester: Spring 2020
 * 
 * List Collaborators:None
 * 
 * Other Credits: None
 * 
 * Known Bugs: None
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * FileManager Unit Test case
 * 
 * @author aTeam 147 (2020)
 */
public class FileManagerTest {

	private FileManager fm; // Instance to be tested

	/**
	 * instantiate a DateManager instance every time before test
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		fm = new FileManager();
	}

	/**
	 * The end of the test
	 * 
	 * @throws Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
		fm = null;
	}

	/**
	 * Test the correct exception is thrown when non-existing file is input
	 */
	@Test
	public void test001_importFile_invalid() {
		try {
			List<Record> test = fm.importFile(new File("test.csv"));
		} catch (IOException e) {
			return;
		} catch (Exception e) {
			fail("Wrong exception thrown when file input is empty.");
		}
	}

	@Test
	public void test002_importFile_valid() throws Exception {
		List<Record> test = fm.importFile(new File("correctSample.csv"));
	}

	@Test
	public void test003_importFile_missingDate() {
		try {
			List<Record> test = fm.importFile(new File("missingDateSample.csv"));
		} catch (ParseException e) {
			return;
		} catch (Exception e) {
		}
		fail("ParseException is not thrown as expected.");
	}

	@Test
	public void test004_importFile_missingWeight() {
		try {
			List<Record> test = fm.importFile(new File("missingWeightSample.csv"));
		} catch (NumberFormatException e) {
			return;
		} catch (Exception e) {
		}
		fail("NumberFormatException is not thrown as expected.");
	}

	@Test
	public void test005_importFile_invalidWeight() {
		try {
			List<Record> test = fm.importFile(new File("invalidWeightSample.csv"));
		} catch (NumberFormatException e) {
			return;
		} catch (Exception e) {
		}
		fail("NumberFormatException is not thrown as expected.");
	}

	@Test
	public void test006_importFile_invalidDate() {
		try {
			List<Record> test = fm.importFile(new File("invalidDateSample.csv"));
		} catch (ParseException e) {
			return;
		} catch (Exception e) {
		}
		fail("ParseException is not thrown as expected.");
	}

	@Test
	public void test007_exportFile_invalid() throws Exception {
		List<Record> test = fm.importFile(new File("correctSample.csv"));
		try {
			fm.exportFile(test, new File(""));
		} catch (IOException e) {
			return;
		}
	}

	@Test
	public void test008_exportFile_valid_Jan() throws Exception {
		List<Record> test = fm.importFile(new File("correctSample.csv"));
		fm.exportFile(test, new File("exportTester"));
	}

	@Test
	public void test009_exportFile_valid_Sept() throws Exception {
		List<Record> test = fm.importFile(new File("correctSampleSept.csv"));
		fm.exportFile(test, new File("exportTester2"));
	}

}
