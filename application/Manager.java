/**
 * Manager.java created by aTeam 147 in Proudly_Cheese project
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

import java.util.Date;
import java.util.List;

/**
 * RecordsOfDate - used to store Records based on Date
 * 
 * @author aTeam 147 (2020)
 */
public class Manager {
  // Manager for Farm by FarmID
  private FarmIDManager farmIDManager;
  // Manager for Farm by date
  private DateManager dateManager;

  public Manager() {
    this.farmIDManager = new FarmIDManager();
    this.dateManager = new DateManager();
  }

  public List<Record> getFarmReport(String farmID)
      throws IllegalNullKeyException, KeyNotFoundException {
    return farmIDManager.getFarmRecords(farmID);
  }
  
  public List<Record> getFarmReport(Date date){
    return dateManager.getAnnualReport(date);
  }
}
