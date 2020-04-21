package application;

import java.io.File;
import javafx.collections.FXCollections;
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

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

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

    adData.setOnAction(e -> {
      getAddDeleteScene(primaryStage);
    });

    Button chData = new Button("Change");
    chData.setShape(p);
    chData.setMaxSize(3 * r2, 3 * r2);
    chData.setMinSize(3 * r2, 3 * r2);
    chData.setCursor(Cursor.HAND);
    chData.setStyle("-fx-base: yellow;");

    chData.setOnAction(e -> {
      getChangeScene(primaryStage);
    });

    Button returnBn = new Button("return");
    double r = 25;
    returnBn.setShape(new Circle(r));
    returnBn.setMaxSize(2.8 * r, 1.8 * r);
    returnBn.setMinSize(2.8 * r, 1.8 * r);
    returnBn.setCursor(Cursor.HAND);
    returnBn.setStyle("-fx-base: orange;");
    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    VBox vboxLf = new VBox();
    HBox md = new HBox(10);
    VBox vboxRt = new VBox(100);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    md.getChildren().addAll(adData, chData);
    vboxRt.getChildren().addAll(imageViewCheese, returnBn);
    root.setLeft(vboxLf);
    root.setCenter(md);
    root.setRight(vboxRt);

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    return;
  }

  public static void getAddDeleteScene(Stage primaryStage) {
    Text text = new Text();
    double r = 25;

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("AddDeleteScene");

    BorderPane root = new BorderPane();

    VBox vBox = new VBox(10);

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    TextField farmIDTextField = new TextField();
    farmIDTextField.setPromptText("Farm ID");
    TextField dateTextField = new TextField();
    dateTextField.setPromptText("Date");
    TextField weightTextField = new TextField();
    weightTextField.setPromptText("Weight");

    farmIDTextField.setPrefSize(50, 10);
    dateTextField.setPrefSize(50, 10);
    weightTextField.setPrefSize(50, 10);

    vBox.getChildren().addAll(farmIDTextField, dateTextField, weightTextField);
    vBox.setAlignment(Pos.CENTER);

    root.setCenter(vBox);

    Button returnButton = new Button("return");
    root.setRight(returnButton);
    returnButton.setShape(new Circle(r));
    returnButton.setMaxSize(3 * r, 3 * r);
    returnButton.setMinSize(3 * r, 3 * r);
    returnButton.setCursor(Cursor.HAND);
    returnButton.setStyle("-fx-base: orange;");

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese);
    root.setRight(sceneRight);

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);

    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);

    root.setLeft(sceneLeft);
    returnButton.setOnAction(e -> {
      getEditScene(primaryStage);
    });

    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button clear = new Button("Clear");
    add.setShape(new Circle(r));
    add.setMaxSize(4 * r, 2 * r);
    add.setMinSize(4 * r, 2 * r);

    delete.setShape(new Circle(r));
    delete.setMaxSize(4 * r, 2 * r);
    delete.setMinSize(4 * r, 2 * r);
    clear.setShape(new Circle(r));
    clear.setMaxSize(4 * r, 2 * r);
    clear.setMinSize(4 * r, 2 * r);

    add.setCursor(Cursor.HAND);
    add.setStyle("-fx-base: moccasin;");
    delete.setCursor(Cursor.HAND);
    delete.setStyle("-fx-base: navajowhite;");
    clear.setCursor(Cursor.HAND);
    clear.setStyle("-fx-base: gold;");

    HBox bottomBox = new HBox(10);
    bottomBox.getChildren().addAll(add, delete, clear);
    bottomBox.setAlignment(Pos.CENTER);

    root.setBottom(bottomBox);
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));

    return;
  }

  public static void getChangeScene(Stage primaryStage) {

    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("changeScene");
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    BorderPane root = new BorderPane();

    root.setTop(text);

    double r = 25;

    Button change = new Button("Change");
    Button clear = new Button("Clear");
    change.setShape(new Circle(r));
    change.setMaxSize(4 * r, 2 * r);
    change.setMinSize(4 * r, 2 * r);
    clear.setShape(new Circle(r));
    clear.setMaxSize(4 * r, 2 * r);
    clear.setMinSize(4 * r, 2 * r);
    change.setCursor(Cursor.HAND);
    change.setStyle("-fx-base: navajowhite;");
    clear.setCursor(Cursor.HAND);
    clear.setStyle("-fx-base: gold;");
    HBox sceneBottom = new HBox(20);
    sceneBottom.getChildren().addAll(change, clear);
    sceneBottom.setAlignment(Pos.CENTER);
    root.setBottom(sceneBottom);
    BorderPane.setAlignment(sceneBottom, Pos.BOTTOM_CENTER);

    TextField oldFarmID = new TextField();
    oldFarmID.setPromptText("Old Farm ID");
    oldFarmID.setPrefSize(90, 10);
    TextField oldDate = new TextField();
    oldDate.setPromptText("Old Date");
    oldDate.setPrefSize(90, 10);
    TextField oldWeight = new TextField();
    oldWeight.setPromptText("Old Weight");
    oldWeight.setPrefSize(90, 10);
    VBox center1 = new VBox(15);
    center1.getChildren().addAll(oldFarmID, oldDate, oldWeight);
    center1.setAlignment(Pos.CENTER);
    File inputArrow = new File("changeArrow.png");
    Image imageArrow = new Image(inputArrow.toURI().toString());
    ImageView imageViewArrow = new ImageView();
    imageViewArrow.setImage(imageArrow);
    imageViewArrow.setFitHeight(140);
    imageViewArrow.setFitWidth(40);
    TextField newFarmID = new TextField();
    newFarmID.setPromptText("New Farm ID");
    newFarmID.setPrefSize(90, 10);
    TextField newDate = new TextField();
    newDate.setPromptText("New Date");
    newDate.setPrefSize(90, 10);
    TextField newWeight = new TextField();
    newWeight.setPromptText("New Weight");
    newWeight.setPrefSize(90, 10);
    VBox center3 = new VBox(15);
    center3.getChildren().addAll(newFarmID, newDate, newWeight);
    center3.setAlignment(Pos.CENTER);
    HBox sceneCenter = new HBox(8);
    sceneCenter.getChildren().addAll(center1, imageViewArrow, center3);
    sceneCenter.setAlignment(Pos.CENTER);
    root.setCenter(sceneCenter);

    Button returnButton = new Button("return");
    returnButton.setShape(new Circle(r));
    returnButton.setMaxSize(3 * r, 3 * r);
    returnButton.setMinSize(3 * r, 3 * r);
    returnButton.setCursor(Cursor.HAND);
    returnButton.setStyle("-fx-base: orange;");
    returnButton.setOnAction(e -> {
      getEditScene(primaryStage);
    });

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese);
    root.setRight(sceneRight);

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);
    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);
    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);
    root.setLeft(sceneLeft);

    Scene changeScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(changeScene);

    return;
  }

  public static void getFarmReportScene(Stage primaryStage) {
    BorderPane root = new BorderPane();
    HBox hbox = new HBox(20);
    VBox vboxL = new VBox(60);
    VBox vboxR = new VBox(20);

    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("Farm Report\n");

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

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

    Button buttonReturn = new Button("return");
    double r = 25;
    buttonReturn.setShape(new Circle(r));
    buttonReturn.setMaxSize(2 * r, 2 * r);
    buttonReturn.setMinSize(2 * r, 2 * r);
    buttonReturn.setCursor(Cursor.HAND);
    buttonReturn.setStyle("-fx-base: orange;");
    buttonReturn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    Label farmID = new Label("FarmID: ");

    TextField inputField = new TextField();

    Button search = new Button("Search");
    search.setShape(new Circle(r));
    search.setMaxSize(4 * r, 2 * r);
    search.setMinSize(4 * r, 2 * r);
    search.setCursor(Cursor.HAND);
    search.setStyle("-fx-base: navajowhite;");
    search.setOnAction(e -> {
      if (!inputField.getText().equals("")) {
        getFarmResultScene(primaryStage);
      }
    });

    Button clear = new Button("Clear");
    clear.setShape(new Circle(r));
    clear.setMaxSize(4 * r, 2 * r);
    clear.setMinSize(4 * r, 2 * r);
    clear.setCursor(Cursor.HAND);
    clear.setStyle("-fx-base: gold;");

    HBox sceneBottom = new HBox(20);
    sceneBottom.setAlignment(Pos.CENTER);
    sceneBottom.getChildren().addAll(search, clear);

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    hbox.getChildren().addAll(farmID, inputField);
    vboxL.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxR.getChildren().addAll(buttonReturn, imageViewCheese);
    root.setLeft(vboxL);
    root.setCenter(hbox);
    root.setRight(vboxR);
    root.setBottom(sceneBottom);
    BorderPane.setAlignment(sceneBottom, Pos.BOTTOM_CENTER);

    Scene farmReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(farmReportScene);
  }

  public static void getFarmResultScene(Stage primaryStage) {
    BorderPane root = new BorderPane();
    HBox hbox = new HBox();
    VBox vboxCL = new VBox(20);
    VBox vboxCR = new VBox();
    VBox vboxL = new VBox(60);
    VBox vboxR = new VBox(20);

    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("Farm Report Result\n");

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(50);
    imageViewCheese.setFitWidth(50);

    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);

    Button buttonReturn = new Button("return");
    double r = 25;
    buttonReturn.setShape(new Circle(r));
    buttonReturn.setMaxSize(2 * r, 2 * r);
    buttonReturn.setMinSize(2 * r, 2 * r);
    buttonReturn.setCursor(Cursor.HAND);
    buttonReturn.setStyle("-fx-base: orange;");
    buttonReturn.setOnAction(e -> {
      getFarmReportScene(primaryStage);
    });

    Label title = new Label("\nTOTAL MILK\n WEIGHT");

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

    vboxCL.getChildren().addAll(title, stack, stack2);

    String[][] result = {{"Mon", "Tue", "Wed"}, {"4567", "5322", "234"}, {"12.4%", "15%", "2%"}};

    Label farmDateLabel = new Label("Date");
    Label farmWeightLabel = new Label("Weight");
    Label farmPercentLabel = new Label("Percent");
    VBox vBoxFirstList = new VBox();
    VBox vBoxSecondList = new VBox();
    VBox vBoxThirdList = new VBox();

    ListView<String> farmDateList = new ListView<String>();

    for (int i = 0; i < result[0].length; i++) {
      farmDateList.getItems().add(result[0][i]);
    }

    farmDateList.setMaxSize(100, 200);

    ListView<String> totalWeightList = new ListView<String>();

    for (int i = 0; i < result[1].length; i++) {
      totalWeightList.getItems().add(result[1][i]);
    }

    totalWeightList.setMaxSize(100, 200);

    ListView<String> percentList = new ListView<String>();

    for (int i = 0; i < result[2].length; i++) {
      percentList.getItems().add(result[1][i]);
    }

    percentList.setMaxSize(100, 200);

    vBoxFirstList.getChildren().addAll(farmDateLabel, farmDateList);
    vBoxSecondList.getChildren().addAll(farmWeightLabel, totalWeightList);
    vBoxThirdList.getChildren().addAll(farmPercentLabel, percentList);


    HBox hBox = new HBox();

    hBox.getChildren().addAll(vBoxFirstList, vBoxSecondList, vBoxThirdList);

    Button farmWeightSortButton = new Button("Asc/Des");
    farmWeightSortButton.setShape(new Circle(r));
    farmWeightSortButton.setMaxSize(3.2 * r, 2 * r);
    farmWeightSortButton.setMinSize(3.2 * r, 2 * r);
    farmWeightSortButton.setCursor(Cursor.HAND);
    farmWeightSortButton.setStyle("-fx-base: navajowhite;");

    Button ExportButton = new Button("Export");
    ExportButton.setShape(new Circle(r));
    ExportButton.setMaxSize(3.2 * r, 2 * r);
    ExportButton.setMinSize(3.2 * r, 2 * r);
    ExportButton.setCursor(Cursor.HAND);
    ExportButton.setStyle("-fx-base: navajowhite;");

    HBox buttonBox = new HBox(100);
    buttonBox.getChildren().addAll(farmWeightSortButton, ExportButton);

    vboxCR.getChildren().addAll(hBox, buttonBox);

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    hbox.getChildren().addAll(vboxCL, vboxCR);
    vboxL.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxR.getChildren().addAll(buttonReturn, imageViewCheese);
    root.setLeft(vboxL);
    root.setCenter(hbox);
    root.setRight(vboxR);

    Scene farmResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(farmResultScene);
  }

  public static void getAnnualReportScene(Stage primaryStage) {
    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("AnnualReportScene");

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

    Button returnBn = new Button("return");
    double r = 25;
    returnBn.setShape(new Circle(r));
    returnBn.setMaxSize(2.5 * r, 2.5 * r);
    returnBn.setMinSize(2.5 * r, 2.5 * r);
    returnBn.setCursor(Cursor.HAND);
    returnBn.setStyle("-fx-base: orange;");

    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    Button search = new Button("Search");
    search.setShape(new Circle(r));
    search.setMaxSize(3.2 * r, 2 * r);
    search.setMinSize(3.2 * r, 2 * r);
    search.setCursor(Cursor.HAND);
    search.setStyle("-fx-base: navajowhite;");

    search.setOnAction(e -> {
      getAnnualResultScene(primaryStage);
    });

    Button clear = new Button("Clear");
    clear.setShape(new Circle(r));
    clear.setMaxSize(3.2 * r, 2 * r);
    clear.setMinSize(3.2 * r, 2 * r);
    clear.setCursor(Cursor.HAND);
    clear.setStyle("-fx-base: gold;");

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    String array[] = {"2019", "2018", "2017"};
    ComboBox combo = new ComboBox(FXCollections.observableArrayList(array));
    Text year = new Text();
    year.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    year.setText("Year : ");

    BorderPane root = new BorderPane();
    HBox hbt = new HBox(30);
    HBox hbb = new HBox(30);
    VBox vboxLf = new VBox(130);
    VBox vboxRt = new VBox(30);
    VBox vboxmd = new VBox(100);


    hbt.getChildren().addAll(year, combo);
    hbb.getChildren().addAll(search, clear);
    hbt.setAlignment(Pos.CENTER);
    hbb.setAlignment(Pos.CENTER);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, imageViewCheese);
    vboxmd.getChildren().addAll(hbt, hbb);
    vboxmd.setAlignment(Pos.CENTER);


    root.setLeft(vboxLf);
    root.setCenter(vboxmd);
    root.setRight(vboxRt);

    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene annualReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    primaryStage.setScene(annualReportScene);
    return;
  }

  public static void getAnnualResultScene(Stage primaryStage) {
    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("AnnualResultScene");
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

    File inputCow = new File("cow.png");
    Image imageCow = new Image(inputCow.toURI().toString());
    ImageView imageViewCow = new ImageView();
    imageViewCow.setImage(imageCow);
    imageViewCow.setFitHeight(100);
    imageViewCow.setFitWidth(100);


    Button returnBn = new Button("return");
    double r = 25;
    returnBn.setShape(new Circle(r));
    returnBn.setMaxSize(2.5 * r, 2.5 * r);
    returnBn.setMinSize(2.5 * r, 2.5 * r);
    returnBn.setCursor(Cursor.HAND);
    returnBn.setStyle("-fx-base: orange;");

    returnBn.setOnAction(e -> {
      getMainScene(primaryStage);
    });


    Button AscendLf = new Button("Ascend/Descend");
    AscendLf.setShape(new Circle(r));
    AscendLf.setMaxSize(5 * r, r);
    AscendLf.setMinSize(5 * r, r);
    AscendLf.setCursor(Cursor.HAND);
    AscendLf.setStyle("-fx-base: navajowhite;");


    Button AscendRt = new Button("Ascend/Descend");
    AscendRt.setShape(new Circle(r));
    AscendRt.setMaxSize(5 * r, r);
    AscendRt.setMinSize(5 * r, r);
    AscendRt.setCursor(Cursor.HAND);
    AscendRt.setStyle("-fx-base: gold;");


    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);


    ListView farmIDList = new ListView();

    farmIDList.getItems().add("Farm ID");
    farmIDList.getItems().add("Farm 123");
    farmIDList.getItems().add("Farm 124");
    farmIDList.getItems().add("Farm 125");
    farmIDList.setMaxSize(100, 150);

    ListView weightList = new ListView();

    weightList.getItems().add("Total Weight");
    weightList.getItems().add("2367");
    weightList.getItems().add("463");
    weightList.getItems().add("346");
    weightList.setMaxSize(100, 150);

    ListView percentList = new ListView();

    percentList.getItems().add("Percent");
    percentList.getItems().add("74.8%");
    percentList.getItems().add("14.6%");
    percentList.getItems().add("10.9%");
    percentList.setMaxSize(100, 150);

    HBox list = new HBox();

    list.getChildren().addAll(farmIDList, weightList, percentList);

    Button farmIDSortButton = new Button("Asc/Des");
    farmIDSortButton.setShape(new Circle(r));
    farmIDSortButton.setMaxSize(3.2 * r, 2 * r);
    farmIDSortButton.setMinSize(3.2 * r, 2 * r);
    farmIDSortButton.setCursor(Cursor.HAND);
    farmIDSortButton.setStyle("-fx-base: navajowhite;");

    Button weightSortButton = new Button("Asc/Des");
    weightSortButton.setShape(new Circle(r));
    weightSortButton.setMaxSize(3.2 * r, 2 * r);
    weightSortButton.setMinSize(3.2 * r, 2 * r);
    weightSortButton.setCursor(Cursor.HAND);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    Button exportButton = new Button("Export");
    exportButton.setShape(new Circle(r));
    exportButton.setMaxSize(3.2 * r, 2 * r);
    exportButton.setMinSize(3.2 * r, 2 * r);
    exportButton.setCursor(Cursor.HAND);
    exportButton.setStyle("-fx-base: gold;");

    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);

    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(list, sortButton);

    VBox vboxLf = new VBox(130);
    VBox vboxRt = new VBox(30);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, imageViewCheese, exportButton);

    BorderPane root = new BorderPane();
    root.setLeft(vboxLf);
    root.setRight(vboxRt);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    Scene annualResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(annualResultScene);

    return;
  }

  @SuppressWarnings("unchecked")
  public static void getMonthlyReportScene(Stage primaryStage) {

    BorderPane root = new BorderPane();

    Text title = new Text();
    title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    title.setText("MonthlyReportScene");
    root.setTop(title);
    BorderPane.setAlignment(title, Pos.TOP_CENTER);

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

    Button returnButton = new Button("return");
    double r = 25;
    returnButton.setShape(new Circle(r));
    returnButton.setMaxSize(2.5 * r, 2.5 * r);
    returnButton.setMinSize(2.5 * r, 2.5 * r);
    returnButton.setCursor(Cursor.HAND);
    returnButton.setStyle("-fx-base: orange;");
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    Button searchButton = new Button("Search");
    searchButton.setShape(new Circle(r));
    searchButton.setMaxSize(3.2 * r, 2 * r);
    searchButton.setMinSize(3.2 * r, 2 * r);
    searchButton.setCursor(Cursor.HAND);
    searchButton.setStyle("-fx-base: navajowhite;");
    searchButton.setOnAction(e -> {
      getMonthlyResultScene(primaryStage);
    });

    Button clearButton = new Button("Clear");
    clearButton.setShape(new Circle(r));
    clearButton.setMaxSize(3.2 * r, 2 * r);
    clearButton.setMinSize(3.2 * r, 2 * r);
    clearButton.setCursor(Cursor.HAND);
    clearButton.setStyle("-fx-base: gold;");

    Image inputCloud = new Image("cloud.png", 40, 30, false, false);
    ImageView imageViewCloud = new ImageView(inputCloud);
    ToggleButton buttonCloud = new ToggleButton("", imageViewCloud);

    String[] sampleMonth = {"2019/01", "2019/02", "2019/03"};
    @SuppressWarnings("rawtypes")
    ComboBox comboMonth = new ComboBox(FXCollections.observableArrayList(sampleMonth));
    Text monthPrompt = new Text();
    monthPrompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    monthPrompt.setText("Year/Month: ");

    HBox topBox = new HBox(20);
    HBox bottomBox = new HBox(30);
    VBox leftBox = new VBox(130);
    VBox rightBox = new VBox(30);
    VBox centerBox = new VBox(100);


    topBox.getChildren().addAll(monthPrompt, comboMonth);
    bottomBox.getChildren().addAll(searchButton, clearButton);
    topBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);

    leftBox.getChildren().addAll(buttonCloud, imageViewBrand);
    rightBox.getChildren().addAll(returnButton, imageViewCheese);
    centerBox.getChildren().addAll(topBox, bottomBox);
    centerBox.setAlignment(Pos.CENTER);


    root.setLeft(leftBox);
    root.setCenter(centerBox);
    root.setRight(rightBox);

    Scene monthlyReportScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    primaryStage.setScene(monthlyReportScene);
  }

  public static void getMonthlyResultScene(Stage primaryStage) {
    Text text = new Text();

    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("MonthlyResultScene");
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

    File inputCow = new File("cow.png");
    Image imageCow = new Image(inputCow.toURI().toString());
    ImageView imageViewCow = new ImageView();
    imageViewCow.setImage(imageCow);
    imageViewCow.setFitHeight(100);
    imageViewCow.setFitWidth(100);

    Button returnBn = new Button("return");
    double r = 25;
    returnBn.setShape(new Circle(r));
    returnBn.setMaxSize(2.5 * r, 2.5 * r);
    returnBn.setMinSize(2.5 * r, 2.5 * r);
    returnBn.setCursor(Cursor.HAND);
    returnBn.setStyle("-fx-base: orange;");
    returnBn.setOnAction(e -> {
      getMonthlyReportScene(primaryStage);
    });

    Button export = new Button("Export");
    export.setShape(new Circle(r));
    export.setMaxSize(3.2 * r, 2 * r);
    export.setMinSize(3.2 * r, 2 * r);
    export.setCursor(Cursor.HAND);
    export.setStyle("-fx-base: gold;");

    Button AscendLf = new Button("Ascend/Descend");
    AscendLf.setShape(new Circle(r));
    AscendLf.setMaxSize(5 * r, r);
    AscendLf.setMinSize(5 * r, r);
    AscendLf.setCursor(Cursor.HAND);
    AscendLf.setStyle("-fx-base: navajowhite;");

    Button AscendRt = new Button("Ascend/Descend");
    AscendRt.setShape(new Circle(r));
    AscendRt.setMaxSize(5 * r, r);
    AscendRt.setMinSize(5 * r, r);
    AscendRt.setCursor(Cursor.HAND);
    AscendRt.setStyle("-fx-base: gold;");

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    ListView farmIDList = new ListView();

    farmIDList.getItems().add("Farm ID");
    farmIDList.getItems().add("Farm 123");
    farmIDList.getItems().add("Farm 124");
    farmIDList.getItems().add("Farm 125");
    farmIDList.setMaxSize(100, 150);

    ListView weightList = new ListView();

    weightList.getItems().add("Total Weight");
    weightList.getItems().add("2367");
    weightList.getItems().add("463");
    weightList.getItems().add("346");
    weightList.setMaxSize(100, 150);

    ListView percentList = new ListView();

    percentList.getItems().add("Percent");
    percentList.getItems().add("74.8%");
    percentList.getItems().add("14.6%");
    percentList.getItems().add("10.9%");
    percentList.setMaxSize(100, 150);

    HBox list = new HBox();

    list.getChildren().addAll(farmIDList, weightList, percentList);

    Button farmIDSortButton = new Button("Asc/Des");
    farmIDSortButton.setShape(new Circle(r));
    farmIDSortButton.setMaxSize(3.2 * r, 2 * r);
    farmIDSortButton.setMinSize(3.2 * r, 2 * r);
    farmIDSortButton.setCursor(Cursor.HAND);
    farmIDSortButton.setStyle("-fx-base: navajowhite;");

    Button weightSortButton = new Button("Asc/Des");
    weightSortButton.setShape(new Circle(r));
    weightSortButton.setMaxSize(3.2 * r, 2 * r);
    weightSortButton.setMinSize(3.2 * r, 2 * r);
    weightSortButton.setCursor(Cursor.HAND);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);

    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(list, sortButton);

    VBox vboxLf = new VBox(130);
    VBox vboxRt = new VBox(30);

    vboxLf.getChildren().addAll(buttonCloud, imageViewBrand);
    vboxRt.getChildren().addAll(returnBn, imageViewCheese, export);

    BorderPane root = new BorderPane();
    root.setLeft(vboxLf);
    root.setRight(vboxRt);
    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);
    Scene monthlyResultScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(monthlyResultScene);
    return;
  }

  public static void getDateReportScene(Stage primaryStage) {
    Text text = new Text();
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("DateReportScene");

    double r = 25;

    BorderPane root = new BorderPane();

    TextField endDateField = new TextField();
    TextField startDateField = new TextField();

    endDateField.setPromptText("Enter end Date");
    startDateField.setPromptText("Enter start data");

    endDateField.setPrefSize(50, 10);
    startDateField.setPrefSize(50, 10);

    VBox vBox = new VBox(20);
    vBox.getChildren().addAll(endDateField, startDateField);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    vBox.setAlignment(Pos.CENTER);
    root.setCenter(vBox);

    Button returnButton = new Button("return");
    root.setRight(returnButton);
    returnButton.setShape(new Circle(r));
    returnButton.setMaxSize(3 * r, 3 * r);
    returnButton.setMinSize(3 * r, 3 * r);
    returnButton.setCursor(Cursor.HAND);
    returnButton.setStyle("-fx-base: orange;");

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);
    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese);
    root.setRight(sceneRight);

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);

    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);

    root.setLeft(sceneLeft);
    returnButton.setOnAction(e -> {
      getMainScene(primaryStage);
    });

    Button search = new Button("Search");
    search.setShape(new Circle(r));
    search.setMaxSize(4 * r, 2 * r);
    search.setMinSize(4 * r, 2 * r);
    search.setOnAction(e -> {
      getDateResultScene(primaryStage);
    });

    Button clear = new Button("Clear");
    clear.setShape(new Circle(r));
    clear.setMaxSize(4 * r, 2 * r);
    clear.setMinSize(4 * r, 2 * r);

    search.setCursor(Cursor.HAND);
    search.setStyle("-fx-base: moccasin;");

    clear.setCursor(Cursor.HAND);
    clear.setStyle("-fx-base: gold;");

    HBox bottomBox = new HBox(20);
    bottomBox.getChildren().addAll(search, clear);
    bottomBox.setAlignment(Pos.CENTER);

    root.setBottom(bottomBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    return;
  }

  public static void getDateResultScene(Stage primaryStage) {
    Text text = new Text();
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
    text.setText("DateReportScene");

    double r = 25;

    BorderPane root = new BorderPane();

    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    ListView farmIDList = new ListView();

    farmIDList.getItems().add("Farm ID");
    farmIDList.getItems().add("Farm 123");
    farmIDList.getItems().add("Farm 124");
    farmIDList.getItems().add("Farm 125");
    farmIDList.setMaxSize(600, 100);

    ListView totalWeightList = new ListView();

    totalWeightList.getItems().add("Weight");
    totalWeightList.getItems().add("2367");
    totalWeightList.getItems().add("463");
    totalWeightList.getItems().add("346");
    totalWeightList.setMaxSize(600, 100);

    ListView percentList = new ListView();

    percentList.getItems().add("Percent");
    percentList.getItems().add("74.8%");
    percentList.getItems().add("14.6%");
    percentList.getItems().add("10.9%");
    percentList.setMaxSize(600, 100);

    HBox listHBox = new HBox();
    listHBox.getChildren().addAll(farmIDList, totalWeightList, percentList);

    Button farmIDSortButton = new Button("Asc/Des");
    farmIDSortButton.setShape(new Circle(r));
    farmIDSortButton.setMaxSize(3.2 * r, 2 * r);
    farmIDSortButton.setMinSize(3.2 * r, 2 * r);
    farmIDSortButton.setCursor(Cursor.HAND);
    farmIDSortButton.setStyle("-fx-base: moccasin;");

    Button weightSortButton = new Button("Asc/Des");
    weightSortButton.setShape(new Circle(r));
    weightSortButton.setMaxSize(3.2 * r, 2 * r);
    weightSortButton.setMinSize(3.2 * r, 2 * r);
    weightSortButton.setCursor(Cursor.HAND);
    weightSortButton.setStyle("-fx-base: navajowhite;");

    HBox sortButton = new HBox(25);
    sortButton.getChildren().addAll(farmIDSortButton, weightSortButton);
    VBox centerBox = new VBox(8);
    centerBox.getChildren().addAll(listHBox, sortButton);

    Button returnButton = new Button("return");
    root.setRight(returnButton);
    returnButton.setShape(new Circle(r));
    returnButton.setMaxSize(3 * r, 3 * r);
    returnButton.setMinSize(3 * r, 3 * r);
    returnButton.setCursor(Cursor.HAND);
    returnButton.setStyle("-fx-base: orange;");

    File inputCheese = new File("cheese.png");
    Image imageCheese = new Image(inputCheese.toURI().toString());
    ImageView imageViewCheese = new ImageView();
    imageViewCheese.setImage(imageCheese);
    imageViewCheese.setFitHeight(100);
    imageViewCheese.setFitWidth(100);

    Button exportButton = new Button("Export");
    exportButton.setShape(new Circle(r));
    exportButton.setMaxSize(3.2 * r, 2 * r);
    exportButton.setMinSize(3.2 * r, 2 * r);
    exportButton.setCursor(Cursor.HAND);
    exportButton.setStyle("-fx-base: gold;");

    VBox sceneRight = new VBox(20);
    sceneRight.getChildren().addAll(returnButton, imageViewCheese, exportButton);
    root.setRight(sceneRight);

    Image image = new Image("cloud.png", 40, 30, false, false);
    ImageView imageView = new ImageView(image);
    ToggleButton buttonCloud = new ToggleButton("", imageView);

    File inputBrand = new File("brand.png");
    Image imageBrand = new Image(inputBrand.toURI().toString());
    ImageView imageViewBrand = new ImageView();
    imageViewBrand.setImage(imageBrand);
    imageViewBrand.setFitHeight(100);
    imageViewBrand.setFitWidth(100);

    VBox sceneLeft = new VBox(10);
    sceneLeft.getChildren().addAll(buttonCloud, imageViewBrand);

    root.setLeft(sceneLeft);
    returnButton.setOnAction(e -> {
      getDateReportScene(primaryStage);
    });

    root.setCenter(centerBox);
    root.setTop(text);
    BorderPane.setAlignment(text, Pos.TOP_CENTER);

    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    return;
  }

}
