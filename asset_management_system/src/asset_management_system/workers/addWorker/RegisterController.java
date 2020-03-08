
package asset_management_system.workers.addWorker;


import asset_management_system.usedAlot.DBOps;
import asset_management_system.usedAlot.sendingPass;
import asset_management_system.usedAlot.checkDetails;
import asset_management_system.usedAlot.emailValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class RegisterController implements Initializable {   

   
  
    @FXML
    private JFXButton register_btn;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField secondName;

    @FXML
    private JFXTextField phoneNo;

    @FXML
    private JFXTextField natID;

    @FXML
    private JFXTextField email;
    
    @FXML
    private JFXTextField department;

    @FXML
    private JFXTextField location;

    @FXML
    private VBox vbox;
    
    /*@FXML
    private ImageView backToWorkers;*/
    
    @FXML
    private ImageView closeApp;

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();            
            window.close();
    }

   
    @FXML
    void register(ActionEvent event) throws SQLException, ClassNotFoundException, InterruptedException {
         sendingPass nwAcc=new sendingPass();      
        
        
         if(email.getText().isEmpty() || firstName.getText().isEmpty() || secondName.getText().isEmpty() || phoneNo.getText().isEmpty() || natID.getText().isEmpty() || department.getText().isEmpty() || location.getText().isEmpty()){
              
             int response = JOptionPane.showConfirmDialog(
        null,"Make sure the text fields should not be empty","Required Input",JOptionPane.DEFAULT_OPTION); 
               
                  
         
         } else 
         {
             DBOps ops=new DBOps();
          String first= toUpperCase(firstName.getText());
          String second= toUpperCase(secondName.getText());
          String phone= phoneNo.getText();
          String nat= natID.getText();
          String mail=email.getText();
          String dep=toUpperCase( department.getText());
          String loc=toUpperCase( location.getText());          
          ops.addData(first, second, phone, nat, mail, dep, loc);
          TimeUnit.SECONDS.sleep(1);  
          nwAcc.NewAcc(email.getText());
          
          email.clear();
          firstName.clear();
          secondName.clear();
          phoneNo.clear();
          natID.clear();
          department.clear();
          location.clear();
          
          
         }        
         
           
       } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox.setStyle("-fx-background-color: #28d474;\n"
                + "-fx-padding: 10;\n"
                + "-fx-spacing:8;\n"
                );
        checkDetails nw=new checkDetails();
        emailValidation valid=new emailValidation();
        valid.emailVal(email,"workerEmail",register_btn);
        nw.checker(phoneNo,"workerTell",register_btn);
        nw.checker(natID,"workerNationalID",register_btn);
        
        nw.checker2(firstName,register_btn);
        nw.checker2(secondName,register_btn);         
         nw.checker2(department,register_btn);
         nw.checker2(location,register_btn);
         
               }
               }