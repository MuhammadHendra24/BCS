
package controller.application.maintenance;


import java.net.URL;
import java.util.ResourceBundle;

import BLL.SortDocBLL;
import Getway.SortDocGetway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import media.userNameMedia;
import custom.*;
import dataBase.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import DAL.SortDoc;



public class AddSortDocController implements Initializable {

	
    public String sortId;
    @FXML
    public Button btnSave;
    @FXML
    private TextField tfSortDocName;
    
    @FXML
    private Button btnClrSortDocName;
    @FXML
    private Button btnClrDescripDocName;
    
    public String sortsId;
    
    private String usrId;
    
    userNameMedia nameMedia;
    
    CustomTf ctf = new CustomTf();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    private TextField tfSortDesDoc;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblContent;
    @FXML
    private Button btnClose;

   

    public void setNameMedia(userNameMedia nameMedia) {
        usrId =  nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    SortDoc sortdoc = new SortDoc();
    SortDocGetway sortdocGetway = new SortDocGetway();
    SortDocBLL sortDocBLL = new SortDocBLL();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
    }    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    	sortdoc.sortDocName = tfSortDocName.getText().trim();
        sortdoc.sortDocDescription = tfSortDesDoc.getText().trim();
        sortdoc.userId= usrId;
        sortDocBLL.save(sortdoc);
        
        
       
    }

   @FXML
 private void btnUpdateOnAction(ActionEvent event) {
	sortdoc.id=sortsId;
	sortdoc.sortDocName = tfSortDocName.getText().trim();
    sortdoc.sortDocDescription = tfSortDesDoc.getText().trim();
    sortDocBLL.update(sortdoc);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

	

    public void viewDetails(){
   	    sortdoc.id = sortsId;
        sortdocGetway.selectedView(sortdoc);
        tfSortDocName.setText(sortdoc.sortDocName);
        tfSortDesDoc.setText(sortdoc.sortDocDescription);
        }
}