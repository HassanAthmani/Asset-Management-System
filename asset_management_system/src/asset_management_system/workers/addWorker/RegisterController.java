
package asset_management_system.workers.addWorker;


import asset_management_system.usedAlot.sendingPass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RegisterController implements Initializable {
     public static String userName = "root";
    public static String pass = "";

    public Connection connection;

   
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
    private ImageView backToWorkers;
    
    @FXML
    private ImageView closeApp;

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();            
            window.close();

    }

    @FXML
    void imageClicked(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/workers/workers.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();

    }

    @FXML
    void register(ActionEvent event) throws SQLException, ClassNotFoundException {
         sendingPass nw=new sendingPass();      
        nw.NewAcc(email.getText());

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox.setStyle("-fx-background-color: #28d474;\n"
                + "-fx-padding: 10;\n"
                + "-fx-spacing:8;\n"
                );
       phoneNo.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!phoneNo.getText().isEmpty()){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, pass);
            //Execute query and store result in a resultset
            String srch=phoneNo.getText();
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM `asset_management_system`.`worker_details` WHERE workerTell = '"+srch+"'");
            int quan;
            int a=1;
               while(rs.next())
              {

             String str = rs.getString(1);
             quan = Integer.parseInt(str);
             System.out.println(quan);
             if(quan>=a){
                 int response = JOptionPane.showConfirmDialog(
        null,"data is in DB","Required Input",JOptionPane.DEFAULT_OPTION); 
                   
               }
              }
               
               
           } catch(ClassNotFoundException e){
               
           }     
               catch (SQLException ex) {     
                       Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                   }     
               }
           }
         
               if ( phoneNo.getText().isEmpty() ){
                   //DB connection details
                  
        
               }
           
           
           
                     });
                  

    

               }
               }