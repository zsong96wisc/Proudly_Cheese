/**
 * Record.java created by aTeam 147 in Proudly_Cheese project
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
import java.util.GregorianCalendar;

/**
 * Record - Each instance stores one piece of data
 * 
 * @author aTeam 147 (2020)
 *
 */
public class Record implements Comparable<Record> {
  // date of the record
  private GregorianCalendar date;
  // denotes the farm of this record
  private String farmID;
  // the weight of milk provided by the farm specified in ID
  private long weight;

  /**
   * Constructor of the record
   * 
   * @param date   - of the record
   * @param farmID - denotes the farm of this record
   * @param weight - the weight of milk provided by the farm specified in ID
   */
  public Record(GregorianCalendar date, String farmID, long weight) {
    // Initialize all the variables with parameters
    this.date = date;
    this.farmID = farmID;
    this.weight = weight;
  }

  /**
   * @return the date
   */
  public GregorianCalendar getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(GregorianCalendar date) {
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
  public long getWeight() {
    return weight;
  }

  /**
   * @param weight the weight to set
   */
  public void setWeight(long weight) {
    this.weight = weight;
  }

  /**
   * Compare method. Compare priority is: farmID, date, and weight.
   * 
   * @param Record r - the record to be compared with
   * 
   * @return a int value denote compare result. If greater than 0, this > r. If equal to 0, this =
   *         r. If less than 0, this < r.
   * 
   * @override method in Comparable interface
   */
  @Override
  public int compareTo(Record r) {
    // Store the result of ID difference
    int farmIDDiff = farmID.compareTo(r.farmID);
    // Check the difference
    if (farmIDDiff != 0)
      return farmIDDiff;

    // Store the result of year difference
    int yearDiff = this.getDate().get(Calendar.YEAR) - r.getDate().get(Calendar.YEAR);
    // Check the difference
    if (yearDiff != 0)
      return yearDiff;

    // Store the result of month difference
    int monthDiff = this.getDate().get(Calendar.MONTH) - r.getDate().get(Calendar.MONTH);
    // Check the difference
    if (monthDiff != 0)
      return monthDiff;

    // Store the result of date difference
    int dateDiff = this.getDate().get(Calendar.DATE) - r.getDate().get(Calendar.DATE);
    // Check the difference
    if (dateDiff != 0)
      return dateDiff;

    // FarmID the same, Date the same
    if (weight > r.weight)
      return 1;
    else if (weight < r.weight)
      return -1;
    else
      return 0;
  }
}
