/**
 * ResultRecord.java created by aTeam 147 in Proudly_Cheese project
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

import javafx.beans.property.SimpleStringProperty; 

/**
 * ResultRecord - Representing one piece of the result to be shown in the table
 * @author aTeam 147 (2020)
 *
 */
public class ResultRecord {
  // Store the info for each column
  private final SimpleStringProperty columnOne;
  private final SimpleStringProperty columnTwo;
  private final SimpleStringProperty columnThree;

  /**
   * Constructor - create an object with info for each column
   * 
   * @param columnOne - the info for first column
   * @param columnTwo - the info for second column
   * @param columnThree - the info for third column
   */
  public ResultRecord(String columnOne, String columnTwo, String columnThree) {
    // Initialize all the instance variables
    this.columnOne = new SimpleStringProperty(columnOne);
    this.columnTwo = new SimpleStringProperty(columnTwo);
    this.columnThree = new SimpleStringProperty(columnThree);
  }

  /**
   * Getter method for ColumnOne
   * 
   * @return String in ColumnOne
   */
  public String getColumnOne() {
    return columnOne.get();
  }

  /**
   * Getter method for ColumnTwo
   * 
   * @return String in ColumnTwo
   */
  public String getColumnTwo() {
    return columnTwo.get();
  }

  /**
   * Getter method for ColumnThree
   * 
   * @return String in ColumnThree
   */
  public String getColumnThree() {
    return columnThree.get();
  }

  /**
   * Setter method for ColumnOne
   */
  public void setColumnOne(String columnOne) {
    this.columnOne.set(columnOne);
  }

  /**
   * Setter method for ColumnTwo
   */
  public void setColumnTwo(String columnTwo) {
    this.columnTwo.set(columnTwo);
  }

  /**
   * Setter method for ColumnThree
   */
  public void setColumnThree(String columnThree) {
    this.columnThree.set(columnThree);
  }
}
