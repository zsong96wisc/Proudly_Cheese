package application;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 200;
    private static final String APP_TITLE = "Hello World!";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // save args example
        Double [] polygonPoints = new Double[]{
            5.0, 0.0,
            12.07, 0.0,
            17.07, 5.0,
            17.07, 10.0,
            12.07, 15.0,
            5.0, 15.0,
            0.0, 10.0,
            0.0, 5.0
            };
        args = this.getParameters().getRaw();

        // Create a vertical box with Hello labels for each args
        VBox vbox = new VBox();

        Label welcomeMessage = new Label("Welcome to "
            + "Cheese Factory Management System");

        Button farmReportButton = new Button("Farm Report");
        Button annualReportButton = new Button("Anual Report");
        Button monthlyReportButton = new Button("Monthly Report");
        Button dateRangeReportButton = new Button("Return");
        Polygon p = new Polygon();
        
        p.getPoints().addAll(polygonPoints);
        dateRangeReportButton.setShape(p);

        vbox.getChildren().add(welcomeMessage);
        vbox.getChildren().add(farmReportButton);
        vbox.getChildren().add(annualReportButton);
        vbox.getChildren().add(monthlyReportButton);
        vbox.getChildren().add(dateRangeReportButton);

        // Main layout is Border Pane example (top,left,center,right,bottom)
        BorderPane root = new BorderPane();

        // Add the vertical box to the center of the root pane
        root.setCenter(vbox);
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