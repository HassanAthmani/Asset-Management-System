
package asset_management_system.assets.all_assetsPop;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;


public class All_assetsPopController implements Initializable {

     @FXML
    private JFXTextField additionDate;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField assetDetails;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField workerID;

    @FXML
    private JFXTextField categoryID;

    @FXML
    private JFXTextField assetID;

    @FXML
    private JFXTextField cost;

    @FXML
    private ImageView qr_code;

    @FXML
    private Button printData;

    @FXML
    void printStuff(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        json_read nw= new json_read();
         try {
             nw.setToAllItems(assetID, assetName, assetCode, assetDetails, workerName, workerID, categoryID, additionDate, cost);
             
             assetID.setEditable(false);
             assetName.setEditable(false);
             assetCode.setEditable(false);
             assetDetails.setEditable(false);
             workerName.setEditable(false);
             workerID.setEditable(false);
             categoryID.setEditable(false);
             additionDate.setEditable(false);
             cost.setEditable(false);
             
             QR_Creator maker=new QR_Creator();
             maker.QRGen(assetID.getText(), assetCode.getText(), assetName.getText());
             
             String path="Asset"+assetID.getText()+".png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
             qr_code.setImage(imageObject);
             
             
             
             // TODO
         } catch (IOException ex) {
             Logger.getLogger(All_assetsPopController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParseException ex) {
             Logger.getLogger(All_assetsPopController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(All_assetsPopController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (WriterException ex) {
             Logger.getLogger(All_assetsPopController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
}
