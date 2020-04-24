/**
 * RecordsOfDate.java created by aTeam 147 in Proudly_Cheese project
 * 
 * Author: Hairong Yin (hyin55@wisc.edu) (Lec 002), Haonan Shen (hshen37@wisc.edu) (Lec 001), 
 * Xiaoxi Sun (xsun279@wisc.edu) (Lec 002), Zhiwei Song (zsong96@wisc.edu) (Lec 002)
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
import java.util.Date;
import java.util.List;

/**
 * RecordsOfDate - used to store Records based on Date
 * 
 * @author aTeam 147 (2020)
 */
public class RecordsOfDate {
  private Date date; // Date of this 
  private STADT<Record> recordBST; // BST for record
  
  /**
   * 
   * @param date
   */
  public RecordsOfDate(Date date) {
    this.date = date;
    this.recordBST = new BST<>();
  }
  
  /**
   * 
   * @param record
   */
  public void insert(Record record) {
    return;
  }
  
  /**
   * 
   * @param record
   * @return
   */
  public boolean remove(Record record) {
    return false;
  }
  
  /**
   * 
   * @param record
   * @return
   */
  public boolean contains(Record record) {
    return false;
  }
  
  /**
   * 
   * @return
   */
  public List<Record> getInOrderTraversal() {
    return new ArrayList<Record>();
  }
  
  /**
   * 
   * @param farmID
   * @return
   */
  public List<Record> get(String farmID) {
    return null;
  }
  
  /**
   * 
   * @return
   */
  public int getTotalWeight() {
    return -1;
  }
  
  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }
  
  /**
   * 
   * @return
   */
  public int numKeys() {
    return 0;
  }
}
