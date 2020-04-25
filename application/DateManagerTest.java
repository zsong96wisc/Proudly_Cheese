/**
 * Name: Xiaoxi Sun
 * E-mail: xsun279@wisc.edu
 * Lecture number: 002
 * Description: 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * DataManager Unit Test case
 * 
 * @author Xiaoxi Sun
 */
class DateManagerTest {
  private DateManager dm; // Instance to be tested
  /**
   * instantiate a hash table instance every time before test
   * 
   * @throws Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
    dm = new DateManager();
  }

  /**
   * The end of the test
   * 
   * @throws Exception
   */
  @AfterEach
  public void tearDown() throws Exception {
    dm = null;
  }
  
  /**
   * Test addFarmRecord() will successfully add a 
   * new record
   */
  @Test
  public void test001_add_farm_record() {
    try {
      GregorianCalendar date = new GregorianCalendar(2013, 1, 1, 0, 0, 0);
      Record record = new Record(date, "Farm 001", 13);
      dm.addFarmRecord(record);
      List<Record> report = dm.getAnnualReport(date);
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
      List<Record> recordsAddedIn2013 = new LinkedList<Record>();
      for (int i = 1; i<=12; i++) {
        GregorianCalendar date = new GregorianCalendar(2013, i-1, i, 0, 0, 0);
        date.setLenient(false);

        // Record the record being added
        Record record = new Record(date, "Farm 001", 13);
        recordsAddedIn2013.add(record);
        
        dm.addFarmRecord(record);
      }
      
      List<Record> recordsAddedIn2014 = new LinkedList<Record>();
      for (int i = 1; i<=12; i++) {
        GregorianCalendar date = new GregorianCalendar(2014, i-1, i, 0, 0, 0);
        date.setLenient(false);
        
        // Record the record being added
        Record record = new Record(date, "Farm 001", 13);
        recordsAddedIn2014.add(record);
        
        dm.addFarmRecord(record);
      }
      // total number of records should be 24
      assert dm.getTotalNumberOfRecords() == 24;
      List<Record> reportListIn2013 = dm.getAnnualReport(new 
          GregorianCalendar(2013, 1, 1, 0, 0, 0));
      List<Record> reportListIn2014 = dm.getAnnualReport(new 
          GregorianCalendar(2014, 1, 1, 0, 0, 0));

      // (A is subset of B && B is subset of A) => (A == B)
      assert reportListIn2013.containsAll(recordsAddedIn2013);
      assert recordsAddedIn2013.containsAll(reportListIn2013);
      assert reportListIn2014.containsAll(recordsAddedIn2014);
      assert recordsAddedIn2014.containsAll(reportListIn2014);
    } catch(Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }
  
  /**
   * Test addFarmRecord() will throw exceptions under special 
   * circumstances
   */
  @Test
  public void test003_add_farm_records_excetions() {
    try {
      dm.addFarmRecord(null);
      fail("no exception triggered");
    } catch(IllegalNullKeyException e) {
      // expected
    } 
    catch(Exception e) {
      fail("Unkonw Exception Thrown");
    }
    
    try {
      GregorianCalendar date = new GregorianCalendar(2013, 1, 1, 0, 0, 0);
      Record record = new Record(date, "Farm 001", 13);
      dm.addFarmRecord(record);
      dm.addFarmRecord(record);
      fail("no exception triggered");
    } catch(DuplicateKeyException e) {
      // expected
    } 
    catch(Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }
  
  /**
   * Test addFarmRecord() will throw exceptions under special 
   * circumstances
   */
  @Test
  public void test004_remove_farm_record() {
    try {
      List<Record> recordsAdded = new LinkedList<Record>();
      for (int i = 1; i<=12; i++) {
        GregorianCalendar date = 
            new GregorianCalendar(2000 + i, i - 1, i, 0, 0, 0);
        date.setLenient(false);
        // Record the record being added
        Record record = new Record(date, "Farm 001", 13);
        recordsAdded.add(record);
        
        dm.addFarmRecord(record);
        // one new record added
        assert dm.getTotalNumberOfRecords() == 1; 
        dm.removeFarmRecord(record);
        assert dm.getTotalNumberOfRecords() == 0;
        
        // Should be able to add once again
        dm.addFarmRecord(record);
        dm.removeFarmRecord(record); // Then remove it
      }
    } catch(Exception e) {
      fail("Unkonw Exception Thrown");
    }
  }
}
