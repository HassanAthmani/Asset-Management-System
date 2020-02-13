/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.profile;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ProfileController implements Initializable {

    
    @FXML
    private JFXTextField phoneNo_txtfield;

    @FXML
    private JFXTextField email_txtfield;

    @FXML
    private JFXTextField nationaID_txtfield;

    @FXML
    private JFXTextField workerID_txtfield;

    @FXML
    private JFXPasswordField current_pass;

    @FXML
    private JFXPasswordField new_pass;

    @FXML
    private JFXPasswordField confirm_pass;

    @FXML
    private TableView<?> tble_view;

    @FXML
    private TableColumn<?, ?> id_column;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TableColumn<?, ?> phoneNo_column;

    @FXML
    private TableColumn<?, ?> email_column;

    @FXML
    private TableColumn<?, ?> assetID_column;

    @FXML
    private TableColumn<?, ?> assetName_column;

    @FXML
    private TableColumn<?, ?> assetCode_column;

    @FXML
    private TableColumn<?, ?> assignedDate_column;

    @FXML
    private ImageView imageviewer;

    @FXML
    private Label firstName_lbl;

    @FXML
    private Label lastName_lbl;

    @FXML
    private ImageView closeApp;

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
