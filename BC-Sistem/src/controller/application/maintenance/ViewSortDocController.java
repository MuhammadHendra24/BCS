/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.maintenance;

import Getway.SortDocGetway;
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
import List.ListSortDoc;
import media.userNameMedia;
import DAL.SortDoc;

import BLL.SortDocBLL;

public class ViewSortDocController implements Initializable {

   
    SortDocGetway sortGetway = new SortDocGetway();
    SortDoc sortdoc = new SortDoc();
    
    
    private String usrId;
    private String sortId;
    
    private userNameMedia media;

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<ListSortDoc> tblViewSortDoc;
    @FXML
    private TableColumn<Object, Object> clmSortId;
    @FXML
    private TableColumn<Object, Object> clmSortDokName;
    @FXML
    private TableColumn<Object, Object> clmSortDokDescription;
    @FXML
    private TableColumn<Object, Object> clmSortCreator;
    @FXML
    private TableColumn<Object, Object> clmSorrtCreateDate;
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
    private void tblViewSortOnClick(MouseEvent event) {
        if(event.getClickCount() == 2){
            viewDetails();
        }else{
            System.out.println(event.getClickCount());
        }
    }

    @FXML
    private void btnAddSortDokOnAction(ActionEvent event) {
        AddSortDocController addSortDocController = new AddSortDocController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddSortDokumen.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddSortDocController docController = fxmlLoader.getController();
            media.setId(usrId);
            docController.setNameMedia(media);
            docController.lblContent.setText("ADD SORT DOCUMENT");
            docController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if(tblViewSortDoc.getSelectionModel().getSelectedItem() != null){
            viewDetails();
        }else{
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if(!tblViewSortDoc.getSelectionModel().isEmpty()){
        ListSortDoc selectedSortdok = tblViewSortDoc.getSelectionModel().getSelectedItem();
        String sortDocName = selectedSortdok.getSortDocName();
        sortId = selectedSortdok.getId();
            Alert alert = new Alert(AlertType.CONFIRMATION);              
             alert.setTitle("Delete");
             alert.setContentText("Apakah anda yakin ingin menghapus data ini??");
             Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                sortdoc.id = sortId;
                sortGetway.delete(sortdoc);
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
        btnAddSortDokOnAction(event);
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        miUpdateOnAction(event);
    }
  
    public void showDetails() {
    	tblViewSortDoc.setItems(sortdoc.ListSortDetail);
    	clmSortId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmSortDokName.setCellValueFactory(new PropertyValueFactory<>("sortDocName"));
        clmSortDokDescription.setCellValueFactory(new PropertyValueFactory<>("sortDocDescription"));
        clmSortCreator.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        clmSorrtCreateDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        sortGetway.view(sortdoc);
    }

    private void viewDetails() {
    	 ListSortDoc listSortDoc = tblViewSortDoc.getSelectionModel().getSelectedItem();
         String item = listSortDoc.getId();
         if (!item.equals(null)) {
              AddSortDocController addSortDocController = new AddSortDocController();
              userNameMedia media = new userNameMedia();
              FXMLLoader fxmlLoader = new FXMLLoader();
              fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddSortDokumen.fxml"));
              try {
                  fxmlLoader.load();
                  Parent parent = fxmlLoader.getRoot();
                  Scene scene = new Scene(parent);
                  scene.setFill(new Color(0, 0, 0, 0));
                  AddSortDocController sortDocController = fxmlLoader.getController();
                  media.setId(usrId);
                  sortDocController.setNameMedia(media);
                 sortDocController.lblContent.setText("ADD CURRENCY");
                  sortDocController.btnSave.setVisible(false);
                  sortDocController.sortsId = listSortDoc.getId();
                  sortDocController.viewDetails();
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
        sortdoc.ListSortDetail.clear();
        sortdoc.sortDocName = tfSearch.getText().trim();
        tblViewSortDoc.setItems(sortdoc.ListSortDetail);
        clmSortId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmSortDokName.setCellValueFactory(new PropertyValueFactory<>("sortDocName"));
        clmSortDokDescription.setCellValueFactory(new PropertyValueFactory<>("sortDocDescription"));
        clmSortCreator.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        clmSorrtCreateDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        sortGetway.view(sortdoc);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        sortdoc.ListSortDetail.clear();
        showDetails();
    }
}
