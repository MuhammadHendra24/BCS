
package controller.application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.application.incoming.Report;
import controller.application.incoming.ViewIncomingController;
import controller.application.incoming.ViewOutgoingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class IncomingController implements Initializable {

    @FXML
    private ToggleButton tbtnIncoming;
    @FXML
    private ToggleButton tbtnOutgoing;
    @FXML
    private ToggleButton tbtnReport;
   
    @FXML
    private Label lblPathInfo;
    @FXML
    private StackPane spMainContent;

    userNameMedia nameMedia;

    String userId;
    @FXML
    public AnchorPane acMainSells;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
       
       // tbtnIncoming.setToggleGroup(group);
    }

    @FXML
    public void tbtnIncomingOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Incoming");
        ViewIncomingController incomingController = new ViewIncomingController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/incoming/ViewIncoming.fxml").openStream());
        media.setId(userId);
        ViewIncomingController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.showDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }
    @FXML
    public void tbtnOutgoingOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Outgoing");
        ViewOutgoingController outgoingController = new ViewOutgoingController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/incoming/ViewOutgoing.fxml").openStream());
        media.setId(userId);
        ViewOutgoingController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.showDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }
    public void btnReportOnAction(ActionEvent actionEvent) {
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
            media.setId(userId);
            clikreport.setNameMedia(media);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.UNDECORATED);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
   


