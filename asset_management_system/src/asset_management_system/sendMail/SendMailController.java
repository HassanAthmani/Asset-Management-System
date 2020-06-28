/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.sendMail;

import asset_management_system.usedAlot.emailValidation;
import asset_management_system.usedAlot.notification;
import asset_management_system.usedAlot.sendingPass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SendMailController implements Initializable {

    @FXML
    private JFXTextField recipient;

    @FXML
    private JFXTextArea message;

    @FXML
    private JFXTextField fileName;

    @FXML
    private JFXButton send;

    @FXML
    private ImageView attach;

    @FXML
    private ImageView close;
    
    @FXML
    private JFXTextField subject;
    
    notification nw=new notification();
    
    
    
    @FXML    
   void close(MouseEvent event){
       
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
       
   }
   
    @FXML           
   void attachFile(MouseEvent event){
       FileChooser fileChooser = new FileChooser();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        //Set extension filter
       
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        fileChooser.getExtensionFilters().addAll( extFilterPDF);
        fileChooser.setTitle("PICK FILE ");
        fileChooser.setInitialDirectory(new File("C:\\Bit_torrent"));
        

        //Show open file dialog
        File file = fileChooser.showOpenDialog(window);
        fileName.setText(file.getAbsolutePath());

              
   }


    @FXML
    void sendFile(ActionEvent event) throws IOException {

        if (!recipient.getText().isEmpty() && !message.getText().isEmpty() && !fileName.getText().isEmpty() && !subject.getText().isEmpty()) {
            sendingPass sender=new sendingPass();
            
            File f=new File(fileName.getText());
            
            sender.sendAttachment(recipient.getText(), subject.getText(), message.getText(), f,send);
            subject.clear();
           

        }else {
            
            nw.flash(send,"NO FIELD SHOULD BE EMPTY");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailValidation nw = new emailValidation();
        nw.loginVal(recipient, "Hello", send);
        fileName.setEditable(false);
        // TODO
    }

}
