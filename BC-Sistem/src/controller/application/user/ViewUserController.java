
package controller.application.user;

import Getway.UsersGetway;
import dataBase.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import media.userNameMedia;
import custom.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;

import List.ListUser;

import DAL.Users;



public class ViewUserController implements Initializable {

    CustomPf cPf = new CustomPf();
    CustomTf cTf = new CustomTf();
    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();

    private File file;
    private BufferedImage bufferedImage;
    private String imagePath;
    private Image image;
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    private String userId;
    private String name;
    private String id;
    private userNameMedia nameMedia;
    private String creatorId;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private TextField tfSearch;
    @FXML
    private TextField tfCreatePassword;
    @FXML
    private Rectangle recUsrImage;
    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfDateofJoin;
    @FXML
    private TextField tfCreatedBy;
    @FXML
    public Button btnClrFulNametf;
    @FXML
    public Button btnClrEmailtf;
    @FXML
    public Button btnClrPasword;
    @FXML
    private CheckBox cbStatus;
    @FXML
    private Hyperlink hlChangePassword;
    @FXML
    private Hyperlink hlViewPermission;
    @FXML
    private TableView<ListUser> tbllUserList;
    @FXML
    private TableColumn<Object, Object> clmUserId;
    @FXML
    private TableColumn<Object, Object> clmUserName;
    @FXML
    private Label lblCreator;


    Image usrImg = new Image("/image/rifat.jpg");


    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        name = nameMedia.getUsrName();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	CustomTf cTf = new CustomTf();
        cTf.clearTextFieldByButton(tfFullName, btnClrFulNametf);
        cTf.clearTextFieldByButton(tfEmailAddress, btnClrEmailtf);
        cTf.clearTextFieldByButton(tfCreatePassword, btnClrPasword);
          }

    @FXML
    private void tfSearchOnAction(ActionEvent event) {

    }

    @FXML
    private void btnAttachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            recUsrImage.setFill(new ImagePattern(image));
            imagePath = file.getAbsolutePath();
        }

    }

    @FXML
    private void tblViewOnClick(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            setselectedView();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            setselectedView();
        }
    }

    public void tblEmloyeOnClick(Event event) {
        setselectedView();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws FileNotFoundException {

        users.userName = tfUserName.getText();
        users.fullName = tfFullName.getText();
        users.emailAddress = tfEmailAddress.getText();
        users.password = tfCreatePassword.getText();
        users.image = usrImg;
        if (cbStatus.isSelected()) {
            users.status = "1";
        } else {
            users.status = "0";
        }
        users.imagePath = imagePath;
        users.creatorId = userId;
        usersGetway.update(users);
        
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Delete");
    	alert.setContentText("Apakah akan di hapus " + "  '" + tfUserName.getText() + "' ??");
    	Optional<ButtonType> result = alert.showAndWait();
    	  	
        if(result.get() == ButtonType.OK){
            usersGetway.selectedView(users);
            usersGetway.delete(users);
        }

    }

    @FXML
    private void cbOnAction(ActionEvent event) {
            if (cbStatus.isSelected()) {
                cbStatus.setText("Active");
            } else {
                cbStatus.setText("Deactive");
            }
    }

    @FXML
    private void hlChangePasswordOnAction(ActionEvent event) {


    }

    @FXML
    private void hlViewPermissionOnAction(ActionEvent event) throws IOException {
        usersGetway.selectedView(users);
        id = users.id;

       
        FXMLLoader loader = new FXMLLoader();
        System.out.println(id);
        loader.setLocation(getClass().getResource("/view/application/user/UserPermission.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root); scene.setFill(new Color(0, 0, 0, 0));
        UserPermissionController PermissionController = loader.getController();
        nameMedia.setId(id);
        PermissionController.setMedia(nameMedia); PermissionController.checqPermission();
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL); nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();
    }



    public void setselectedView() {
        clearAll();
        ListUser UserList = tbllUserList.getSelectionModel().getSelectedItem();
        if ( UserList!= null) {
            users.id = UserList.getUid();
            usersGetway.selectedView(users);
            id = users.id;
            tfUserName.setText(users.userName);
            tfFullName.setText(users.fullName);
            tfEmailAddress.setText(users.emailAddress);
            tfCreatePassword.setText(users.password);
            tfDateofJoin.setText(users.date);
            creatorId = users.creatorId;
            image = users.image;
            recUsrImage.setFill(new ImagePattern(image));
            sql.creatorNameFindar(creatorId, lblCreator);
            tfCreatedBy.setText(lblCreator.getText());
            if (users.status.matches("1")) {
                cbStatus.setSelected(true); cbStatus.setText("Active");
            } else if (users.status.matches("0")) {
                cbStatus.setSelected(false); cbStatus.setText("Deactive");
            }
            if(users.id.matches("1")){
                btnUpdate.setVisible(false); btnDelete.setVisible(false); hlChangePassword.setVisible(false); hlViewPermission.setVisible(false);
            }else{
                btnUpdate.setVisible(true); btnDelete.setVisible(true); hlChangePassword.setVisible(true); hlViewPermission.setVisible(true);
            }

        }
    }

    public void showDetails() {
        tbllUserList.setItems(users.Ulist);
        clmUserId.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        clmUserName.setCellValueFactory(new PropertyValueFactory<>("Uname"));
        usersGetway.view(users);


    }

    public void checqPermission() {
        try {
            pst = con.prepareStatement("select * from UserPermission where UserId=?");
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(13) != 1) {
                    hlChangePassword.setDisable(true);
                } else {
                    hlChangePassword.setDisable(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearAll() {
        tfUserName.clear();
        tfFullName.clear();
        tfCreatedBy.clear();
        tfEmailAddress.clear();
        tfDateofJoin.clear();
       
    }
}
