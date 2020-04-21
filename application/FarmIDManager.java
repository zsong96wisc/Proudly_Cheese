/**
 * FarmIDManager.java created by aTeam 147 in Proudly_Cheese project
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

import java.util.Hashtable;

/**
 * FarmIDManager - manage records based on FarmID
 * 
 * @author aTeam 147 (2020)
 */
public class FarmIDManager {
  Hashtable<String, Farm> hashTable; // map farmID to String
  int totalNumberOfRecords; // total number of records
}
