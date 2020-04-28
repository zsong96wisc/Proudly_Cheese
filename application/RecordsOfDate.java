/**
 * RecordsOfDate.java created by aTeam 147 in Proudly_Cheese project
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * RecordsOfDate - used to store Records based on Date
 * 
 * @author aTeam 147 (2020)
 */
public class RecordsOfDate {

  // Date of this records set
  private GregorianCalendar g;
  // tree set of records storing all records of this specific date
  private TreeSet<Record> recordSet;
  // int field that stores total weights of the day
  private int totalWeight;
  // int field storing total record numbers
  private int numKeys;

  /**
   * Constructor initializing fields
   * 
   * @param g - date that this records set stores
   */
  public RecordsOfDate(GregorianCalendar g) {
    // Initialize all the variables
    this.g = g;
    this.recordSet = new TreeSet<Record>();
    this.totalWeight = 0;
    this.numKeys = 0;
  }

  /**
   * This method is used to add new record to the BST
   * 
   * @param record - record to be stored
   * 
   * @throws IllegalNullKeyException - when input is null
   * @throws DuplicateKeyException   - when input already exists in the BST
   */
  public void insert(Record record) throws IllegalNullKeyException, DuplicateKeyException {
    // Check whether the input parameter is null
    if (record == null)
      throw new IllegalNullKeyException("null Record input");

    // Check whether the record is duplicated
    if (recordSet.contains(record))
      throw new DuplicateKeyException("duplicate Record");

    // Add the record to the tree set
    recordSet.add(record);

    // update total weight and number
    totalWeight += record.getWeight();
    numKeys++;
  }

  /**
   * This method is used to remove a record from the BST
   * 
   * @param record - record to be removed
   * 
   * @return true when remove successfully, false otherwise
   * 
   * @throws IllegalNullKeyException - when input is null
   */
  public boolean remove(Record record) throws IllegalNullKeyException {
    // Check whether the input parameter is null
    if (record == null)
      throw new IllegalNullKeyException("null Record input");

    // Remove the record from the set
    boolean removeResult = recordSet.remove(record);

    // check if remove successfully, if so update total weight and number
    if (removeResult) {
      totalWeight -= record.getWeight();
      numKeys--;
    }
    return removeResult;
  }

  /**
   * This method is used to check if a record already stored
   * 
   * @param record - record to be checked
   * 
   * @return true if exists, false otherwise
   * 
   * @throws IllegalNullKeyException - when input is null
   */
  public boolean contains(Record record) throws IllegalNullKeyException {
    // Check whether the input parameter is null
    if (record == null)
      throw new IllegalNullKeyException("null Record input");
    return recordSet.contains(record);
  }

  /**
   * Getter method for record set
   * 
   * @return tree set of records stored
   */
  public TreeSet<Record> getRecordSet() {
    return recordSet;
  }

  /**
   * Getter method for total weight in this day
   * 
   * @return total weight
   */
  public int getTotalWeight() {
    return totalWeight;
  }

  /**
   * Getter method for date
   * 
   * @return the date
   */
  public GregorianCalendar getDate() {
    return g;
  }

  /**
   * This method is used to get all records stored by using iterator
   * 
   * @return list of records stored
   */
  public List<Record> getTraversal() {
    // Create the iterator to iterate through the list
    Iterator<Record> setIterator = recordSet.iterator();

    // Create a list to store results
    List<Record> result = new LinkedList<Record>();

    // Loop to retrieve records
    while (setIterator.hasNext()) {
      Record element = (Record) setIterator.next();
      result.add(element);
    }
    return result;
  }

  /**
   * Setter method for date
   * 
   * @param date the date to set
   */
  public void setDate(GregorianCalendar g) {
    this.g = g;
  }

  /**
   * Getter method for number of records stored
   * 
   * @return number of records stored
   */
  public int numKeys() {
    return numKeys;
  }
}
