
package controller.application.incoming;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAL.Currency;
import DAL.IncOutGoing;
import DAL.ItemMaster;
import DAL.SortDoc;
import DAL.Vendor;
import Getway.CurrencyGetway;
import Getway.IncomOutgoingGateway;
import Getway.ItemMasterGateway;
import Getway.SortDocGetway;
import Getway.VendorGetway;
import List.ListCurrency;

import List.ListItem;
import List.ListSortDoc;
import List.ListIncomingOutgoing;
import List.ListVendor;
import controller.application.maintenance.AddItemMasterController;
import controller.application.maintenance.AddSortDocController;
import controller.application.maintenance.AddVendorController;
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
public class AddIncomingMaterials implements Initializable{
	public Button btnAddSortDoc;
    public Button btnAddItemCode;
    public Button btnAddVendor;
    
    @FXML
    private MenuButton mbtnVendor;
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
 
    String vendorId;
    String itemId;
    private String userId;
  
	@FXML
    private TextField tfnoid;
	@FXML
    private TextField tfsortdoc;
	@FXML
    private TextField tfVendorSearch;
	@FXML
    private TextField tfVendor;
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
    private TextField tfQReject;
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
	private TableView<ListVendor> tblVendorSortView;
	@FXML
	private TableColumn<Object, Object> tblClmCurrencyName;
	@FXML
	private TableColumn<Object, Object> tblClmSelectDocument;
	@FXML
	private TableColumn<Object, Object> tblClmSelectDescription;
	@FXML
	private TableColumn<Object, Object> tblClmVendorName;
	@FXML
	private TableColumn<Object, Object> tblClmVendorAddress;
	
	@FXML
    private DatePicker docDate;
	@FXML
    private DatePicker DoDate;
	@FXML
	private TableView<ListIncomingOutgoing> tblIncoming;
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

	public String incomingId;
    public String sortId;
    private String currencyId;
    private String sortdokid;
    private String vendorid;
    private String itemcodeid;
    private String id;
    private String incomid;
   
    
    
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
    IncomOutgoingGateway incomOutgoingGateway = new IncomOutgoingGateway();
    IncOutGoing incOutGoing = new IncOutGoing();
	Currency currency = new Currency();
	CurrencyGetway currencyGetway = new CurrencyGetway();
    Vendor vendor = new Vendor();
    ItemMaster itemmaster = new ItemMaster();
    VendorGetway vendorGetway = new VendorGetway();
    ItemMasterGateway itemMasterGatway = new ItemMasterGateway();
    
    ObservableList<ListIncomingOutgoing> listIncoming = FXCollections.observableArrayList();
    ObservableList<ListVendor> vendorDetails = FXCollections.observableArrayList();
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
	            sortController.lblContent.setText("Add Type Document");
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
	  
	  public void btnAddVendorOnAction(ActionEvent actionEvent) {
	        AddVendorController addvendorcontroller = new AddVendorController();
	        userNameMedia media = new userNameMedia();
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/view/application/maintenance/AddVendor.fxml"));
	        try {
	            fxmlLoader.load();
	            Parent parent = fxmlLoader.getRoot();
	            Scene scene = new Scene(parent);
	            scene.setFill(new Color(0, 0, 0, 0));
	            AddVendorController vendorController = fxmlLoader.getController();
	            media.setId(userId);
	            vendorController.setMedia(media);;
	            vendorController.lblCaption.setText("ADD VENDOR");
	            vendorController.btnUpdate.setVisible(false);
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
	    private void tblVendorOnClick(MouseEvent event) {
	        tfVendor.setText(tblVendorSortView.getSelectionModel().getSelectedItem().getVendorName());
	        vendorId = tblVendorSortView.getSelectionModel().getSelectedItem().getVendorId();
	        System.out.println(vendorId);
	  } 
	  
	  @FXML
	    private void mbtnVendorOnClicked(MouseEvent event) {
	        vendor.vendorName = tfVendorSearch.getText().trim();
	        tblVendorSortView.setItems(vendor.vendorDetails);
	        tblClmVendorName.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
	        tblClmVendorAddress.setCellValueFactory(new PropertyValueFactory<>("vendorAddress"));
	        vendorGetway.searchView(vendor);
           
	  }
	  @FXML
	    private void tfVendorSearchOnKeyReleased(KeyEvent event) {
	        vendor.vendorName = tfVendorSearch.getText().trim();
	        tblVendorSortView.setItems(vendor.vendorDetails);
	        tblClmVendorName.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
	        tblClmVendorAddress.setCellValueFactory(new PropertyValueFactory<>("vendorAddress"));
	        vendorGetway.searchView(vendor);
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
		    incOutGoing.id = incomingId;
		    incomOutgoingGateway.selectedView(incOutGoing);
		    tfnoid.setText(incOutGoing.id);
		    tfSortDokument.setText(incOutGoing.sortdokumen); 
		    tfCurrency.setText(incOutGoing.kurenci);
	          tfVendor.setText(incOutGoing.vendor); 
	         tfItemCode.setText(incOutGoing.itemCode);
	         tfDescription.setText(incOutGoing.description);
	         tfNoDO.setText(incOutGoing.noDo);
	         tfNoDoc.setText(incOutGoing.noDoc);
	         tfUM.setText(incOutGoing.U_m);
	         tfQgood.setText(incOutGoing.qtygood);
	         tfQReject.setText(incOutGoing.qtyreject);
	         docDate.setValue(LocalDate.parse(incOutGoing.dateNoDoc));
	         DoDate.setValue(LocalDate.parse(incOutGoing.dateNoDo));
	         tfamount.setText(incOutGoing.price);
	         tfQgood.setText(incOutGoing.qtygood);
	         tfGw.setText(incOutGoing.grosWeigh);
	         tfNoInvoice.setText(incOutGoing.noInvoice);
		     tblIncoming.setItems(incOutGoing.listIncoming);
	  }
	  public void viewDetails2() {
		    incOutGoing.id = incomingId;
		    incomOutgoingGateway.selectedView(incOutGoing);
	        tblClmItemCodeView.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
	         tblClmItemDescriptionView.setCellValueFactory(new PropertyValueFactory<>("description"));
	         tblClmUmView.setCellValueFactory(new PropertyValueFactory<>("U_m"));
	        tblClmQuantyGoodView.setCellValueFactory(new PropertyValueFactory<>("qtygood"));
	        tblClmQtyRejectView.setCellValueFactory(new PropertyValueFactory<>("qtyreject"));
	         tblClmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
	        tblClmGweight.setCellValueFactory(new PropertyValueFactory<>("grosWeigh"));
	        incomOutgoingGateway.view(incOutGoing);
	  }     
	       public void showd() {
	         ListIncomingOutgoing listItem = tblIncoming.getSelectionModel().getSelectedItem();
	         AddIncomingMaterials addIncomingMaterials = new AddIncomingMaterials();
	         addIncomingMaterials.id = tblIncoming.getSelectionModel().getSelectedItem().getId();
	         tfnoid.setText(incOutGoing.id);
	         tfSortDokument.setText(incOutGoing.sortdokumen); 
			 tfCurrency.setText(incOutGoing.kurenci);
	         tfVendor.setText(incOutGoing.vendor);
	         tfItemCode.setText(incOutGoing.itemCode);
	         tfDescription.setText(incOutGoing.description);
	         tfNoDO.setText(incOutGoing.noDo);
	         tfNoDoc.setText(incOutGoing.noDoc);
	         tfUM.setText(incOutGoing.U_m);
	         tfQgood.setText(incOutGoing.qtygood);
	         tfQReject.setText(incOutGoing.qtyreject);
	         docDate.setValue(LocalDate.parse(incOutGoing.dateNoDoc));
	         DoDate.setValue(LocalDate.parse(incOutGoing.dateNoDo));
	         tfamount.setText(incOutGoing.price);
	         tfQgood.setText(incOutGoing.qtygood);
	         tfGw.setText(incOutGoing.grosWeigh);
	         tfNoInvoice.setText(incOutGoing.noInvoice);
	         incomOutgoingGateway.view(incOutGoing);
	  }
	  	  
	  
	        private boolean isNotNull() {
	            boolean insNotNull = false;
	            if (tfVendor.getText().trim().isEmpty()
	                    && tfSortDokument.getText().isEmpty()
	                    ||tfCurrency.getText().isEmpty()
	             
	                    && tfDescription.getText().isEmpty()
	                    || tfGw.getText().isEmpty()
	                    || tfItemCode.getText().isEmpty()
	                    || tfNoDO.getText().isEmpty() 
		            	|| tfNoDoc.getText().isEmpty()
	                    || tfNoInvoice.getText().isEmpty()
	                    || tfQgood.getText().isEmpty()
	                    || tfQReject.getText().isEmpty()
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
	        		 incOutGoing.id = incomingId;    	
	        		 incOutGoing.itemCode = tfItemCode.getText().trim();
		             incOutGoing.vendor = tfVendor.getText().trim();
		             incOutGoing.dateNoDoc = docDate.getValue().toString();
		             incOutGoing.dateNoDo = DoDate.getValue().toString();
		             incOutGoing.noDo = tfNoDO.getText().trim();
		             incOutGoing.noDoc = tfNoDoc.getText().trim();
		             incOutGoing.qtygood = tfQgood.getText().trim();
		             incOutGoing.qtyreject = tfQReject.getText().trim();
		             incOutGoing.description = tfDescription.getText().trim();
		             incOutGoing.price = tfamount.getText().trim();
		             incOutGoing.noInvoice = tfNoInvoice.getText().trim();	
		             incOutGoing.kurenci = tfCurrency.getText().trim();
		             incOutGoing.sortdokumen =tfSortDokument.getText().trim();
		             incOutGoing.U_m = tfUM.getText().trim();
		             incOutGoing.grosWeigh = tfGw.getText().trim();
		             incOutGoing.description =tfDescription.getText().trim();
		             incOutGoing.userId =userId;
		             incomOutgoingGateway.save(incOutGoing);
		             
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
	        	        tfQReject.clear();
	        	        tfamount.clear();
	        	        tfGw.clear();
	        	    }
	        	 private void clrAll2() {
	        		     tfItemCode.clear();
	        	        tfDescription.clear();
	        	        tfUM.clear();
	        	        tfQgood.clear();
	        	        tfQReject.clear();
	        	        tfamount.clear();
	        	        tfGw.clear();
	        	        tfNoDO.clear();
	        	        tfNoDoc.clear();
	        	        tfNoInvoice.clear();
	        	         tfCurrency.clear();
	        	        tfSortDokument.clear();
	       	            tfVendor.clear();
	       	            tfnoid.clear();
	        	        
	        	    }
	        	 @FXML
	        	    private void btnRefreshOnAction(ActionEvent event) {
	        		 incOutGoing.listIncoming.clear();
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
	        	    private void tblincomingOnClick(MouseEvent event) {
	        	        if(event.getClickCount() == 2){
	        	       btnAdd.setVisible(false);
	 	        	   btnSave.setVisible(false);
	 	        	   btnDelete.setVisible(false);
	 	        	   btnEdit.setVisible(true);
	 	        	   btnSave1.setVisible(false);
	 	        	  tfnoid.setText(tblIncoming.getSelectionModel().getSelectedItem().getId());
	        	       tfItemCode.setText(tblIncoming.getSelectionModel().getSelectedItem().getItemCode());
	        	 	   tfDescription.setText(tblIncoming.getSelectionModel().getSelectedItem().getDescription());
	        	 	   tfUM.setText(tblIncoming.getSelectionModel().getSelectedItem().getU_m());
	        	 	   tfCurrency.setText(tblIncoming.getSelectionModel().getSelectedItem().getKurenci());
	        	 	   tfSortDokument.setText(tblIncoming.getSelectionModel().getSelectedItem().getSortdokumen());
	        	 	   tfamount.setText(tblIncoming.getSelectionModel().getSelectedItem().getPrice());
	        	 	   tfNoDO.setText(tblIncoming.getSelectionModel().getSelectedItem().getNoDo());
	        	 	   tfNoDoc.setText(tblIncoming.getSelectionModel().getSelectedItem().getNoDoc());
	        	 	   tfNoInvoice.setText(tblIncoming.getSelectionModel().getSelectedItem().getNoInvoice());
	        	 	   tfQgood.setText(tblIncoming.getSelectionModel().getSelectedItem().getQtygood());
	        	 	   tfQReject.setText(tblIncoming.getSelectionModel().getSelectedItem().getQtyreject());
	        	 	    tfGw.setText(tblIncoming.getSelectionModel().getSelectedItem().getGrosWeigh());
	        	 	   tfVendor.setText(tblIncoming.getSelectionModel().getSelectedItem().getVendor());
	        	 	   docDate.setValue(LocalDate.parse(incOutGoing.dateNoDoc));
	     	           DoDate.setValue(LocalDate.parse(incOutGoing.dateNoDo));
	     	           incOutGoing.userId =userId;
			           incOutGoing.listIncoming.clear();
			           viewDetails2();
	        	       
	        	        }else{
	        	            System.out.println("CLICK");
	        	        }
	        	 }
	        	        @FXML
		        	    private void btnAddOnAction(ActionEvent event) {
		        		 incOutGoing.listIncoming.clear();
		        		  clrAll2();
		        		  viewDetails2();
	        	   }
		        		
		       @FXML
		       private void btnSaveOnAction2(ActionEvent event) {
		      	        	 if (isNotNull()) {
		      	        		 incOutGoing.id =incomingId;    	
		      	        		 incOutGoing.itemCode = tfItemCode.getText().trim();
		      		             incOutGoing.vendor = tfVendor.getText().trim();
		      		             incOutGoing.dateNoDoc = docDate.getValue().toString();
		      		             incOutGoing.dateNoDo = DoDate.getValue().toString();
		      		             incOutGoing.noDo = tfNoDO.getText().trim();
		      		             incOutGoing.noDoc = tfNoDoc.getText().trim();
		      		             incOutGoing.qtygood = tfQgood.getText().trim();
		      		             incOutGoing.qtyreject = tfQReject.getText().trim();
		      		             incOutGoing.description = tfDescription.getText().trim();
		      		             incOutGoing.price = tfamount.getText().trim();
		      		             incOutGoing.noInvoice = tfNoInvoice.getText().trim();	
		      		             incOutGoing.kurenci = tfCurrency.getText().trim();
		      		             incOutGoing.sortdokumen =tfSortDokument.getText().trim();
		      		             incOutGoing.U_m = tfUM.getText().trim();
		      		             incOutGoing.grosWeigh = tfGw.getText().trim();
		      		             incOutGoing.description =tfDescription.getText().trim();
		      		             incOutGoing.userId =userId;
		      		             incomOutgoingGateway.save(incOutGoing);
		      		             Alert alert = new Alert(AlertType.INFORMATION);
		      		                alert.setTitle("");
		      		               	alert.setContentText("Data Telah Disimpan");
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
		        	  incOutGoing.id =incomingId;    	
   	        		 incOutGoing.itemCode = tfItemCode.getText().trim();
   		             incOutGoing.vendor = tfVendor.getText().trim();
   		             incOutGoing.dateNoDoc = docDate.getValue().toString();
   		             incOutGoing.dateNoDo = DoDate.getValue().toString();
   		             incOutGoing.noDo = tfNoDO.getText().trim();
   		             incOutGoing.noDoc = tfNoDoc.getText().trim();
   		             incOutGoing.qtygood = tfQgood.getText().trim();
   		             incOutGoing.qtyreject = tfQReject.getText().trim();
   		             incOutGoing.description = tfDescription.getText().trim();
   		             incOutGoing.price = tfamount.getText().trim();
   		             incOutGoing.noInvoice = tfNoInvoice.getText().trim();	
   		             incOutGoing.kurenci = tfCurrency.getText().trim();
   		             incOutGoing.sortdokumen =tfSortDokument.getText().trim();
   		             incOutGoing.U_m = tfUM.getText().trim();
   		             incOutGoing.grosWeigh = tfGw.getText().trim();
   		             incOutGoing.description =tfDescription.getText().trim();
   		              incomOutgoingGateway.update(incOutGoing);
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
		       	 if(!tblIncoming.getSelectionModel().isEmpty()){
		           ListIncomingOutgoing selectedincoming = tblIncoming.getSelectionModel().getSelectedItem();
		           incomid = selectedincoming.getNoDoc();
		           Alert alert = new Alert(AlertType.CONFIRMATION);
		       	   alert.setTitle("Delete");
		       	    alert.setContentText("Apakah anda yakin ing meghapus Doc " + "  '" +selectedincoming.getNoDoc() + "' ??");
		       	    Optional<ButtonType> result = alert.showAndWait();
		       	    if(result.get() == ButtonType.OK){
		           
		               incomOutgoingGateway.delete(incOutGoing);
		               tblIncoming.getItems().clear();
		               viewDetails();
		               viewDetails2();
		                }else{
		               System.out.println("NULL SELECTED");;
		           }

		       	 }	
		               
		       }
		       
}
