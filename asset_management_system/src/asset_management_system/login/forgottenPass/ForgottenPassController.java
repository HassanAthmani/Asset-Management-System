
package asset_management_system.login.forgottenPass;

import asset_management_system.usedAlot.sendingPass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;



public class ForgottenPassController implements Initializable {

   @FXML
    private JFXTextField email;

    @FXML
    private JFXButton sendPass;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private ImageView backToDashboard;
    
     @FXML
    private ImageView closeApp;
     
     @FXML
     public void closeAppWindow(MouseEvent event){
         //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.close();
         
     }

    @FXML
    void sendNewPass(ActionEvent event) throws SQLException {
        if( email.getText().isEmpty() ){
            int response = JOptionPane.showConfirmDialog(
        null,"no field should be empty","Required Input",JOptionPane.DEFAULT_OPTION);
            
        }else
        {
            sendingPass nw=new sendingPass();
           nw.recoverAcc(email.getText());
        }       
       

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
        
    }    
    
}