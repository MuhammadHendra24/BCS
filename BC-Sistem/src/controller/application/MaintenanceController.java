
package controller.application;

import controller.ApplicationController;
import controller.application.maintenance.ViewCurrencyController;
import controller.application.maintenance.ViewCustomerController;
import controller.application.maintenance.ViewItemController;
import controller.application.maintenance.ViewSortDocController;
import controller.application.maintenance.ViewVendorController;
import dataBase.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.sun.crypto.provider.CipherWithWrappingSpi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


import media.userNameMedia;


public class MaintenanceController implements Initializable {
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private StackPane spMainContent;
    userNameMedia nameMedia;
    String usrId;

    private userNameMedia userId;
    @FXML
    public BorderPane bpStore;
    @FXML
    private Label lblHeader;
    @FXML
    private ToggleButton btnSortDok;
    @FXML
    private ToggleButton btnCurrency;
    @FXML
    private ToggleButton tbtnVendor;
    @FXML	
    private ToggleButton tbtnCustomer;
    @FXML
    private ToggleButton tbtnItem;
   
    
    public userNameMedia getUserId() {
        return userId;
    }

    public void setUserId(userNameMedia userId) {
        usrId = userId.getId();
        this.userId = userId;
    }
    
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ToggleGroup toggleGroup = new ToggleGroup();
        //tbtnCustomer.setToggleGroup(group);
        
        


    }


    @FXML
    public void btnStockOnAction(ActionEvent event) throws IOException {
    }
    
    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Customers");
        ViewCustomerController customerController = new ViewCustomerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/maintenance/ViewCustomer.fxml").openStream());
        media.setId(usrId);
        ViewCustomerController controller = fXMLLoader.getController();
        controller.setNameMedia(userId);
        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }
    @FXML
    private void tbtnItemOnAction(ActionEvent event) throws IOException {
    	lblHeader.setText("Item Master");
        ViewItemController itemController = new ViewItemController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/maintenance/ViewItem.fxml").openStream());
        media.setId(usrId);
        ViewItemController controller = fXMLLoader.getController();
        controller.setNameMedia(userId);
        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    
    @FXML
    private void tbtnVendorOnAction(ActionEvent event) throws IOException {
    	lblHeader.setText("Vendor");
         ViewVendorController vendorController = new ViewVendorController();
         userNameMedia media = new userNameMedia();
         FXMLLoader fXMLLoader = new FXMLLoader();
         fXMLLoader.load(getClass().getResource("/view/application/maintenance/ViewVendor.fxml").openStream());
         media.setId(usrId);
         ViewVendorController vcontroller = fXMLLoader.getController();
         vcontroller.setMedia(userId);
         vcontroller.showDetails();
         spMainContent.getChildren().clear();
         spMainContent.getChildren().add(fXMLLoader.getRoot());
     }

    @FXML
    private void btnCurrencyOnAction(ActionEvent event) throws IOException {
       lblHeader.setText("Currency");
       ViewCurrencyController vcc= new ViewCurrencyController();
       userNameMedia media = new userNameMedia();
       FXMLLoader fXMLLoader = new FXMLLoader();
       fXMLLoader.load(getClass().getResource("/view/application/maintenance/ViewCurency.fxml").openStream());
        media.setId(usrId);
       ViewCurrencyController CurrencyController = fXMLLoader.getController();
       CurrencyController.setMedia(userId);
       CurrencyController.showDetails();
       AnchorPane acPane = fXMLLoader.getRoot();
       spMainContent.getChildren().clear();
       spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSortDokOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Type Dokument");
        ViewSortDocController vuc = new ViewSortDocController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/maintenance/ViewSortDokumen.fxml").openStream());
        media.setId(usrId);
        ViewSortDocController viewsortController = fXMLLoader.getController();
        viewsortController.setMedia(userId);
        viewsortController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRmaOnAction(ActionEvent event) throws IOException {
       
    }

    @FXML
    private void btnRepoertsOnAction(ActionEvent event) {
    }
    
    public void settingPermission(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from UserPermission where id=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(2)==0 && rs.getInt(9) == 0){
                  btnSortDok.setDisable(true);
               }
                
                else{
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
