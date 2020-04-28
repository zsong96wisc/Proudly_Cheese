/**
 * Farm.java created by aTeam 147 in Proudly_Cheese project
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
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Farm - used to store information of a particular farm
 * 
 * @author aTeam 147 (2020)
 *
 */
public class Farm {
  // denotes a particular farm
  private String farmID;

  // denotes the total milk weight provided by this farm
  private long totalWeight;

  // tree set storing Records for this particular farm
  private TreeSet<Record> farmRecords;

  /**
   * Default constructor
   * 
   * @param farmID - farm ID of the constructed farm
   */
  public Farm(String farmID) {
    // Initialize all the variables
    this.farmID = farmID;
    this.totalWeight = 0;
    this.farmRecords = new TreeSet<Record>();
  }

  /**
   * This method is used to list all records stored through in-order traversal
   * 
   * @return list containing all records stored
   */
  public List<Record> getInOrderTraversal() {
    // Create the iterator to iterate through the list
    Iterator<Record> itr = farmRecords.iterator();

    // Create a list to store results
    List<Record> list = new ArrayList<Record>();

    // Loop to retrieve records
    while (itr.hasNext()) {
      list.add(itr.next());
    }
    return list;
  }

  /**
   * Get total weight of milk in records in a given date range.
   * 
   * @param start - the start date
   * @param end   - the start date
   * 
   * @return the sum of the weight
   */
  public long getTotalWeightInRange(GregorianCalendar start, GregorianCalendar end) {
    // Create two record with start date and end date
    Record startRecord = new Record(start, this.farmID, Long.MIN_VALUE);
    Record endRecord = new Record(end, this.farmID, Long.MAX_VALUE);

    // Integer to store total weight
    long weightSum = 0;

    // Retrieve the certain subset
    NavigableSet<Record> searchResult = farmRecords.subSet(startRecord, true, endRecord, true);

    // loop through the subset and add to the sum weight
    for (Record r : searchResult) {
      weightSum += r.getWeight();
    }

    return weightSum;
  }


  /**
   * Insert a Record data structure maintaining records of its specified date
   * 
   * @param key - given records of date to be inserted
   * 
   * @throws IllegalNullKeyException - when the given parameter is null
   * @throws DuplicateKeyException   - when the inserted Record is already in the set
   */
  public void insert(Record key) throws IllegalNullKeyException, DuplicateKeyException {
    // Check whether the input parameter is null
    if (key == null)
      throw new IllegalNullKeyException("null Record input");

    // Check whether the record is duplicated
    if (farmRecords.contains(key))
      throw new DuplicateKeyException("duplicate Record");

    // Add the record to the tree set
    farmRecords.add(key);

    // update total weight
    totalWeight += key.getWeight();

  }

  /**
   * Remove a Record instance
   * 
   * @param key - to be removed
   * 
   * @returns true means a successful removal. false means a removal failure.
   * @throws IllegalNullKeyException - if the given key is null
   */
  public boolean remove(Record key) throws IllegalNullKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (key == null)
      throw new IllegalNullKeyException("null Record input");

    // Remove the record from the BST
    boolean removeResult = farmRecords.remove(key);

    // check if remove successfully, if so update total weight
    if (removeResult)
      totalWeight -= key.getWeight();

    return removeResult;
  }

  /**
   * Determine whether there is a Record instance having the same date as in the treeset bstDate.
   * 
   * @param RecordsOfDate - to be removed
   * 
   * @return true if there is a RecordsOfDate instance having the same date as what the parameter
   *         rod has
   * 
   * @throws IllegalNullKeyException - if the given parameter is null
   */
  public boolean contains(Record key) throws IllegalNullKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (key == null)
      throw new IllegalNullKeyException("null Record input");

    return farmRecords.contains(key);
  }

  /**
   * Getter method for total weight in this day
   * 
   * @return total weight
   */
  public long getTotalWeight() {
    return totalWeight;
  }

  /**
   * @return number of records of this farm
   */
  public int numKeys() {
    return farmRecords.size();
  }

}
