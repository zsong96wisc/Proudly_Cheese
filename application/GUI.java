
package application;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.Scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  public Scene addDeleteScene = null;
  public Scene currentScene = null;
  
  
  public static void getMainScene(Stage primaryStage) {
    VBox vbox = new VBox(20);
    VBox vboxL = new VBox(5);
    VBox vboxR = new VBox(20);

    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("Welcome to cheese factory management system!\n");

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);

    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);

    Button buttonFarm = new Button("Farm Report", new Label());
    buttonFarm.setPrefSize(300, 50);
    Button buttonAnnual = new Button("Annual Report", new Label());
    buttonAnnual.setPrefSize(300, 50);
    Button buttonMonthly = new Button("Monthly Report", new Label());
    buttonMonthly.setPrefSize(300, 50);
    Button buttonDate = new Button("Date Range Report", new Label());
    buttonDate.setPrefSize(300, 50);


    buttonFarm.setCursor(Cursor.HAND);
    buttonFarm.setStyle("-fx-base: moccasin;");
    buttonAnnual.setCursor(Cursor.HAND);
    buttonAnnual.setStyle("-fx-base: navajowhite;");
    buttonMonthly.setCursor(Cursor.HAND);
    buttonMonthly.setStyle("-fx-base: gold;");
    buttonDate.setCursor(Cursor.HAND);
    buttonDate.setStyle("-fx-base: darkorange;");

    ToggleButton buttonCloud = new ToggleButton();

    // Button button5 = new Button("return");
    //
    // double r = 25;
    // button5.setShape(new Circle(r));
    // button5.setMaxSize(2.8*r,1.8*r);
    // button5.setMinSize(2.8*r,1.8*r);
    // button5.setCursor(Cursor.HAND);
    // button5.setStyle("-fx-base: orange;");


    Polygon p = new Polygon();
    p.getPoints().addAll(new Double[] {5.0, 0.0, 12.07, 0.0, 17.07, 5.0, 17.07, 10.0, 12.07, 15.0,
        5.0, 15.0, 0.0, 10.0, 0.0, 5.0});

    Button edData = new Button("Edit Data");
    double r2 = 30;
    edData.setShape(p);
    edData.setMaxSize(3 * r2, 3 * r2);
    edData.setMinSize(3 * r2, 3 * r2);
    edData.setCursor(Cursor.HAND);
    edData.setStyle("-fx-base: yellow;");

    Button ldData = new Button("Load Data");
    ldData.setShape(p);
    ldData.setMaxSize(3 * r2, 3 * r2);
    ldData.setMinSize(3 * r2, 3 * r2);
    ldData.setCursor(Cursor.HAND);
    ldData.setStyle("-fx-base: yellow;");

    edData.setOnAction(e -> {
        GUI.getEditScene(primaryStage);
      });

    // Add the vertical box to the center of the root pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    vbox.getChildren().addAll(buttonFarm, buttonAnnual, buttonMonthly, buttonDate);
    vboxL.getChildren().addAll(buttonCloud, edData, imageViewBrand);
    vboxR.getChildren().addAll(imageViewCheese, ldData);
    root.setLeft(vboxL);
    root.setCenter(vbox);
    root.setRight(vboxR);
    
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
  }
  
  
  public static void getEditScene(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("editScene");

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);

    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);

    Polygon p = new Polygon();
    p.getPoints().addAll(new Double[] {5.0, 0.0, 12.07, 0.0, 17.07, 5.0, 17.07, 10.0, 12.07, 15.0,
        5.0, 15.0, 0.0, 10.0, 0.0, 5.0});

    Button adData = new Button("Add/Delete Data");
    double r2 = 50;
    adData.setShape(p);
    adData.setMaxSize(3 * r2, 3 * r2);
    adData.setMinSize(3 * r2, 3 * r2);
    adData.setCursor(Cursor.HAND);
    adData.setStyle("-fx-base: yellow;");
    
    adData.setOnAction(e->{
      getAddDeleteScene(primaryStage);
    });
    
    Button chData = new Button("Change");
    chData.setShape(p);
    chData.setMaxSize(3 * r2, 3 * r2);
    chData.setMinSize(3 * r2, 3 * r2);
    chData.setCursor(Cursor.HAND);
    chData.setStyle("-fx-base: yellow;");
    
    chData.setOnAction(e->{
      getChangeScene(primaryStage);
    });
    
    Button returnBn = new Button("return");
    double r = 25;
    returnBn.setShape(new Circle(r));
    returnBn.setMaxSize(2.8 * r, 1.8 * r);
    returnBn.setMinSize(2.8 * r, 1.8 * r);
    returnBn.setCursor(Cursor.HAND);
    returnBn.setStyle("-fx-base: orange;");

    VBox vboxLf = new VBox();
    HBox md = new HBox(10);
    VBox vboxRt = new VBox(100);
    
    vboxLf.getChildren().addAll(imageViewBrand);
    md.getChildren().addAll(adData,chData);
    vboxRt.getChildren().addAll(imageViewCheese,returnBn);
    root.setLeft(vboxLf);
    root.setCenter(md);
    root.setRight(vboxRt);
    
    ToggleButton buttonCloud = new ToggleButton();
    // buttonCloud.getStylesheets().add(this.getClass().getResource("imagetogglebutton.css").toExternalForm());

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    return;
  }

  public static void getAddDeleteScene(Stage primaryStage) {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("AddDeleteScene");
    
    BorderPane root = new BorderPane(); 
    
    VBox vBox = new VBox();

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    
    Text farmIDText = new Text("Farm ID");
    Text dateText = new Text("Date");
    Text weightText = new Text("Weight");

    TextField farmIDTextField = new TextField("Farm ID");
    TextField dateTextField = new TextField("Date");
    TextField weightTextField = new TextField("Weight");

    HBox farmIDHBox = new HBox();
    farmIDHBox.getChildren().addAll(farmIDText, farmIDTextField);
    HBox dateHBox = new HBox();
    dateHBox.getChildren().addAll(dateText, dateTextField);
    HBox weightHBox = new HBox();
    weightHBox.getChildren().addAll(weightText, dateTextField);

    vBox.getChildren().addAll(farmIDHBox, dateHBox, weightHBox);
    root.setCenter(vBox);
    
    Button returnButton = new Button("return");
    root.setRight(returnButton);
    
    returnButton.setOnAction(e->{
      getEditScene(primaryStage);
    });

    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    
    return ;
  }
  
  public static void getChangeScene(Stage primaryStage) {
    Text text = new Text();
    
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
    text.setText("changeScene");
    
    BorderPane root = new BorderPane(); 
    
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene changeScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    primaryStage.setScene(changeScene);
    return;
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
