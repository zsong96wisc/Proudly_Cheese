/**
 * ManagerTest.java created by aTeam 147 in Proudly_Cheese project
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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * DataManager Unit Test case
 * 
 * @author aTeam 147 (2020)
 */
class ManagerTest {
  private Manager m; // Instance to be tested
  /**
   * instantiate a DateManager instance every time before test
   * 
   * @throws Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
    m = new Manager();
  }

  /**
   * The end of the test
   * 
   * @throws Exception
   */
  @AfterEach
  public void tearDown() throws Exception {
    m = null;
  }
  
  /**
   * Test addFarmRecord() will successfully add a 
   * new record
   */
  @Test
  public void test001_get_farm_report() {
    try {
      GregorianCalendar date = new GregorianCalendar(2013, 0, 1, 0, 0, 0);
      date.setLenient(false);
      Record record = new Record(date, "Farm 001", 13);
      m.addRecords(record);
      
      date = new GregorianCalendar(2013, 0, 1, 0, 0, 0);
      
      String[][] report = m.getFarmReport("Farm 001", date);
      System.out.println(report[0][2]);
      assert report[0][1].equals("13");
      assert report[0][2].equals("1.0");
      
    } catch (Exception e) {
      fail("Unknown Exception Thrown");
    }
  }
  
  /**
   * Test getMonthlyReport() will return a correct report
   * of a given month for only one farm added
   */
  @Test
  public void test002_get_monthly_report_for_one_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month at day
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011+i, 0, j+1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
        }
      }
      
      // Get records of each month. It should only consists of 
      // Farm 001's record and the weight should be i + 1
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date =
              new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          ArrayList<ArrayList<String>> report = m.getMonthlyReport(date);

          // Each month
          for (int k = 0; k < report.size(); k++) {
            assert report.get(k).get(0).equals("Farm 001");
            assert report.get(k).get(1).equals( Integer.toString(12*(i+1)));
            assert report.get(k).get(2).equals("1.0");
          }
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getMonthlyReport() will return a correct report
   * of a given month for only one farm added
   */
  @Test
  public void test003_get_monthly_report_for_multiple_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011+i, 0, j+1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", i + 1));
          m.addRecords(new Record(date, "Farm 003", i + 1));
        }
      }
      
      // Get records of each month. It should only consists of 
      // Farm 001's record and the weight should be i + 1
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011+i, 0, j+1, 0, 0, 0);
          date.setLenient(false);
          ArrayList<ArrayList<String>> report = m.getMonthlyReport(date);
          
          // Each month
          for (int k = 0; k < report.size(); k++) {
              report.get(k).get(0).equals("Farm 00" + k); // two Farm
              report.get(k).get(1).equals(Integer.toString(i+1));
              report.get(k).get(2).equals(Double.toString(0.33));
          }
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }
}
