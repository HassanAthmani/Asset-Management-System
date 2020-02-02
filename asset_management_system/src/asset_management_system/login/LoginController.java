/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {
     public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
 


    @FXML
    private VBox vbox;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton loginbtn;

    @FXML
    private JFXButton forgotPassBtn;

    @FXML
    private JFXPasswordField password;

    @FXML
    void forgotPass(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        String usrname = username.getText();
        String pass = password.getText();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "inventory_project";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName1, password); 
           
            
            try{
            String sql = "SELECT * FROM login WHERE username = ? and password = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usrname );
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                
                int response = JOptionPane.showConfirmDialog(
                    null, "Please enter correct username and Password", "Login Failed!", JOptionPane.DEFAULT_OPTION);
            }else{
                
                //infoBox("Login Successful",null,"Success" );
                int response = JOptionPane.showConfirmDialog(
                    null, "Login Successful", "Success", JOptionPane.DEFAULT_OPTION);
                
             //setting scene variable
                
            Parent sceneFxml = FXMLLoader.load(getClass().getResource("/inventory_system/dashboard.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
            
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

            

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
        

    }



    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
