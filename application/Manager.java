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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Hashtable;
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
  // Manager for Input files
  private FileManager fileManager;

  String[] day = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "June", "Jul", "Aug", "Sep",
      "Oct", "Nov", "Dec"};

  /**
   * Constructor - create the Manager objects
   */
  public Manager() {
    // Initialize all the instance variables
    this.farmIDManager = new FarmIDManager();
    this.dateManager = new DateManager();
    this.fileManager = new FileManager();
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
  public String[][] getFarmReport(String farmID, GregorianCalendar date)
      throws IllegalNullKeyException {
    // if the date is null
    if (date == null) 
      throw new IllegalNullKeyException("null date input");
    // if the farmID is null
    if (farmID == null)
      throw new IllegalNullKeyException("null farmID input");

    String[][] result = new String[12][3];
    long sum = 0;

    for (int i = 0; i < 12; i++) {
      result[i][0] = day[i];
      date.set(Calendar.MONTH, i);
      long weight = dateManager.getFarmMonthlyWeight(farmID, date);
      sum += weight;
      result[i][1] = Long.toString(weight);
    }

    for (int i = 0; i < 12; i++) {
      result[i][2] = Double.toString(1.0 * Long.parseLong(result[i][1]) / sum);
    }

    return result;
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
  public ArrayList<ArrayList<String>> getMonthlyReport(GregorianCalendar date)
      throws IllegalNullKeyException {
    // 
    List<Record> records = dateManager.getMonthlyReport(date);
    Hashtable<String, Long> monthlyRecords = new Hashtable<String, Long>();
    long sumOfWeights = 0;
    // Traverse all of the records to and their total weight
    for (Record r : records) {
      sumOfWeights += r.getWeight();
      if (monthlyRecords.contains(r.getFarmID()))
        monthlyRecords.put(r.getFarmID(), monthlyRecords.get(r.getFarmID()) + r.getWeight());
      else
        monthlyRecords.put(r.getFarmID(), Long.valueOf(r.getWeight()));
    }

    ArrayList<ArrayList<String>> reportOfMonth = new ArrayList<ArrayList<String>>();
    // Get statistics of monthly report
    for (String s : monthlyRecords.keySet()) {
      ArrayList<String> tempList = new ArrayList<String>();
      tempList.add(s);
      tempList.add(monthlyRecords.get(s).toString());
      tempList.add(String.valueOf((1.0 * monthlyRecords.get(s) / sumOfWeights)));
      reportOfMonth.add(tempList);
    }

    Collections.sort(reportOfMonth, (a, b) -> {
      return a.get(0).compareTo(b.get(0));
    });

    return reportOfMonth;
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
  public ArrayList<ArrayList<String>> getAnnualReport(GregorianCalendar date)
      throws IllegalNullKeyException {
    List<Record> records = dateManager.getAnnualReport(date);
    Hashtable<String, Long> yearlyRecords = new Hashtable<String, Long>();
    long sumOfWeights = 0;
    // Traverse all of the records to get all FarmID and their total weight
    for (Record r : records) {
      sumOfWeights += r.getWeight();
      if (yearlyRecords.contains(r.getFarmID()))
        yearlyRecords.put(r.getFarmID(), yearlyRecords.get(r.getFarmID()) + r.getWeight());
      else
        yearlyRecords.put(r.getFarmID(), Long.valueOf(r.getWeight()));
    }

    ArrayList<ArrayList<String>> reportOfYear = new ArrayList<ArrayList<String>>();
    // Get statistics of yearly report
    for (String s : yearlyRecords.keySet()) {
      ArrayList<String> tempList = new ArrayList<String>();
      tempList.add(s);
      tempList.add(yearlyRecords.get(s).toString());
      tempList.add(String.valueOf((1.0 * yearlyRecords.get(s) / sumOfWeights)));
      reportOfYear.add(tempList);
    }

    Collections.sort(reportOfYear, (a, b) -> {
      return a.get(0).compareTo(b.get(0));
    });

    return reportOfYear;
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
  public ArrayList<ArrayList<String>> getDateReport(GregorianCalendar start, GregorianCalendar end)
      throws IllegalNullKeyException {
    List<Record> records = dateManager.getDateRangeReport(start, end);
    Hashtable<String, Long> rangeRecords = new Hashtable<String, Long>();
    long sumOfWeights = 0;
    // Traverse all of the records to get all FarmID and their total weight
    for (Record r : records) {
      sumOfWeights += r.getWeight();
      if (rangeRecords.contains(r.getFarmID()))
        rangeRecords.put(r.getFarmID(), rangeRecords.get(r.getFarmID()) + r.getWeight());
      else
        rangeRecords.put(r.getFarmID(), Long.valueOf(r.getWeight()));
    }

    ArrayList<ArrayList<String>> reportOfRange = new ArrayList<ArrayList<String>>();
    // Get statistics of yearly report
    for (String s : rangeRecords.keySet()) {
      ArrayList<String> tempList = new ArrayList<String>();
      tempList.add(s);
      tempList.add(rangeRecords.get(s).toString());
      tempList.add(String.valueOf((1.0 * rangeRecords.get(s) / sumOfWeights)));
      reportOfRange.add(tempList);
    }

    Collections.sort(reportOfRange, (a, b) -> {
      return a.get(0).compareTo(b.get(0));
    });

    return reportOfRange;
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

  /**
   * This method is used to add records to storage after loading from file
   * 
   * @param recordList - list of records read from the file
   * 
   * @throws IllegalNullKeyException - if key argument is null
   * @throws DuplicateKeyException   - if the key is duplicated
   */
  public void importList(List<Record> recordList)
      throws IllegalNullKeyException, DuplicateKeyException {
    // add each of record in the list to storage
    // remove record from list after insertion
    for (Record record : recordList) {
      addRecords(record);
      recordList.remove(record);
    }
  }
}
