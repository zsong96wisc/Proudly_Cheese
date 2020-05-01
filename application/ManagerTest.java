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
   * Test addFarmRecord() will successfully add a new record
   */
  @Test
  public void test001_get_farm_report() {
    try {
      GregorianCalendar date = new GregorianCalendar(2013, 0, 1, 0, 0, 0);
      date.setLenient(false);
      Record record = new Record(date, "Farm 001", 13);
      m.addRecords(record);

      ArrayList<ArrayList<String>> report = m.getFarmReport("Farm 001", date);
      assert report.get(0).get(1).equals("13");
      assert report.get(0).get(2).equals("100.00%");

    } catch (Exception e) {
      fail("Unknown Exception Thrown");
    }
  }

  /**
   * Test getMonthlyReport() will return a correct report of a given month for only one farm added
   */
  @Test
  public void test002_get_monthly_report_for_one_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month at day
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
        }
      }

      // Get records of each month. It should only consists of
      // Farm 001's record and the weight should be i + 1
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          ArrayList<ArrayList<String>> report = m.getMonthlyReport(date);

          // Each month
          for (int k = 0; k < report.size(); k++) {
            assert report.get(k).get(0).equals("Farm 001");
            assert report.get(k).get(1).equals(Integer.toString(12 * (i + 1)));
            assert report.get(k).get(2).equals("100.00%");
          }
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getMonthlyReport() will return a correct report of a given month for multiple farms added
   */
  @Test
  public void test003_get_monthly_report_for_multiple_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
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
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          ArrayList<ArrayList<String>> report = m.getMonthlyReport(date);

          // Each month
          for (int k = 0; k < report.size(); k++) {
            assert report.get(k).get(0).equals("Farm 00" + (k + 1)); // two Farm
            assert report.get(k).get(1).equals(Integer.toString(12 * (i + 1)));
            assert report.get(k).get(2).equals("33.33%");
          }
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getAnnualReport() will return a correct report of a given year for only one farm added
   */
  @Test
  public void test004_get_annual_report_for_one_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month at day
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
        }
      }

      // Get records of each month. It should only consists of
      // Farm 001's record and the weight should be i + 1
      for (int i = 0; i < 2; i++) {
        GregorianCalendar date = new GregorianCalendar(2011 + i, 0, 1, 0, 0, 0);
        ArrayList<ArrayList<String>> report = m.getAnnualReport(date);
        // For year 2011 and 2012
        assert report.get(0).get(0).equals("Farm 001");
        assert report.get(0).get(1).equals(Integer.toString(12 * (i + 1)));
        assert report.get(0).get(2).equals("100.00%");
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getAnnualReport() will return a correct report of a given year for multiple farms added
   */
  @Test
  public void test005_get_annual_report_for_multiple_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }

      // Get records of each month. It should only consists of
      // Farm 001's record and the weight should be i + 1
      for (int i = 0; i < 2; i++) {
        GregorianCalendar date = new GregorianCalendar(2011 + i, 0, 1, 0, 0, 0);
        date.setLenient(false);
        ArrayList<ArrayList<String>> report = m.getMonthlyReport(date);

        // Each farm
        for (int k = 0; k < report.size(); k++) {
          assert report.get(k).get(0).equals("Farm 00" + (k + 1)); // Three Farm
          assert report.get(k).get(1).equals(Integer.toString(12 * (k + 1) * (i + 1))); // total
                                                                                        // weight in
                                                                                        // a month
          // assert report.get(k).get(2).equals(Double.toString(10.0/6*(k+1)));
          // Percentage is hard to code in loops
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getDateReport() will return a correct report of a given year for only one farm added
   */
  @Test
  public void test006_get_date_report_for_one_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month at day
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
        }
      }

      // Create date instances for search
      GregorianCalendar start = new GregorianCalendar(2011, 0, 1, 0, 0, 0);
      GregorianCalendar end = new GregorianCalendar(2012, 11, 31, 0, 0, 0);

      // Get all of the records in two years
      ArrayList<ArrayList<String>> report = m.getDateReport(start, end);

      // Only have one farm - Farm 001
      assert report.get(0).get(0).equals("Farm 001");
      // Total weight is 12 + 24
      assert report.get(0).get(1).equals("36");
      // Percentage should be 1.0
      assert report.get(0).get(2).equals("100.00%");

    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getDateReport() will return a correct report of a given year for multiple farms added
   */
  @Test
  public void test007_get_date_report_for_one_farm() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }

      // Get records of each month. It should only consists of
      // Farm 001's record and the weight should be i + 1
      for (int i = 0; i < 2; i++) {
        GregorianCalendar date = new GregorianCalendar(2011 + i, 0, 1, 0, 0, 0);
        date.setLenient(false);
        ArrayList<ArrayList<String>> report = m.getMonthlyReport(date);

        // Each farm
        for (int k = 0; k < report.size(); k++) {
          report.get(k).get(0).equals("Farm 00" + k); // Three Farm
          report.get(k).get(1).equals(Integer.toString(i + 1));
          report.get(k).get(2).equals(Double.toString(1.0 / 6 * (k + 1)));
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test getNumberOfRecords() and getNumberOfFarms() return correct total numbers
   */
  @Test
  public void test008_get_numbers() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }
      assert m.getNumberOfFarms() == 3;
      assert m.getNumberOfRecords() == 72;
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test removeRecords() can successfully remove the specified record;
   */
  @Test
  public void test009_remove_records() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }

      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);

          // All removal should succeed
          assert m.removeRecords(new Record(date, "Farm 001", i + 1));
          assert m.removeRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          assert m.removeRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
        assert m.getNumberOfRecords() == (1 - i) * 36;
      }
      assert m.getNumberOfFarms() == 0;

      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);

          // All removal should fail
          assert !m.removeRecords(new Record(date, "Farm 001", i + 1));
          assert !m.removeRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          assert !m.removeRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }

    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test removeRecords() throws exceptions as specified
   */
  @Test
  public void test010_remove_records_throw_exceptions() {
    try {
      m.removeRecords(null);
      fail("removeRecords should throw IllegalNullKeyException");
    } catch (IllegalNullKeyException e) {
      // Expected
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test addRecords() throws exceptions as specified;
   */
  @Test
  public void test011_add_records_throw_exceptions() {
    try {
      try { // throw IllegalNullKeyException
        m.addRecords(null);
      } catch (IllegalNullKeyException e) {
        // expected
      }
      assert m.getNumberOfFarms() == 0;
      assert m.getNumberOfRecords() == 0;
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }
      // Do the same procedures as the above. Each time there
      // would be an DuplicateKeyException thrown
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          try {
            m.addRecords(new Record(date, "Farm 001", i + 1));
          } catch (DuplicateKeyException e) {
            // Expected
          }
          try {
            m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          } catch (DuplicateKeyException e) {
            // Expected
          }
          try {
            m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
          } catch (DuplicateKeyException e) {
            // Expected
          }
        }
      }
      // Numbers should not change
      assert m.getNumberOfFarms() == 3;
      assert m.getNumberOfRecords() == 72;
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test changeRecords() will change records accordingly
   */
  @Test
  public void test012_change_records() {
    try {
      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.addRecords(new Record(date, "Farm 001", i + 1));
          m.addRecords(new Record(date, "Farm 002", 2 * (i + 1)));
          m.addRecords(new Record(date, "Farm 003", 3 * (i + 1)));
        }
      }

      assert m.getNumberOfFarms() == 3;
      assert m.getNumberOfRecords() == 72;

      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, j + 1, 0, 0, 0);
          date.setLenient(false);
          m.changeRecords(new Record(date, "Farm 001", i + 1),
              new Record(date, "Farm 001", 3 * (i + 1)));
          m.changeRecords(new Record(date, "Farm 002", 2 * (i + 1)),
              new Record(date, "Farm 002", 1 * (i + 1)));
          m.changeRecords(new Record(date, "Farm 003", 3 * (i + 1)),
              new Record(date, "Farm 003", 2 * (i + 1)));
        }
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test changeRecords() will throw exceptions accordingly
   */
  @Test
  public void test013_change_records_throw_exceptions() {
    try {
      try { // IllegalNullKeyException
        m.changeRecords(null, null);
        fail("Should throw IllegalNullKeyException");
      } catch (IllegalNullKeyException e) {
        // Expected
      }
      // Nothing has been inserted. So total number should all be 0
      assert m.getNumberOfRecords() == 0;
      assert m.getNumberOfFarms() == 0;

      for (int i = 0; i < 2; i++) {
        // Insert record into 2 years and each month
        for (int j = 0; j < 12; j++) {
          GregorianCalendar date = new GregorianCalendar(2011 + i, 0, 1, 0, 0, 0);
          date.setLenient(false);
          // Weight has been changed
          m.addRecords(new Record(date, "Farm 001", j + 1));
        }
      }
      assert m.getNumberOfRecords() == 24;
      assert m.getNumberOfFarms() == 1;

      GregorianCalendar date = new GregorianCalendar(2011, 0, 1, 0, 0, 0);

      try { // DuplicateKeyException
        m.changeRecords(new Record(date, "Farm 001", 1), new Record(date, "Farm 001", 2));
        fail("Should throw DuplicateKeyException");
      } catch (DuplicateKeyException e) {
        // Expected
      }

      assert m.getNumberOfRecords() == 24;
      assert m.getNumberOfFarms() == 1;
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }


}
