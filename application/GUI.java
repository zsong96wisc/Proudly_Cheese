
package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI {

  private static final int WINDOW_WIDTH = 500;
  private static final int WINDOW_HEIGHT = 300;
  private static final String APP_TITLE = "Proudly Cheese";

  public static Scene getEditScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("editScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene editScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return editScene;
  }
  
  public static Scene getAddDeleteScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("AddDeleteScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene addDeleteScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return addDeleteScene;
  }
  
  public static Scene getChangeScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("changeScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene changeScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return changeScene;
  }
  
  public static Scene getFarmReportScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("FarmReportScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene farmReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return farmReportScene;
  }
  
  public static Scene getFarmResultScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("farmResultScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene farmResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return farmResultScene;
  }
  
  public static Scene getAnnualReportScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("AnnualReportScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene annualReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return annualReportScene;
  }
  
  public static Scene getAnnualResultScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("AnnualResultScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene annualResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return annualResultScene;
  }
  
  public static Scene getMonthlyReportScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("MonthlyReportScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene monthlyReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return monthlyReportScene;
  }
  
  public static Scene getMonthlyResultScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("MonthlyResultScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene monthlyResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return monthlyResultScene;
  }
  
  public static Scene getDateReportScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("DateReportScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene dateReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return dateReportScene;
  }
  
  public static Scene getDateResultScene() {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("DateResultScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene dateResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    return dateResultScene;
  }
 
}
