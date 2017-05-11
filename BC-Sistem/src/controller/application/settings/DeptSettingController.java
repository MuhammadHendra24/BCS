
package controller.application.settings;

import dataBase.DBConnection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import media.userNameMedia;


public class DeptSettingController implements Initializable {

    @FXML
    private TextField tfDeptName;
    @FXML
    private Rectangle retOrgLogo;
    @FXML
    private Button btnAttechLogo;
    @FXML
    private Button btnSaveDept;

    private File file;

    private BufferedImage bufferedImage;

    private Image image;

    private String userId;

    private String imagePath;

    private userNameMedia usrIdMedia;
    
    

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TextField tfSection;
    @FXML
    private TextArea taDeptContactNumber;
    @FXML
    private TextArea taAdddress;

    public userNameMedia getUsrIdMedia() {
        return usrIdMedia;
    }

    public void setUsrIdMedia(userNameMedia usrIdMedia) {
        userId = usrIdMedia.getId();
        this.usrIdMedia = usrIdMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBind = tfDeptName.textProperty().isEmpty()
                .or(tfSection.textProperty().isEmpty()
                .or(taDeptContactNumber.textProperty().isEmpty())
                .or(taAdddress.textProperty().isEmpty()));

        btnSaveDept.disableProperty().bind(boolenBind);
    }

    @FXML
    private void btnSaveDeptOnClick(ActionEvent event) {
        if (isFoundData()) {
            //update
            if (imagePath != null) {
                updateDeptWithImage();
                } else {
                updateDepartWithOutImage();
            }

        } else {
            
        	inserDepartWithImage();
        }
    }

    @FXML
    private void btnAttechLogoOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            retOrgLogo.setFill(new ImagePattern(image));
            imagePath = file.getAbsolutePath();
        }
    }
    /*
    
     */

    public void showDetails() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Departement where Id=?");
            pst.setString(1, "1");
            rs = pst.executeQuery();
            while (rs.next()) {
                tfDeptName.setText(rs.getString(2));
                tfSection.setText(rs.getString(3));
                taDeptContactNumber.setText(rs.getString(4));
                taAdddress.setText(rs.getString(5));

                Blob blob = rs.getBlob(6);
                if (blob != null) {
                    ByteArrayInputStream in = new ByteArrayInputStream(blob.getBytes(1, (int) blob.length()));
                    image = new Image(in);
                    retOrgLogo.setFill(new ImagePattern(image));
                } else {

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
     */

    private boolean isFoundData() {
        boolean dataFound = true;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Departement ORDER BY Id ASC LIMIT 1");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Data Found");
                return dataFound;
            }
            System.out.println("Data not found");
            dataFound = false;

        } catch (SQLException ex) {
            Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataFound;
    }
    /*
    
     */

    private void updateDeptWithImage() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Update Departement set Deptname=?,DeprtSection=?,DeptContactNumbers=?,DeptContactAddress=?,OrgLogo=? where Id=1");

            pst.setString(1, tfDeptName.getText());
            pst.setString(2, tfSection.getText());
            pst.setString(3, taDeptContactNumber.getText());
            pst.setString(4, taAdddress.getText());
            if (imagePath != null) {
                try {
                    InputStream is = new FileInputStream(new File(imagePath));
                    pst.setBlob(5, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                pst.setBlob(5, (Blob) null);
            }

            pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("update");
        	alert.setContentText("Data BerHasil di update");
        	alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
     */

    private void inserDepartWithImage() {

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into Departement values(?,?,?,?,?,?,?)");
            pst.setString(1, "1");
            pst.setString(2, tfDeptName.getText());
            pst.setString(3, tfSection.getText());
            pst.setString(4, taDeptContactNumber.getText());
            pst.setString(5, taAdddress.getText());
            if (imagePath != null) {
                try {
                    InputStream is = new FileInputStream(new File(imagePath));
                    pst.setBlob(6, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                pst.setBlob(6, (Blob) null);
            }
            pst.setString(7, userId);
            pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("update");
        	alert.setContentText("Data BerHasil di update");
        	alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
     */

    private void updateDepartWithOutImage() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Update Departement set Deptname=?,DeprtSection=?,DeptContactNumbers=?,DeptContactAddress=?,OrgLogo=? where Id=1");

            pst.setString(1, tfDeptName.getText());
            pst.setString(2, tfSection.getText());
            pst.setString(3, taDeptContactNumber.getText());
            pst.setString(4, taAdddress.getText());

            pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("update");
        	alert.setContentText("Data BerHasil di update");
        	alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(DeptSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
