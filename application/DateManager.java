/**
 * DateManager.java created by aTeam 147 in Proudly_Cheese project
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

import java.util.Comparator;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * DateManager - used to store records basd on Date.
 * 
 * @author aTeam 147 (2020)
 *
 */
public class DateManager {
  private TreeSet<RecordsOfDate> rodSet; // Storing all of the Records based on date
  private int totalNumberOfRecords; // Storing total number of records
  
  /**
   * Default constructor
   */
  public DateManager() {
    // TreeSet will sort records of date set based on its date
    this.rodSet = new TreeSet<RecordsOfDate>(new Comparator<RecordsOfDate>() {
      @Override
      public int compare(RecordsOfDate rod1, RecordsOfDate rod2) {
        return rod1.getDate().compareTo(rod2.getDate());
      }
    });
    this.totalNumberOfRecords = 0;
  }

  /**
   * Insert a RecordsOfDate data structure maintaining records of its 
   * specified date
   * 
   * @param recordsOfDate - given records of date to be inserted
   * @throws IllegalNullKeyException - when the given parameter is null
   * @throws DuplicateKeyException - when the inserted RecordsOfDate is 
   *    already in the set
   */
  public void insert(RecordsOfDate recordsOfDate) 
      throws IllegalNullKeyException, DuplicateKeyException {
    
    if (recordsOfDate == null) // parameter is null
      throw new IllegalNullKeyException("insert null reference");
    if (rodSet.contains(recordsOfDate)) // there is already such a rod.
      throw new DuplicateKeyException("duplicate records of Date");
    
    rodSet.add(recordsOfDate);
    totalNumberOfRecords += recordsOfDate.numKeys();
  }
  
  /**
   * Remove a RecordsOfDate instance
   * 
   * @param RecordsOfDate - to be removed
   * @returns true means a successful removal. false means a removal 
   *    failure.
   * @throws IllegalNullKeyException - if the given rod is null
   */
  public boolean remove(RecordsOfDate recordsOfDate) 
      throws IllegalNullKeyException {
    if (recordsOfDate == null)
      throw new IllegalNullKeyException("null RecordsOfDate input");
    
    // The search result
    NavigableSet<RecordsOfDate> searchResult =
        rodSet.subSet(recordsOfDate, true, recordsOfDate, true);

    RecordsOfDate rod = searchResult.first();
    if (rod == null)
      // there is no such recordsOfDate
      return false;

    this.totalNumberOfRecords -= rod.numKeys();
    
    return rodSet.remove(rod);
  }
  
  /**
   * remove a RecordsOfDate instance based on date
   * 
   * @param date - the given date binded to RecordsOfDate
   * @returns true means a successful removal. false means a removal 
   *    failure.
   * @throws IllegalNullKeyException - if the given data is null
   */
  public boolean remove(Date date) throws IllegalNullKeyException {
    if (date == null)
      throw new IllegalNullKeyException("null data input");
    
    // Need a temporary variable to search for such rod.
    RecordsOfDate tempRecordOfDate = new RecordsOfDate(date);
    // The search result
    NavigableSet<RecordsOfDate> searchResult =
        rodSet.subSet(tempRecordOfDate, true, tempRecordOfDate, true);

    RecordsOfDate rod = searchResult.first();
    if (rod == null)
      // there is no such recordsOfDate
      return false;

    this.totalNumberOfRecords -= rod.numKeys();
    
    return rodSet.remove(rod);
  }
  
  /**
   * Determine whether there is a RecordsOfDate instance having the same 
   * date as what the parameter rod has.
   * 
   * @param RecordsOfDate - to be removed
   * @return true if there is a RecordsOfDate instance having the same date
   *    as what the parameter rod has
   * @throws IllegalNullKeyException - if the given parameter is null
   */
  public boolean contains(RecordsOfDate recordsOfDate) 
      throws IllegalNullKeyException {
    if (recordsOfDate == null)
      throw new IllegalNullKeyException("null RecordsOfDate input");
    
    return rodSet.contains(recordsOfDate);
  }

  /**
   * Determine whether there is a RecordsOfDate instance binded to  
   * date specified as the parameter
   * 
   * @param date - binded to RecordsOfDate to be detected
   * @return true if there is a RecordsOfDate instance having the same Date
   * @throws IllegalNullKeyException - if the date is null
   */
  public boolean contains(Date date) throws IllegalNullKeyException {
    if (date == null) // if the date is null
      throw new IllegalNullKeyException("null date input");
    
    RecordsOfDate recordsOfDate = new RecordsOfDate(date);
    return rodSet.contains(recordsOfDate);
  }

  /**
   * Get the RecordsOfDate instance having the date specified in 
   * the parameter.
   * 
   * @param date - binded to RecordsOfDate to be detected
   * @return the RecordsOfDate having the same date
   * @throws IllegalNullKeyException - if the date is null
   * @throws KeyNotFoundException - if there is no such 
   *    RecordsOfDate instances
   */
  public RecordsOfDate getRecordsOfDate(Date date) throws IllegalNullKeyException, KeyNotFoundException {
    if (date == null) // if the date is null
      throw new IllegalNullKeyException("null date input");
    
    // Need a temporary variable to search for such rod.
    RecordsOfDate tempRecordOfDate = new RecordsOfDate(date);
    // The search result
    NavigableSet<RecordsOfDate> searchResult =
        rodSet.subSet(tempRecordOfDate, true, tempRecordOfDate, true);

    RecordsOfDate rod = searchResult.first();
    
    if (rod == null)
      throw new KeyNotFoundException("No such RecordsOfDate data structure");
    
    return rod;
  }
  
  /**
   * @return the rodSet
   */
  public TreeSet<RecordsOfDate> getRodSet() {
    return rodSet;
  }

  /**
   * @param rodSet the rodSet to set
   */
  public void setRodSet(TreeSet<RecordsOfDate> rodSet) {
    this.rodSet = rodSet;
  }

  /**
   * @return the totalNumberOfRecords
   */
  public int getTotalNumberOfRecords() {
    return totalNumberOfRecords;
  }

  /**
   * @param totalNumberOfRecords the totalNumberOfRecords to set
   */
  public void setTotalNumberOfRecords(int totalNumberOfRecords) {
    this.totalNumberOfRecords = totalNumberOfRecords;
  }
}
