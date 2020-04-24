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

/**
 * Farm - used to store information of a particular farm
 * 
 * @author aTeam 147 (2020)
 *
 */
public class Farm {
  private String farmID; // denotes a particular farm
  
  //denotes the total milk weight provided by this farm
  private int totalWeight; 
  
  //Binary search tree storing Records for this particular farm
  private STADT<Record> bstDate; 
}
