/*
hendra * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.incoming;

import DAL.IncOutGoing;
import Getway.IncomOutgoingGateway;
import List.ListCustomer;
import List.ListIncomingOutgoing;
import List.ListItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

public class ViewIncomingController implements Initializable{
    
    userNameMedia nameMedia;
       
    String userId;
    IncomOutgoingGateway incomOutgoingGateway = new IncomOutgoingGateway();
    IncOutGoing incOutGoing = new IncOutGoing();
    ObservableList<ListIncomingOutgoing> listIncoming = FXCollections.observableArrayList();
    
    @FXML
    private Button btntAddIncoming;
    @FXML
    private TableView<ListIncomingOutgoing> tblincomingView;
    @FXML
    private TableColumn<Object, Object> tblClmDocNo;
    @FXML
    private TableColumn<Object, Object> tblClmDocType;
    @FXML
    private TableColumn<Object, Object> tblClmDocDate;
    @FXML
    private TableColumn<Object, Object> tblClmVendor;
    @FXML
    private TableColumn<Object, Object> tblClmItemCodeview;
    @FXML
    private TableColumn<Object, Object> tblClmItemDescriptionview;
    @FXML
    private TableColumn<Object, Object> tblClmQtyGood;
    @FXML
    private TableColumn<Object, Object> tblClmQtyReject;
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
    private void AddIncomingOnAction(ActionEvent event) {
        System.out.println(userId);
        AddIncomingMaterials ac = new AddIncomingMaterials();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/incoming/AddIncomingMaterials.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddIncomingMaterials addincom = fXMLLoader.getController();
            media.setId(userId);
            addincom.setNameMedia(nameMedia);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setMaximized(false);
            stage.setMinHeight(0);
            stage.setMinWidth(0);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ViewIncomingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showDetails(){
        tblincomingView.setItems(incOutGoing.listIncoming);
        tblClmDocNo.setCellValueFactory(new PropertyValueFactory<>("noDoc"));
        tblClmDocType.setCellValueFactory(new PropertyValueFactory<>("sortdokumen"));
        tblClmVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        tblClmInvoice.setCellValueFactory(new PropertyValueFactory<>("noInvoice"));
        tblClmInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDo"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmDocDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDoc"));
        tblClmItemCodeview.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblClmItemDescriptionview.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmUM.setCellValueFactory(new PropertyValueFactory<>("U_m"));
        tblClmQtyGood.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
        tblClmCurrency.setCellValueFactory(new PropertyValueFactory<>("kurenci"));
        tblClmQtyReject.setCellValueFactory(new PropertyValueFactory<>("qtyreject"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmGWeight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
        tblClmAdd.setCellValueFactory(new PropertyValueFactory<>("addBy"));
         tblClmAddDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
         incomOutgoingGateway.view(incOutGoing);    
         }
    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
    SortedList<ListIncomingOutgoing> sortList = new SortedList<>(incOutGoing.listIncoming);
    tblincomingView.setItems(incOutGoing.listIncoming);
    incOutGoing.sortdokumen = tfSearch.getText();
    tblClmDocNo.setCellValueFactory(new PropertyValueFactory<>("noDoc"));
    tblClmDocType.setCellValueFactory(new PropertyValueFactory<>("sortdokumen"));
    tblClmVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
    tblClmInvoice.setCellValueFactory(new PropertyValueFactory<>("noInvoice"));
    tblClmInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDo"));
    tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
    tblClmDocDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDoc"));
    tblClmItemCodeview.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
    tblClmItemDescriptionview.setCellValueFactory(new PropertyValueFactory<>("description"));
    tblClmUM.setCellValueFactory(new PropertyValueFactory<>("U_m"));
    tblClmQtyGood.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
    tblClmCurrency.setCellValueFactory(new PropertyValueFactory<>("kurenci"));
    tblClmQtyReject.setCellValueFactory(new PropertyValueFactory<>("qtyreject"));
    tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
    tblClmGWeight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
    tblClmAdd.setCellValueFactory(new PropertyValueFactory<>("addBy"));
     tblClmAddDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
    incomOutgoingGateway.searchView(incOutGoing);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    	
       tblincomingView.getItems().clear();
        tfSearch.clear();
        showDetails();
      
    }
    
    public void selectedView() {
    	    ListIncomingOutgoing listItem = tblincomingView.getSelectionModel().getSelectedItem();
    	    String item = listItem.getId();
    	    if (!item.equals(null)) {
            AddIncomingMaterials addIncomingMaterials = new AddIncomingMaterials();
            userNameMedia media = new userNameMedia();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/view/application/incoming/AddIncomingMaterials.fxml"));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddIncomingMaterials incomingMaterials = fXMLLoader.getController();
                media.setId(userId);
                incomingMaterials.setNameMedia(nameMedia);
                //incomingMaterials.lblItemContent.setText("Item Details");
                //incomingMaterials.btnSave.setVisible(false);
                incomingMaterials.incomingId = listItem.getId();
                 incomingMaterials.viewDetails();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNIFIED);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewIncomingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void tblincomingOnClick(MouseEvent event) {
        if(event.getClickCount() == 2){
        	selectedView();
        }else{
            System.out.println("CLICK");
        }
    }
    @FXML
    public void tfSearchOnKeyResele(Event event) {
        incOutGoing.listIncoming.clear();
        incOutGoing.sortdokumen = tfSearch.getText().trim();
        tblincomingView.setItems(incOutGoing.listIncoming);
        tblClmDocNo.setCellValueFactory(new PropertyValueFactory<>("noDoc"));
        tblClmDocType.setCellValueFactory(new PropertyValueFactory<>("sortdokumen"));
        tblClmVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        tblClmInvoice.setCellValueFactory(new PropertyValueFactory<>("noInvoice"));
        tblClmInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDo"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmDocDate.setCellValueFactory(new PropertyValueFactory<>("dateNoDoc"));
        tblClmItemCodeview.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblClmItemDescriptionview.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmUM.setCellValueFactory(new PropertyValueFactory<>("U_m"));
        tblClmQtyGood.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
        tblClmCurrency.setCellValueFactory(new PropertyValueFactory<>("kurenci"));
        tblClmQtyReject.setCellValueFactory(new PropertyValueFactory<>("qtyreject"));
        tblClmAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblClmGWeight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
        tblClmAdd.setCellValueFactory(new PropertyValueFactory<>("addBy"));
         tblClmAddDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
         incomOutgoingGateway.view(incOutGoing); 
}
}
