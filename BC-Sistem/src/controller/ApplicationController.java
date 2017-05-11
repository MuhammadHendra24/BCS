package controller;
import DAL.IncOutGoing;
/**
 * @author Hendra
 *
 * Oct 18, 2016
 */
import DAL.Users;
import Getway.IncomOutgoingGateway;
import Getway.UsersGetway;

import controller.application.UserController;
import controller.application.incoming.Report;
import controller.application.IncomingController;
import controller.application.SettingsController;
import controller.application.MaintenanceController;
import dataBase.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.TabableView;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import media.userNameMedia;

public class ApplicationController implements Initializable {

    @FXML
    private StackPane acContent;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnClose;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMaintenance;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnIncoming;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnAbout;
    @FXML
    private Label lblUsrName;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Label lblUserId;
    @FXML
    private MenuBar MenBar;
    @FXML
    private MenuItem MenuReport;
    @FXML
    private MenuItem MenItem;
    @FXML
    private MenuItem MenuMaintenence;
    @FXML
    private MenuItem MenuIncoming;
    @FXML
    private MenuItem MenuOutgoing;
    @FXML
    private MenuItem MenuUser;
    @FXML
    private MenuItem MenuSettings;
    @FXML
    private MenuItem MenuExit;
	
	
    String usrName;
    String id;

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();

    private userNameMedia usrNameMedia;

    public userNameMedia getUsrNameMedia() {
        return usrNameMedia;
    }

    public void setUsrNameMedia(userNameMedia usrNameMedia) {
        lblUserId.setText(usrNameMedia.getId());
        lblUsrName.setText(usrNameMedia.getUsrName());
        id = usrNameMedia.getId();
        usrName = usrNameMedia.getUsrName();

        this.usrNameMedia = usrNameMedia;
    }

    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");
    Image image;
    IncomOutgoingGateway incomOutgoingGateway = new IncomOutgoingGateway();
    IncOutGoing incOutGoing = new IncOutGoing();


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgMenuBtn.setImage(menuImage);
        

       

    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (sideMenuToogleBtn.isSelected()) {
            imgMenuBtn.setImage(menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(-130);
            sideMenu.play();
            acDashBord.getChildren().clear();
        } else {
            imgMenuBtn.setImage(menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(130);
            sideMenu.play();
            acDashBord.getChildren().add(leftSideBarScroolPan);
        }
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        acContent.getChildren().clear();
        acContent.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
        acDashBord.getChildren().clear();
        acHead.getChildren().clear();
        acHead.setMaxHeight(0.0);
    }

    @FXML
    private void acMain(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) acMain.getScene().getWindow();
            stage.setFullScreen(true);
        }
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event){
        homeActive();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/application/home/Home.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);
        System.out.println(lblUsrName.getText());
        System.out.println(lblUserId.getText());

    }

    @FXML
    private void btnMaintenanceOnClick(ActionEvent event) throws IOException {
    	MaintenanceActive();
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/Maintenance.fxml").openStream());
        nm.setId(id);
        MaintenanceController mainController = fXMLLoader.getController();
        mainController.bpStore.getStylesheets().add("/style/MainStyle.css");
        mainController.setUserId(usrNameMedia);
        mainController.btnStockOnAction(event);
        mainController.settingPermission();
        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }
        @FXML
        private void btnIncomingOnClick(ActionEvent event) throws IOException {
            IncomingActive();
            userNameMedia nm = new userNameMedia();
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.load(getClass().getResource("/view/application/Incoming.fxml").openStream());
                nm.setId(id);
                IncomingController incomingController = fXMLLoader.getController();
                incomingController.setNameMedia(usrNameMedia);
                incomingController.acMainSells.getStylesheets().add("/style/MainStyle.css");
                incomingController.tbtnIncomingOnAction(event);
                AnchorPane anchorPane = fXMLLoader.getRoot();
                acContent.getChildren().clear();
                acContent.getChildren().add(anchorPane);
        
        
    }
        @FXML
        private void btnOutgoingClick(ActionEvent event) throws IOException {
            IncomingActive();
            userNameMedia nm = new userNameMedia();
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.load(getClass().getResource("/view/application/Incoming.fxml").openStream());
                nm.setId(id);
                IncomingController incomingController = fXMLLoader.getController();
                incomingController.setNameMedia(usrNameMedia);
                incomingController.acMainSells.getStylesheets().add("/style/MainStyle.css");
                incomingController.tbtnOutgoingOnAction(event);
                AnchorPane anchorPane = fXMLLoader.getRoot();
                acContent.getChildren().clear();
                acContent.getChildren().add(anchorPane);
        
        
    }


    @FXML
    private void btnUserOnClick(ActionEvent event) throws IOException {
        userActive();
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/User.fxml").openStream());
        nm.setId(id);
        UserController UserController = fXMLLoader.getController();
        UserController.bpContent.getStylesheets().add("/style/MainStyle.css");
        UserController.setNameMedia(usrNameMedia);
        UserController.btnViewUserOnAction(event);
        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);

    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) throws IOException {
        MyAccountsActive();
        userNameMedia usrMedia = new userNameMedia();
        FXMLLoader settingLoader = new FXMLLoader();
        settingLoader.load(getClass().getResource("/view/application/Settings.fxml").openStream());
        usrMedia.setId(id);
        SettingsController settingControl = settingLoader.getController();
        settingControl.bpSettings.getStylesheets().add("/style/MainStyle.css");
        settingControl.setUsrMedia(usrMedia);
        settingControl.miMyASccountOnClick(event);
        settingControl.settingPermission();
        AnchorPane acPane = settingLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(acPane);

    }
    @FXML
    private void btnAboutOnClick(ActionEvent event) {
        try {
            aboutActive();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/application/about/AboutMe.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void btnReportClik(ActionEvent actionEvent) {
            Report report = new Report();
            userNameMedia media = new userNameMedia();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/application/incoming/Report.fxml"));
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                Report clikreport = fxmlLoader.getController();
                media.setUsrName(usrName);
                clikreport.setNameMedia(media);
                //sortController.lblContent.setText("ADD Sort Doc");
                //sortController.btnUpdate.setVisible(false);
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.UNDECORATED);
                nStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    


   
    @FXML
    private void hlUpdateAccount(ActionEvent event) {

    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {

    }

    @FXML
    private void acMainOnMouseMove(MouseEvent event) {

    }

    public void permission() {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from UserPermission where UserId=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(17) == 0) {
                    btnUser.setDisable(true);
                }
                if (rs.getInt(15) == 0) {
                    btnIncoming.setDisable(true);
                } else {

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void homeActive() {
       
    }

    private void MaintenanceActive() {
       
    }

    private void IncomingActive() {
       
    }

    private void  userActive() {
       
    }

    private void MyAccountsActive() {
        
    }

    private void aboutActive() {
        
    }

    public void viewDetails() {
        users.id = id;
        usersGetway.selectedView(users);
        lblFullName.setText(users.fullName);
        lblUsrNamePopOver.setText(users.userName);
    }
    @FXML
    private void MenuExitnAction(ActionEvent event) {
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
     	   alert.setTitle("Exit");
     	    alert.setContentText("Apa anda yakin pengin keluar");
     	    Optional<ButtonType> result = alert.showAndWait();
     	    if(result.get() == ButtonType.OK){
    	 ((Stage)acContent.getScene().getWindow()).close();
    	
    }
} 
}
    
   