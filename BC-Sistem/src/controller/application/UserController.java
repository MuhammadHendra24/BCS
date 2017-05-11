
 
package controller.application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.application.user.AddUserController;
import controller.application.user.ViewUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import media.userNameMedia;


public class UserController implements Initializable {
    @FXML
    private MenuItem btnViewUser;
    @FXML
    private MenuItem btnAddUser;
    
    private String userId;
    
    private userNameMedia nameMedia;
    @FXML
    private StackPane spUserContent;
    @FXML
    public BorderPane bpContent;
    @FXML
    private Label lblView;
    @FXML
    private ImageView ivUser;
    
    Image image = new Image("/icon/d.png");

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivUser.setImage(image);
    }    

    @FXML
    public void btnViewUserOnAction(ActionEvent event) throws IOException {
        lblView.setText("User");
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/user/ViewUser.fxml").openStream());
        media.setId(userId);
        ViewUserController viewUser = fXMLLoader.getController();
        viewUser.setNameMedia(nameMedia);
        viewUser.showDetails();
        viewUser.btnClrEmailtf.getStylesheets().add("/style/btnOnText.css");
        viewUser.btnClrFulNametf.getStylesheets().add("/style/btnOnText.css");
       
//        viewEmployeController.checqPermission();
        
        AnchorPane acPane = fXMLLoader.getRoot();
        
        spUserContent.getChildren().clear();
        
        spUserContent.getChildren().add(acPane);
    }

    @FXML
    private void btnAddUserOnClick(ActionEvent event) throws IOException {
        lblView.setText("Add User");
        userNameMedia media = new userNameMedia();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/user/AddUser.fxml").openStream());
        media.setId(userId);
        
        AddUserController addUserController = fXMLLoader.getController();
        addUserController.setNameMedia(nameMedia);
        addUserController.btnClrEmail.getStylesheets().add("/style/btnOnText.css");
        addUserController.btnClrFullName.getStylesheets().add("/style/btnOnText.css");
        addUserController.btnClrPassword.getStylesheets().add("/style/btnOnText.css");
        addUserController.btnClrUsrName.getStylesheets().add("/style/btnOnText.css");
//        addEmployeController.showDetails();
        
        AnchorPane acPane = fXMLLoader.getRoot();
        
        spUserContent.getChildren().clear();
        
        spUserContent.getChildren().add(acPane);
        
    }

    
}
