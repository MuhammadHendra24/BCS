package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	 public static void main(String[] args) { launch(args); }
	  @Override public void start(Stage stage) {
	    System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));
	    System.exit(0);
	  }
	}