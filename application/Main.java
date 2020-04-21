package application;

import javafx.application.Application;
import javafx.stage.Stage;



enum SceneIndex{
  MAINMENU,
  
  ADDDELETECHANGE,
  ADDDELETERESULT,
  CHANGERESULT,
  
  FARMREPORT,
  FARMREPORTRESULT,
  
  ANNUALREPORT,
  ANNUALREPORTRESULT,

  MONTHLYREPORT,
  MONTHLYREPORTRESULT,
  
  DATERANGEREPORT,
  DATERANGEREPORTRESULT
};

public class Main extends Application {
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    GUI.getMainScene(primaryStage);
    
    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
