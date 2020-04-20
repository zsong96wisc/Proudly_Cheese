
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GUI extends Application {

	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();

	

		Scene mainScene = new Scene(root, 500, 500);

		ToggleButton test = new ToggleButton();
		test.getStylesheets().add(
				this.getClass().getResource("imagetogglebutton.css").toExternalForm());
		root.setCenter(test);

		primaryStage.setTitle(" ");
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
