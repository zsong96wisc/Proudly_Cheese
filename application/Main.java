package application;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final int WINDOW_WIDTH = 500;
  private static final int WINDOW_HEIGHT = 300;
  private static final String APP_TITLE = "Proudly Cheese";

  @Override
  public void start(Stage primaryStage) throws Exception {
    Scene editScene = GUI.getEditScene();

    // save args example
    args = this.getParameters().getRaw();

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
    buttonCloud.getStylesheets()
        .add(this.getClass().getResource("imagetogglebutton.css").toExternalForm());

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
    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
