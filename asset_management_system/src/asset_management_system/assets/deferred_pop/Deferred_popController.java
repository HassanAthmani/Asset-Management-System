/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.assets.deferred_pop;

import asset_management_system.usedAlot.QR_Creator;
import asset_management_system.usedAlot.json_read;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Deferred_popController implements Initializable {
    
    @FXML
    private JFXTextField assetID;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetDetails;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField additionDate;

    @FXML
    private JFXTextField deferredDate;

    @FXML
    private JFXTextField reason;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField workerID;

    @FXML
    private ImageView closeApp;
    
    @FXML
    private ImageView qr_code;

 @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

    }
    ///////////// image iwekwe kwa ile function itafill the fields
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        json_read nw=new json_read();
        
        try {
            nw.setToDeferred(assetID, assetName, assetCode, assetDetails, workerName, workerID, category, additionDate, deferredDate, reason);
            
             assetID.setEditable(false);
             assetName.setEditable(false);
             assetCode.setEditable(false);
             assetDetails.setEditable(false);
             workerName.setEditable(false);
             workerID.setEditable(false);
             reason.setEditable(false);
             additionDate.setEditable(false);
             deferredDate.setEditable(false);
             reason.setEditable(false);
             
              QR_Creator maker=new QR_Creator();
             maker.QRGen(assetID.getText(), assetCode.getText(), assetName.getText());
             
             String path="Asset"+assetID.getText()+".png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
             qr_code.setImage(imageObject);
           
            
        } catch (IOException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
