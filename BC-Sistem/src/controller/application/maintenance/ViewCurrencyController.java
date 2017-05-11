/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.maintenance;

import dataBase.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import List.ListCurrency;
import List.ListCustomer;
import media.userNameMedia;
import DAL.Currency;
import Getway.CurrencyGetway;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import BLL.CurrencyBLL;
import BLL.SortDocBLL;

public class ViewCurrencyController implements Initializable {

   
    CurrencyGetway currencyGetway = new CurrencyGetway();
    Currency curenci = new Currency();
   
    
    private String usrId;
    private String creatorId;
    private String curenciId;

    private userNameMedia media;

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<ListCurrency> tblViewCurrencyDoc;
    @FXML
    private TableColumn<Object, Object> clmCurrencytId;
    @FXML
    private TableColumn<Object, Object> clmCurrencyName;
    @FXML
    private TableColumn<Object, Object> clmCurrencyDescription;
    @FXML
    private TableColumn<Object, Object> clmCurrencyCreator;
    @FXML
    private TableColumn<Object, Object> clmCurrencyCreateDate;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;

    

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void tblViewCurrencyOnClick(MouseEvent event) {
        if(event.getClickCount() == 2){
            viewDetails();
        }else{
            System.out.println(event.getClickCount());
        }
    }

    @FXML
    private void btnAddCurrencyOnAction(ActionEvent event) {
        AddCurrencyController addCurrencyController = new AddCurrencyController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddCurrency.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCurrencyController curencyController = fxmlLoader.getController();
            media.setId(usrId);
            curencyController.setNameMedia(media);
            curencyController.lblCurencyContent.setText("ADD CURRENCY");
            curencyController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
        	 Logger.getLogger(ViewCurrencyController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btnUpdateCurrencyOnAction(ActionEvent event) {
        if(tblViewCurrencyDoc.getSelectionModel().getSelectedItem() != null){
            viewDetails();
        }else{
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteCurrencyOnAction(ActionEvent event) {
        if(!tblViewCurrencyDoc.getSelectionModel().isEmpty()){
        ListCurrency selectedcurenci = tblViewCurrencyDoc.getSelectionModel().getSelectedItem();
        String currenci = selectedcurenci.getCurrency();
        curenciId = selectedcurenci.getCurrency();
            Alert alert = new Alert(AlertType.CONFIRMATION);              
             alert.setTitle("Delete");
             alert.setContentText("Apakah anda yaking ingin menghapus '" + curenci + "' ??");
             Optional<ButtonType> result = alert.showAndWait();
                    
             if(result.get() == ButtonType.OK){
                curenci.currency = curenciId;
                currencyGetway.delete(curenci);
                
                tfSearchOnKeyResele(event);
            }
        }else {
            System.out.println("NULL SELECTED");
        }


    }

    @FXML
    private void miSearchOnAction(ActionEvent event) {
        tfSearch.requestFocus();
    }

    @FXML
    private void miAddNewOnAction(ActionEvent event) {
    	btnAddCurrencyOnAction(event);
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
    	btnUpdateCurrencyOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
    	btnDeleteCurrencyOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        miUpdateOnAction(event);
    }

    public void showDetails() {
    	tblViewCurrencyDoc.setItems(curenci.currencyList);
    	clmCurrencytId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCurrencyName.setCellValueFactory(new PropertyValueFactory<>("currency"));
        clmCurrencyDescription.setCellValueFactory(new PropertyValueFactory<>("currencyDescription"));
        clmCurrencyCreator.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        clmCurrencyCreateDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        currencyGetway.view(curenci);
    }

    private void viewDetails() {
    	   ListCurrency listCurrency = tblViewCurrencyDoc.getSelectionModel().getSelectedItem();
           String item = listCurrency.getId();
            if (!item.equals(null)) {
                AddCurrencyController addcurrencyController = new AddCurrencyController();
                userNameMedia media = new userNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddCurrency.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddCurrencyController curController = fxmlLoader.getController();
                    media.setId(usrId);
                    curController.setNameMedia(media);
                    curController.lblCurencyContent.setText("ADD CURRENCY");
                    curController.btnSave.setVisible(false);
                    curController.curencyId = listCurrency.getId();
                    curController.viewDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                	 Logger.getLogger(ViewCurrencyController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
       



    }


    @FXML
    public void tfSearchOnKeyResele(Event event) {
        curenci.currencyList.clear();
        curenci.currency = tfSearch.getText().trim();
        tblViewCurrencyDoc.setItems(curenci.currencyList);
        clmCurrencytId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCurrencyName.setCellValueFactory(new PropertyValueFactory<>("currency"));
        clmCurrencyDescription.setCellValueFactory(new PropertyValueFactory<>("currencyDescription"));
        clmCurrencyCreator.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        clmCurrencyCreateDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        currencyGetway.searchView(curenci);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        curenci.currencyList.clear();
        showDetails();
    }
}
