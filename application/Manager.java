/**
 * Manager.java created by aTeam 147 in Proudly_Cheese project
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

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Manager - manages two objects, one is FarmIDManager, the other is DateManager
 * 
 * @author aTeam 147 (2020)
 */
public class Manager {
  // Manager for Farm by FarmID
  private FarmIDManager farmIDManager;
  // Manager for Farm by date
  private DateManager dateManager;

  /**
   * Constructor - create the Manager objects
   */
  public Manager() {
    // Initialize all the instance variables
    this.farmIDManager = new FarmIDManager();
    this.dateManager = new DateManager();
  }

  /**
   * Retrieve the farm report
   * 
   * @param farmID - the ID from input
   * 
   * @return List of results
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public List<Record> getFarmReport(String farmID) throws IllegalNullKeyException {
    return farmIDManager.getFarmRecords(farmID);
  }

  /**
   * Retrieve the monthly report
   * 
   * @param date - the input date
   * 
   * @return List of results
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public List<Record> getMonthlyReport(GregorianCalendar date) throws IllegalNullKeyException {
    return dateManager.getMonthlyReport(date);
  }

  /**
   * Retrieve the annual report
   * 
   * @param date - the input date
   * 
   * @return List of results
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public List<Record> getAnnualReport(GregorianCalendar date) throws IllegalNullKeyException {
    return dateManager.getAnnualReport(date);
  }

  /**
   * Retrieve the date range report
   * 
   * @param date - the input date
   * 
   * @return List of results
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public List<Record> getDateReport(GregorianCalendar start, GregorianCalendar end)
      throws IllegalNullKeyException {
    return dateManager.getDateRangeReport(start, end);
  }

  /**
   * Retrieve the number of records
   * 
   * @return the number of records
   */
  public int getNumberOfRecords() {
    // Check whether the record is consistent
    if (farmIDManager.getTotalNumberOfRecords() == dateManager.getTotalNumberOfRecords()) {
      return farmIDManager.getTotalNumberOfRecords();
    }
    return -1;
  }

  /**
   * Retrieve the number of farms
   * 
   * @return the number of farms
   */
  public int getNumberOfFarms() {
    return farmIDManager.getNumberOfFarms();
  }

  /**
   * Add records to the system
   * 
   * @param r - the record to be added
   * 
   * @throws IllegalNullKeyException - if key argument is null
   * @throws DuplicateKeyException   - if the key is duplicated
   */
  public void addRecords(Record r) throws IllegalNullKeyException, DuplicateKeyException {
    farmIDManager.addFarmRecord(r);
    dateManager.addFarmRecord(r);
  }

  /**
   * Remove records to the system
   * 
   * @param r - the record to be removed
   * 
   * @return whether the record is successfully removed or not
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public boolean removeRecords(Record r) throws IllegalNullKeyException {
    // Store the results of removal
    boolean result1 = farmIDManager.removeFarmRecord(r);
    boolean result2 = dateManager.removeFarmRecord(r);
    // Check the two results
    if (result1 && result2) {
      return true;
    }
    return false;
  }

  /**
   * Change records to the system
   * 
   * @param oldRecord - the record to be removed
   * @param newRecord - the record to be added
   * 
   * @return whether the record is successfully changed or not
   * 
   * @throws IllegalNullKeyException - if key argument is null
   * @throws DuplicateKeyException   - if the key is duplicated
   */
  public boolean changeRecords(Record oldRecord, Record newRecord)
      throws IllegalNullKeyException, DuplicateKeyException {
    // get the result of the change operation
    boolean result1 = farmIDManager.changeFarmRecord(oldRecord, newRecord);
    boolean result2 = dateManager.changeFarmRecord(oldRecord, newRecord);
    // Check the two results
    if (result1 && result2) {
      return true;
    }
    return false;
  }
}
