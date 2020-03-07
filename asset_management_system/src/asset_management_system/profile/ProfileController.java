/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.profile;

import asset_management_system.dashboard.dashboard;
import asset_management_system.usedAlot.json_read;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;


public class ProfileController implements Initializable {
    
    public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private ObservableList<profile> data;

    
    @FXML
    private JFXTextField phoneNo_txtfield;

    @FXML
    private JFXTextField email_txtfield;

    @FXML
    private JFXTextField nationalID_txtfield;

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
    private JFXTextField secondName_txtfield;
     
     @FXML
    private JFXTextField firstName_txtfield;


    
    
    @FXML
    private ImageView backToDashboard;
    
     @FXML
    private JFXTextField department_txtfield;

    @FXML
    private JFXTextField location_txtfield;
    
    
    @FXML
    private ImageView editInfo;

    @FXML
    private ImageView saveInfo;
    
     @FXML
    private Button changePass;
     
     @FXML
    private Label name2_lbl;
     
     @FXML
    private Label name1_lbl;


     
      @FXML
    void change_password(ActionEvent event) {
        if((new_pass.getText().isEmpty() || confirm_pass.getText().isEmpty()) || (!Objects.equals(new_pass.getText(), confirm_pass.getText()))){
             int response = JOptionPane.showConfirmDialog(
                    null, "input required", "error", JOptionPane.DEFAULT_OPTION);
        }else{
            if(Objects.equals(new_pass.getText(), confirm_pass.getText())){
               int response = JOptionPane.showConfirmDialog(
                    null, "input is okay", "error", JOptionPane.DEFAULT_OPTION);;
            }
        }
                          
       

    }

    
    @FXML
    void saveStuff(MouseEvent event){
        if(firstName_txtfield.getText().isEmpty() || secondName_txtfield.getText().isEmpty() || phoneNo_txtfield.getText().isEmpty() || email_txtfield.getText().isEmpty() || nationalID_txtfield.getText().isEmpty() || department_txtfield.getText().isEmpty() || location_txtfield.getText().isEmpty()){
            int response = JOptionPane.showConfirmDialog(
                    null, "input required,no field should be empty", "error", JOptionPane.DEFAULT_OPTION);
        }
                
                
                
                
                
                
                
                
        editInfo.setVisible(true);
        saveInfo.setVisible(false);
        //current_pass.setVisible(false);
        new_pass.setVisible(false);
        confirm_pass.setVisible(false);
        secondName_txtfield.setVisible(false);
        firstName_txtfield.setVisible(false);
        name1_lbl.setVisible(false);
        name2_lbl.setVisible(false);
        changePass.setVisible(false);
        
        
    }

    
      @FXML
    void imageClicked(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();

    }

   
    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

    }
    
    @FXML
    void editStuff(MouseEvent event){
        editInfo.setVisible(false);
        saveInfo.setVisible(true);
        //current_pass.setVisible(true);
        new_pass.setVisible(true);
        confirm_pass.setVisible(true);
        secondName_txtfield.setVisible(true);
        firstName_txtfield.setVisible(true);
        name1_lbl.setVisible(true);
        name2_lbl.setVisible(true);
        changePass.setVisible(true);
        
    }
    
    
    public void fill_data() throws SQLException{
        json_read jsonReader=new json_read();
        try {
            String id=jsonReader.profile_id();
            String dbName = "asset_management_system";
            String userName="root";
            String password="";
            Class.forName("com.mysql.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= "+id);
            
             while (rs.next()) {    
                 
                 //PUT DATA INTO THE TEXTFIELD
                 
               // workerID_txtfield.setText(rs.getString(1));
                firstName_lbl.setText(rs.getString(2));
                lastName_lbl.setText(rs.getString(3));
                phoneNo_txtfield.setText(rs.getString(4));
                email_txtfield.setText(rs.getString(5));
                nationalID_txtfield.setText(rs.getString(6));
                department_txtfield.setText(rs.getString(7));
                location_txtfield.setText(rs.getString(8));
                
                //DISABLE THE TEXTFIELDS
                //workerID_txtfield.setEditable(false);
                //firstName_lbl.set
                //lastName_lbl
                phoneNo_txtfield.setEditable(false);
                email_txtfield.setEditable(false);
                nationalID_txtfield.setEditable(false);
                department_txtfield.setEditable(false);
                location_txtfield.setEditable(false);
                
            }
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fill_data();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        saveInfo.setVisible(false);
        current_pass.setVisible(false);
        new_pass.setVisible(false);
        confirm_pass.setVisible(false);
        secondName_txtfield.setVisible(false);
        firstName_txtfield.setVisible(false);
        name1_lbl.setVisible(false);
        name2_lbl.setVisible(false);
        changePass.setVisible(false);
                
        
    }    
    
}
