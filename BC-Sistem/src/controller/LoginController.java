	
package controller;
/**
 * @author Hendra
 *
 * Oct 18, 2016
 */
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import custom.CustomTf;
import custom.CustomPf;
import dataBase.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import media.userNameMedia;
import DAL.Users;
import javafx.scene.image.Image;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;


public class LoginController implements Initializable {

    Users users = new Users();

    @FXML
    private TextField tfUserName;
    @FXML
    private Button btnUserNameTfClear;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private PasswordField pfUserPassword;

    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();
    
    @FXML
    private Button btnLogin;
    
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane apDesignPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cTF.clearTextFieldByButton(tfUserName, btnUserNameTfClear);
        cPF.clearPassFieldByButton(pfUserPassword, btnPassFieldClear);
        BooleanBinding boolenBinding = tfUserName.textProperty().isEmpty()
                .or(pfUserPassword.textProperty().isEmpty());

        btnLogin.disableProperty().bind(boolenBinding);

    }

    @FXML
    private void btnLogin(ActionEvent event) throws IOException {

        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        userNameMedia media = new userNameMedia();

        ApplicationController apController = new ApplicationController();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Application.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);


        if (isValidCondition()) {
            try {
                pst = con.prepareStatement("select * from User where UsrName=? and Password=?");
                pst.setString(1, tfUserName.getText());
                pst.setString(2, pfUserPassword.getText());
                rs = pst.executeQuery();

                while (rs.next()) {
                    pst = con.prepareStatement("select * from User where UsrName=? and Status=?");
                    pst.setString(1, tfUserName.getText());
                    pst.setInt(2, 1);
                    rs = pst.executeQuery();

                    while (rs.next()) {
//                        System.out.println(rs.getInt(1));
                        userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                        ApplicationController apControl = loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        apControl.btnHomeOnClick(event);
                        apControl.permission();
                        apControl.viewDetails();
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.getIcons().add(new Image("/image/icon.png"));
                        adminPanelStage.setTitle(rs.getString(3));
                        adminPanelStage.show();

                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        System.out.println("Sekarang Anda siap untuk pergi ke Panel Admin");
                        return;
                    }
                    System.out.println("Akun Tidak Aktiv");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information ");        
                    alert.setContentText("Akun ini sudah tidak aktif \n"
                                    + "Silahkan hubungi Admin untuk mengaktifkannya kembali  \n"
                                    + " Terima Kasih");
                    alert.showAndWait();
                    return;
                   
                }
                System.out.println("Password yang anda masukan salah");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information");        
                alert.setContentText("\n Nama dan Password yang anda masukan salah !!");
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private boolean isValidCondition() {
        boolean validCondition = false;
        if (tfUserName.getText().trim().isEmpty()
                || pfUserPassword.getText().isEmpty()) {
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");        
            alert.setContentText("Silahkan isikan teks dengan benar");
            alert.showAndWait(); 
            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
