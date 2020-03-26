
package asset_management_system.withUsers.withUsers_pop;

import asset_management_system.usedAlot.json_read;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;


public class WithUsers_popController implements Initializable {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField workerID;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField workerTell;

    @FXML
    private JFXTextField workerEmail;

    @FXML
    private JFXTextField assetID;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField assigned_date;
    
    @FXML
    private JFXTextField assignedBy;

    @FXML
    private ImageView qr_code;

    @FXML
    private Button maintenance;

    @FXML
    private Button deferBtn;

    @FXML
    private Button assetsBtn;

    @FXML
    private JFXTextField reason;

    @FXML
    private CheckBox deferCheck;
    

    @FXML
    void ToAssets(ActionEvent event) {

    }

    @FXML
    void ToMaintenance(ActionEvent event) {

    }

    @FXML
    void check(ActionEvent event) {
        
          if(deferCheck.isSelected()){
            deferBtn.setVisible(true);
            reason.setVisible(true); 
            maintenance.setVisible(false);
        }
        else
        {
             deferBtn.setVisible(false);
             reason.setVisible(false);
              maintenance.setVisible(true);
        }

    }

    @FXML
    void defer(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        json_read nw=new json_read();
        try {
            nw.setToWithUser(id, workerID, workerName, workerTell, workerEmail, assetID, assetName, assetCode, assigned_date,assignedBy);
            
        } catch (IOException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
