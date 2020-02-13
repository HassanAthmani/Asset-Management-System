
package asset_management_system.login.register;

import asset_management_system.usedAlot.sendingPass;
import asset_management_system.usedAlot.escapeChar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private ImageView backToDashboard;
     
      @FXML
    private ImageView closeApp;

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();


    }


    @FXML
    void register(ActionEvent event) throws SQLException, ClassNotFoundException {
        sendingPass nw=new sendingPass();      
        nw.NewAcc(email.getText());

    }
    
    @FXML
    public void imageClicked(MouseEvent event) throws IOException{
        //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/login/login.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
        
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
