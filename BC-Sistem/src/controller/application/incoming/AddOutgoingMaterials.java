
package controller.application.incoming;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAL.Currency;
import DAL.Customer;
import DAL.ItemMaster;
import DAL.Outgoing;
import DAL.SortDoc;
import Getway.CurrencyGetway;
import Getway.CustomerGetway;
import Getway.ItemMasterGateway;
import Getway.OutgoingGateway;
import Getway.SortDocGetway;
import List.ListCurrency;
import List.ListCustomer;
import List.ListItem;
import List.ListOutgoing;
import List.ListSortDoc;
import List.ListIncomingOutgoing;
import controller.application.maintenance.AddCustomerController;
import controller.application.maintenance.AddItemMasterController;
import controller.application.maintenance.AddSortDocController;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

/**
 * @author Hendra
 *
 * Nov 17, 2016
 */
public class AddOutgoingMaterials implements Initializable{
	public Button btnAddSortDoc;
    public Button btnAddItemCode;
    public Button btnAddCustomer;
    
    @FXML
    private MenuButton mbtnCustomer;
    @FXML
    private MenuButton mbtnItemCode;
    @FXML
    private MenuButton mbtnSortDokument;
    @FXML
    private MenuButton mbtnCurrency;
    
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnSave1;
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnDelete2;
    @FXML
    public Button btnDelete;
 
    String customerId;
    String itemId;
    private String userId;
  
	@FXML
    private TextField tfnoid;
	@FXML
    private TextField tfsortdoc;
	@FXML
    private TextField tfCustomerSearch;
	@FXML
    private TextField tfCustomer;
	@FXML
    private TextField tfUM;
	@FXML
    private TextField tfitemSearch;
	@FXML
    private TextField tfNoInvoice;
	@FXML
    private TextField tfNoDO;
	@FXML
    private TextField tfNoDoc;
	@FXML
    private TextField tfItemCode;
	@FXML
    private TextField tfDescription;
	@FXML
    private TextField tfQgood;
	@FXML
    private TextField tfamount;
	@FXML
    private TextField tfGw;
	@FXML
    private TextField tfSortDokument;
	@FXML
    private TextField tfCurrency;
	@FXML
    private TextField tfCurrencySearch;
	@FXML
    private TextField tfSortDocumentSearch;
	@FXML
	private TableView<ListSortDoc> tblSortdocumentSortView;
	@FXML
	private TableView<ListCurrency> tblCurrencySortView;
	@FXML
	private TableView<ListItem> tblItemSortView;
	@FXML
	private TableView<ListCustomer> tblCustomerSortView;
	@FXML
	private TableColumn<Object, Object> tblClmCurrencyName;
	@FXML
	private TableColumn<Object, Object> tblClmSelectDocument;
	@FXML
	private TableColumn<Object, Object> tblClmSelectDescription;
	@FXML
	private TableColumn<Object, Object> tblClmCustomerName;
	@FXML
	private TableColumn<Object, Object> tblClmCustomerAddress;
	
	@FXML
    private DatePicker docDate;
	@FXML
    private DatePicker DoDate;
	@FXML
	private TableView<ListOutgoing> tblOutgoing;
	@FXML
	private TableColumn<Object, Object> tblClmItemCode;
	@FXML
	private TableColumn<Object, Object> tblClmItemDescription;
	@FXML
	private TableColumn<Object, Object> tblClmUm;
	@FXML
	private TableColumn<Object, Object> tblClmQuantyGoodView;
	@FXML
	private TableColumn<Object, Object> tblClmQtyRejectView;
	@FXML
	private TableColumn<Object, Object> tblClmPrice;
	@FXML
	private TableColumn<Object, Object> tblClmGweight;
	
	@FXML
	private TableColumn<Object, Object> tblClmItemCodeView;
	@FXML
	private TableColumn<Object, Object> tblClmItemDescriptionView;
	@FXML
	private TableColumn<Object, Object> tblClmUmView;

	public String outgoingId;
    public String sortId;
    private String currencyId;
    private String sortdokid;
    private String customerid;
    private String itemcodeid;
    private String id;
    private String Outid;
   
    
    
	SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
	

    
    userNameMedia nameMedia;

   
    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
        
    }
    SortDoc sortDoc = new SortDoc();
    SortDocGetway sortDocGetway = new SortDocGetway();
    OutgoingGateway outgoingGateway = new OutgoingGateway();
    Outgoing outgoing = new Outgoing();
	Currency currency = new Currency();
	CurrencyGetway currencyGetway = new CurrencyGetway();
    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();
    ItemMaster itemmaster = new ItemMaster();
    ItemMasterGateway itemMasterGatway = new ItemMasterGateway();
    
    ObservableList<ListIncomingOutgoing> listIncoming = FXCollections.observableArrayList();
    ObservableList<ListCustomer> listCustomers = FXCollections.observableArrayList();
    ObservableList<ListItem> itemList = FXCollections.observableArrayList();
    ObservableList<ListCurrency> currencyList = FXCollections.observableArrayList();
 
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	 

	 
	  public void btnAddSortDocumentOnAction(ActionEvent actionEvent) {
	        AddSortDocController addsortdocontroller = new AddSortDocController();
	        userNameMedia media = new userNameMedia();
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddSortDokumen.fxml"));
	        try {
	            fxmlLoader.load();
	            Parent parent = fxmlLoader.getRoot();
	            Scene scene = new Scene(parent);
	            scene.setFill(new Color(0, 0, 0, 0));
	            AddSortDocController sortController = fxmlLoader.getController();
	            media.setId(userId);
	            sortController.setNameMedia(media);
	            sortController.lblContent.setText("Add Type Documents");
	            sortController.btnUpdate.setVisible(false);
	            Stage nStage = new Stage();
	            nStage.setScene(scene);
	            nStage.initModality(Modality.APPLICATION_MODAL);
	            nStage.initStyle(StageStyle.TRANSPARENT);
	            nStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  public void btnAddCustomerOnAction(ActionEvent actionEvent) {
	        AddCustomerController addCustomerController = new AddCustomerController();
	        userNameMedia media = new userNameMedia();
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddCustomer.fxml"));
	        try {
	            fxmlLoader.load();
	            Parent parent = fxmlLoader.getRoot();
	            Scene scene = new Scene(parent);
	            scene.setFill(new Color(0, 0, 0, 0));
	            AddCustomerController customerController = fxmlLoader.getController();
	            media.setId(userId);
	            customerController.setNameMedia(media);
	            customerController.lblCustomerContent.setText("ADD CUSTOMER");
	            customerController.btnUpdate.setVisible(false);
	            Stage nStage = new Stage();
	            nStage.setScene(scene);
	            nStage.initModality(Modality.APPLICATION_MODAL);
	            nStage.initStyle(StageStyle.TRANSPARENT);
	            nStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  public void btnAddItemMasterOnAction(ActionEvent actionEvent) {
	        AddItemMasterController additemcontroller = new AddItemMasterController();
	        userNameMedia media = new userNameMedia();
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddItemMaster.fxml"));
	        try {
	            fxmlLoader.load();
	            Parent parent = fxmlLoader.getRoot();
	            Scene scene = new Scene(parent);
	            scene.setFill(new Color(0, 0, 0, 0));
	           AddItemMasterController itemMasterController = fxmlLoader.getController();
	            media.setId(userId);
	            itemMasterController.setNameMedia(media);;
	            itemMasterController.lblItemContent.setText("ADD ITEM MASTER");
	            itemMasterController.btnUpdate.setVisible(false);
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
	    private void tblSortDocOnClick(MouseEvent event) {
	        tfSortDokument.setText(tblSortdocumentSortView.getSelectionModel().getSelectedItem().getSortDocName());
	        sortdokid = tblSortdocumentSortView.getSelectionModel().getSelectedItem().getId();
	        System.out.println(sortdokid);
	  } 
	  
	  @FXML
	    private void mbtnSortDocClicked(MouseEvent event) {
	        sortDoc.sortDocName = tfSortDocumentSearch.getText().trim();
	        tblSortdocumentSortView.setItems(sortDoc.ListSortDetail);
	        tblClmSelectDocument.setCellValueFactory(new PropertyValueFactory<>("sortDocName"));
	        tblClmSelectDescription.setCellValueFactory(new PropertyValueFactory<>("sortDocDescription"));
	        sortDocGetway.searchView(sortDoc);
         
	  }
	  @FXML
	    private void tfSortdocSearchOnKeyReleased(KeyEvent event) {
	        sortDoc.sortDocName = tfSortDocumentSearch.getText().trim();
	        tblSortdocumentSortView.setItems(sortDoc.ListSortDetail);
	        tblClmSelectDocument.setCellValueFactory(new PropertyValueFactory<>("sortDocName"));
	        tblClmSelectDescription.setCellValueFactory(new PropertyValueFactory<>("sortDocDescription"));
	        sortDocGetway.searchView(sortDoc);
	    } 
	  @FXML
	    private void tblCurrencyOnClick(MouseEvent event) {
	        tfCurrency.setText(tblCurrencySortView.getSelectionModel().getSelectedItem().getCurrency());
	        currencyId = tblCurrencySortView.getSelectionModel().getSelectedItem().getId();
	        System.out.println(currencyId);
	  } 
	  
	  @FXML
	    private void mbtnCurrencyOnClicked(MouseEvent event) {
	        currency.currency= tfCurrencySearch.getText().trim();
	        tblCurrencySortView.setItems(currency.currencyList);
	        tblClmCurrencyName.setCellValueFactory(new PropertyValueFactory<>("currency"));
	        currencyGetway.searchView(currency);
       
	  }
	  @FXML
	    private void tfCurrencySearchOnKeyReleased(KeyEvent event) {
	        currency.currency = tfCurrencySearch.getText().trim();
	        tblCurrencySortView.setItems(currency.currencyList);
	        tblClmCurrencyName.setCellValueFactory(new PropertyValueFactory<>("currency"));
	        currencyGetway.searchView(currency);
	    } 
	  @FXML
	    private void tblCustomerOnClick(MouseEvent event) {
	        tfCustomer.setText(tblCustomerSortView.getSelectionModel().getSelectedItem().getCustomerName());
	        customerId = tblCustomerSortView.getSelectionModel().getSelectedItem().getId();
	        System.out.println(customerId);
	  } 
	  
	  @FXML
	    private void mbtnCusClicked(MouseEvent event) {
	        customer.customerName = tfCustomerSearch.getText().trim();
	        tblCustomerSortView.setItems(customer.customerList);
	        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
	        tblClmCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
	        customerGetway.searchView(customer);
           
	  }
	  @FXML
	    private void tfCustomerSearchOnKeyReleased(KeyEvent event) {
		    customer.customerName = tfCustomerSearch.getText().trim();
	        tblCustomerSortView.setItems(customer.customerList);
	        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
	        tblClmCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
	        customerGetway.searchView(customer);
	    } 
	  @FXML
	    private void tblItemOnClick(MouseEvent event) {
	       tfItemCode.setText(tblItemSortView.getSelectionModel().getSelectedItem().getItemCode());
	        tfDescription.setText(tblItemSortView.getSelectionModel().getSelectedItem().getItemDescription());
	        tfUM.setText(tblItemSortView.getSelectionModel().getSelectedItem().getUm());
		    itemId = tblItemSortView.getSelectionModel().getSelectedItem().getId();
	        System.out.println(itemId);
	        
	  }
	  @FXML
	    private void mbtnItemOnClicked(MouseEvent event) {
	        itemmaster.itemCode = tfitemSearch.getText().trim();
	        tblItemSortView.setItems(itemmaster.itemList);
	        tblClmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
	        tblClmItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
	        tblClmUm.setCellValueFactory(new PropertyValueFactory<>("um"));
	        itemMasterGatway.searchView(itemmaster);

	  }
	  @FXML
	    private void tfItemMasterSearchOnKeyReleased(KeyEvent event) {
	        itemmaster.itemCode = tfitemSearch.getText().trim();
	        tblItemSortView.setItems(itemmaster.itemList);
	        tblClmItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
	        tblClmItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
	        tblClmUm.setCellValueFactory(new PropertyValueFactory<>("um"));
	        itemMasterGatway.searchView(itemmaster);
	    }
	  public void viewDetails() {
		     outgoing.id = outgoingId;
		     outgoingGateway.selectedView(outgoing);
		     tfnoid.setText(outgoing.id);
		     tfSortDokument.setText(outgoing.sortdokumen); 
		     tfCurrency.setText(outgoing.kurenci);
	         tfCustomer.setText(outgoing.customer); 
	         tfItemCode.setText(outgoing.itemCode);
	         tfDescription.setText(outgoing.description);
	         tfNoDO.setText(outgoing.noDo);
	         tfNoDoc.setText(outgoing.noDoc);
	         tfUM.setText(outgoing.U_m);
	         tfQgood.setText(outgoing.qtygood);
	         docDate.setValue(LocalDate.parse(outgoing.dateNoDoc));
	         DoDate.setValue(LocalDate.parse(outgoing.dateNoDo));
	         tfamount.setText(outgoing.price);
	         tfQgood.setText(outgoing.qtygood);
	         tfGw.setText(outgoing.grosWeigh);
	         tfNoInvoice.setText(outgoing.noInvoice);
		     tblOutgoing.setItems(outgoing.listoutgoing);
	  }
	  public void viewDetails2() {
		     outgoing.id = outgoingId;
		     outgoingGateway.selectedView(outgoing);
	         tblClmItemCodeView.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
	         tblClmItemDescriptionView.setCellValueFactory(new PropertyValueFactory<>("description"));
	         tblClmUmView.setCellValueFactory(new PropertyValueFactory<>("U_m"));
	         tblClmQuantyGoodView.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
	         tblClmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
	         tblClmGweight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
	         outgoingGateway.view(outgoing);
	  }     
	       public void showd() {
	         ListOutgoing listItem = tblOutgoing.getSelectionModel().getSelectedItem();
	         AddOutgoingMaterials addOutgoingMaterials = new AddOutgoingMaterials();
	         addOutgoingMaterials.id = tblOutgoing.getSelectionModel().getSelectedItem().getId();
	         tfnoid.setText(outgoing.id);
	         tfSortDokument.setText(outgoing.sortdokumen); 
			 tfCurrency.setText(outgoing.kurenci);
	         tfCustomer.setText(outgoing.customer);
	         tfItemCode.setText(outgoing.itemCode);
	         tfDescription.setText(outgoing.description);
	         tfNoDO.setText(outgoing.noDo);
	         tfNoDoc.setText(outgoing.noDoc);
	         tfUM.setText(outgoing.U_m);
	         tfQgood.setText(outgoing.qtygood);
	         docDate.setValue(LocalDate.parse(outgoing.dateNoDoc));
	         DoDate.setValue(LocalDate.parse(outgoing.dateNoDo));
	         tfamount.setText(outgoing.price);
	         tfQgood.setText(outgoing.qtygood);
	         tfGw.setText(outgoing.grosWeigh);
	         tfNoInvoice.setText(outgoing.noInvoice);
	         outgoingGateway.view(outgoing);
	  }
	  	  
	  
	        private boolean isNotNull() {
	            boolean insNotNull = false;
	            if (tfCustomer.getText().trim().isEmpty()
	                    && tfSortDokument.getText().isEmpty()
	                    || tfCurrency.getText().isEmpty()
	                    && tfDescription.getText().isEmpty()
	                    || tfGw.getText().isEmpty()
	                    || tfItemCode.getText().isEmpty()
	                    || tfNoDO.getText().isEmpty() 
		            	|| tfNoDoc.getText().isEmpty()
	                    || tfNoInvoice.getText().isEmpty()
	                    || tfQgood.getText().isEmpty()
	                    || tfamount.getText().isEmpty()
	                    || tfUM.getText().isEmpty()
	                    
	                         ) {
	                   
	             insNotNull = false;
	        } else {
	            insNotNull = true;
	        }
	        return insNotNull;
	    }

	        @FXML
	        private void btnSaveOnAction(ActionEvent event) {
	        	 if (isNotNull()) {
	        		 outgoing.id = outgoingId;    	
	        		 outgoing.itemCode = tfItemCode.getText().trim();
	        		 outgoing.customer = tfCustomer.getText().trim();
	        		 outgoing.dateNoDoc = docDate.getValue().toString();
	        		 outgoing.dateNoDo = DoDate.getValue().toString();
	        		 outgoing.noDo = tfNoDO.getText().trim();
	        		 outgoing.noDoc = tfNoDoc.getText().trim();
	        		 outgoing.qtygood = tfQgood.getText().trim();
	        		 outgoing.description = tfDescription.getText().trim();
	        		 outgoing.price = tfamount.getText().trim();
	        		 outgoing.noInvoice = tfNoInvoice.getText().trim();	
	        		 outgoing.kurenci = tfCurrency.getText().trim();
	        		 outgoing.sortdokumen =tfSortDokument.getText().trim();
	        		 outgoing.U_m = tfUM.getText().trim();
	        		 outgoing.grosWeigh = tfGw.getText().trim();
	        		 outgoing.description =tfDescription.getText().trim();
	        		 outgoing.userId =userId;
		             outgoingGateway.save(outgoing);
		             //incOutGoing.listIncoming.clear();
		             Alert alert = new Alert(AlertType.INFORMATION);
		                alert.setTitle("Save");
		               	alert.setContentText("Data Berhasil di simpan");
		             	alert.showAndWait();
		             	
		             	viewDetails();
		             	clrAll();
	        	 } else {
		               Alert alert = new Alert(AlertType.ERROR);
		              	alert.setContentText("Erro harap di isi semua");
		             	alert.showAndWait();
		                 	
	        }
	        }
	        	 private void clrAll() {
	        	        tfItemCode.clear();
	        	        tfDescription.clear();
	        	        tfUM.clear();
	        	        tfQgood.clear();
	        	       tfamount.clear();
	        	        tfGw.clear();
	        	    }
	        	 private void clrAll2() {
	        		     tfItemCode.clear();
	        	        tfDescription.clear();
	        	        tfUM.clear();
	        	        tfQgood.clear();
	        	        tfamount.clear();
	        	        tfGw.clear();
	        	        tfNoDO.clear();
	        	        tfNoDoc.clear();
	        	        tfNoInvoice.clear();
	        	        tfCurrency.clear();
	        	        tfSortDokument.clear();
	       	            tfCustomer.clear();
	       	            tfnoid.clear();
	        	        
	        	    }
	        	 @FXML
	        	    private void btnRefreshOnAction(ActionEvent event) {
	        		 outgoing.listoutgoing.clear();
	        		 btnAdd.setVisible(true);
	        		 btnSave.setVisible(true);
	        		 btnSave1.setVisible(true);
	        		 btnEdit.setVisible(false);
	        		 btnDelete.setVisible(true);
	        		 clrAll2();
	        		  viewDetails();
	        		  viewDetails2();
	        		  
	        	 }
	        	 @FXML
	        	    private void tblOutgoingOnClick(MouseEvent event) {
	        	        if(event.getClickCount() == 2){
	        	       btnAdd.setVisible(false);
	 	        	   btnSave.setVisible(false);
	 	        	   btnDelete.setVisible(false);
	 	        	   btnEdit.setVisible(true);
	 	        	   btnSave1.setVisible(false);
	 	        	   tfnoid.setText(tblOutgoing.getSelectionModel().getSelectedItem().getId());
	        	       tfItemCode.setText(tblOutgoing.getSelectionModel().getSelectedItem().getItemCode());
	        	 	   tfDescription.setText(tblOutgoing.getSelectionModel().getSelectedItem().getDescription());
	        	 	   tfUM.setText(tblOutgoing.getSelectionModel().getSelectedItem().getU_m());
	        	 	   tfCurrency.setText(tblOutgoing.getSelectionModel().getSelectedItem().getKurenci());
	        	 	   tfSortDokument.setText(tblOutgoing.getSelectionModel().getSelectedItem().getSortdokumen());
	        	 	   tfamount.setText(tblOutgoing.getSelectionModel().getSelectedItem().getPrice());
	        	 	   tfNoDO.setText(tblOutgoing.getSelectionModel().getSelectedItem().getNoDo());
	        	 	   tfNoDoc.setText(tblOutgoing.getSelectionModel().getSelectedItem().getNoDoc());
	        	 	   tfNoInvoice.setText(tblOutgoing.getSelectionModel().getSelectedItem().getNoInvoice());
	        	 	   tfQgood.setText(tblOutgoing.getSelectionModel().getSelectedItem().getQtygood());
	        	 	   tfGw.setText(tblOutgoing.getSelectionModel().getSelectedItem().getGrosWeigh());
	        	 	   tfCustomer.setText(tblOutgoing.getSelectionModel().getSelectedItem().getCustomer());
	        	 	   docDate.setValue(LocalDate.now());
	     	           DoDate.setValue(LocalDate.now());
	     	           outgoing.userId =userId;
	     	           outgoing.listoutgoing.clear();
			           viewDetails2();
	        	       
	        	        }else{
	        	            System.out.println("CLICK");
	        	        }
	        	 }
	        	        @FXML
		        	    private void btnAddOnAction(ActionEvent event) {
		        		 outgoing.listoutgoing.clear();
		        		  clrAll2();
		        		  viewDetails2();
	        	   }
		        		
		       @FXML
		       private void btnSaveOnAction2(ActionEvent event) {
		      	        	 if (isNotNull()) {
			      	        	 outgoing.id = outgoingId;    	
			   	        		 outgoing.itemCode = tfItemCode.getText().trim();
			   	        		 outgoing.customer = tfCustomer.getText().trim();
			   	        		 outgoing.dateNoDoc = docDate.getValue().toString();
			   	        		 outgoing.dateNoDo = DoDate.getValue().toString();
			   	        		 outgoing.noDo = tfNoDO.getText().trim();
			   	        		 outgoing.noDoc = tfNoDoc.getText().trim();
			   	        		 outgoing.qtygood = tfQgood.getText().trim();
			   	        		 outgoing.description = tfDescription.getText().trim();
			   	        		 outgoing.price = tfamount.getText().trim();
			   	        		 outgoing.noInvoice = tfNoInvoice.getText().trim();	
			   	        		 outgoing.kurenci = tfCurrency.getText().trim();
			   	        		 outgoing.sortdokumen =tfSortDokument.getText().trim();
			   	        		 outgoing.U_m = tfUM.getText().trim();
			   	        		 outgoing.grosWeigh = tfGw.getText().trim();
			   	        		 outgoing.description =tfDescription.getText().trim();
			   	        		 outgoing.userId =userId;
			   		             outgoingGateway.save(outgoing);
		      		             Alert alert = new Alert(AlertType.INFORMATION);
		      		                alert.setTitle("");
		      		               	alert.setContentText("Data Telah di Simpan");
		      		             	alert.showAndWait();
		      		             	
		      		             	clrAll2();
		      	        	 } else {
		      		               Alert alert = new Alert(AlertType.ERROR);
		      		              	alert.setContentText("Erro harap di isi semua");
		      		             	alert.showAndWait();		             	
                              }
 		                      }
		       @FXML
		       private void btnUpdateOnAction(ActionEvent event) {
		          if(isNotNull()) {
		        	  outgoing.id = outgoingId;    	
		        		 outgoing.itemCode = tfItemCode.getText().trim();
		        		 outgoing.customer = tfCustomer.getText().trim();
		        		 outgoing.dateNoDoc = docDate.getValue().toString();
		        		 outgoing.dateNoDo = DoDate.getValue().toString();
		        		 outgoing.noDo = tfNoDO.getText().trim();
		        		 outgoing.noDoc = tfNoDoc.getText().trim();
		        		 outgoing.qtygood = tfQgood.getText().trim();
		        		 outgoing.description = tfDescription.getText().trim();
		        		 outgoing.price = tfamount.getText().trim();
		        		 outgoing.noInvoice = tfNoInvoice.getText().trim();	
		        		 outgoing.kurenci = tfCurrency.getText().trim();
		        		 outgoing.sortdokumen =tfSortDokument.getText().trim();
		        		 outgoing.U_m = tfUM.getText().trim();
		        		 outgoing.grosWeigh = tfGw.getText().trim();
		        		 outgoing.description =tfDescription.getText().trim();
		        		 outgoingGateway.update(outgoing);
	   		              btnSave.setVisible(true);
	   		              btnEdit.setVisible(false);
	   		              btnAdd.setVisible(true);
	   		              btnAdd.setVisible(true);
   		           
   		           clrAll2();
    		       viewDetails2();
    	        	 } else {
    		               Alert alert = new Alert(AlertType.ERROR);
    		             	alert.setContentText("Erro harap di isi semua");
    		             alert.showAndWait();		             	
                    }
                    }
		       @FXML
		       private void mbDeleteItem(ActionEvent event) {
		       	 if(!tblOutgoing.getSelectionModel().isEmpty()){
		           ListOutgoing selectedOutgoing = tblOutgoing.getSelectionModel().getSelectedItem();
		           Outid = selectedOutgoing.getNoDoc();
		           Alert alert = new Alert(AlertType.CONFIRMATION);
		       	   alert.setTitle("Delete");
		       	    alert.setContentText("Apakah anda yakin ing meghapus Doc " + "  '" + selectedOutgoing.getNoDoc() + "' ??");
		       	    Optional<ButtonType> result = alert.showAndWait();
		       	    if(result.get() == ButtonType.OK){
		           
		               outgoingGateway.delete(outgoing);
		               tblOutgoing.getItems().clear();
		               viewDetails();
		               viewDetails2();
		                }else{
		               System.out.println("NULL SELECTED");;
		           }

		       	 }	
		               
		       }
}
