/**
 * Record.java created by aTeam 147 in Proudly_Cheese project
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

import java.util.Date;

/**
 * Record - Each instance stores one piece of data
 * 
 * @author aTeam 147 (2020)
 *
 */
public class Record implements Comparable<Record>{
  private Date date; // date of the record
  private String farmID; // denotes the farm of this record 
  private int weight; // the weight of milk provided by the farm specified in ID

  /**
   * Constructor of the record
   * 
   * @param date - of the record
   * @param farmID - denotes the farm of this record
   * @param weight - the weight of milk provided by the farm specified in ID
   */
  public Record(Date date, String farmID, int weight) {
    this.date = date;
    this.farmID = farmID;
    this.weight = weight;
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
   * @return the farmID
   */
  public String getFarmID() {
    return farmID;
  }

  /**
   * @param farmID the farmID to set
   */
  public void setFarmID(String farmID) {
    this.farmID = farmID;
  }

  /**
   * @return the weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * @param weight the weight to set
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Compare method. Compare priority is: farmID, date, and weight.
   * 
   * @param Record r - the record to be compared with
   * @return a int value denote compare result. If greater than 0, this > r.
   *    If equal to 0, this = r. If less than 0, this < r.
   */
  @Override
  public int compareTo(Record r) {
    if (farmID.compareTo(r.farmID) != 0) {
      return farmID.compareTo(r.farmID);
    } else if (date.compareTo(r.date) != 0) {
      return date.compareTo(r.date);
    } else {
      return weight - r.weight;
    }
  }
}
