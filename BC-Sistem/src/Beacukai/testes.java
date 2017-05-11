	package Beacukai;

import dataBase.DBModel;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class testes extends Application {
    
    public testes(){
        DBModel model = new DBModel();
        model.createDataBase();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("/image/icon.png"));
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(500.0);
        primaryStage.setMinWidth(850.0);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
