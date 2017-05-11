
package controller.application.maintenance;

import Getway.VendorGetway;
import custom.EffectUtility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import media.userNameMedia;
import DAL.Vendor;



public class AddVendorController implements Initializable {


    private String usrId;

    public String vendorId;

    private userNameMedia media;
    @FXML
    private TextField tfVendorName;
    @FXML
    private TextArea taVendorAddress;
    @FXML
    private TextArea taVendorDescription;
    @FXML
    public Button btnSave;
    @FXML
    private TextField taContactNumbers;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnClose;
    @FXML
    public Label lblCaption;
    
    private Stage primaryStage;
    @FXML
    private AnchorPane apContent;


    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    Vendor ovendor = new Vendor();
    VendorGetway vendorGetway = new VendorGetway();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (isNotNull()) {
        	ovendor.vendorName = tfVendorName.getText();
        	ovendor.vendorContactNumber = taContactNumbers.getText();
        	ovendor.vendorAddress = taVendorAddress.getText();
        	ovendor.vendorDescription = taVendorDescription.getText();
        	ovendor.creatorId = usrId;
            vendorGetway.save(ovendor);

            clrAll();
        }
    }

    public boolean isNotNull() {
        boolean isNotNull;
        if (tfVendorName.getText().trim().isEmpty()
                || tfVendorName.getText().trim().isEmpty()
                || taVendorAddress.getText().trim().isEmpty()
                || taVendorAddress.getText().trim().isEmpty()) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Please fill all requre field");
            alert.showAndWait();
        	
            isNotNull = false;

        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    private void clrAll() {
        tfVendorName.clear();
        taContactNumbers.clear();
        taVendorAddress.clear();
        taVendorDescription.clear();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if(isNotNull()) {
        	ovendor.id = vendorId;
        	ovendor.vendorName = tfVendorName.getText().trim();
        	ovendor.vendorContactNumber = taContactNumbers.getText().trim();
        	ovendor.vendorAddress = taVendorAddress.getText().trim();
        	ovendor.vendorDescription = taVendorDescription.getText().trim();
            vendorGetway.update(ovendor);
//            takeHistoy();
//            tfSearchOnType(event);
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.close();
    }

    public void showDetails(){
    	ovendor.id = vendorId;
        vendorGetway.selectedView(ovendor);
        tfVendorName.setText(ovendor.vendorName);
        taContactNumbers.setText(ovendor.vendorContactNumber);
        taVendorAddress.setText(ovendor.vendorAddress);
        taVendorDescription.setText(ovendor.vendorDescription);
    }

    @FXML
    private void apOnMouseDragged(MouseEvent event) {

    }

    @FXML
    private void apOnMousePressed(MouseEvent event) {

    }
    
    public void addSupplyerStage(Stage stage){
        EffectUtility.makeDraggable(stage, apContent);
    }
    
    
}
