/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import asset_management_system.login.register.RegisterController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class checkDetails {
     public static String userName = "root";
    public static String pass = "";

    public Connection connection;
    String invalid="-jfx-focus-color: #fc1212;";
    String valid="-jfx-focus-color: #4059a9;";
    
    //FOR PHONE NUMBERS AND ID
    public  void checker(JFXTextField example,String column,JFXButton button){
        
         example.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!example.getText().isEmpty()){
                   special_char specialChar=new special_char();
                   if(specialChar.hasSpecialAndLetters(example.getText())==true){
                       int response = JOptionPane.showConfirmDialog(
                    null, "No special characters and letters are to be used in this field", "error", JOptionPane.DEFAULT_OPTION);
                       example.setStyle(invalid);
                        button.setDisable(true);  
                       
                   }
                   else{
                       try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, pass);
            //Execute query and store result in a resultset
            String srch=example.getText();
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM `asset_management_system`.`current_workers` WHERE "+column+" = '"+srch+"'");
            int quan;
            int a=1;
               while(rs.next()){

              
             String str = rs.getString(1);
             quan = Integer.parseInt(str);
             System.out.println(quan);
             if(quan>=a){
                /* int response = JOptionPane.showConfirmDialog(
                  null,"data is in DB","Required Input",JOptionPane.DEFAULT_OPTION); */
                example.setStyle(invalid);
                button.setDisable(true);                        
                                
               }
             else{
                 example.setStyle(valid);
                 button.setDisable(false);
             }
             
              }
               
               
           } catch(ClassNotFoundException e){
               
           }     
               catch (SQLException ex) {     
                       Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                   }     
                   }
                   
        
               }
           }
         
               if ( example.getText().isEmpty() ){
                   //DB connection details
                  
        
               }
           
           
           
                     });
        
    }
    
    
    //FOR   NAME LOCATION AND DEPT  
    public void checker2(JFXTextField example,JFXButton button){
        example.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!example.getText().isEmpty()){
                   special_char specialChar=new special_char();
                   if(specialChar.hasSpecialAndDigits(example.getText())==true){
                        int response = JOptionPane.showConfirmDialog(
                    null, "No special characters and numbers are to be used in this field", "error", JOptionPane.DEFAULT_OPTION);
                       example.setStyle(invalid);
                        button.setDisable(true); 
                       
                   }
                   else{
                       example.setStyle(valid);
                        button.setDisable(false); 
                       
                   }
       
               
           }
         
               if ( example.getText().isEmpty() ){
                   //DB connection details
                   
        
               }
           }
           
           
       });
    }
    
    
    //FOR PROFILE NUMBERS AND 1D
    public  void profile_checker(JFXTextField example,String column,ImageView image){
        
         example.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!example.getText().isEmpty()){
                   special_char specialChar=new special_char();
                   if(specialChar.hasSpecialAndLetters(example.getText())==true){
                       int response = JOptionPane.showConfirmDialog(
                    null, "No special characters and letters are to be used in this field", "error", JOptionPane.DEFAULT_OPTION);
                       example.setStyle(invalid);
                       image.setVisible(false);  
                       
                   }
                   else{
                       try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, pass);
            //Execute query and store result in a resultset
            String srch=example.getText();
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM `asset_management_system`.`current_workers` WHERE "+column+" = '"+srch+"'");
            int quan;
            int a=1;
               while(rs.next()){

              
             String str = rs.getString(1);
             quan = Integer.parseInt(str);
             System.out.println(quan);
             if(quan>=a){
                /* int response = JOptionPane.showConfirmDialog(
                  null,"data is in DB","Required Input",JOptionPane.DEFAULT_OPTION); */
                example.setStyle(invalid);
               
                image.setVisible(false);
                                
               }
             else{
                 example.setStyle(valid);
               image.setVisible(true);
             }
             
              }
               
               
           } catch(ClassNotFoundException e){
               
           }     
               catch (SQLException ex) {     
                       Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                   }     
                   }
                   
        
               }
           }
         
               if ( example.getText().isEmpty() ){
                   //DB connection details
                  
        
               }
           
           
           
                     });
        
    }
    
    
    //FOR PROFILE NAME AND DEPT
    public void profile_checker2(JFXTextField example,ImageView image){
        example.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!example.getText().isEmpty()){
                   special_char specialChar=new special_char();
                   if(specialChar.hasSpecialAndDigits(example.getText())==true){
                        int response = JOptionPane.showConfirmDialog(
                    null, "No special characters and numbers are to be used in this field", "error", JOptionPane.DEFAULT_OPTION);
                       example.setStyle(invalid);
                        
                        image.setVisible(false);  
                       
                   }
                   else{
                       example.setStyle(valid);
                        
                         image.setVisible(true);  
                       
                   }
       
               
           }
         
               if ( example.getText().isEmpty() ){
                   //DB connection details
                   
        
               }
           }
           
           
       });
    }
    
    
    
     //FOR ASSET CODE
    public  void AssetCodechecker(JFXTextField example,String column,Button button){
        
         example.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!example.getText().isEmpty()){
                   special_char specialChar=new special_char();
                   if(specialChar.hasSpecial(example.getText())==true){
                       int response = JOptionPane.showConfirmDialog(
                    null, "No special characters are to be used in this field", "error", JOptionPane.DEFAULT_OPTION);
                       example.setStyle(invalid);
                        button.setDisable(true);  
                       
                   }
                   else{
                       try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, pass);
            //Execute query and store result in a resultset
            String srch=example.getText();
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM `asset_management_system`.`available_assets` WHERE "+column+" = '"+srch+"'");
            int quan;
            int a=1;
               while(rs.next()){

              
             String str = rs.getString(1);
             quan = Integer.parseInt(str);
             System.out.println(quan);
             if(quan>=a){
                /* int response = JOptionPane.showConfirmDialog(
                  null,"data is in DB","Required Input",JOptionPane.DEFAULT_OPTION); */
                example.setStyle(invalid);
                button.setDisable(true);                        
                                
               }
             else{
                 example.setStyle(valid);
                 button.setDisable(false);
             }
             
              }
               
               
           } catch(ClassNotFoundException e){
               
           }     
               catch (SQLException ex) {     
                       Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                   }     
                   }
                   
        
               }
           }
         
               if ( example.getText().isEmpty() ){
                   //DB connection details
                  
        
               }
           
           
           
                     });
        
    }
    
    //FOR ASSET COST
    public void assetCost(JFXTextField example,Button button){
        example.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue){
               //DB connection details
               if(!example.getText().isEmpty()){
                   special_char specialChar=new special_char();
                   if(specialChar.hasSpecialAndLetters(example.getText())==true){
                        int response = JOptionPane.showConfirmDialog(
                    null, "No special characters and LETTERS are to be used in this field", "error", JOptionPane.DEFAULT_OPTION);
                       example.setStyle(invalid);
                        
                         button.setDisable(true);    
                       
                   }
                   else{
                       example.setStyle(valid);
                        
                          button.setDisable(false);   
                       
                   }
       
               
           }
         
               if ( example.getText().isEmpty() ){
                   //DB connection details
                   example.setStyle(valid);
        
               }
           }
           
           
       });
    }
    
    
    
    
}
