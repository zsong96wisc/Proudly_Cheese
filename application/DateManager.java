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

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * DateManager - used to store records basd on Date.
 * 
 * @author aTeam 147 (2020)
 */
public class DateManager {
  private TreeSet<RecordsOfDate> rodSet; // Storing all of the Records based on
                                         // date
  private int totalNumberOfRecords; // Storing total number of records

  /**
   * Default constructor
   */
  public DateManager() {
    // TreeSet will sort records of date set based on its date
    this.rodSet = new TreeSet<RecordsOfDate>(new Comparator<RecordsOfDate>() {
      @Override
      /**
       * override the compare method
       * 
       * @param rod1 - the first RecordsOfDate to be compared
       * @param rod2 - the second RecordsOfDate to be compared
       * 
       * @return the result of comparison
       * 
       * @override Override method in Comparable
       */
      public int compare(RecordsOfDate rod1, RecordsOfDate rod2) {
        // Get the difference in Year
        int yearDiff = rod1.getDate().get(Calendar.YEAR) - rod2.getDate().get(Calendar.YEAR);
        // Check the difference
        if (yearDiff != 0)
          return yearDiff;

        // Get the difference in Month
        int monthDiff = rod1.getDate().get(Calendar.MONTH) - rod2.getDate().get(Calendar.MONTH);
        // Check the difference
        if (monthDiff != 0)
          return monthDiff;

        // Return the difference in date
        return rod1.getDate().get(Calendar.DATE) - rod2.getDate().get(Calendar.DATE);
      }
    });
    this.totalNumberOfRecords = 0;
  }

  /**
   * 
   * Change the Farm record from the storage
   * 
   * @param oldRecord - the record to be removed
   * @param newRecord - the record to be inserted
   * 
   * @return whether the record is successfully changed or not
   * 
   * @throws IllegalNullKeyException - if key argument is null
   * @throws DuplicateKeyException   - if the key is duplicated
   */
  public boolean changeFarmRecord(Record oldRecord, Record newRecord)
      throws IllegalNullKeyException, DuplicateKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (oldRecord == null || newRecord == null)
      throw new IllegalNullKeyException("insert null reference");
    // Remove the record from the storage and store the result
    boolean result = removeFarmRecord(oldRecord);
    // If there is a record successfully removed, add the new record
    if (result) {
      try {
        addFarmRecord(newRecord);
      } catch (DuplicateKeyException e) {
        addFarmRecord(oldRecord);
        result = false;
      }
    }
    return result;
  }

  /**
   * Insert a Record instance into the data structure
   * 
   * @param record - given record to be inserted
   * @throws IllegalNullKeyException - when the given parameter is null
   * @throws DuplicateKeyException   - when the inserted RecordsOfDate is already in the set
   */
  public void addFarmRecord(Record record) throws IllegalNullKeyException, DuplicateKeyException {

    if (record == null) // parameter is null
      throw new IllegalNullKeyException("insert null reference");
    RecordsOfDate tempRecordsOfDate = new RecordsOfDate(record.getDate());
    NavigableSet<RecordsOfDate> searchResult =
        rodSet.subSet(tempRecordsOfDate, true, tempRecordsOfDate, true);

    if (searchResult.isEmpty()) {// there is already such a rod.
      tempRecordsOfDate.insert(record);
      rodSet.add(tempRecordsOfDate);
    } else { // There is no such RecordsOfDate
      if (searchResult.first().contains(record))
        // If there is a duplicate record
        throw new DuplicateKeyException("duplicate records");

      searchResult.first().insert(record);
    }

    // Increment the count
    totalNumberOfRecords += 1;
  }

  /**
   * Remove a Record instance
   * 
   * @param record - to be removed
   * 
   * @returns true means a successful removal. false means a removal failure.
   * 
   * @throws IllegalNullKeyException - if the given record is null
   */
  public boolean removeFarmRecord(Record record) throws IllegalNullKeyException {
    // Check the parameter
    if (record == null)
      throw new IllegalNullKeyException("null record input");

    RecordsOfDate tempRecordsOfDate = new RecordsOfDate(record.getDate());
    // The search result
    NavigableSet<RecordsOfDate> searchResult =
        rodSet.subSet(tempRecordsOfDate, true, tempRecordsOfDate, true);

    if (searchResult.isEmpty())
      // there is no such record
      return false;

    if (searchResult.first().remove(record)) {
      // successful removal
      this.totalNumberOfRecords -= 1;
      return true;
    } else { // unsuccessful removal
      return false;
    }
  }

  /**
   * Get the RecordsOfDate instance having the date specified in the parameter.
   * 
   * @param date - binded to RecordsOfDate to be detected
   * 
   * @return the RecordsOfDate having the same date. Return null if no such RecordsOfDate is found
   * 
   * @throws IllegalNullKeyException - if the date is null
   */
  public List<Record> getMonthlyReport(GregorianCalendar date) throws IllegalNullKeyException {
    if (date == null) // if the date is null
      throw new IllegalNullKeyException("null date input");

    // Need start and end of the month
    GregorianCalendar[] startAndEnd = getMonthStartAndEnd(date);

    // Create two instances of ROD as the boundary for search
    RecordsOfDate startROD = new RecordsOfDate(startAndEnd[0]);
    RecordsOfDate endROD = new RecordsOfDate(startAndEnd[1]);
    // The search result
    NavigableSet<RecordsOfDate> searchResult = rodSet.subSet(startROD, true, endROD, true);

    // test search result
    if (searchResult.isEmpty()) {
      return new LinkedList<Record>();
    } else {
      // Traverse the search result
      List<Record> monthlyReport = new LinkedList<Record>();
      for (RecordsOfDate rod : searchResult) {
        monthlyReport.addAll(rod.getTraversal());
      }
      return monthlyReport;
    }
  }

  /**
   * Get the RecordsOfDate instance having the date specified in the parameter.
   * 
   * @param date - binded to RecordsOfDate to be detected
   * 
   * @return the RecordsOfDate having the same date. Return null if no such RecordsOfDate is found
   * 
   * @throws IllegalNullKeyException - if the date is null
   */
  public List<Record> getAnnualReport(GregorianCalendar date) throws IllegalNullKeyException {
    if (date == null) // if the date is null
      throw new IllegalNullKeyException("null date input");

    // Need start and end of the month
    GregorianCalendar[] startAndEnd = getYearStartAndEnd(date);

    // Create two instances of ROD as the boundary for search
    RecordsOfDate startROD = new RecordsOfDate(startAndEnd[0]);
    RecordsOfDate endROD = new RecordsOfDate(startAndEnd[1]);

    // The search result
    NavigableSet<RecordsOfDate> searchResult = rodSet.subSet(startROD, true, endROD, true);

    // test search result
    if (searchResult.isEmpty()) {
      return new LinkedList<Record>();
    } else {
      // Traverse the search result
      List<Record> annualReport = new LinkedList<Record>();
      for (RecordsOfDate rod : searchResult) {
        annualReport.addAll(rod.getTraversal());
      }
      return annualReport;
    }
  }

  /**
   * Get date range report based on start date and end date
   * 
   * @param start - date
   * @param end   - date
   * 
   * @return Records in a List
   */
  public List<Record> getDateRangeReport(GregorianCalendar start, GregorianCalendar end) {
    // Create two instances of ROD as the boundary for search
    RecordsOfDate startROD = new RecordsOfDate(start);
    RecordsOfDate endROD = new RecordsOfDate(end);

    // The search result
    NavigableSet<RecordsOfDate> searchResult = rodSet.subSet(startROD, true, endROD, true);

    // test search result
    if (searchResult.isEmpty()) {
      return new LinkedList<Record>();
    } else {
      // Traverse the search result
      List<Record> annualReport = new LinkedList<Record>();
      for (RecordsOfDate rod : searchResult) {
        annualReport.addAll(rod.getTraversal());
      }
      return annualReport;
    }
  }

  /**
   * Retrieve the total weight of a certain farm in a month
   * 
   * @param farmID - the farm to be search
   * @param date - the month to be searched
   * 
   * @return - total weight of that farm
   * 
   * @throws IllegalNullKeyException - if the date is null
   */
  public long getFarmMonthlyWeight(String farmID, GregorianCalendar date)
      throws IllegalNullKeyException {
    // if the date is null
    if (date == null) 
      throw new IllegalNullKeyException("null date input");
    // if the farmID is null
    if (farmID == null)
      throw new IllegalNullKeyException("null farmID input");

    // Need start and end of the month
    GregorianCalendar[] startAndEnd = getMonthStartAndEnd(date);

    // Create two instances of ROD as the boundary for search
    RecordsOfDate startROD = new RecordsOfDate(startAndEnd[0]);
    RecordsOfDate endROD = new RecordsOfDate(startAndEnd[1]);
    // The search result
    NavigableSet<RecordsOfDate> searchResult = rodSet.subSet(startROD, true, endROD, true);

    if (searchResult.isEmpty()) {
      return 0;
    } else {
      // Traverse the search result
      long totolWeight = 0;
      for (RecordsOfDate rod : searchResult) {
        // Create the MAX and MIN record 
        Record startRecord = new Record(rod.getDate(), farmID, 0);
        Record endRecord = new Record(rod.getDate(), farmID, Long.MAX_VALUE);
        // Retrieve the subset
        NavigableSet<Record> searchedResult =
            rod.getRecordSet().subSet(startRecord, true, endRecord, true);
        // Loop through the set to get the weight
        for (Record record : searchedResult) {
          totolWeight += record.getWeight();
        }
      }
      return totolWeight;
    }
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

  /**
   * Given a date, return the start date of the year and end date of the year
   * 
   * @param date - the given date
   * 
   * @return GregorianCalendar[] storing start at 0 and end at 1
   */
  private GregorianCalendar[] getYearStartAndEnd(GregorianCalendar date) {
    // Create two instances of the start and end of a year
    GregorianCalendar start = new GregorianCalendar();
    GregorianCalendar end = new GregorianCalendar();
    start.set(date.get(Calendar.YEAR), 1, 1, 0, 0, 0);
    start.set(Calendar.MONTH, Calendar.JANUARY);

    end.set(date.get(Calendar.YEAR), 1, 31, 0, 0, 0);
    end.set(Calendar.MONTH, Calendar.DECEMBER);
    end.set(Calendar.DATE, 31);

    // Store in an array and return
    return new GregorianCalendar[] {start, end};
  }

  /**
   * Given a date, return the start date of the month and end date of the month
   * 
   * @param date - the given date
   * 
   * @return GregorianCalendar[] storing start at 0 and end at 1
   */
  private GregorianCalendar[] getMonthStartAndEnd(GregorianCalendar date) {
    // Create two instances of the start and end of a month
    GregorianCalendar start = new GregorianCalendar();
    GregorianCalendar end = new GregorianCalendar();
    start.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1, 0, 0, 0);

    end.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), getNumberOfDaysInMonth(date), 0, 0,
        0);

    // Store in an array and return
    return new GregorianCalendar[] {start, end};
  }

  /**
   * Given a year and a month, return the number of days in a month
   * 
   * @param date - given date
   * 
   * @return the number of days in a month
   */
  private static int getNumberOfDaysInMonth(GregorianCalendar date) {
    // Retrieve the number of days in a month
    Calendar lastDay = Calendar.getInstance();
    lastDay.set(Calendar.YEAR, date.get(Calendar.YEAR));
    lastDay.set(Calendar.MONTH, date.get(Calendar.MONTH));
    lastDay.set(Calendar.DATE, 1);
    lastDay.roll(Calendar.DATE, -1);
    int maxDate = lastDay.get(Calendar.DATE);

    return maxDate;
  }

}
