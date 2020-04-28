/**
 * FarmIDManager.java created by aTeam 147 in Proudly_Cheese project
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

import java.util.Hashtable;
import java.util.List;

/**
 * FarmIDManager - manage records based on FarmID
 * 
 * @author aTeam 147 (2020)
 */
public class FarmIDManager {
  // map farmID to String
  private Hashtable<String, Farm> hashTable;
  // total number of records
  private int totalNumberOfRecords;

  /**
   * Constructor - A FarmID Manager stores all the farms instances inside a Hash table and keep
   * track of the number of records inside the Hash table.
   */
  public FarmIDManager() {
    // Initialize the Hash Table
    this.hashTable = new Hashtable<String, Farm>();
    // Initialize the number of records inside the Hash Table
    this.totalNumberOfRecords = 0;
  }

  /**
   * Retrieve the records from a particular farmID
   * 
   * @param farmID - The farmID used to search for a particular farm
   * 
   * @return The In order traversal of the matched farm
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public List<Record> getFarmRecords(String farmID) throws IllegalNullKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (farmID == null) {
      throw new IllegalNullKeyException("The input param is null");
    }

    // Check whether the farmID presents in the hashTable
    // If not, throw KeyNotFoundException
    if (!this.hashTable.containsKey(farmID)) {
      return null;
    }

    // Return the in order traversal of the particular farm
    return this.hashTable.get(farmID).getInOrderTraversal();
  }

  /**
   * Check whether a certain Farm with farmID is inside the Hash table
   * 
   * @param farmID - the farmID to be searched
   * 
   * @return - whether the farm with farmID is in the Hash table
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  private boolean contains(String farmID) throws IllegalNullKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (farmID == null) {
      throw new IllegalNullKeyException("The input param is null");
    }
    // Return the result
    return this.hashTable.containsKey(farmID);
  }

  /**
   * Insert the Farm record into the hash Table
   * 
   * @param record - the record to be inserted
   * 
   * @throws IllegalNullKeyException - if key argument is null
   * @throws DuplicateKeyException   - if the key is duplicated
   */
  public void addFarmRecord(Record record) throws IllegalNullKeyException, DuplicateKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (record == null) {
      throw new IllegalNullKeyException("The input param is null");
    }

    // Check whether the farm with respected to the record is in the hash Table
    // If not, create a new Farm object and insert into the Hash Table
    if (!contains(record.getFarmID())) {
      this.hashTable.put(record.getFarmID(), new Farm(record.getFarmID()));
    }

    // Insert the record into the Farm object
    this.hashTable.get(record.getFarmID()).insert(record);
    // Increment the count
    this.totalNumberOfRecords++;
  }

  /**
   * Delete the Farm record into the hash Table
   * 
   * @param record - the record to be removed
   * 
   * @return whether the record is successfully removed or not
   * 
   * @throws IllegalNullKeyException - if key argument is null
   */
  public boolean removeFarmRecord(Record record) throws IllegalNullKeyException {
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (record == null) {
      throw new IllegalNullKeyException("The input param is null");
    }

    // Check whether the farm with respected to the record is in the hash Table
    // If not, return false
    if (!contains(record.getFarmID())) {
      return false;
    }

    // Check whether this is only one record in the Farm object
    if (this.hashTable.get(record.getFarmID()).numKeys() == 1) {
      // If true, remove the Farm from hash table
      this.hashTable.remove(record.getFarmID());
    } else {
      // Else, remove the record from the farm
      this.hashTable.get(record.getFarmID()).remove(record);
    }

    // Decrement the count
    this.totalNumberOfRecords--;
    return true;
  }

  /**
   * Change the Farm record from the hash Table
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
    if (oldRecord == null || newRecord == null) {
      throw new IllegalNullKeyException("The input param is null");
    }

    // Remove the record from the table and store the result
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
   * Retrieve the number of records in the hash table
   * 
   * @return the number of records
   */
  public int getTotalNumberOfRecords() {
    return this.totalNumberOfRecords;
  }

  /**
   * Retrieve the number of farms in the hash table
   * 
   * @return the number of farms
   */
  public int getNumberOfFarms() {
    return this.hashTable.size();
  }
}
