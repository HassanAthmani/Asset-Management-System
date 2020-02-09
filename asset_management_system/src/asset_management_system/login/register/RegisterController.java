
package asset_management_system.login.register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class RegisterController implements Initializable {

   
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPass;

    @FXML
    private JFXButton register_btn;

    @FXML
    private JFXTextField fristName;

    @FXML
    private JFXTextField secondName;

    @FXML
    private JFXTextField phoneNo;

    @FXML
    private JFXTextField natID;

    @FXML
    private JFXTextField email;

    @FXML
    private VBox vbox;

    @FXML
    void register(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox.setStyle("-fx-background-color: #28d474;\n"
                + "-fx-padding: 10;\n"
                + "-fx-spacing:8;\n"
                );
        // TODO
    }    
    
}
