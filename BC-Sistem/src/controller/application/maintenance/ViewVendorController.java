
package controller.application.maintenance;

import BLL.VendorBLL;
import Getway.VendorGetway;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import List.ListVendor;
import DAL.Vendor;
import media.userNameMedia;

public class ViewVendorController implements Initializable {
    @FXML
    public AnchorPane acContent;
    SQL sql = new SQL();
    Vendor vendor = new Vendor();
    VendorGetway vendorGetway = new VendorGetway();
    VendorBLL vendorBLL = new VendorBLL();
  

    private String usrId;
    private String creatorName;
    private String creatorId;
    private String vendorId;
    private String userName;

    private userNameMedia media;


    @FXML
    private TableView<ListVendor> tblVendor;
    @FXML
    private TableColumn<Object, Object> clmVendorId;
    @FXML
    private TableColumn<Object, Object> clmVendorName;
    @FXML
    private TableColumn<Object, Object> clmVendorPhoneNumber;
    @FXML
    private TableColumn<Object, Object> clmVendorAddress;
    @FXML
    private TableColumn<Object, Object> clmVendorJoining;
    @FXML
    private TableColumn<Object, Object> clmVendorDescription;

    private final static int dataSize = 10_023;
    private final static int rowsPerPage = 1000;
    @FXML
    private Button btnAdditems;
    @FXML
    private Button btnUpdate;
    
    @FXML
    private TextField tfSearch;
    private Text text;
    @FXML
    private MenuItem mbSearch;
    @FXML
    private Button btnRefresh;


    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void tblVendorOnClick(MouseEvent event) {
        int click = event.getClickCount();
        if (click == 2) {
            viewDetails();
        }

    }

    @FXML
    private void tblVendorOnKeyPress(KeyEvent event) {

    }


    @FXML
    public void tfSearchOnType(Event event) {
        vendor.vendorDetails.removeAll();
        vendor.vendorName = tfSearch.getText().trim();
        tblVendor.setItems(vendor.vendorDetails);
        clmVendorId.setCellValueFactory(new PropertyValueFactory<>("vendorId"));
        clmVendorName.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
        clmVendorPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("vendorPhoneNumber"));
        clmVendorAddress.setCellValueFactory(new PropertyValueFactory<>("vendorAddress"));
        clmVendorDescription.setCellValueFactory(new PropertyValueFactory<>("vendorDescription"));
        clmVendorJoining.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        vendorGetway.searchView(vendor);
    }


    public void showDetails() {
    	tblVendor.setItems(vendor.vendorDetails);
        clmVendorId.setCellValueFactory(new PropertyValueFactory<>("vendorId"));
        clmVendorName.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
        clmVendorPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("vendorPhoneNumber"));
        clmVendorAddress.setCellValueFactory(new PropertyValueFactory<>("vendorAddress"));
        clmVendorDescription.setCellValueFactory(new PropertyValueFactory<>("vendorDescription"));
        clmVendorJoining.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        vendorGetway.view(vendor);

    }





    @FXML
    private void btnAdditemsOnAction(ActionEvent event) {
        AddVendorController addVendorController = new AddVendorController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddVendor.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddVendorController vController = fxmlLoader.getController();
            media.setId(usrId);
            vController.setMedia(media);
            vController.lblCaption.setText("Add Vendor");
            vController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            vController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tfSearchOnType(event);


    }

    @FXML
    private void btnUpdateOnAction(Event event) {
        if(tblVendor.getSelectionModel().getSelectedItem() != null){
            viewDetails();
        }else {
            System.out.println("EMPTY SELECTION");
        }

    }

    private void viewDetails() {
        if(!tblVendor.getSelectionModel().isEmpty()){
            ListVendor selectedVendor = tblVendor.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedVendor.getVendorId());
            String items = selectedVendor.getVendorId();
            if (!items.equals(null)) {
                AddVendorController addVendorController = new AddVendorController();
                userNameMedia media = new userNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddVendor.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddVendorController supplyerController = fxmlLoader.getController();
                    media.setId(usrId);
                    supplyerController.setMedia(media);
                    supplyerController.lblCaption.setText("Vendor Details");
                    supplyerController.btnUpdate.setVisible(true);
                    supplyerController.btnSave.setVisible(false);
                    supplyerController.vendorId = selectedVendor.getVendorId();
                    supplyerController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("empty Selection");
        }



    }

    @FXML
    private void mbView(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void mbViewHistory(ActionEvent event) {
    }

    @FXML
    private void mbAddNew(ActionEvent event) {
    	btnAdditemsOnAction(event);
    }

    @FXML
    private void mbDeleteItem(ActionEvent event) {
    	 if(!tblVendor.getSelectionModel().isEmpty()){
        ListVendor selectedVendor = tblVendor.getSelectionModel().getSelectedItem();
        vendorId = selectedVendor.getVendorId();
        Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Delete");
    	alert.setContentText("Apakah anad yakin ingin menghapus " + "  '" + selectedVendor.getVendorName() + "' ??");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK){
        
            vendorGetway.delete(vendor);
            acContent.setOpacity(1);
            tblVendor.getItems().clear();
            tfSearchOnType(event);
        }else{
            System.out.println("NULL SELECTED");;
        }

    	 }	
            
    }

    @FXML
    private void mbEdit(ActionEvent event) {
        btnUpdateOnAction(event);
        tfSearchOnType(event);
    }

    @FXML
    private void mbSearch(ActionEvent event) {
        tblVendor.getSelectionModel().clearSelection();
        tfSearch.requestFocus();

    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
    	mbDeleteItem(event);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    	vendor.vendorDetails.clear();
        showDetails();
    }


}
