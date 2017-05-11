
package controller.application.settings;

import Getway.UsersGetway;
import dataBase.DBConnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import media.userNameMedia;
import DAL.Users;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

public class MyAccountController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private TextField tfCreatePassword;
    @FXML
    private Rectangle retImage;

    private Image image;

    private File file;

    private FileInputStream fileInput;

    private FileOutputStream fileOutput;

    private byte[] userImage;

    private String imgPath;

    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    private String userID;

    private userNameMedia usrMediaID;
    @FXML
    private AnchorPane apMyAccountMother;
    
    public userNameMedia getUsrMediaID() {
        return usrMediaID;
    }

    public void setUsrMediaID(userNameMedia usrMediaID) {
        userID = usrMediaID.getId();
        this.usrMediaID = usrMediaID;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    

   
    
    @FXML
    private void apOnOpenAction(MouseEvent event) {

    }

    public void showDetails(){
        users.id = userID;
        usersGetway.selectedView(users);
        tfUserName.setText(users.userName);
        tfFullName.setText(users.fullName);
        tfCreatePassword.setText(users.password);
        tfEmailAddress.setText(users.emailAddress);
        image = users.image;
        retImage.setFill(new ImagePattern(image));
    }

    public void accountPermission() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from UserPermission where UserId=?");

        } catch (SQLException ex) {
            Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
