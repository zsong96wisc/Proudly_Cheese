/**
 * DateManagerTest.java created by aTeam 147 in Proudly_Cheese project
 * 
 * Author: Hairong Yin (hyin55@wisc.edu) (Lec 002), Haonan Shen (hshen37@wisc.edu) (Lec 001), Xiaoxi
 * Sun (xsun279@wisc.edu) (Lec 002), Zhiwei Song (zsong96@wisc.edu) (Lec 002)
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
      // Create a list to import from file
      List<Record> test = fm.importFile(new File("test.csv"));
    } catch (IOException e) {
      return;
    } catch (Exception e) {
      // Print fail message
      fail("Wrong exception thrown when file input is empty.");
    }
  }

  /**
   * Test if correctly import valid file
   */
  @Test
  public void test002_importFile_valid() throws Exception {
    // Create a list to import from file
    List<Record> test = fm.importFile(new File("correctSample.csv"));
  }

  /**
   * Test the correct exception is thrown when missing date
   */
  @Test
  public void test003_importFile_missingDate() {
    try {
      // Create a list to import from file
      List<Record> test = fm.importFile(new File("missingDateSample.csv"));
    } catch (ParseException e) {
      return;
    } catch (Exception e) {
      
    }
    // Print fail message
    fail("ParseException is not thrown as expected.");
  }

  /**
   * Test the correct exception is thrown when missing weight
   */
  @Test
  public void test004_importFile_missingWeight() {
    try {
      // Create a list to import from file
      List<Record> test = fm.importFile(new File("missingWeightSample.csv"));
    } catch (NumberFormatException e) {
      return;
    } catch (Exception e) {
      
    }
    // Print fail message
    fail("NumberFormatException is not thrown as expected.");
  }

  /**
   * Test the correct exception is thrown when weight invalid
   */
  @Test
  public void test005_importFile_invalidWeight() {
    try {
      // Create a list to import from file
      List<Record> test = fm.importFile(new File("invalidWeightSample.csv"));
    } catch (NumberFormatException e) {
      return;
    } catch (Exception e) {
      
    }
    // Print fail message
    fail("NumberFormatException is not thrown as expected.");
  }

  /**
   * Test the correct exception is thrown when invalid date
   */
  @Test
  public void test006_importFile_invalidDate() {
    try {
      // Create a list to import from file
      List<Record> test = fm.importFile(new File("invalidDateSample.csv"));
    } catch (ParseException e) {
      return;
    } catch (Exception e) {
      
    }
    // Print fail message
    fail("ParseException is not thrown as expected.");
  }

  /**
   * Test the correct exception is not thrown and new file is created
   */
  @Test
  public void test007_exportFile_invalid() throws Exception {
    // Create a list to import from file
    List<Record> test = fm.importFile(new File("correctSample.csv"));
    Manager m = new Manager();
    m.importList(test, "Add as a new set of data");
    // export the report
    fm.exportReport(m.getAnnualReport(new GregorianCalendar(2019, 1, 1, 0, 0, 0)), new File(" "),
        1);
  }

  /**
   * Test if export correctly
   */
  @Test
  public void test008_exportFile_valid_Jan() throws Exception {
    // Create a list to import from file
    List<Record> test = fm.importFile(new File("correctSample.csv"));
    Manager m = new Manager();
    m.importList(test, "Add as a new set of data");
    // export the report
    fm.exportReport(m.getAnnualReport(new GregorianCalendar(2019, 1, 1, 0, 0, 0)),
        new File("exportTester"), 1);
  }

  /**
   * Test if export correctly
   */
  @Test
  public void test009_exportFile_valid_Sept() throws Exception {
    // Create a list to export
    List<Record> test = fm.importFile(new File("correctSampleSept.csv"));
    Manager m = new Manager();
    m.importList(test, "Add as a new set of data");
    // export the report
    fm.exportReport(m.getAnnualReport(new GregorianCalendar(2019, 9, 1, 0, 0, 0)),
        new File("exportTester2"), 1);
  }

}
