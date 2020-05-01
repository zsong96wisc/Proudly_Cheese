/**
 * FarmIDManagerTest.java created by aTeam 147 in Proudly_Cheese project
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
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * FarmIDManager Unit Test case
 * 
 * @author aTeam 147 (2020)
 */
class FarmIDManagerTest {
  private FarmIDManager fm; // Instance to be tested

  /**
   * instantiate a FarmIDManager instance every time before test
   * 
   * @throws Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
    fm = new FarmIDManager();
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

  @Test
  public void test001_add_farm_record() {
    try {
      GregorianCalendar date = new GregorianCalendar(2013, 1, 1, 0, 0, 0);
      Record record = new Record(date, "Farm 001", 13);
      fm.addFarmRecord(record);
      List<Record> report = fm.getFarmRecords("Farm 001");
      assert report.get(0).compareTo(record) == 0;

    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test addFarmRecord() will successfully add multiple records
   */
  @Test
  public void test002_add_more_farm_records() {
    try {
      List<Record> recordsAddedInFarm1 = new LinkedList<Record>();
      for (int i = 1; i <= 12; i++) {
        GregorianCalendar date = new GregorianCalendar(2013, i - 1, i, 0, 0, 0);
        date.setLenient(false);

        // Record the record being added
        Record record = new Record(date, "Farm 001", 13);
        recordsAddedInFarm1.add(record);

        fm.addFarmRecord(record);
      }

      List<Record> recordsAddedInFarm2 = new LinkedList<Record>();
      for (int i = 1; i <= 12; i++) {
        GregorianCalendar date = new GregorianCalendar(2014, i - 1, i, 0, 0, 0);
        date.setLenient(false);

        // Record the record being added
        Record record = new Record(date, "Farm 002", 13);
        recordsAddedInFarm2.add(record);

        fm.addFarmRecord(record);
      }
      // total number of records should be 24
      assert fm.getTotalNumberOfRecords() == 24;
      List<Record> reportListInFarm1 = fm.getFarmRecords("Farm 001");
      List<Record> reportListInFarm2 = fm.getFarmRecords("Farm 002");

      // (A is subset of B && B is subset of A) => (A == B)
      assert reportListInFarm1.containsAll(recordsAddedInFarm1);
      assert recordsAddedInFarm1.containsAll(reportListInFarm1);
      assert reportListInFarm2.containsAll(recordsAddedInFarm2);
      assert recordsAddedInFarm2.containsAll(reportListInFarm2);
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test addFarmRecord() will throw exceptions under special circumstances
   */
  @Test
  public void test003_add_farm_records_excetions() {
    try {
      fm.addFarmRecord(null);
      fail("no exception triggered");
    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }

    try {
      GregorianCalendar date = new GregorianCalendar(2013, 1, 1, 0, 0, 0);
      Record record = new Record(date, "Farm 001", 13);
      fm.addFarmRecord(record);
      fm.addFarmRecord(record);
      fail("no exception triggered");
    } catch (DuplicateKeyException e) {
      // expected
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }

  /**
   * Test addFarmRecord() will throw exceptions under special circumstances
   */
  @Test
  public void test004_remove_farm_record() {
    try {
      List<Record> recordsAdded = new LinkedList<Record>();
      for (int i = 1; i <= 12; i++) {
        GregorianCalendar date = new GregorianCalendar(2000 + i, i - 1, i, 0, 0, 0);
        date.setLenient(false);
        // Record the record being added
        Record record = new Record(date, "Farm 001", 13);
        recordsAdded.add(record);

        fm.addFarmRecord(record);
        // one new record added
        assert fm.getTotalNumberOfRecords() == 1;
        fm.removeFarmRecord(record);
        assert fm.getTotalNumberOfRecords() == 0;

        // Should be able to add once again
        fm.addFarmRecord(record);
        fm.removeFarmRecord(record); // Then remove it
      }
    } catch (Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }
}
