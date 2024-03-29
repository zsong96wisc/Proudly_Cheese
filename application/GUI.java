/**
 * GUI.java created by aTeam 147 in Proudly_Cheese project
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * GUI - define the contents of each scene and use event handler to switch between scenes
 * 
 * @author aTeam 147 (2020)
 */
public class GUI {
  // enum variable for different scene
  enum SceneIndex {
    MAINMENU,

    ADDDELETECHANGE, ADDDELETERESULT, CHANGERESULT,

    FARMREPORT, FARMREPORTRESULT,

    ANNUALREPORT, ANNUALREPORTRESULT,

    MONTHLYREPORT, MONTHLYREPORTRESULT,

    DATERANGEREPORT, DATERANGEREPORTRESULT
  };

  // enum variable for different excpetion warning
  enum WarningIndex {
    IOEXCEPTION, ILLEGALRECORDEXCEPTION, NUMBERFORMATEXCEPTION, ILLEGALARGUMENTEXCEPTION,

    PARSEEXCEPTION, FILENOTFOUNDEXCEPTION, DUPLICATEKEYEXCEPTION, ILLEGALNULLKEYEXCEPTION,

    KEYNOTFOUNDEXCEPTION
  };

  // ImageView storing the cheese picture
  ImageView imageViewCheese;
  // ImageView storing the brand picture
  ImageView imageViewBrand;
  // FileChooser to choose files
  FileChooser fileChooser;
  // Manager to manage records
  Manager manager;

  // integer for window's width
  private static final int WINDOW_WIDTH = 500;
  // integer for window's height
  private static final int WINDOW_HEIGHT = 300;
  // String for window's title
  private static final String APP_TITLE = "Proudly Cheese";
  // A String array to store the possible user choice when loading
  private final String[] loadFunction =
      new String[] {"Append to the existing data", "Add as a new set of data"};
  // ArrayList to store the result
  private ArrayList<ArrayList<String>> result;
  // ObservableList to store the result
  private ObservableList<ResultRecord> data;
  // Flags to keep track of the order
  private boolean firstFlag = false;
  private boolean secondFlag = false;
  // ArrayList to store the summary statistics
  private ArrayList<String> summary;

  // Info for the main menu
  private static final String MAINMENU_INFO =
      "Please Load Data first. " + "Then select a report type or edit data.";

  // Info for the edit scene
  private static final String ADDDELETECHANGE_INFO =
      "Add or Delete " + "one piece of data. \n" + "Or Change one piece of data. \n";

  // Info for add delete scene
  private static final String ADDDELETERESULT_INFO =
      "Enter Farm ID, Date, Weight to add or delete this piece of data. \n"
          + "Clear will clear your input so that you can input new \ninformation.";

  // Info for change scene
  private static final String CHANGERESULT_INFO =
      "Enter old Farm ID, Date, Weight and the new ones accordingly to"
          + " swap change the old record to the new one. \n"
          + "Clear will clear your input so that you can input new \ninformation.";

  // Info for farm report scene
  private static final String FARMREPORT_INFO = "Please enter your Farm ID. Then press Search. \n"
      + "Clear will clear your input so that you can input new \ninformation.";

  // Info for farm report result scene
  private static final String FARMREPORTRESULT_INFO = "Click Asc/Des to sort based on Date \n"
      + "The upper circle shows the total weights of milk provided by this farm."
      + "The lower circle shows the percentage of the total weight of "
      + "milk provided by the farm. \n" + "Export will export data shown to the file system.";

  // Info for annual report scene
  private static final String ANNUALREPORT_INFO = "Please enter the year. Then click Search.\n"
      + "Clear will clear your input so that you can input new \ninformation";

  // Info for annual report result scene
  private static final String ANNUALREPORTRESULT_INFO =
      "Click left Asc/Des to sort based on Farm ID. \n"
          + "Click right Asc/Des to sort based on total weights.\n"
          + "Export will export data shown to the file system.";

  // Info for monthly report scene
  private static final String MONTHLYREPORT_INFO =
      "Please enter the year or the month. Then click Search. \n"
          + "Clear will clear your input so that you can input new \ninformation";

  // Info for monthly report result scene
  private static final String MONTHLYREPORTRESULT_INFO =
      "Click left Asc/Des to sort based on Farm ID. \n"
          + "Click right Asc/Des to sort based on total weights. \n"
          + "Export will export data shown to the file system.";

  // Info for data range report scene
  private static final String DATERANGEREPORT_INFO =
      "Please enter end date and start date accordingly. Then click search.\n"
          + "Clear will clear your input so that you can input new \ninformation";

  // Info for data range report result scene
  private static final String DATERANGEREPORTRESULT_INFO =
      "Click left Asc/Des to sort based on Farm ID. \n"
          + "Click right Asc/Des to sort based on total weights. \n"
          + "Export will export data shown to the file system.";

  // Warning for IOException
  private static final String IOEXCEPTION_INFO = "File did not load successfully";

  // Warning for IllegalRecordException
  private static final String ILLEGALRECORDEXCEPTION_INFO =
      "The input record format is not correct.\n" + "Please check the record format.";

  // Warning for NUMBERFORMATEXCEPTION
  private static final String NUMBERFORMATEXCEPTION_INFO =
      "The input representing weight cannot be interpreted as an integer.\n"
          + "Please check the weight format.";

  // Warning for IllEGALARUGMENTEXCEPTION
  private static final String ILLEGALARGUMENTEXCEPTION_INFO =
      "There is lack of information in the input.\n" + "Please check the information.";

  // Warning for PARSEEXCEPTION
  private static final String PARSEEXCEPTION_INFO =
      "The input representing date cannot be interpreted.\n" + "Please check the date format.";

  // Warning for FILENOTFOUNDEXCEPTION
  private static final String FILENOTFOUNDEXCEPTION_INFO = "The file can not be found.";

  // Warning for DUPLICATEKEYEXCEPTION
  private static final String DUPLICATEKEYEXCEPTION_INFO =
      "The input already existed.\n" + "Please try other inputs.";

  // Warning for IllegalNullKeyEXCEPTION
  private static final String ILLEGALNULLKEYEXCEPTION_INFO =
      "The input has a null key.\n" + "Please check your inputs.";

  // Warning for KEYNOTFOUNDEXCEPTION
  private static final String KEYNOTFOUNDEXCEPTION_INFO =
      "The key can not be found.\n" + "Please check the data.";

  /**
   * Define the main scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public void getMainScene(Stage primaryStage) {
    if (manager == null) {
      // Initialize the manager instance
      this.manager = new Manager();
    }

    // Three VBox layout management in the BroadPane
    VBox vbox = new VBox(20);
    VBox vboxL = new VBox(15);
    VBox vboxR = new VBox(20);

    // Text field for title
    Text text = getTitle("Welcome to cheese factory management system!\n");

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Create the ImageView Cheese
    this.imageViewCheese = getImageViewCheese();
    // Create the ImageView Brand
    getImageViewBrand();

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
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.MAINMENU);

    // Create a button "Edit Data"
    Button edData = getPolygonButton("Edit Data", 30);
    // Set the event handler
    edData.setOnAction(e -> {
      getEditScene(primaryStage);
    });

    // Initialize the fileChooser
    this.fileChooser = new FileChooser();
    // Add the extension constraint
    this.fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

    // Create a button "Load Data" and add event handler
    Button ldData = getPolygonButton("Load Data", 30);
    ldData.setOnAction(e -> {
      // Create a Choice Dialog to let the user choice
      ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>(loadFunction[0], loadFunction);
      // Set the header text
      choiceDialog.setHeaderText("Functions of Loading data");
      // Set content text
      choiceDialog.setContentText("please select the way to load data");
      // Wait the user choice
      Optional<String> selectResult = choiceDialog.showAndWait();
      // Store the respond
      String choice = choiceDialog.getSelectedItem();

      if (selectResult.isPresent()) {
        // Open the fileChooser
        List<File> selectedFiles = this.fileChooser.showOpenMultipleDialog(primaryStage);
        try {
          // import the file and get the list of records
          for (File selectedFile : selectedFiles) {
            List<Record> list = this.manager.importFile(selectedFile);
            if (choice.equals(loadFunction[1])) {
              // import the list to the internal system
              this.manager.importList(list, choice);
              choice = loadFunction[0];
            } else {
              // import the list to the internal system
              this.manager.importList(list, choice);
            }
          }

          // Create an alert
          Alert alert = new Alert(AlertType.INFORMATION, "Successfully load data");
          // Set the title
          alert.setTitle("Information");
          alert.showAndWait().filter(response -> response == ButtonType.OK);

        } catch (NumberFormatException e1) {
          // display the warning message
          displayWarningMessage(WarningIndex.NUMBERFORMATEXCEPTION);
        } catch (IllegalArgumentException e1) {
          displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
        } catch (IOException e1) {
          displayWarningMessage(WarningIndex.IOEXCEPTION);
        } catch (IllegalRecordException e1) {
          displayWarningMessage(WarningIndex.ILLEGALRECORDEXCEPTION);
        } catch (ParseException e1) {
          displayWarningMessage(WarningIndex.PARSEEXCEPTION);
        } catch (IllegalNullKeyException e1) {
          displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
        } catch (DuplicateKeyException e1) {
          displayWarningMessage(WarningIndex.DUPLICATEKEYEXCEPTION);
        } catch (NullPointerException e1) {

        }
      }
    });

    // Add the vertical box to the center of the root pane
    root.setTop(text);
    // Set the text to the center
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    // Add objects to VBox
    vbox.getChildren().addAll(buttonFarm, buttonAnnual, buttonMonthly, buttonDate);
    vboxL.getChildren().addAll(buttonCloud, edData, this.imageViewBrand);
    vboxR.getChildren().addAll(this.imageViewCheese, ldData);
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
  public void getEditScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("Edit Scene\n");

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
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.ADDDELETECHANGE);

    // Create various layout managers
    VBox vboxLf = new VBox(120);
    HBox md = new HBox(10);
    VBox vboxRt = new VBox(60);

    // Add objects into layouts
    vboxLf.getChildren().addAll(buttonCloud, this.imageViewBrand);
    md.getChildren().addAll(adData, chData);
    vboxRt.getChildren().addAll(returnBn, this.imageViewCheese);
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
  public void getAddDeleteScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("Add Delete Scene\n");

    // Set the Text into the pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    // Create a vBox to store TextFields
    VBox vBox = new VBox(10);

    // Define three TextFields
    TextField farmIDTextField = getInputTextField("Farm ID", 50, 10);
    TextField dateTextField = getInputTextField("Date (YYYY-MM-DD)", 50, 10);
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

    // Create VBox layout and add components
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, this.imageViewCheese);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.ADDDELETERESULT);

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, this.imageViewBrand);
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

    add.setOnAction(e -> {
      try {
        // input the user input and retrieve the record
        Record record = manager.inputRecord(farmIDTextField.getText(), dateTextField.getText(),
            weightTextField.getText());
        // add the records to the internal system
        manager.addRecords(record);

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully add record");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

        farmIDTextField.clear();
        dateTextField.clear();
        weightTextField.clear();

      } catch (IllegalRecordException e1) {
        // Display warning messages
        displayWarningMessage(WarningIndex.ILLEGALRECORDEXCEPTION);
      } catch (NumberFormatException e2) {
        displayWarningMessage(WarningIndex.NUMBERFORMATEXCEPTION);
      } catch (IllegalArgumentException e3) {
        displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
      } catch (ParseException e4) {
        displayWarningMessage(WarningIndex.PARSEEXCEPTION);
      } catch (IllegalNullKeyException e5) {
        displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
      } catch (DuplicateKeyException e6) {
        displayWarningMessage(WarningIndex.DUPLICATEKEYEXCEPTION);
      }
    });

    delete.setOnAction(e -> {
      try {
        // input the user input and retrieve the record
        Record record = manager.inputRecord(farmIDTextField.getText(), dateTextField.getText(),
            weightTextField.getText());
        // remove the records from the internal system
        boolean r = manager.removeRecords(record);
        if (!r) {
          throw new KeyNotFoundException("Record can not be found");
        }

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully delete record");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

        farmIDTextField.clear();
        dateTextField.clear();
        weightTextField.clear();

      } catch (IllegalRecordException e1) {
        // Display warning messages
        displayWarningMessage(WarningIndex.ILLEGALRECORDEXCEPTION);
      } catch (NumberFormatException e2) {
        displayWarningMessage(WarningIndex.NUMBERFORMATEXCEPTION);
      } catch (IllegalArgumentException e3) {
        displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
      } catch (ParseException e4) {
        displayWarningMessage(WarningIndex.PARSEEXCEPTION);
      } catch (IllegalNullKeyException e5) {
        displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
      } catch (KeyNotFoundException e6) {
        displayWarningMessage(WarningIndex.KEYNOTFOUNDEXCEPTION);
      }
    });

    // Set the event handler
    clear.setOnAction(e -> {
      // Clear all the TextFields
      farmIDTextField.clear();
      dateTextField.clear();
      weightTextField.clear();
    });

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Change Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public void getChangeScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("Change Scene\n");

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
    TextField oldDate = getInputTextField("Date(YYYY-MM-DD)", 90, 10);
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
    TextField newDate = getInputTextField("Date(YYYY-MM-DD)", 90, 10);
    TextField newWeight = getInputTextField("New Weight", 90, 10);
    newWeight.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        change.fire();
      }
    });

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

    // Create VBox layout and add components
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, this.imageViewCheese);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.CHANGERESULT);

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, this.imageViewBrand);
    root.setLeft(sceneLeft);

    change.setOnAction(e -> {
      try {
        // input the user input and retrieve the record
        Record oldRecord =
            manager.inputRecord(oldFarmID.getText(), oldDate.getText(), oldWeight.getText());
        Record newRecord =
            manager.inputRecord(newFarmID.getText(), newDate.getText(), newWeight.getText());
        // Change the records from the internal system
        boolean r = manager.changeRecords(oldRecord, newRecord);
        if (!r) {
          throw new KeyNotFoundException("Record can not be found");
        }

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully change record");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

        newFarmID.clear();
        newDate.clear();
        newWeight.clear();
        oldFarmID.clear();
        oldDate.clear();
        oldWeight.clear();

      } catch (IllegalNullKeyException e1) {
        // Display the warning message
        displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
      } catch (DuplicateKeyException e1) {
        displayWarningMessage(WarningIndex.DUPLICATEKEYEXCEPTION);
      } catch (NumberFormatException e1) {
        displayWarningMessage(WarningIndex.NUMBERFORMATEXCEPTION);
      } catch (IllegalArgumentException e1) {
        displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
      } catch (IllegalRecordException e1) {
        displayWarningMessage(WarningIndex.ILLEGALRECORDEXCEPTION);
      } catch (ParseException e1) {
        displayWarningMessage(WarningIndex.PARSEEXCEPTION);
      } catch (KeyNotFoundException e1) {
        displayWarningMessage(WarningIndex.KEYNOTFOUNDEXCEPTION);
      }
    });

    // Set the event handler
    clear.setOnAction(e -> {
      // Clear all the TextFields
      newFarmID.clear();
      newDate.clear();
      newWeight.clear();
      oldFarmID.clear();
      oldDate.clear();
      oldWeight.clear();
    });

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Farm Report Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public void getFarmReportScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Define layout managers
    HBox hboxUp = new HBox(20);
    HBox hboxDown = new HBox(35);
    VBox vboxL = new VBox(60);
    VBox vboxR = new VBox(20);
    VBox vboxC = new VBox(20);

    // Text field for title
    Text text = getTitle("Farm Report\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.FARMREPORT);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create a Label and TextField
    Label farmID = new Label("FarmID: ");
    TextField inputFarmID = getInputTextField("FarmID", 110, 10);
    Label year = new Label("Year: ");
    TextField inputYear = getInputTextField("Year", 110, 10);

    // Create the Button and set event Handler
    Button search = getOvalButton("Search", 4, 2);
    search.setStyle("-fx-base: navajowhite;");
    search.setOnAction(e -> {
      try {
        // Check the input field
        if (inputFarmID.getText().equals("") || inputYear.getText().equals("")) {
          throw new IllegalArgumentException("input is blank");
        }

        // Create a DateFormat
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        // Add the year to a String
        String date = inputYear.getText() + "-01-01";
        // Parse the String through the format
        format.parse(date);

        getFarmResultScene(primaryStage, inputFarmID.getText(), inputYear.getText());
      } catch (IllegalArgumentException e1) {
        // Display warning message
        displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
      } catch (ParseException e1) {
        displayWarningMessage(WarningIndex.PARSEEXCEPTION);
      }
    });

    inputYear.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        search.fire();
      }
    });

    Button clear = getOvalButton("Clear", 4, 2);
    clear.setStyle("-fx-base: gold;");
    clear.setOnAction(e -> {
      // Clear all the TextFields
      inputFarmID.clear();
      inputYear.clear();
    });

    // Create HBox layout and add components
    HBox sceneBottom = new HBox(20);
    sceneBottom.setAlignment(Pos.CENTER);
    sceneBottom.getChildren().addAll(search, clear);

    // Set the main pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    hboxUp.getChildren().addAll(farmID, inputFarmID);
    hboxDown.getChildren().addAll(year, inputYear);
    vboxC.getChildren().addAll(hboxUp, hboxDown);
    vboxL.getChildren().addAll(buttonCloud, this.imageViewBrand);
    vboxR.getChildren().addAll(returnButton, this.imageViewCheese);
    root.setLeft(vboxL);
    root.setCenter(vboxC);
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
   * @param farmID       - the input farmID
   * @param year         - the input year
   */
  @SuppressWarnings("unchecked")
  public void getFarmResultScene(Stage primaryStage, String farmID, String year) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Define layout managers
    HBox hbox = new HBox();
    VBox vboxCL = new VBox(20);
    VBox vboxCR = new VBox();
    VBox vboxL = new VBox(30);
    VBox vboxR = new VBox(20);

    // Text field for title
    Text text = getTitle("Farm Report Result\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.FARMREPORTRESULT);

    // Create the ImageView Cheese
    ImageView imageViewCheese2 = getImageViewCheese();
    imageViewCheese2.setFitHeight(50);
    imageViewCheese2.setFitWidth(50);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      this.firstFlag = false;
      this.secondFlag = false;
      getFarmReportScene(primaryStage);
    });

    // Define a label
    Label title = new Label("\nTOTAL MILK\n WEIGHT");

    // The result to be displayed in the list
    result = new ArrayList<ArrayList<String>>();

    try {
      // Retrieve the result
      result = manager.getFarmReport(farmID,
          new GregorianCalendar(Integer.valueOf(year), 1, 1, 0, 0, 0));
      if (result.size() != 0)
        summary = result.remove(result.size() - 1);
    } catch (NumberFormatException e1) {
      // Display the warning message
      displayWarningMessage(WarningIndex.NUMBERFORMATEXCEPTION);
    } catch (IllegalNullKeyException e1) {
      displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
    }

    // Summary button
    Button getSummary = getOvalButton("Summary", 3.2, 3.2);
    getSummary.setStyle("-fx-base: gold;");
    getSummary.setOnAction(e -> {
      displaySummary(true);
    });

    // Create two circles to display the result
    Circle circle1 = new Circle(40.0);
    circle1.setFill(Color.rgb(255, 255, 0, 0.7));
    circle1.setStroke(Color.BLACK);
    Text amount = new Text(result.get(0).get(1));
    text.setBoundsType(TextBoundsType.VISUAL);
    StackPane stack = new StackPane();
    stack.getChildren().addAll(circle1, amount);

    Circle circle2 = new Circle(40.0);
    circle2.setFill(Color.rgb(255, 255, 0, 0.7));
    circle2.setStroke(Color.BLACK);
    Text percent = new Text(result.get(0).get(2));
    percent.setBoundsType(TextBoundsType.VISUAL);
    StackPane stack2 = new StackPane();
    stack2.getChildren().addAll(circle2, percent);

    // Add the components to the VBox
    vboxCL.getChildren().addAll(title, stack, stack2);

    data = FXCollections.observableArrayList();
    for (int i = 0; i < 12; i++) {
      data.add(new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
    }

    // Create table view
    TableView<ResultRecord> table = new TableView<ResultRecord>();

    // Create the first column
    TableColumn<ResultRecord, String> monthRow = new TableColumn<ResultRecord, String>("Month");

    // Set the width
    monthRow.setPrefWidth(90);
    monthRow.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnOne"));

    // Create the second column
    TableColumn<ResultRecord, String> weightRow = new TableColumn<ResultRecord, String>("Weight");

    // Set the width
    weightRow.setPrefWidth(90);
    weightRow.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnTwo"));

    // Create the third column
    TableColumn<ResultRecord, String> percentRow = new TableColumn<ResultRecord, String>("Percent");

    // Set the width
    percentRow.setPrefWidth(90);
    percentRow.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnThree"));

    // Set the items
    table.setItems(data);

    // Add the columns to the table
    table.getColumns().addAll(monthRow, weightRow, percentRow);
    table.setMaxSize(300, 200);

    // Add handler
    table.setOnMouseClicked(e -> {
      amount.setText(table.getSelectionModel().getSelectedItem().getColumnTwo());
      percent.setText(table.getSelectionModel().getSelectedItem().getColumnThree());
    });

    // Initialize the fileChooser
    this.fileChooser = new FileChooser();
    // Add the extension constraint
    this.fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

    // Create two buttons and add event handler
    Button farmWeightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmWeightSortButton.setStyle("-fx-base: navajowhite;");
    farmWeightSortButton.setOnAction(e -> {
      if (!firstFlag) {
        // Sort the arraylist ascending
        Collections.sort(result, (a, b) -> {
          if (Long.parseLong(a.get(1)) - (Long.parseLong(b.get(1))) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - (Long.parseLong(b.get(1))) < 0)
            return -1;
          else
            return 0;
        });

        // Flip the flag
        firstFlag = !firstFlag;
      } else {
        // Sort the arraylist descending
        Collections.sort(result, (a, b) -> {
          if (Long.parseLong(a.get(1)) - (Long.parseLong(b.get(1))) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - (Long.parseLong(b.get(1))) < 0)
            return -1;
          else
            return 0;
        });

        // Flip the flag
        firstFlag = !firstFlag;
      }

      // Add the new data to table
      data = FXCollections.observableArrayList();
      for (int i = 0; i < 12; i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }

      // Set the items
      table.setItems(data);
    });

    // Create exprot button
    Button exportButton = getOvalButton("Export", 3.2, 2);
    exportButton.setStyle("-fx-base: navajowhite;");
    exportButton.setOnAction(e -> {
      File selectedFile = this.fileChooser.showSaveDialog(primaryStage);
      try {
        manager.exportReport(result, selectedFile, 0);

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully export result");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

      } catch (FileNotFoundException e1) {

      }
    });

    Text bText = new Text("                ");
    // Add to the layout
    HBox buttonBox = new HBox(20);
    buttonBox.getChildren().addAll(bText, farmWeightSortButton, exportButton);

    vboxCR.getChildren().addAll(table, buttonBox);

    // Set the main pane
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    hbox.getChildren().addAll(vboxCL, vboxCR);
    vboxL.getChildren().addAll(buttonCloud, getSummary, this.imageViewBrand);
    vboxR.getChildren().addAll(returnButton, imageViewCheese2);
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
  public void getAnnualReportScene(Stage primaryStage) {
    // Text field for title
    Text text = getTitle("Annual Report Scene\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.ANNUALREPORT);

    // Create the return button and set the event handler
    Button returnBn = getReturnButton();
    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create two buttons and set event handler
    Button search = getOvalButton("Search", 3.2, 2);
    search.setStyle("-fx-base: navajowhite;");

    Button clear = getOvalButton("Clear", 3.2, 2);
    clear.setStyle("-fx-base: gold;");

    // Create TextField and Text
    TextField textfield = new TextField();
    Text year = new Text();
    year.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    year.setText("Year : ");
    textfield.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        System.out.println("bb");
        search.fire();
      }
    });

    clear.setOnAction(e -> {
      textfield.clear();
    });

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

    vboxLf.getChildren().addAll(buttonCloud, this.imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, this.imageViewCheese);
    vboxmd.getChildren().addAll(hbt, hbb);
    vboxmd.setAlignment(Pos.CENTER);

    // Set the main pane
    root.setLeft(vboxLf);
    root.setCenter(vboxmd);
    root.setRight(vboxRt);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    search.setOnAction(e -> {
      try {
        if (textfield.getText().equals("")) {
          throw new IllegalNullKeyException();
        }
        int i = Integer.parseInt(textfield.getText());
        getAnnualResultScene(primaryStage, i);
      } catch (NumberFormatException e1) {
        displayWarningMessage(WarningIndex.NUMBERFORMATEXCEPTION);
      } catch (IllegalNullKeyException e2) {
        // Display warning message
        displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
      }
    });

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Annual Result Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   * @param year         - the input year
   */
  @SuppressWarnings("unchecked")
  public void getAnnualResultScene(Stage primaryStage, int year) {
    // Text field for title
    Text text = getTitle("Annual Result Scene\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.ANNUALREPORTRESULT);

    // Create the return button and set the event handler
    Button returnBn = getReturnButton();
    returnBn.setOnAction(e -> {
      this.firstFlag = false;
      this.secondFlag = false;
      getAnnualReportScene(primaryStage);
    });

    try {
      // create new calendar
      GregorianCalendar date = new GregorianCalendar(year, 1, 1, 0, 0, 0);
      // set the data
      result = manager.getAnnualReport(date);
    } catch (IllegalNullKeyException e1) {
      // display warning
      displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
    }


    if (result.size() > 0) {
      summary = result.remove(result.size() - 1);
    }

    // add data from result into the data list
    data = FXCollections.observableArrayList();
    for (int i = 0; i < result.size(); i++) {
      data.add(new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
    }

    // this is the tableView of the final shown table
    TableView<ResultRecord> table = new TableView<ResultRecord>();

    // Create the first column
    TableColumn<ResultRecord, String> firstCol = new TableColumn<ResultRecord, String>("FarmID");

    // Set the width
    firstCol.setPrefWidth(90);
    firstCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnOne"));

    // Create the second column
    TableColumn<ResultRecord, String> secondCol =
        new TableColumn<ResultRecord, String>("Total Weight");

    // Set the width
    secondCol.setPrefWidth(90);
    secondCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnTwo"));

    // Create the three column
    TableColumn<ResultRecord, String> thirdCol = new TableColumn<ResultRecord, String>("Percent");

    // Set the width
    thirdCol.setPrefWidth(90);
    thirdCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnThree"));

    // Set the items
    table.setItems(data);

    // Add the columns to table
    table.getColumns().addAll(firstCol, secondCol, thirdCol);
    table.setMaxSize(300, 200);

    // Initialize the fileChooser
    this.fileChooser = new FileChooser();
    // Add the extension constraint
    this.fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

    Button getSummary = getOvalButton("Summary", 3.2, 3.2);
    getSummary.setStyle("-fx-base: gold;");

    getSummary.setOnAction(e -> {
      displaySummary(false);
    });

    // Create three buttons and add event handler
    Button farmIDSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmIDSortButton.setStyle("-fx-base: navajowhite;");

    // set the action of the farmID
    farmIDSortButton.setOnAction(e -> {
      // when the order of the farmID is the descending order
      if (!this.firstFlag) {
        // sort it into ascending order
        Collections.sort(result, (a, b) -> {
          if (a.get(0).compareTo(b.get(0)) != 0)
            return a.get(0).compareTo(b.get(0));
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return 0;
        });
        // set the flag to be true
        firstFlag = true;
      } else {
        // sort it into ascending order
        Collections.sort(result, (b, a) -> {
          if (a.get(0).compareTo(b.get(0)) != 0)
            return a.get(0).compareTo(b.get(0));
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return 0;
        });
        // set result to be reversed
        firstFlag = false;
      }

      data.clear();
      // set data to be new order of result
      for (int i = 0; i < result.size(); i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }
      // set the table with new data
      table.setItems(data);
    });

    Button weightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    weightSortButton.setOnAction(e -> {

      // when the order of the farmID is the descending order
      if (!this.secondFlag) {
        // sort it into ascending order
        Collections.sort(result, (a, b) -> {
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return a.get(0).compareTo(b.get(0));
        });
        // set the flag to be true
        secondFlag = true;
      } else {
        // sort it into ascending order
        Collections.sort(result, (b, a) -> {
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return a.get(0).compareTo(b.get(0));
        });
        secondFlag = false;
      }

      data.clear();
      // set data to be new order of result
      for (int i = 0; i < result.size(); i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }
      // set the table with new data
      table.setItems(data);
    });

    Button exportButton = getOvalButton("Export", 3.2, 2);
    exportButton.setStyle("-fx-base: gold;");
    exportButton.setOnAction(e -> {
      File selectedFile = this.fileChooser.showSaveDialog(primaryStage);
      try {
        manager.exportReport(result, selectedFile, 1);

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully export result");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

      } catch (FileNotFoundException e1) {

      }
    });

    // Create other layout managers and add components
    HBox sortBox = new HBox(25);
    sortBox.getChildren().addAll(farmIDSortButton, weightSortButton);

    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(table, sortBox);

    VBox vboxLf = new VBox(25);
    VBox vboxRt = new VBox(25);

    vboxLf.getChildren().addAll(buttonCloud, getSummary, this.imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, this.imageViewCheese, exportButton);

    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Set the main pane
    root.setLeft(vboxLf);
    root.setRight(vboxRt);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    firstFlag = false;
    secondFlag = false;

    // Add the scene to stage
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  /**
   * Define the Monthly Report Scene of the GUI
   * 
   * @param primaryStage - the stage that displays the scene
   */
  public void getMonthlyReportScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text title = getTitle("Monthly Report Scene\n");
    // Add to the pane
    root.setTop(title);
    BorderPane.setAlignment(title, Pos.TOP_CENTER);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.MONTHLYREPORT);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    // Create TextField and Text
    TextField inputYear = new TextField();
    Text yearPrompt = new Text();
    yearPrompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    yearPrompt.setText("Year: ");

    TextField inputMonth = new TextField();
    Text monthPrompt = new Text();
    monthPrompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    monthPrompt.setText("Month: ");

    // Create two buttons and set event handler
    Button searchButton = getOvalButton("Search", 3.2, 2);
    searchButton.setStyle("-fx-base: navajowhite;");
    searchButton.setOnAction(e -> {
      try {
        // Check the input field
        if (inputYear.getText().equals("") || inputMonth.getText().equals(""))
          throw new IllegalArgumentException("input is blank");

        // Create a DateFormat
        DateFormat format = new SimpleDateFormat("yyyy/MM");
        format.setLenient(false);
        // Add the year to a String
        String date = inputYear.getText() + "/" + inputMonth.getText();
        // Parse the String through the format
        format.parse(date);

        getMonthlyResultScene(primaryStage, inputYear.getText(), inputMonth.getText());
      } catch (IllegalArgumentException e1) {
        // Display warning message
        displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
      } catch (ParseException e1) {
        displayWarningMessage(WarningIndex.PARSEEXCEPTION);
      } catch (IllegalNullKeyException e1) {
      }
    });

    inputMonth.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        searchButton.fire();
      }
    });

    Button clearButton = getOvalButton("Clear", 3.2, 2);
    clearButton.setStyle("-fx-base: gold;");
    clearButton.setOnAction(e -> {
      inputYear.clear();
      inputMonth.clear();
    });

    // Create other layout managers
    HBox topBox = new HBox(20);
    HBox bottomBox = new HBox(30);
    VBox leftBox = new VBox(130);
    VBox rightBox = new VBox(30);
    VBox centerBox = new VBox(100);

    // Add components to layout managers
    topBox.getChildren().addAll(yearPrompt, inputYear, monthPrompt, inputMonth);
    bottomBox.getChildren().addAll(searchButton, clearButton);
    topBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);

    leftBox.getChildren().addAll(buttonCloud, this.imageViewBrand);
    rightBox.getChildren().addAll(returnButton, this.imageViewCheese);
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
   * @param year         - the input year
   * @param month        - the input month
   * 
   * @throws IllegalNullKeyException
   * @throws ParseException
   */
  @SuppressWarnings("unchecked")
  public void getMonthlyResultScene(Stage primaryStage, String year, String month)
      throws IllegalNullKeyException, ParseException {
    // Text field for title
    Text text = getTitle("Monthly Result Scene\n");

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.MONTHLYREPORTRESULT);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      this.firstFlag = false;
      this.secondFlag = false;
      getMonthlyReportScene(primaryStage);
    });

    // Initialize the fileChooser
    this.fileChooser = new FileChooser();
    // Add the extension constraint
    this.fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

    // Create export buttons and add event handler
    Button export = getOvalButton("Export", 3.2, 2);
    export.setStyle("-fx-base: gold;");
    export.setOnAction(e -> {
      File selectedFile = this.fileChooser.showSaveDialog(primaryStage);
      try {
        manager.exportReport(result, selectedFile, 1);

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully export result");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

      } catch (FileNotFoundException e1) {

      }
    });

    // Get the date
    String originalDate = year + "-" + month;
    DateFormat df = new SimpleDateFormat("yyyy-MM");
    Date date = df.parse(originalDate);
    GregorianCalendar g = (GregorianCalendar) GregorianCalendar.getInstance();
    g.setTime(date);
    try {
      // Get the result
      result = manager.getMonthlyReport(g);
      // Get the summary
      if (result.size() != 0)
        summary = result.remove(result.size() - 1);
    } catch (IllegalNullKeyException e) {
      displayWarningMessage(WarningIndex.ILLEGALNULLKEYEXCEPTION);
    }
    // Add the result to data
    data = FXCollections.observableArrayList();
    for (ArrayList<String> element : result)
      data.add(new ResultRecord(element.get(0), element.get(1), element.get(2)));

    // Create a table view
    TableView<ResultRecord> table = new TableView<ResultRecord>();

    // Create first columns
    TableColumn<ResultRecord, String> firstCol = new TableColumn<ResultRecord, String>("Farm ID");

    // Set the width
    firstCol.setPrefWidth(90);
    firstCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnOne"));

    // Create second columns
    TableColumn<ResultRecord, String> secondCol = new TableColumn<ResultRecord, String>("Weight");

    // Set the width
    secondCol.setPrefWidth(90);
    secondCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnTwo"));

    // Create three columns
    TableColumn<ResultRecord, String> thirdCol =
        new TableColumn<ResultRecord, String>("Percentage");

    // Set the width
    thirdCol.setPrefWidth(90);
    thirdCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnThree"));
    table.setItems(data);

    // Add three columns
    table.getColumns().addAll(firstCol, secondCol, thirdCol);
    table.setMaxSize(300, 200);

    // Create HBox layout and add components
    HBox list = new HBox();
    list.getChildren().addAll(table);

    // Create two buttons
    Button farmIDSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmIDSortButton.setStyle("-fx-base: navajowhite;");

    Button weightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    // set the action of the farmID
    farmIDSortButton.setOnAction(e -> {
      // when the order of the farmID is the descending order
      if (!this.firstFlag) {
        // sort it into ascending order
        Collections.sort(result, (a, b) -> {
          if (a.get(0).compareTo(b.get(0)) != 0)
            return a.get(0).compareTo(b.get(0));
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return 0;
        });
        // set the flag to be true
        firstFlag = true;
      } else {
        // Sort Descending
        Collections.sort(result, (b, a) -> {
          if (a.get(0).compareTo(b.get(0)) != 0)
            return a.get(0).compareTo(b.get(0));
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return 0;
        });
        firstFlag = false;
      }

      data.clear();
      // set data to be new order of result
      for (int i = 0; i < result.size(); i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }
      // set the table with new data
      table.setItems(data);
    });

    weightSortButton.setOnAction(e -> {

      // when the order of the farmID is the descending order
      if (!this.secondFlag) {
        // sort it into ascending order
        Collections.sort(result, (a, b) -> {
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return a.get(0).compareTo(b.get(0));
        });
        // set the flag to be true
        secondFlag = true;
      } else {
        // Sort in descending order
        Collections.sort(result, (b, a) -> {
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return a.get(0).compareTo(b.get(0));
        });
        secondFlag = false;
      }

      data.clear();
      // set data to be new order of result
      for (int i = 0; i < result.size(); i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }
      // set the table with new data
      table.setItems(data);
    });

    // Create HBox and VBox layout and add components
    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);

    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(list, sortButton);

    VBox vboxLf = new VBox(25);
    VBox vboxRt = new VBox(25);
    // Summary button
    Button getSummary = getOvalButton("Summary", 3.2, 3.2);
    getSummary.setStyle("-fx-base: gold;");
    getSummary.setOnAction(e -> {
      displaySummary(false);
    });

    vboxLf.getChildren().addAll(buttonCloud, getSummary, this.imageViewBrand);
    vboxRt.getChildren().addAll(returnButton, this.imageViewCheese, export);

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
  public void getDateReportScene(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Text field for title
    Text text = getTitle("Date Report Scene\n");

    // Define two TextFields for start date and end date
    TextField endDateField = getInputTextField("Enter end Date (Format YYYY-MM-DD)", 50, 10);
    TextField startDateField = getInputTextField("Enter start Date (Format YYYY-MM-DD)", 50, 10);

    // Create VBox layout and add components
    VBox vBox = new VBox(20);
    vBox.getChildren().addAll(startDateField, endDateField);
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

    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, this.imageViewCheese);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.DATERANGEREPORT);

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, this.imageViewBrand);

    root.setLeft(sceneLeft);

    // Define two buttons for search and clear
    Button search = getOvalButton("Search", 3.2, 2);
    search.setStyle("-fx-base: moccasin;");
    search.setOnAction(e -> {
      try {
        // Check the input field
        if (startDateField.getText().equals("") || endDateField.getText().equals("")) {
          throw new IllegalArgumentException("input is blank");
        }

        // Create a DateFormat
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // Parse the String through the format
        format.setLenient(false);
        // Add the year to a String
        String startDate = startDateField.getText();
        // Parse the String through the format
        format.parse(startDate);
        String endDate = endDateField.getText();
        format.parse(endDate);
        getDateResultScene(primaryStage, startDate, endDate);

      } catch (IllegalArgumentException e1) {
        // Display warning message
        displayWarningMessage(WarningIndex.ILLEGALARGUMENTEXCEPTION);
      } catch (ParseException e1) {
        displayWarningMessage(WarningIndex.PARSEEXCEPTION);
      }
    });

    // Set handler
    endDateField.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        search.fire();
      }
    });

    Button clear = getOvalButton("Clear", 3.2, 2);
    clear.setStyle("-fx-base: gold;");
    clear.setOnAction(e -> {
      endDateField.clear();
      startDateField.clear();
    });

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
   * @param start        - the start date
   * @param end          - the end date
   */
  @SuppressWarnings("unchecked")
  public void getDateResultScene(Stage primaryStage, String start, String end) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Text field for title
    Text text = getTitle("Date Result Scene\n");

    // Add the text to the root pane
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    GregorianCalendar startCalendar;
    GregorianCalendar endCalendar;
    // Parse String to date
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    Date startDate = null;
    try {
      startDate = df.parse(start);
    } catch (ParseException e2) {
      this.displayWarningMessage(WarningIndex.PARSEEXCEPTION);
    }
    startCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
    startCalendar.setTime(startDate);

    Date endDate = null;
    try {
      endDate = df.parse(end);
    } catch (ParseException e2) {
      this.displayWarningMessage(WarningIndex.PARSEEXCEPTION);
    }
    endCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
    endCalendar.setTime(endDate);

    // Get the result
    try {
      result = manager.getDateReport(startCalendar, endCalendar);
      if (result.size() != 0)
        summary = result.remove(result.size() - 1);
    } catch (IllegalNullKeyException e1) {
      displayWarningMessage(WarningIndex.PARSEEXCEPTION);
    }

    // Add to data
    data = FXCollections.observableArrayList();
    for (int i = 0; i < result.size(); i++) {
      data.add(new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
    }

    // table
    TableView<ResultRecord> table = new TableView<ResultRecord>();
    // First column of the table
    TableColumn<ResultRecord, String> firstCol = new TableColumn<ResultRecord, String>("Farm ID");
    firstCol.setPrefWidth(90);
    firstCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnOne"));
    // Second column of the table
    TableColumn<ResultRecord, String> secondCol = new TableColumn<ResultRecord, String>("Weight");
    secondCol.setPrefWidth(90);
    secondCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnTwo"));
    // Third column of the table
    TableColumn<ResultRecord, String> thirdCol = new TableColumn<ResultRecord, String>("Percent");
    thirdCol.setPrefWidth(90);
    thirdCol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("columnThree"));
    table.setItems(data);
    table.getColumns().addAll(firstCol, secondCol, thirdCol);
    table.setMaxSize(300, 200);

    // Create HBox layout and add components
    HBox listHBox = new HBox();
    listHBox.getChildren().add(table);

    // farmID sort button
    Button farmIDSortButton = getOvalButton("Asc/Des", 3.2, 2);
    farmIDSortButton.setStyle("-fx-base: moccasin;");
    // Create three buttons and add event handler
    farmIDSortButton.setOnAction(e -> {
      if (!this.firstFlag) {
        // Sort ascending
        Collections.sort(result, (a, b) -> {
          if (a.get(0).compareTo(b.get(0)) != 0)
            return a.get(0).compareTo(b.get(0));
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return 0;
        });
        firstFlag = true;
      } else {
        // Sort descending
        Collections.sort(result, (b, a) -> {
          if (a.get(0).compareTo(b.get(0)) != 0)
            return a.get(0).compareTo(b.get(0));
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return 0;
        });
        firstFlag = false;
      }
      // Clear the old sorted data
      data.clear();
      // Re-assign data into the result
      for (int i = 0; i < result.size(); i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }
      table.setItems(data);
    });

    // weight sort button
    Button weightSortButton = getOvalButton("Asc/Des", 3.2, 2);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    weightSortButton.setOnAction(e -> {

      if (this.secondFlag) {
        // Sort ascending
        Collections.sort(result, (a, b) -> {
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return a.get(0).compareTo(b.get(0));
        });
        secondFlag = false;
      } else {
        // Sort descending
        Collections.sort(result, (b, a) -> {
          if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) > 0)
            return 1;
          else if (Long.parseLong(a.get(1)) - Long.parseLong(b.get(1)) < 0)
            return -1;
          else
            return a.get(0).compareTo(b.get(0));
        });
        secondFlag = true;
      }

      // Clear the data
      data.clear();

      // Add the data to the table
      for (int i = 0; i < result.size(); i++) {
        data.add(
            new ResultRecord(result.get(i).get(0), result.get(i).get(1), result.get(i).get(2)));
      }
      table.refresh();
    });

    // Create HBox and VBox layout and add components
    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);
    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(listHBox, sortButton);

    // Create the return button and set the event handler
    Button returnButton = getReturnButton();
    returnButton.setOnAction(e -> {
      this.firstFlag = false;
      this.secondFlag = false;
      getDateReportScene(primaryStage);
    });

    // Initialize the fileChooser
    this.fileChooser = new FileChooser();
    // Add the extension constraint
    this.fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

    // Create Button for export and add event handler
    Button exportButton = getOvalButton("Export", 3.2, 2);
    exportButton.setStyle("-fx-base: gold;");
    exportButton.setOnAction(e -> {
      File selectedFile = this.fileChooser.showSaveDialog(primaryStage);
      try {
        manager.exportReport(result, selectedFile, 1);

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION, "Successfully export result");
        // Set the title
        alert.setTitle("Information");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

      } catch (FileNotFoundException e1) {

      }
    });

    // Create VBox layout and add components
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, this.imageViewCheese, exportButton);
    root.setRight(sceneRight);

    // Create the ToggleButton buttonCloud
    ToggleButton buttonCloud = getbuttonCloud(SceneIndex.DATERANGEREPORTRESULT);

    // Create VBox layout and add components
    VBox sceneLeft = new VBox(25);
    // Summary button
    Button getSummary = getOvalButton("Summary", 3.2, 3.2);
    getSummary.setStyle("-fx-base: gold;");
    getSummary.setOnAction(e -> {
      // DisplayWarningMessage
      displaySummary(false);
    });
    sceneLeft.getChildren().addAll(buttonCloud, getSummary, this.imageViewBrand);

    // Set the main pane
    root.setLeft(sceneLeft);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    firstFlag = false;
    secondFlag = false;
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
  private Text getTitle(String content) {
    // Create a new Text
    Text text = new Text(content);
    // Set the font
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    return text;
  }

  /**
   * Create the ImageView for the Brand picture if there is no cheese picture loaded. Otherwise
   * return the already loaded cheese picture
   * 
   * @return the ImageView Cheese
   */
  private ImageView getImageViewCheese() {
    // Create a file to read image
    File inputCheese = new File("cheese.png");
    // Put the file into Image
    Image imageCheese = new Image(inputCheese.toURI().toString());
    // Put Image to ImageView
    ImageView imageViewCheese2 = new ImageView();
    imageViewCheese2.setImage(imageCheese);
    // Set the size
    imageViewCheese2.setFitHeight(100);
    imageViewCheese2.setFitWidth(100);

    return imageViewCheese2;
  }

  /**
   * Create the ImageView for the Brand picture if there is no brand picture loaded. Otherwise
   * return the already loaded brand picture
   * 
   * @return the ImageView Brand
   */
  private ImageView getImageViewBrand() {
    // Create a file to read image
    File inputBrand = new File("brand.png");
    // Put the file into Image
    Image imageBrand = new Image(inputBrand.toURI().toString());
    // Put Image to ImageView
    this.imageViewBrand = new ImageView();
    this.imageViewBrand.setImage(imageBrand);
    // Set the size
    this.imageViewBrand.setFitHeight(100);
    this.imageViewBrand.setFitWidth(100);

    return this.imageViewBrand;
  }

  /**
   * Create the ToggleButton for Help
   * 
   * @return the ToggleButton for Help
   */
  private ToggleButton getbuttonCloud(SceneIndex i) {
    // Create a image
    Image image = new Image("cloud.png", 40, 30, false, false);
    // Put Image to ImageView
    ImageView imageView = new ImageView(image);
    // Create the button
    ToggleButton buttonCloud = new ToggleButton("", imageView);
    buttonCloud.setOnAction(event -> {
      Alert alert = new Alert(AlertType.INFORMATION, getSceneInstruction(i));
      alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      alert.setTitle("Instructions");
      alert.showAndWait().filter(response -> response == ButtonType.OK);
    });

    return buttonCloud;
  }

  /**
   * Given the SceneIndex, return the corresponding instruction string.
   * 
   * @param i - the Scene index
   * @return a String of instruction
   */
  private String getSceneInstruction(SceneIndex i) {
    switch (i) {
      case MAINMENU:
        return MAINMENU_INFO;
      case ADDDELETECHANGE:
        return ADDDELETECHANGE_INFO;
      case ADDDELETERESULT:
        return ADDDELETERESULT_INFO;
      case CHANGERESULT:
        return CHANGERESULT_INFO;
      case FARMREPORT:
        return FARMREPORT_INFO;
      case FARMREPORTRESULT:
        return FARMREPORTRESULT_INFO;
      case ANNUALREPORT:
        return ANNUALREPORT_INFO;
      case ANNUALREPORTRESULT:
        return ANNUALREPORTRESULT_INFO;
      case MONTHLYREPORT:
        return MONTHLYREPORT_INFO;
      case MONTHLYREPORTRESULT:
        return MONTHLYREPORTRESULT_INFO;
      case DATERANGEREPORT:
        return DATERANGEREPORT_INFO;
      case DATERANGEREPORTRESULT:
        return DATERANGEREPORTRESULT_INFO;
      default:
        return "";
    }
  }

  /**
   * Given the WarningIndex, return the corresponding warning string.
   * 
   * @param i - the warning index
   * @return a String of warning message
   */
  private String getExceptionWarning(WarningIndex i) {
    switch (i) {
      case IOEXCEPTION:
        return IOEXCEPTION_INFO;
      case ILLEGALRECORDEXCEPTION:
        return ILLEGALRECORDEXCEPTION_INFO;
      case NUMBERFORMATEXCEPTION:
        return NUMBERFORMATEXCEPTION_INFO;
      case ILLEGALARGUMENTEXCEPTION:
        return ILLEGALARGUMENTEXCEPTION_INFO;
      case PARSEEXCEPTION:
        return PARSEEXCEPTION_INFO;
      case FILENOTFOUNDEXCEPTION:
        return FILENOTFOUNDEXCEPTION_INFO;
      case DUPLICATEKEYEXCEPTION:
        return DUPLICATEKEYEXCEPTION_INFO;
      case ILLEGALNULLKEYEXCEPTION:
        return ILLEGALNULLKEYEXCEPTION_INFO;
      case KEYNOTFOUNDEXCEPTION:
        return KEYNOTFOUNDEXCEPTION_INFO;
      default:
        return "";
    }
  }

  /**
   * Display the Warning Message
   * 
   * @param i - the index of the message
   */
  private void displayWarningMessage(WarningIndex i) {
    // Create an alert
    Alert alert = new Alert(AlertType.WARNING, getExceptionWarning(i));
    // Set the title
    alert.setTitle("Warning");
    alert.showAndWait().filter(response -> response == ButtonType.OK);
  }

  /**
   * Create the Polygon Button
   * 
   * @param text - the text displayed on the button
   * @param r    - the radius
   * 
   * @return the Polygon Button
   */
  private Button getPolygonButton(String text, double r) {
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
  private Button getReturnButton() {
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
   * @param text   - the text displayed on the button\
   * @param length - the length of the oval
   * @param width  - the width of the oval
   * 
   * @return the Oval Button
   */
  private Button getOvalButton(String text, double length, double width) {
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
  private TextField getInputTextField(String text, int length, int height) {
    // Create a textField
    TextField textField = new TextField();
    textField.setPromptText(text);

    // Set their size
    textField.setPrefSize(length, height);
    return textField;
  }

  /**
   * display summary in a new window
   * 
   * @param farmReport - the boolean value for whether it is farm report or not
   */
  private void displaySummary(boolean farmReport) {
    // Create an alert
    Alert alert = new Alert(AlertType.INFORMATION);
    // Set the title
    alert.setTitle("Summary");
    alert.setHeaderText("");
    // If there is no summary
    if (summary == null) {
      alert.setContentText("No summary shown");
    } else {// The format of the Min, max Record would be Farm ID/Month, Weight, Percent
      if (farmReport) // Report for farms
        alert.setContentText("Max Record: " + summary.get(0) + " Weight - " + summary.get(1)
            + " Percent - " + summary.get(2) + "\n" + "Min Record: " + summary.get(3) + " "
            + " Weight - " + summary.get(4) + " Percent - " + summary.get(5) + "\n" + "Average: "
            + summary.get(6));
      else // Report for Date
        alert.setContentText("Max Record: " + summary.get(0) + " Weight - " + summary.get(1)
            + " Percent - " + summary.get(2) + "\n" + "Min Record: " + summary.get(3) + " "
            + " Weight - " + summary.get(4) + " Percent - " + summary.get(5) + "\n" + "Average: "
            + summary.get(6));
      alert.showAndWait().filter(response -> response == ButtonType.OK);
    }
  }
}
