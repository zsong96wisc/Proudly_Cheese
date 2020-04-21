/**
 * GUI.java created by aTeam 147 in Proudly_Cheese project
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

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/**
 * GUI - define the contents of each scene and use event handler to switch between scenes
 * 
 * @author aTeam 147 (2020)
 */
public class GUI {
  // integer for window's width
  private static final int WINDOW_WIDTH = 500;
  // integer for window's height
  private static final int WINDOW_HEIGHT = 300;
  // String for window's title
  private static final String APP_TITLE = "Proudly Cheese";

  /**
   * Define the main scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getMainScene(Stage primaryStage) {
    // Three VBox layout management in the BroadPane
    VBox vbox = new VBox(20);
    VBox vboxL = new VBox(5);
    VBox vboxR = new VBox(20);

    // Text field for title
    Text text = getTitle("Welcome to cheese factory management system!\n");

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();
    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create four buttons to direct to various reports
    // Set their sizes
    Button buttonFarm = new Button("Farm Report", new Label());
    buttonFarm.setPrefSize(300, 50);
    Button buttonAnnual = new Button("Annual Report", new Label());
    buttonAnnual.setPrefSize(300, 50);
    Button buttonMonthly = new Button("Monthly Report", new Label());
    buttonMonthly.setPrefSize(300, 50);
    Button buttonDate = new Button("Date Range Report", new Label());
    buttonDate.setPrefSize(300, 50);

    // Set the style and add event handler
    buttonFarm.setCursor(Cursor.HAND);
    buttonFarm.setStyle("-fx-base: moccasin;");
    buttonFarm.setOnAction(e -> {
      getFarmReportScene(primaryStage);
    });

    buttonAnnual.setCursor(Cursor.HAND);
    buttonAnnual.setStyle("-fx-base: navajowhite;");
    buttonAnnual.setOnAction(e -> {
      getAnnualReportScene(primaryStage);
    });

    buttonMonthly.setCursor(Cursor.HAND);
    buttonMonthly.setStyle("-fx-base: gold;");
    buttonMonthly.setOnAction(e -> {
      getMonthlyReportScene(primaryStage);
    });

    buttonDate.setCursor(Cursor.HAND);
    buttonDate.setStyle("-fx-base: darkorange;");
    buttonDate.setOnAction(e -> {
      getDateReportScene(primaryStage);
    });

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create a button "Edit Data"
    Button edData = getPolygonButton("Edit Data", 30);
    // Set the event handler
    edData.setOnAction(e -> {
      GUI.getEditScene(primaryStage);
    });

    // Create a button "Load Data"
    Button ldData = getPolygonButton("Load Data", 30);

    // Add the vertical box to the center of the root pane
    root.setTop(text);
    // Set the text to the center
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    // Add objects to VBox
    vbox.getChildren().addAll(buttonFarm, buttonAnnual, buttonMonthly, buttonDate);
    vboxL.getChildren().addAll(buttonCloud, edData, imageViewBrand);
    vboxR.getChildren().addAll(imageViewCheese, ldData);
    // Add the vertical box to the left of the root pane
    root.setLeft(vboxL);
    // Add the vertical box to the center of the root pane
    root.setCenter(vbox);
    // Add the vertical box to the right of the root pane
    root.setRight(vboxR);

    // Add panes to the scene
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
  }

  /**
   * Define the Edit Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getEditScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("editScene\n");

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();
    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create a button "Add/Delete Data"
    Button adData = getPolygonButton("Add/Delete Data", 50);
    // Set event handler
    adData.setOnAction(e -> {
      getAddDeleteScene(primaryStage);
    });

    // Create a button "Add/Delete Data"
    Button chData = getPolygonButton("Change", 50);
    // Set event handler
    chData.setOnAction(e -> {
      getChangeScene(primaryStage);
    });

    // Create the return button and set the event handler
    Button returnBn = getReturnButton();
    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create various layout managers
    VBox vboxLf = new VBox();
    HBox md = new HBox(10);
    VBox vboxRt = new VBox(100);

    // Add objects into layouts
    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    md.getChildren().addAll(adData, chData);
    vboxRt.getChildren().addAll(imageViewCheese, returnBn);
    // Add layouts into root pane
    root.setLeft(vboxLf);
    root.setCenter(md);
    root.setRight(vboxRt);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Add Delete Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getAddDeleteScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("AddDeleteScene\n");

    // Set the Text into the pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Create a vBox to store TextFields
    VBox vBox = new VBox(10);

    // Define three TextFields
    TextField farmIDTextField = getInputTextField("Farm ID", 50, 10);
    TextField dateTextField = getInputTextField("Date", 50, 10);
    TextField weightTextField = getInputTextField("Weight", 50, 10);

    // Add them to the layout
    vBox.getChildren().addAll(farmIDTextField, dateTextField, weightTextField);
    vBox.setAlignment(Pos.CENTER);

    root.setCenter(vBox);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getEditScene(primaryStage);
    });

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create VBox layout and add components
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);
    root.setLeft(sceneLeft);

    // Create functional buttons
    Button add = getOvalButton("Add", 4, 2);
    Button delete = getOvalButton("Delete", 4, 2);
    Button clear = getOvalButton("Clear", 4, 2);

    // Add style to the button
    add.setStyle("-fx-base: moccasin;");
    delete.setStyle("-fx-base: navajowhite;");
    clear.setStyle("-fx-base: gold;");

    // Create HBox layout and add components
    HBox bottomBox = new HBox(10);
    bottomBox.getChildren().addAll(add, delete, clear);
    bottomBox.setAlignment(Pos.CENTER);
    root.setBottom(bottomBox);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Change Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getChangeScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("AddDeleteScene\n");

    // Set the Text into the pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Create Functional Buttons
    Button change = getOvalButton("Change", 4, 2);
    Button clear = getOvalButton("Clear", 4, 2);

    // Set the style
    change.setStyle("-fx-base: navajowhite;");
    clear.setStyle("-fx-base: gold;");

    // Create HBox layout and add components
    HBox sceneBottom = new HBox(20);
    sceneBottom.getChildren().addAll(change, clear);
    sceneBottom.setAlignment(Pos.CENTER);
    root.setBottom(sceneBottom);
    BorderPane.setAlignment(sceneBottom, Pos.BOTTOM_CENTER);

    // Define three TextFields
    TextField oldFarmID = getInputTextField("Old Farm ID", 90, 10);
    TextField oldDate = getInputTextField("Old Date", 90, 10);
    TextField oldWeight = getInputTextField("Old Weight", 90, 10);

    // Create VBox layout and add components
    VBox center1 = new VBox(15);
    center1.getChildren().addAll(oldFarmID, oldDate, oldWeight);
    center1.setAlignment(Pos.CENTER);

    // Create the ImageView for the arrow
    File inputArrow = new File("changeArrow.png");
    Image imageArrow = new Image(inputArrow.toURI().toString());
    ImageView imageViewArrow = new ImageView();
    imageViewArrow.setImage(imageArrow);
    imageViewArrow.setFitHeight(140);
    imageViewArrow.setFitWidth(40);

    // Define three TextField
    TextField newFarmID = getInputTextField("New Farm ID", 90, 10);
    TextField newDate = getInputTextField("New Date", 90, 10);
    TextField newWeight = getInputTextField("New Weight", 90, 10);

    // Create VBox layout and add components
    VBox center3 = new VBox(15);
    center3.getChildren().addAll(newFarmID, newDate, newWeight);
    center3.setAlignment(Pos.CENTER);

    // Create HBox layout and add components
    HBox sceneCenter = new HBox(8);
    sceneCenter.getChildren().addAll(center1, imageViewArrow, center3);
    sceneCenter.setAlignment(Pos.CENTER);
    root.setCenter(sceneCenter);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getEditScene(primaryStage);
    });

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create VBox layout and add components
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);
    root.setLeft(sceneLeft);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Farm Report Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getFarmReportScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Define layout managers
    HBox hbox = new HBox(20);
    VBox vboxL = new VBox(60);
    VBox vboxR = new VBox(20);

    // Text field for title
    Text text = getTitle("Farm Report\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create a Label and TextField
    Label farmID = new Label("FarmID: ");
    TextField inputField = getInputTextField("FarmID", 110, 10);

    // Create the Button and set event Handler
    Button search = getOvalButton("Search", 4, 2);
    search.setStyle("-fx-base: navajowhite;");
    search.setOnAction(e -> {
        getFarmResultScene(primaryStage);
    });

    Button clear = getOvalButton("Clear", 4, 2);
    clear.setStyle("-fx-base: gold;");

    // Create HBox layout and add components
    HBox sceneBottom = new HBox(20);
    sceneBottom.setAlignment(Pos.CENTER);
    sceneBottom.getChildren().addAll(search, clear);

    // Set the main pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    hbox.getChildren().addAll(farmID, inputField);
    vboxL.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxR.getChildren().addAll(returnButton, imageViewCheese);
    root.setLeft(vboxL);
    root.setCenter(hbox);
    root.setRight(vboxR);
    root.setBottom(sceneBottom);
    BorderPane.setAlignment(sceneBottom, Pos.BOTTOM_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Farm Result Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getFarmResultScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Define layout managers
    HBox hbox = new HBox();
    VBox vboxCL = new VBox(20);
    VBox vboxCR = new VBox();
    VBox vboxL = new VBox(60);
    VBox vboxR = new VBox(20);

    // Text field for title
    Text text = getTitle("Farm Report Result\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();
    imageViewCheese.setFitHeight(50);
    imageViewCheese.setFitWidth(50);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getFarmReportScene(primaryStage);
    });

    // Define a label
    Label title = new Label("\nTOTAL MILK\n WEIGHT");

    // Create two circles to display the result
    Circle circle1 = new Circle(40.0);
    circle1.setFill(Color.rgb(255, 255, 0, 0.7));
    circle1.setStroke(Color.BLACK);
    Text amount = new Text("36743");
    text.setBoundsType(TextBoundsType.VISUAL);
    StackPane stack = new StackPane();
    stack.getChildren().addAll(circle1, amount);

    Circle circle2 = new Circle(40.0);
    circle2.setFill(Color.rgb(255, 255, 0, 0.7));
    circle2.setStroke(Color.BLACK);
    Text percent = new Text("65%");
    percent.setBoundsType(TextBoundsType.VISUAL);
    StackPane stack2 = new StackPane();
    stack2.getChildren().addAll(circle2, percent);

    // Add the components to the VBox
    vboxCL.getChildren().addAll(title, stack, stack2);

    // The result to be displayed in the list
    String[][] result = {{"Mon", "Tue", "Wed"}, {"4567", "5322", "234"}, {"12.4%", "15%", "2%"}};

    // Create Labels and VBoxs layout
    Label farmDateLabel = new Label("Date");
    Label farmWeightLabel = new Label("Weight");
    Label farmPercentLabel = new Label("Percent");
    VBox vBoxFirstList = new VBox();
    VBox vBoxSecondList = new VBox();
    VBox vBoxThirdList = new VBox();

    // Create three ListViews
    ListView<String> farmDateList = getListView(result[0], 100, 200);
    ListView<String> totalWeightList = getListView(result[1], 100, 200);
    ListView<String> percentList = getListView(result[2], 100, 200);

    // Add them to the VBox
    vBoxFirstList.getChildren().addAll(farmDateLabel, farmDateList);
    vBoxSecondList.getChildren().addAll(farmWeightLabel, totalWeightList);
    vBoxThirdList.getChildren().addAll(farmPercentLabel, percentList);

    // Create HBox layout and add components
    HBox hBox = new HBox();
    hBox.getChildren().addAll(vBoxFirstList, vBoxSecondList, vBoxThirdList);

    // Create two buttons
    Button farmWeightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmWeightSortButton.setStyle("-fx-base: navajowhite;");

    Button ExportButton = getOvalButton("Export", 3.2, 2);
    ExportButton.setStyle("-fx-base: navajowhite;");

    // Add to the layout
    HBox buttonBox = new HBox(100);
    buttonBox.getChildren().addAll(farmWeightSortButton, ExportButton);

    vboxCR.getChildren().addAll(hBox, buttonBox);

    // Set the main pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    hbox.getChildren().addAll(vboxCL, vboxCR);
    vboxL.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxR.getChildren().addAll(returnButton, imageViewCheese);
    root.setLeft(vboxL);
    root.setCenter(hbox);
    root.setRight(vboxR);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Annual Report Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getAnnualReportScene(Stage primaryStage) {
    // Text field for title
    Text text = getTitle("Annual Report Scene\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create the return button and set the event handler
    Button returnBn = getReturnButton();
    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create two buttons and set event handler
    Button search = getOvalButton("Search", 3.2, 2);
    search.setStyle("-fx-base: navajowhite;");
    search.setOnAction(e -> {
      getAnnualResultScene(primaryStage);
    });

    Button clear = getOvalButton("Clear", 3.2, 2);
    clear.setStyle("-fx-base: gold;");

    // Create TextField and Text
    TextField textfield = new TextField();
    Text year = new Text();
    year.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    year.setText("Year : ");

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Create other layout managers
    HBox hbt = new HBox(30);
    HBox hbb = new HBox(30);
    VBox vboxLf = new VBox(130);
    VBox vboxRt = new VBox(30);
    VBox vboxmd = new VBox(100);

    // Add components to layout managers
    hbt.getChildren().addAll(year, textfield);
    hbb.getChildren().addAll(search, clear);
    hbt.setAlignment(Pos.CENTER);
    hbb.setAlignment(Pos.CENTER);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, imageViewCheese);
    vboxmd.getChildren().addAll(hbt, hbb);
    vboxmd.setAlignment(Pos.CENTER);

    // Set the main pane
    root.setLeft(vboxLf);
    root.setCenter(vboxmd);
    root.setRight(vboxRt);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Annual Result Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getAnnualResultScene(Stage primaryStage) {
    // Text field for title
    Text text = getTitle("Annual Result Scene\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create the return button and set the event handler
    Button returnBn = getReturnButton();
    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // The result to be displayed in the list
    String[][] result = {{"Farm ID", "Farm 123", "Farm 124", "Farm 125"},
        {"Total Weight", "2367", "463", "346"}, {"Percent", "74.8%", "14.6%", "10.9%"}};

    // Create three ListViews
    ListView<String> farmIDList = getListView(result[0], 100, 150);
    ListView<String> weightList = getListView(result[1], 100, 150);
    ListView<String> percentList = getListView(result[2], 100, 150);

    // Create HBox layout and add components
    HBox list = new HBox();
    list.getChildren().addAll(farmIDList, weightList, percentList);

    // Create three buttons
    Button farmIDSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmIDSortButton.setStyle("-fx-base: navajowhite;");

    Button weightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    Button exportButton = getOvalButton("Export", 3.2, 2);
    exportButton.setStyle("-fx-base: gold;");

    // Create other layout managers and add components
    HBox sortBox = new HBox(25);
    sortBox.getChildren().addAll(farmIDSortButton, weightSortButton);

    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(list, sortBox);

    VBox vboxLf = new VBox(130);
    VBox vboxRt = new VBox(25);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, imageViewCheese, exportButton);

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Set the main pane
    root.setLeft(vboxLf);
    root.setRight(vboxRt);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Monthly Report Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getMonthlyReportScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text title = getTitle("Monthly Report Scene\n");
    // Add to the pane
    root.setTop(title);
    BorderPane.setAlignment(title, Pos.TOP_CENTER);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create two buttons and set event handler
    Button searchButton = getOvalButton("Search", 3.2, 2);
    searchButton.setStyle("-fx-base: navajowhite;");
    searchButton.setOnAction(e -> {
      getMonthlyResultScene(primaryStage);
    });

    Button clearButton = getOvalButton("Clear", 3.2, 2);
    clearButton.setStyle("-fx-base: gold;");

    // Create TextField and Text
    TextField textfield = new TextField();
    Text monthPrompt = new Text();
    monthPrompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    monthPrompt.setText("Year/Month: ");

    // Create other layout managers
    HBox topBox = new HBox(20);
    HBox bottomBox = new HBox(30);
    VBox leftBox = new VBox(130);
    VBox rightBox = new VBox(30);
    VBox centerBox = new VBox(100);

    // Add components to layout managers
    topBox.getChildren().addAll(monthPrompt, textfield);
    bottomBox.getChildren().addAll(searchButton, clearButton);
    topBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);

    leftBox.getChildren().addAll(buttonCloud, imageViewBrand);
    rightBox.getChildren().addAll(returnButton, imageViewCheese);
    centerBox.getChildren().addAll(topBox, bottomBox);
    centerBox.setAlignment(Pos.CENTER);

    // Set the main pane
    root.setLeft(leftBox);
    root.setCenter(centerBox);
    root.setRight(rightBox);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Monthly Result Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getMonthlyResultScene(Stage primaryStage) {
    // Text field for title
    Text text = getTitle("Monthly Result Scene\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getMonthlyReportScene(primaryStage);
    });

    // Create export buttons
    Button export = getOvalButton("Export", 3.2, 2);
    export.setStyle("-fx-base: gold;");

    // The result to be displayed in the list
    String[][] result = {{"Farm ID", "Farm 123", "Farm 124", "Farm 125"},
        {"Total Weight", "2367", "463", "346"}, {"Percent", "74.8%", "14.6%", "10.9%"}};

    // Create three ListViews
    ListView<String> farmIDList = getListView(result[0], 100, 150);
    ListView<String> weightList = getListView(result[1], 100, 150);
    ListView<String> percentList = getListView(result[2], 100, 150);

    // Create HBox layout and add components
    HBox list = new HBox();
    list.getChildren().addAll(farmIDList, weightList, percentList);

    // Create two buttons
    Button farmIDSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmIDSortButton.setStyle("-fx-base: navajowhite;");

    Button weightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    // Create HBox and VBox layout and add components
    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);

    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(list, sortButton);

    VBox vboxLf = new VBox(130);
    VBox vboxRt = new VBox(25);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxRt.getChildren().addAll(returnButton, imageViewCheese, export);

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Set the main pane
    root.setLeft(vboxLf);
    root.setRight(vboxRt);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Date Report Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getDateReportScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("Date Report Scene\n");

    // Define two TextFields for start date and end date
    TextField endDateField = getInputTextField("Enter end Date", 50, 10);
    TextField startDateField = getInputTextField("Enter start Date", 50, 10);

    // Create VBox layout and add components
    VBox vBox = new VBox(20);
    vBox.getChildren().addAll(endDateField, startDateField);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    vBox.setAlignment(Pos.CENTER);
    root.setCenter(vBox);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Add the button to the root pane
    root.setRight(returnButton);

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);

    root.setLeft(sceneLeft);

    // Define two buttons for search and clear
    Button search = getOvalButton("Search", 3.2, 2);
    search.setStyle("-fx-base: moccasin;");
    search.setOnAction(e -> {
      getDateResultScene(primaryStage);
    });

    Button clear = getOvalButton("Clear", 3.2, 2);
    clear.setStyle("-fx-base: gold;");

    // Create HBox layout and add components
    HBox bottomBox = new HBox(20);
    bottomBox.getChildren().addAll(search, clear);
    bottomBox.setAlignment(Pos.CENTER);

    root.setBottom(bottomBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Date Result Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public static void getDateResultScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Text field for title
    Text text = getTitle("Date Result Scene\n");

    // Add the text to the root pane
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // The result to be displayed in the list
    String[][] result = {{"Farm ID", "Farm 123", "Farm 124", "Farm 125"},
        {"Total Weight", "2367", "463", "346"}, {"Percent", "74.8%", "14.6%", "10.9%"}};

    // Create three ListViews
    ListView<String> farmIDList = getListView(result[0], 600, 100);
    ListView<String> totalWeightList = getListView(result[1], 600, 100);
    ListView<String> percentList = getListView(result[2], 600, 100);

    // Create HBox layout and add components
    HBox listHBox = new HBox();
    listHBox.getChildren().addAll(farmIDList, totalWeightList, percentList);

    // Create two buttons
    Button farmIDSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmIDSortButton.setStyle("-fx-base: moccasin;");

    Button weightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    // Create HBox and VBox layout and add components
    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);
    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(listHBox, sortButton);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getDateReportScene(primaryStage);
    });

    // Create the ImageView Cheese
    ImageView imageViewCheese = getImageViewCheese();

    // Create Button for export
    Button exportButton = getOvalButton("Export", 3.2, 2);
    exportButton.setStyle("-fx-base: gold;");

    // Create VBox layout and add components
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese, exportButton);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud();

    // Create the ImageView Brand
    ImageView imageViewBrand = getImageViewBrand();

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);

    // Set the main pane
    root.setLeft(sceneLeft);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Create the text for the title
   * 
   * @param content - the word displayed in the Text
   * 
   * @return the Text
   */
  private static Text getTitle(String content) {
    // Create a new Text
    Text text = new Text(content);
    // Set the font
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    return text;
  }

  /**
   * Create the ImageView for the Cheese picture
   * 
   * @return the ImageView Cheese
   */
  private static ImageView getImageViewCheese() {
    // Create a file to read image
    File inputCheese = new File("cheese.png");
    // Put the file into Image
    Image imageCheese = new Image(inputCheese.toURI().toString());
    // Put Image to ImageView
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    // Set the size
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);
    return imageViewCheese;
  }

  /**
   * Create the ImageView for the Brand picture
   * 
   * @return the ImageView Brand
   */
  private static ImageView getImageViewBrand() {
    // Create a file to read image
    File inputBrand = new File("brand.png");
    // Put the file into Image
    Image imageBrand = new Image(inputBrand.toURI().toString());
    // Put Image to ImageView
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    // Set the size
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);
    return imageViewBrand;
  }

  /**
   * Create the ToggleButton for Help
   * 
   * @return the ToggleButton for Help
   */
  private static ToggleButton getbuttonCloud() {
    // Create a image
    Image image = new Image("cloud.png", 40, 30, false, false);
    // Put Image to ImageView
    ImageView imageView = new ImageView(image);
    // Create the button
    ToggleButton buttonCloud = new ToggleButton("", imageView);
    return buttonCloud;
  }

  /**
   * Create the Polygon Button
   * 
   * @param text - the text displayed on the button
   * @param r    - the radius
   * 
   * @return the Polygon Button
   */
  private static Button getPolygonButton(String text, double r) {
    // Define the polygon
    Polygon p = new Polygon();
    p.getPoints().addAll(new Double[] {5.0, 0.0, 12.07, 0.0, 17.07, 5.0, 17.07, 10.0, 12.07, 15.0,
        5.0, 15.0, 0.0, 10.0, 0.0, 5.0});
    // Create a button with text
    Button button = new Button(text);
    // Set the shape and size of the button
    button.setShape(p);
    button.setMaxSize(3 * r, 3 * r);
    button.setMinSize(3 * r, 3 * r);
    button.setCursor(Cursor.HAND);
    button.setStyle("-fx-base: yellow;");
    return button;
  }

  /**
   * Create the Return Button
   * 
   * @return the Return Button
   */
  private static Button getReturnButton() {
    // Create a button with text "return"
    Button returnButton = new Button("return");
    // Define the radius
    double r = 25;
    // Set the shape and size
    returnButton.setShape(new Circle(r));
    returnButton.setMaxSize(2.5 * r, 2.5 * r);
    returnButton.setMinSize(2.5 * r, 2.5 * r);
    returnButton.setCursor(Cursor.HAND);
    returnButton.setStyle("-fx-base: orange;");
    return returnButton;
  }

  /**
   * Create the Oval Button
   * 
   * @param text - the text displayed on the button
   * 
   * @return the Oval Button
   */
  private static Button getOvalButton(String text, double length, double width) {
    // Define the radius
    double r = 25;
    // Create a button with text "return"
    Button button = new Button(text);
    // Set the shape and size
    button.setShape(new Circle(r));
    button.setMaxSize(length * r, width * r);
    button.setMinSize(length * r, width * r);
    button.setCursor(Cursor.HAND);
    return button;
  }

  /**
   * Create the TextField
   * 
   * @param text   - the text displayed on the field
   * @param length - the length of the field
   * @param height - the height of the field
   * 
   * @return the TextField
   */
  private static TextField getInputTextField(String text, int length, int height) {
    // Create a textField
    TextField textField = new TextField();
    textField.setPromptText(text);

    // Set their size
    textField.setPrefSize(length, height);
    return textField;
  }

  /**
   * Create the ListView
   * 
   * @param result - the array of data
   * @param length - the length of the field
   * @param height - the height of the field
   * 
   * @return the ListView<String>
   */
  private static ListView<String> getListView(String[] result, int length, int height) {
    // Create a new listView
    ListView<String> listView = new ListView<String>();

    // Add data to the list
    for (int i = 0; i < result.length; i++) {
      listView.getItems().add(result[i]);
    }

    // Set the size
    listView.setMaxSize(length, height);
    return listView;
  }

}
