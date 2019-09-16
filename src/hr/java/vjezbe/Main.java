package hr.java.vjezbe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	private static BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Pocetna.fxml"));
			Scene scene = new Scene(root, 350, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			BackgroundFill myBF = new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(1),
			         new Insets(0.0,0.0,0.0,0.0));
			root.setBackground(new Background(myBF));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setCenterPane(BorderPane centerPane) {
		root.setBottom(centerPane);

	}
}
