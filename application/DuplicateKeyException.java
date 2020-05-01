/**
 * DuplicateKeyException.java created by aTeam 147 in Proudly_Cheese project
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

/**
 * Checked exception thrown if the key being inserted already exists in the structure. DO NOT EDIT
 * THIS CLASS
 */
@SuppressWarnings("serial")
public class DuplicateKeyException extends Exception {

  /**
   * default no-arg constructor
   */
  public DuplicateKeyException() {

  }

  /**
   * This constructor is provided to allow user to include a message
   * 
   * @param msg Additional message for this exception
   */
  public DuplicateKeyException(String msg) {
    super(msg);
  }

}
