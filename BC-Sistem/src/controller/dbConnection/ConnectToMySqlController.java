
package controller.dbConnection;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnectToMySqlController implements Initializable {
    @FXML
    private TextField tfHostName;
    @FXML
    private TextField tfPortName;
    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    }
    
}
