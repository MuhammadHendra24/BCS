/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.maintenance;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Getway.ItemMasterGateway;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

import DAL.ItemMaster;

import List.ListItem;


public class ViewItemController implements Initializable {

    ItemMaster itemMaster = new ItemMaster();
    ItemMasterGateway itemMasterGateway = new ItemMasterGateway();

    @FXML
    private AnchorPane acItemMainContent;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListItem> tblItem;
    @FXML
    private TableColumn<Object, Object> tblClmNameItem;
    @FXML
    private TableColumn<Object, Object> tblClmDescription;
    @FXML
    private TableColumn<Object, Object> tblClmUm;
    @FXML
    private TableColumn<Object, Object> tblClmAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmDates;

    String userId;

    userNameMedia nameMedia;
    @FXML
    private Button btnRefresh;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void tfSearchOnKeyReleased(Event event) {
    	SortedList<ListItem> sortList = new SortedList<>(itemMaster.itemList);
    	itemMaster.itemCode = tfSearch.getText().trim();
        tblItem.setItems(itemMaster.itemList);
        tblClmNameItem.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tblClmUm.setCellValueFactory(new PropertyValueFactory<>("um"));
        tblClmAddBy.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        tblClmDates.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        itemMasterGateway.searchView(itemMaster);
        
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        AddItemMasterController itemmaster = new AddItemMasterController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/maintenance/AddItemMaster.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddItemMasterController addItemMasterController = fXMLLoader.getController();
            media.setId(userId);
            addItemMasterController.setNameMedia(nameMedia);
            addItemMasterController.lblItemContent.setText("ADD ITEM");
            addItemMasterController.btnUpdate.setVisible(false);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (tblItem.getSelectionModel().getSelectedItem() != null) {
            selectedView();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (tblItem.getSelectionModel().getSelectedItem() != null) {
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Delete");
        	alert.setContentText("Apakah anda yakin ingin menghapus data ini?? ");
        	Optional<ButtonType> result = alert.showAndWait();
        	
        	if(result.get() == ButtonType.OK){
        	
                ListItem listItem = tblItem.getSelectionModel().getSelectedItem();
                String item = listItem.getId();
                itemMaster.id = item;
                itemMasterGateway.delete(itemMaster);
                tblItem.getItems().clear();
                viewDetails();
            }
        } else {
            System.out.println("EMPTY SELECTION");
        }

    }

    public void viewDetails() {
        tblItem.setItems(itemMaster.itemList);
        tblClmNameItem.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tblClmUm.setCellValueFactory(new PropertyValueFactory<>("um"));
        tblClmAddBy.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        tblClmDates.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        itemMasterGateway.view(itemMaster);
    }

    public void selectedView() {
        ListItem listItem = tblItem.getSelectionModel().getSelectedItem();
        String item = listItem.getId();
        if (!item.isEmpty()) {
            AddItemMasterController addItemMasterController = new AddItemMasterController();
            userNameMedia media = new userNameMedia();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/view/application/maintenance/AddItemMaster.fxml"));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddItemMasterController additemController = fXMLLoader.getController();
                media.setId(userId);
                additemController.setNameMedia(nameMedia);
                additemController.lblItemContent.setText("Item Details");
                additemController.btnSave.setVisible(false);
                additemController.itemId = listItem.getId();
                additemController.viewDetails();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void tblItemOnClick(MouseEvent event) {
        if(event.getClickCount() == 2){
            selectedView();
        }else{
            System.out.println("CLICK");
        }
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tfSearch.clear();
        itemMaster.itemList.clear();
        viewDetails();
    }
    
    
}
