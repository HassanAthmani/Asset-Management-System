/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.withUsers;

import asset_management_system.assets.all_assets;
import asset_management_system.assets.asset_in_maintenance;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class WithUsersController implements Initializable {
     public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
     String userName="root";
     String password="";
     
    public ObservableList<withUsers> data;

   @FXML
    private VBox vbox;
   
     @FXML
    private TableView<withUsers> withUsers_tbl;

    @FXML
    private TableColumn<withUsers, String> id;

    @FXML
    private TableColumn<withUsers, String> worker_id;

    @FXML
    private TableColumn<withUsers, String> worker_name;

    @FXML
    private TableColumn<withUsers, String> worker_phoneNo;

    @FXML
    private TableColumn<withUsers, String> worker_email;

    @FXML
    private TableColumn<withUsers, String> asset_id;

    @FXML
    private TableColumn<withUsers, String> asset_name;

    @FXML
    private TableColumn<withUsers, String> asset_code;

    @FXML
    private TableColumn<withUsers, String> assigned_date;
    
     @FXML
    private TableColumn columnEdit;
     
     @FXML
    private TextField search;

    @FXML
    private ChoiceBox choice;
     
      @FXML
    void loadFromDB(MouseEvent event) throws SQLException {
         //DB connection details
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";
           

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM assigned_asset");

            while (rs.next()) {
                //get string from db
                data.add(new withUsers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
         id.setCellValueFactory(new PropertyValueFactory<>("transID"));
          worker_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
          worker_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
          worker_phoneNo.setCellValueFactory(new PropertyValueFactory<>("workerTell"));
          worker_email.setCellValueFactory(new PropertyValueFactory<>("workerEmail"));  
        asset_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));                
        assigned_date.setCellValueFactory(new PropertyValueFactory<>("assignedDate"));
       
        

        withUsers_tbl.setItems(null);
        withUsers_tbl.setItems(data);
        
        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<withUsers,String>,TableCell<withUsers,String>> cellFactory=(param)->{
         
            //make the tablecell containing button
            final TableCell<withUsers,String> cell=new TableCell<withUsers,String>(){
                
                //override updateItem method
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    
                    //ensure that cell is created only on non-empty rows
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        //Now we create the action button
                        final Button editButton=new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event ->{
                            
                                //Extract the clicked object
                                withUsers p=getTableView().getItems().get(getIndex());
                                
                                //DATA 
                                //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                               
                               
                               
                               
                               //PREVENT USER FROM EDITING TEXTFIELDS                               
                                                                  
                                    
             
                    });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }
                
                };
                    
            };
            
            return cell;
        };  
        //set the custom factory to action column
        columnEdit.setCellFactory(cellFactory);        

    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
