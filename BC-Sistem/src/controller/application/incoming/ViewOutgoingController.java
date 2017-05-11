/*
hendra * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.incoming;


import List.ListIncomingOutgoing;
import List.ListItem;
import List.ListOutgoing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;

import DAL.Outgoing;
import Getway.OutgoingGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class ViewOutgoingController implements Initializable{
    
    userNameMedia nameMedia;
       
    String userId;
    OutgoingGateway outgoingGateway = new OutgoingGateway();
    Outgoing outgoing = new Outgoing();
    ObservableList<ListOutgoing> listOutgoings = FXCollections.observableArrayList();
    
    @FXML
    private Button btntAddOutgoing;
    @FXML
    private TableView<ListOutgoing> tblOutgoingView;
    @FXML
    private TableColumn<Object, Object> tblClmDocNo;
    @FXML
    private TableColumn<Object, Object> tblClmDocType;
    @FXML
    private TableColumn<Object, Object> tblClmDocDate;
    @FXML
    private TableColumn<Object, Object> tblClmCustomer;
    @FXML
    private TableColumn<Object, Object> tblClmItemCodeview;
    @FXML
    private TableColumn<Object, Object> tblClmItemDescriptionview;
    @FXML
    private TableColumn<Object, Object> tblClmQtyGood;
    @FXML
    private TableColumn<Object, Object> tblClmUM;
    @FXML
    private TableColumn<Object, Object> tblClmInvoice;
    @FXML
    private TableColumn<Object, Object> tblClmInvoiceDate;
    @FXML
    private TableColumn<Object, Object> tblClmAmount;
    @FXML
    private TableColumn<Object, Object> tblClmCurrency;
    @FXML
    private TableColumn<Object, Object>  tblClmGWeight;
    @FXML
    private TableColumn<Object, Object> tblClmAdd;
    @FXML
    private TableColumn<Object, Object> tblClmAddDate;
    @FXML
    private TextField tfSearch;
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
    private void AddOutgoingOnAction(ActionEvent event) {
        System.out.println(userId);
        AddOutgoingMaterials ac = new AddOutgoingMaterials();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/incoming/AddOutgoingMaterials.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddOutgoingMaterials addOutgoing = fXMLLoader.getController();
            media.setId(userId);
            addOutgoing.setNameMedia(nameMedia);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setMaximized(false);
            stage.setMinHeight(0);
            stage.setMinWidth(0);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ViewOutgoingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showDetails(){
        tblOutgoingView.setItems(outgoing.listoutgoing);
        tblClmDocNo.setCellValueFactory(new PropertyValueFactory<>("noDoc"));
        tblClmDocType.setCellValueFactory(new PropertyValueFactory<>("sortdokumen"));
        tblClmCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tblClmInvoice.setCellValueFactory(new PropertyValueFactory<>("noInvoice"));
        tblClmInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDo"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmDocDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDoc"));
        tblClmItemCodeview.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblClmItemDescriptionview.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmUM.setCellValueFactory(new PropertyValueFactory<>("U_m"));
        tblClmQtyGood.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
        tblClmCurrency.setCellValueFactory(new PropertyValueFactory<>("kurenci"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmGWeight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
        tblClmAdd.setCellValueFactory(new PropertyValueFactory<>("addBy"));
         tblClmAddDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
         outgoingGateway.view(outgoing);    }
    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
    	SortedList<ListOutgoing> sortList = new SortedList<>(outgoing.listoutgoing);
        tblOutgoingView.setItems(outgoing.listoutgoing);
        outgoing.sortdokumen = tfSearch.getText();
        tblClmDocNo.setCellValueFactory(new PropertyValueFactory<>("noDoc"));
        tblClmDocType.setCellValueFactory(new PropertyValueFactory<>("sortdokumen"));
        tblClmCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tblClmInvoice.setCellValueFactory(new PropertyValueFactory<>("noInvoice"));
        tblClmInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDo"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmDocDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDoc"));
        tblClmItemCodeview.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblClmItemDescriptionview.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmUM.setCellValueFactory(new PropertyValueFactory<>("U_m"));
        tblClmQtyGood.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
        tblClmCurrency.setCellValueFactory(new PropertyValueFactory<>("kurenci"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmGWeight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
        tblClmAdd.setCellValueFactory(new PropertyValueFactory<>("addBy"));
         tblClmAddDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
       outgoingGateway.searchView(outgoing);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
      
      tblOutgoingView.getItems().clear();
      tfSearch.clear();
      showDetails();
    }
    
    public void selectedView() {
    	    ListOutgoing listItem = tblOutgoingView.getSelectionModel().getSelectedItem();
    	    String item = listItem.getId();
    	    if (!item.equals(null)) {
    	    AddOutgoingMaterials addOutgoingMaterials = new AddOutgoingMaterials();
            userNameMedia media = new userNameMedia();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/view/application/incoming/AddOutgoingMaterials.fxml"));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddOutgoingMaterials outgoingMaterials = fXMLLoader.getController();
                media.setId(userId);
                outgoingMaterials.setNameMedia(nameMedia);
                outgoingMaterials.outgoingId = listItem.getId();
                outgoingMaterials.viewDetails();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNIFIED);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewOutgoingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void tblOutgoingOnClick(MouseEvent event) {
        if(event.getClickCount() == 2){
        	selectedView();
        }else{
            System.out.println("CLICK");
        }
    }
}
