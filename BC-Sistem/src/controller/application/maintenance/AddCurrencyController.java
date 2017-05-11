package controller.application.maintenance;

import BLL.CurrencyBLL;
import Getway.CurrencyGetway;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import media.userNameMedia;
import DAL.Currency;


public class AddCurrencyController implements Initializable {
	@FXML
    private TextField tfCurrency;
	@FXML
    private TextField tfICurrencyDescription;
	@FXML
    public Button btnSave;
    @FXML
    public Label lblCurencyContent;
    @FXML
    private Button btnClose;
    @FXML
    public Button btnUpdate;
    
    public String curencyId;
    
    private String userId;
    
    userNameMedia nameMedia;
    

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    
    Currency curenci = new Currency();
    CurrencyGetway currencyGetway = new CurrencyGetway();
    CurrencyBLL curencyBLL = new CurrencyBLL();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSaveCurrenciOnAction(ActionEvent event) {
    	curenci.currency= tfCurrency.getText().trim();
        curenci.currencyDescription = tfICurrencyDescription.getText().trim();
        curenci.userId = userId;
        curencyBLL.save(curenci);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnUpdateCurrencyOnAction(ActionEvent event) {
    	 curenci.id = curencyId;
         curenci.currency = tfCurrency.getText().trim();
         curenci.currencyDescription = tfICurrencyDescription.getText().trim();
         curencyBLL.update(curenci);
       
          }
    
    public void viewDetails(){
    	 curenci.id = curencyId;
         currencyGetway.selectedView(curenci);
         tfCurrency.setText(curenci.currency);
         tfICurrencyDescription.setText(curenci.currencyDescription);
         }
}
