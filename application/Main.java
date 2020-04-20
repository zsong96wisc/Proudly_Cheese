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
    private static final String APP_TITLE = "Hello World!";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // save args example
        args = this.getParameters().getRaw();

        // Create a vertical box with Hello labels for each args
        VBox vbox = new VBox(35);
        VBox vboxR = new VBox(20);
        VBox vboxL = new VBox(5);
        
        Text text = new Text();
        
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
        text.setText("Welcome to cheese factory management system!");
        
        
        //Label label = new Label(text);
        
        
        for (String arg : args) {
          vbox.getChildren().add(new Label("hello " + arg));
        }

        // Main layout is Border Pane example (top,left,center,right,bottom)
        BorderPane root = new BorderPane();
        String array[]= {"day1","day2","day3"};
        ComboBox combo = new ComboBox(FXCollections.observableArrayList(array));
        
        
        File input = new File("cheese.png");
        Image image = new Image(input.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        
        File input2 = new File("cloud.jpg");
        Image image2 = new Image(input2.toURI().toString(),50,50,false,false);
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);
        imageView2.setFitHeight(60);
        imageView2.setFitWidth(60);
        
        File input3 = new File("label.png");
        Image image3 = new Image(input3.toURI().toString(),50,50,false,false);
        ImageView imageView3 = new ImageView();
        imageView3.setImage(image3);
        imageView3.setFitHeight(100);
        imageView3.setFitWidth(100);
        
        Button button = new Button("Farm Report",new Label());
        Button button2 = new Button("Annual Report",new Label());
        Button button3 = new Button("Monthly Report",new Label());
        Button button4 = new Button("Date Range Report",new Label());
        

        button.setCursor(Cursor.HAND);
        button.setStyle("-fx-base: moccasin;");
        button2.setCursor(Cursor.HAND);
        button2.setStyle("-fx-base: navajowhite;");
        button3.setCursor(Cursor.HAND);
        button3.setStyle("-fx-base: gold;");
        button4.setCursor(Cursor.HAND);
        button4.setStyle("-fx-base: darkorange;");
        
//        Button button5 = new Button("return");
//        
//        double r = 25;
//        button5.setShape(new Circle(r));
//        button5.setMaxSize(2.8*r,1.8*r);
//        button5.setMinSize(2.8*r,1.8*r);
//        button5.setCursor(Cursor.HAND);
//        button5.setStyle("-fx-base: orange;");
        
        
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[] {
            5.0, 0.0,
            12.07, 0.0,
            17.07, 5.0,
            17.07, 10.0,
            12.07, 15.0,
            5.0, 15.0,
            0.0, 10.0,
            0.0, 5.0
        });
        
        Button edData = new Button("Edit Data");
        double r2 = 30;
        edData.setShape(p);
        edData.setMaxSize(3*r2,3*r2);
        edData.setMinSize(3*r2,3*r2);
        edData.setCursor(Cursor.HAND);
        edData.setStyle("-fx-base: yellow;");

        
        Button ldData = new Button("Load Data");
        ldData.setShape(p);
        ldData.setMaxSize(3*r2,3*r2);
        ldData.setMinSize(3*r2,3*r2);
        ldData.setCursor(Cursor.HAND);
        ldData.setStyle("-fx-base: yellow;");

               
        // Add the vertical box to the center of the root pane
        root.setTop(text);
        BorderPane.setAlignment(text, Pos.TOP_CENTER);
        vbox.getChildren().addAll(button,button2,button3,button4);
        vboxR.getChildren().addAll(edData,imageView3);
        vboxL.getChildren().addAll(imageView,ldData);
        root.setRight(vboxL);
        root.setCenter(vbox);
        root.setLeft(vboxR);

        
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