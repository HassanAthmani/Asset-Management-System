/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.withUsers;

import asset_management_system.assets.AssetsController;
import asset_management_system.usedAlot.assetSearch;
import asset_management_system.usedAlot.json_code;
import asset_management_system.workers.WorkersController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private TableColumn<withUsers, String> assignedBy;
    
     @FXML
    private TableColumn columnEdit;
     
     @FXML
    private TextField search;

    @FXML
    private ChoiceBox choice;
    
    private double xOffset = 0;
    private double yOffset = 0;
     
      @FXML
    void loadFromDB(MouseEvent event) throws SQLException {
         //DB connection details
         /*
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";
           

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM assigned_asset");

            while (rs.next()) {
                //get string from db
                data.add(new withUsers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
        assignedBy.setCellValueFactory(new PropertyValueFactory<>("assignedBy"));
       
        

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
                                String id = String.valueOf(p.getTransID());
                                String workerid = String.valueOf(p.getWorkerID());
                                String worker = String.valueOf(p.getWorkerName());
                                String workerTell= String.valueOf(p.getWorkerTell());
                                String workeremail = String.valueOf(p.getWorkerEmail());
                                String assetid = String.valueOf(p.getAssetID());
                                String assetname = String.valueOf(p.getAssetName());
                                String code = String.valueOf(p.getAssetCode());
                                String assigned_date = String.valueOf(p.getAssignedDate());
                                String assignedby=String.valueOf(p.getAssignedBy());
                           

                            json_code nw = new json_code();
                            nw.WithUser_json(id, workerid, worker, workerTell, workeremail, assetid, assetname, code, assigned_date,assignedby);

                            ///// POP UP ////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/withUsers/withUsers_pop/withUsers_pop.fxml").openStream());
                                Scene scene = new Scene(root);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ///// POP UP ///////
                               
                               
                               
                               
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
*/
    }
    
    
    public void loada()throws SQLException{
        
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
                data.add(new withUsers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
        assignedBy.setCellValueFactory(new PropertyValueFactory<>("assignedBy"));
       
        

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
                                String id = String.valueOf(p.getTransID());
                                String workerid = String.valueOf(p.getWorkerID());
                                String worker = String.valueOf(p.getWorkerName());
                                String workerTell= String.valueOf(p.getWorkerTell());
                                String workeremail = String.valueOf(p.getWorkerEmail());
                                String assetid = String.valueOf(p.getAssetID());
                                String assetname = String.valueOf(p.getAssetName());
                                String code = String.valueOf(p.getAssetCode());
                                String assigned_date = String.valueOf(p.getAssignedDate());
                                String assignedby=String.valueOf(p.getAssignedBy());
                           

                            json_code nw = new json_code();
                            nw.WithUser_json(id, workerid, worker, workerTell, workeremail, assetid, assetname, code, assigned_date,assignedby);

                            ///// POP UP ////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/withUsers/withUsers_pop/withUsers_pop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ///// POP UP ///////
                               
                               
                               
                               
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
    
    public void search(String searchVal, String table, String colmnVal)throws SQLException{
        
         //DB connection details
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";
           

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`" + table + "` WHERE (`" + colmnVal + "` LIKE '%" + searchVal + "%')");

            while (rs.next()) {
                //get string from db
                data.add(new withUsers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
        assignedBy.setCellValueFactory(new PropertyValueFactory<>("assignedBy"));
       
        

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
                                String id = String.valueOf(p.getTransID());
                                String workerid = String.valueOf(p.getWorkerID());
                                String worker = String.valueOf(p.getWorkerName());
                                String workerTell= String.valueOf(p.getWorkerTell());
                                String workeremail = String.valueOf(p.getWorkerEmail());
                                String assetid = String.valueOf(p.getAssetID());
                                String assetname = String.valueOf(p.getAssetName());
                                String code = String.valueOf(p.getAssetCode());
                                String assigned_date = String.valueOf(p.getAssignedDate());
                                String assignedby=String.valueOf(p.getAssignedBy());
                           

                            json_code nw = new json_code();
                            nw.WithUser_json(id, workerid, worker, workerTell, workeremail, assetid, assetname, code, assigned_date,assignedby);

                            ///// POP UP ////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/withUsers/withUsers_pop/withUsers_pop.fxml").openStream());
                                Scene scene = new Scene(root);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ///// POP UP ///////
                               
                               
                               
                               
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
        choice.getItems().addAll(" ","ID", "ASSET ID", "ASSET NAME", "ASSET CODE", "PHONE NO", "WORKER NAME", "WORKER ID", "EMAIL", "ASSIGNED DATE", "ASSIGNED BY");
        choice.setValue("ASSET ID");
        
        if (search.getText().isEmpty()) {

            try {
                loada();
            } catch (SQLException ex) {
                Logger.getLogger(WithUsersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //choice.getItems().addAll("ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");

            String tbl = "assigned_asset";
            if (oldValue != newValue) {
                try {

                    if (!search.getText().isEmpty()) {

                        String BoxValue = choice.getValue().toString();

                        if ("ASSET ID".equals(BoxValue)) {
                            String clmn = "assetID";
                            search(search.getText(), tbl, clmn);

                        } else if ("ASSET NAME".equals(BoxValue)) {
                            String clmn = "assetName";
                            search(search.getText(), tbl, clmn);
                        } 
                        else if ("ID".equals(BoxValue)) {
                            String clmn = "ID";
                            search(search.getText(), tbl, clmn);
                            
                        }else if ("ASSET CODE".equals(BoxValue)) {
                            String clmn = "assetCode";
                            search(search.getText(), tbl, clmn);
                        } else if ("PHONE NO".equals(BoxValue)) {
                            String clmn = "workerTell";
                            search(search.getText(), tbl, clmn);
                        } else if ("WORKER NAME".equals(BoxValue)) {
                            String clmn = "workerName";
                            search(search.getText(), tbl, clmn);
                        } else if ("WORKER ID".equals(BoxValue)) {
                            String clmn = "workerID";
                            search(search.getText(), tbl, clmn);
                        } else if ("EMAIL".equals(BoxValue)) {
                            String clmn = "workerEmail";
                            search(search.getText(), tbl, clmn);
                        } else if ("ASSIGNED DATE".equals(BoxValue)) {
                            String clmn = "assigned_date";
                            search(search.getText(), tbl, clmn);
                        } else if ("ASSIGNED BY".equals(BoxValue)) {
                            String clmn = "assignedBy";
                            search(search.getText(), tbl, clmn);
                        } else if (" ".equals(BoxValue)) {

                            try {
                                loada();
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        if (search.getText().isEmpty()) {
                            try {
                                 loada();
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(assetSearch.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        ////////////
    }    
    
}
