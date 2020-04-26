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

import java.util.List;

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
  private int totalWeight;

  // Binary search tree storing Records for this particular farm
  private STADT<Record> bstDate;

  /**
   * Default constructor
   * 
   * @param farmID - farm ID of the constructed farm
   */
  public Farm(String farmID) {
    // Initialize all the variables
    this.farmID = farmID;
    this.totalWeight = 0;
    this.bstDate = new BST<Record>();
  }

  /**
   * This method is used to list all records stored through in-order traversal
   * 
   * @return list containing all records stored
   */
  public List<Record> getInOrderTraversal() {
    return bstDate.getInOrderTraversal();
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
    // Check whether the input is null or not
    // If true, throw IllegalNullKeyException
    if (key == null)
      throw new IllegalNullKeyException("null Record input");

    // Check whether the farmID presents in the hashTable
    // If not, throw DuplicateKeyException
    if (bstDate.contains(key))
      throw new DuplicateKeyException("duplicate Record");

    // Insert the key into the BST
    bstDate.insert(key);

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
    boolean removeResult = bstDate.remove(key);

    // check if remove successfully, if so update total weight
    if (removeResult)
      totalWeight -= key.getWeight();

    return removeResult;
  }

  /**
   * Determine whether there is a Record instance having the same date as in the BST tree bstDate.
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
    
    return bstDate.contains(key);
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
   * Getter method for number of records stored
   * 
   * @return number of records stored
   */
  public int numKeys() {
    return bstDate.numKeys();
  }
}
