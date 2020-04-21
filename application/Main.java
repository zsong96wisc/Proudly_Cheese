/**
 * Main.java created by aTeam 147 in Proudly_Cheese project
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

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main - Launch the GUI window
 * 
 * @author aTeam 147 (2020)
 *
 */
public class Main extends Application {

  /**
   * start method to generate the scene and stage
   * 
   * @param primaryStage - the first stage to be shown
   * 
   * @throws Exception - any exception
   * 
   * @Override methods in Application
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    GUI gui = new GUI();
    // Set the stage
    gui.getMainScene(primaryStage);
    // Show the stage
    primaryStage.show();
  }

  /**
   * main method to launch the GUI
   * 
   * @param args - user input
   */
  public static void main(String[] args) {
    launch(args);
  }
}
